package Controller;

import DBConnect.DatabaseConnection;
import model.BookImportDetailModel;
import model.BookImportModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookImportController {

    public int createBookImport(BookImportModel importModel) throws SQLException {
        String sql = "INSERT INTO book_imports (import_date, member_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, new Timestamp(importModel.getImportDate().getTime()));
            ps.setInt(2, importModel.getMemberId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating import failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating import failed, no ID obtained.");
                }
            }
        }
    }

    public boolean addBookImportDetail(BookImportDetailModel detailModel) throws SQLException {
        String sql = "INSERT INTO book_import_details (import_id, book_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detailModel.getImportId());
            ps.setInt(2, detailModel.getBookId());
            ps.setInt(3, detailModel.getQuantity());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateBookStock(int bookId, int quantityToAdd) throws SQLException {
        String sql = "UPDATE books SET quantity = quantity + ? WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantityToAdd);
            ps.setInt(2, bookId);

            return ps.executeUpdate() > 0;
        }
    }

    public List<String[]> getBookImportHistory() {
        List<String[]> history = new ArrayList<>();
        String sql = "SELECT b.title, b.category, bd.quantity, bi.import_date "
                + "FROM book_import_details bd "
                + "JOIN books b ON bd.book_id = b.book_id "
                + "JOIN book_imports bi ON bd.import_id = bi.import_id "
                + "ORDER BY bi.import_date DESC";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] row = new String[5];
                row[0] = rs.getString("title");                         // Tên sách
                row[1] = rs.getString("category");                      // Thể loại
                row[2] = String.valueOf(rs.getInt("quantity"));         // Số lượng
                row[3] = "Admin";                                       // Người nhập
                row[4] = rs.getString("import_date");                   // Ngày nhập
                history.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }
}
