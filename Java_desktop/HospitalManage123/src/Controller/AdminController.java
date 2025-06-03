package controller;

import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.AdminModel;
import view.AdminView;

public class AdminController {
    private final AdminView view;
    private final AdminModel model;
    private final String userCode;
    private List<String> currentPermissions;
    private int currentPage = 1;
    private int totalRecords = 0;

    public AdminController(String userCode) {
        this.userCode = userCode;
        this.view = new AdminView();
        this.model = new AdminModel();

        init();
    }

    private void init() {
        loadPermissions();
        loadAppointments();

        // Gán sự kiện
        view.btn_choose.addActionListener(e -> openModule());
        view.btn_refresh.addActionListener(e -> loadAppointments());
        view.btn_logout.addActionListener(e -> logout());
        view.btn_previous.addActionListener(e -> previousPage());
        view.btn_next.addActionListener(e -> nextPage());

        view.setVisible(true);
    }

    private void loadPermissions() {
        view.cb_choose.removeAllItems();
        currentPermissions = model.getPermissions(userCode);

        if (currentPermissions.isEmpty()) {
            view.cb_choose.addItem("Không có quyền");
            view.cb_choose.setEnabled(false);
        } else {
            view.cb_choose.setEnabled(true);
            for (String perm : currentPermissions) {
                view.cb_choose.addItem(perm);
            }
        }
    }

    private void loadAppointments() {
        totalRecords = model.countAppointments();
        List<String> appointments = model.getAppointmentsByPage(currentPage);

        view.display_lichhen.setText("");
        for (String appt : appointments) {
            view.display_lichhen.append(appt);
        }
    }

    private void nextPage() {
        int totalPages = (int) Math.ceil((double) totalRecords / 5);
        if (currentPage < totalPages) {
            currentPage++;
            loadAppointments();
        }
    }

    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            loadAppointments();
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            new view.LoginView().setVisible(true);
        }
    }

    private void openModule() {
        int selectedIndex = view.cb_choose.getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex >= currentPermissions.size()) return;

        String permission = currentPermissions.get(selectedIndex);
        view.setVisible(false);

        switch (permission) {
            case "Quản lý bệnh nhân" -> new ManagePatient(view, userCode, "admin").setVisible(true);
            case "Quản lý lịch hẹn" -> new ManageAppointment(view, userCode, "admin").setVisible(true);
            case "Quản lý đơn thuốc" -> new CreateMedicines(view, userCode, "admin").setVisible(true);
            case "Quản lý kho" -> new ManageMedicines(view, userCode, "admin").setVisible(true);
            default -> JOptionPane.showMessageDialog(view, "Quyền chưa được hỗ trợ.");
        }
    }
}
