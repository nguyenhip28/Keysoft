package Controller;

import DBConnect.DatabaseConnection;
import model.BooksModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksController {

    // Thêm sách vào CSDL
    public boolean addBook(BooksModel book) {
        String sql = "INSERT INTO books(title, author, publish_year, category, quantity, image_url) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPublishYear());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getQuantity());
            ps.setString(6, book.getImageUrl());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy sách theo phân trang
    public List<BooksModel> getBooksByPage(int page, int limit) {
        List<BooksModel> list = new ArrayList<>();
        String sql = "SELECT * FROM books LIMIT ? OFFSET ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            ps.setInt(2, (page - 1) * limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BooksModel book = extractBookFromResultSet(rs);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Đếm tổng số sách
    public int countBooks() {
        String sql = "SELECT COUNT(*) FROM books";

        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tìm kiếm sách theo từ khóa
    public List<BooksModel> searchBooks(String keyword) {
        List<BooksModel> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BooksModel book = extractBookFromResultSet(rs);
                list.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy thông tin sách theo ID
    public BooksModel getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractBookFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hàm hỗ trợ tái sử dụng để lấy dữ liệu sách từ ResultSet
    private BooksModel extractBookFromResultSet(ResultSet rs) throws SQLException {
        return new BooksModel(
                rs.getInt("book_id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("publish_year"),
                rs.getString("category"),
                rs.getInt("quantity"),
                rs.getString("image_url")
        );
    }

    public List<BooksModel> getBooksByPageImp(int page, int pageSize) throws SQLException {
        List<BooksModel> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM books ORDER BY book_id DESC LIMIT ? OFFSET ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(extractBookFromResultSet(rs));
            }
        }

        return list;
    }

    public int getTotalBookCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM books";
        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public boolean updateBookQuantity(int bookId, int additionalQuantity) {
        String sql = "UPDATE books SET quantity = quantity + ? WHERE book_id = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, additionalQuantity);
            ps.setInt(2, bookId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Tìm sách dựa vào title + author + publishYear

    public BooksModel findBook(String title, String author, int publishYear) {
        String sql = "SELECT * FROM books WHERE title = ? AND author = ? AND publish_year = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, publishYear);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractBookFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
