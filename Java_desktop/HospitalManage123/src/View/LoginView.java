package view;

import javax.swing.*;

public class LoginView extends JFrame {
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JButton btnLogin;

    public LoginView() {
        setTitle("Đăng nhập");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setBounds(200, 20, 100, 40);
        add(lblTitle);

        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setBounds(100, 80, 100, 30);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(200, 80, 150, 30);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setBounds(100, 130, 100, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 130, 150, 30);
        add(txtPassword);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(180, 180, 100, 30);
        add(btnLogin);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public JButton getLoginButton() {
        return btnLogin;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
