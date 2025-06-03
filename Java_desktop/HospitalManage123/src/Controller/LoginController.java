package controller;

import model.UserModel;
import view.LoginView;
import UserRole.admin;
import UserRole.doctor;
import UserRole.letan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;
    private UserModel model;

    public LoginController(LoginView view, UserModel model) {
        this.view = view;
        this.model = model;

        this.view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String password = view.getPassword();

                String[] result = model.login(username, password);
                if (result != null) {
                    String userCode = result[0];
                    String role = result[1];

                    view.showMessage("Đăng nhập " + role + " thành công!");
                    view.setVisible(false);

                    switch (role) {
                        case "Admin" -> new admin(userCode).setVisible(true);
                        case "Bác Sĩ" -> new doctor(userCode).setVisible(true);
                        case "Lễ tân", "Le tan" -> new letan(userCode).setVisible(true);
                        default -> {
                            view.showMessage("Vai trò không được hỗ trợ.");
                            view.setVisible(true);
                        }
                    }
                } else {
                    view.showMessage("Sai tên đăng nhập hoặc mật khẩu.");
                }
            }
        });
    }
}
