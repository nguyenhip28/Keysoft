
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

/**
 *
 * @author Vu Nguyen
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Cài giao diện FlatLaf Light
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Không thể cài đặt FlatLaf.");
        }

        // Khởi chạy form đăng nhập đầu tiên
        new View.LoginView().setVisible(true);
    }
}
