package controller;

import DBConnect.DatabaseConnection;
import model.BorrowModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class BorrowController {

    // Tạo bản ghi mượn sách và trả về borrow_id vừa tạo
    public int createBorrow(BorrowModel borrow) {
        int borrowId = -1;
        String sql = "INSERT INTO borrows (member_id, borrow_date, expected_return_date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, borrow.getMemberId());
            ps.setTimestamp(2, borrow.getBorrowDate());
            ps.setTimestamp(3, borrow.getExpectedReturnDate());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    borrowId = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return borrowId;
    }

    // ✅ Hàm overload: Cho phép tạo borrow trực tiếp bằng 3 tham số
    public int createBorrow(int memberId, Timestamp borrowDate, Timestamp expectedReturnDate) {
        BorrowModel borrow = new BorrowModel();
        borrow.setMemberId(memberId);
        borrow.setBorrowDate(borrowDate);
        borrow.setExpectedReturnDate(expectedReturnDate);
        return createBorrow(borrow);
    }

    // Lấy chi tiết mượn theo ID
    public BorrowModel getBorrowById(int borrowId) {
        String sql = "SELECT * FROM borrows WHERE borrow_id = ?";
        BorrowModel borrow = null;

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, borrowId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                borrow = new BorrowModel(
                        rs.getInt("borrow_id"),
                        rs.getInt("member_id"),
                        rs.getTimestamp("borrow_date"),
                        rs.getTimestamp("expected_return_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return borrow;
    }
}
