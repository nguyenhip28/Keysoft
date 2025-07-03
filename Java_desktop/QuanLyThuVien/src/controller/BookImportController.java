package Controller;

import DBConnect.DatabaseConnection;
import model.BookImportDetailModel;
import model.BookImportModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookImportController {

    // ✅ 1. Tạo mới bản ghi nhập sách
    public int createBookImport(BookImportModel importModel) throws SQLException {
        String sql = "INSERT INTO book_imports (import_date, importer_name) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, new Timestamp(importModel.getImportDate().getTime()));
            ps.setString(2, importModel.getImporterName());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating import failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // import_id
                } else {
                    throw new SQLException("Creating import failed, no ID obtained.");
                }
            }
        }
    }

    // ✅ 2. Thêm chi tiết sách nhập
    public boolean addBookImportDetail(BookImportDetailModel detailModel) throws SQLException {
        String sql = "INSERT INTO book_import_details (import_id, book_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detailModel.getImportId());
            ps.setInt(2, detailModel.getBookId());
            ps.setInt(3, detailModel.getQuantity());

            return ps.executeUpdate() > 0;
        }
    }

    // ✅ 3. Cập nhật tồn kho sau khi nhập sách
    public boolean updateBookStock(int bookId, int quantityToAdd) throws SQLException {
        String sql = "UPDATE books SET quantity = quantity + ? WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantityToAdd);
            ps.setInt(2, bookId);

            return ps.executeUpdate() > 0;
        }
    }

    // ✅ 4. Lịch sử nhập sách (gồm tên sách, thể loại, số lượng, ngày, người nhập)
    public List<String[]> getBookImportHistory() {
        List<String[]> history = new ArrayList<>();
        String sql = "SELECT b.title, b.category, bd.quantity, bi.import_date, bi.importer_name "
                + "FROM book_import_details bd "
                + "JOIN books b ON bd.book_id = b.book_id "
                + "JOIN book_imports bi ON bd.import_id = bi.import_id "
                + "ORDER BY bi.import_date DESC";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] row = new String[5];
                row[0] = rs.getString("title");
                row[1] = rs.getString("category");
                row[2] = String.valueOf(rs.getInt("quantity"));
                row[3] = rs.getString("importer_name");
                row[4] = rs.getString("import_date");
                history.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }

    // ✅ 5. Lấy ID tự động tiếp theo (dự đoán cho hiển thị MNS-xxx)
    public int getEstimatedNextImportId() {
        String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_NAME = 'book_imports' AND TABLE_SCHEMA = 'your_database_name'";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("AUTO_INCREMENT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
