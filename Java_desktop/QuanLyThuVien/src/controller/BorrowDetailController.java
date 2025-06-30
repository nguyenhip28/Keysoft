package controller;

import DBConnect.DatabaseConnection;
import model.BorrowDetailModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDetailController {

    /**
     * Tạo bản ghi mượn sách mới và trừ số lượng sách trong kho.
     */
    public boolean createBorrowDetail(int borrowId, int bookId, int quantity) {
        String insertSql = "INSERT INTO borrow_details (borrow_id, book_id, quantity, status) VALUES (?, ?, ?, 'Đang mượn')";
        String updateBookSql = "UPDATE books SET quantity = quantity - ? WHERE book_id = ? AND quantity >= ?";

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement updatePs = conn.prepareStatement(updateBookSql)) {
                updatePs.setInt(1, quantity);
                updatePs.setInt(2, bookId);
                updatePs.setInt(3, quantity);

                int updated = updatePs.executeUpdate();
                if (updated == 0) {
                    conn.rollback();
                    System.out.println("Không đủ sách để mượn.");
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

    /**
     * Lấy danh sách chi tiết mượn sách của người dùng dựa trên user_code.
     */
    public List<BorrowDetailModel> getBorrowDetailsByUserCode(String userCode) {
        List<BorrowDetailModel> list = new ArrayList<>();

        String sql = """
            SELECT 
                bd.*, 
                b.title AS book_title,
                br.borrow_date, 
                br.expected_return_date
            FROM borrow_details bd
            JOIN borrows br ON bd.borrow_id = br.borrow_id
            JOIN books b ON bd.book_id = b.book_id
            JOIN members m ON br.member_id = m.member_id
            JOIN users u ON m.user_id = u.user_id
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
                detail.setActualReturnDate(rs.getTimestamp("actual_return_date"));
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

    /**
     * Trả sách dựa theo borrowId.
     */
    public boolean returnBook(int borrowId) {
        String sql = "UPDATE borrow_details SET actual_return_date = NOW(), status = 'Đã trả' WHERE borrow_id = ? AND status = 'Đang mượn'";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, borrowId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Trả sách theo tiêu đề và userCode.
     */
    public boolean returnBookByTitle(String userCode, String bookTitle) {
        String sql = """
            UPDATE borrow_details bd
            JOIN borrows br ON bd.borrow_id = br.borrow_id
            JOIN books bk ON bd.book_id = bk.book_id
            JOIN members m ON br.member_id = m.member_id
            JOIN users u ON m.user_id = u.user_id
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
}
