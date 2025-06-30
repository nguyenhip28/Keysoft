package DBConnect;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.LoginView;
import com.formdev.flatlaf.FlatLightLaf;
import view.LoginView;

public class main {

    public static void main(String[] args) {
        try {
            // Cài giao diện FlatLaf
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Không thể tải giao diện FlatLaf: " + ex);
        }

        // Hiển thị loginView
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginView login = new LoginView();
            login.setLocationRelativeTo(null); // Hiển thị giữa màn hình
            login.setVisible(true);
        });
    }
}
