package controller;

import DBConnect.DatabaseConnection;
import model.BorrowDetailModel;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDetailController {

    public boolean createBorrowDetail(int borrowId, int bookId, int quantity) {
        String insertSql = "INSERT INTO borrow_detail (borrow_id, book_id, quantity, status) VALUES (?, ?, ?, 'Đang mượn')";
        String updateBookSql = "UPDATE book SET quantity = quantity - ? WHERE book_id = ? AND quantity >= ?";

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement updatePs = conn.prepareStatement(updateBookSql)) {
                updatePs.setInt(1, quantity);
                updatePs.setInt(2, bookId);
                updatePs.setInt(3, quantity);
                if (updatePs.executeUpdate() == 0) {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                insertPs.setInt(1, borrowId);
                insertPs.setInt(2, bookId);
                insertPs.setInt(3, quantity);
                insertPs.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BorrowDetailModel> getBorrowDetailsByUserCode(String userCode) {
        List<BorrowDetailModel> list = new ArrayList<>();
        String sql = """
            SELECT bd.*, b.title AS book_title, br.borrow_date, br.expected_return_date, bd.actual_return_date
            FROM borrow_detail bd
            JOIN borrow br ON bd.borrow_id = br.borrow_id
            JOIN book b ON bd.book_id = b.book_id
            JOIN member m ON br.member_id = m.member_id
            JOIN user u ON m.user_id = u.user_id
            WHERE u.user_code = ?
            ORDER BY br.borrow_date DESC
        """;

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BorrowDetailModel detail = new BorrowDetailModel();
                detail.setBorrowDetailId(rs.getInt("borrow_detail_id"));
                detail.setBorrowId(rs.getInt("borrow_id"));
                detail.setBookId(rs.getInt("book_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setBookTitle(rs.getString("book_title"));
                detail.setBorrowDate(rs.getTimestamp("borrow_date"));
                detail.setExpectedReturnDate(rs.getTimestamp("expected_return_date"));
                detail.setActualReturnDate(rs.getDate("actual_return_date"));
                detail.setStatus(rs.getString("status"));
                detail.setLateFee(rs.getDouble("late_fee"));
                detail.setTotalFee(rs.getDouble("total_fee"));
                list.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean returnBookByTitle(String userCode, String bookTitle) {
        String sql = """
            UPDATE borrow_detail bd
            JOIN borrow br ON bd.borrow_id = br.borrow_id
            JOIN book bk ON bd.book_id = bk.book_id
            JOIN member m ON br.member_id = m.member_id
            JOIN user u ON m.user_id = u.user_id
            SET bd.actual_return_date = NOW(), bd.status = 'Đã trả'
            WHERE u.user_code = ? AND bk.title = ? AND bd.status = 'Đang mượn'
        """;

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userCode);
            ps.setString(2, bookTitle);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void checkAndInsertPenalties() {
        String lateSql = "SELECT bd.borrow_detail_id, br.expected_return_date FROM borrow_detail bd JOIN borrow br ON bd.borrow_id = br.borrow_id WHERE bd.status = 'Đang mượn' AND NOW() > br.expected_return_date";
        String checkPenaltySql = "SELECT 1 FROM penalty WHERE borrow_detail_id = ?";
        String insertPenaltySql = "INSERT INTO penalty (borrow_detail_id, calculated_on, late_days, fee_applied, rule_id) VALUES (?, NOW(), ?, ?, ?)";
        String updateBorrowDetailSql = "UPDATE borrow_detail SET late_fee = ?, total_fee = ? WHERE borrow_detail_id = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(lateSql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int detailId = rs.getInt("borrow_detail_id");
                    Timestamp expected = rs.getTimestamp("expected_return_date");
                    int lateDays = (int) ((System.currentTimeMillis() - expected.getTime()) / (1000 * 60 * 60 * 24));
                    if (lateDays <= 0) {
                        continue;
                    }

                    try (PreparedStatement check = conn.prepareStatement(checkPenaltySql)) {
                        check.setInt(1, detailId);
                        if (check.executeQuery().next()) {
                            continue;
                        }
                    }

                    BigDecimal feePerDay = LateFeeRuleDAO.getFeePerDay(lateDays);
                    int ruleId = LateFeeRuleDAO.getRuleIdByLateDays(lateDays);
                    if (ruleId == -1 || feePerDay == null) {
                        continue;
                    }

                    BigDecimal totalFee = feePerDay.multiply(BigDecimal.valueOf(lateDays));

                    try (PreparedStatement insert = conn.prepareStatement(insertPenaltySql)) {
                        insert.setInt(1, detailId);
                        insert.setInt(2, lateDays);
                        insert.setBigDecimal(3, totalFee);
                        insert.setInt(4, ruleId);
                        insert.executeUpdate();
                    }

                    try (PreparedStatement update = conn.prepareStatement(updateBorrowDetailSql)) {
                        update.setBigDecimal(1, totalFee);
                        update.setBigDecimal(2, totalFee);
                        update.setInt(3, detailId);
                        update.executeUpdate();
                    }
                }
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
