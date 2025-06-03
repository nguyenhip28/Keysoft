package site.UserRole;

import site.Appointment.ManageAppointment;
import site.Medicines.CreateMedicines;
import site.Medicines.ManageMedicines;
import site.Patient.ManagePatient;
import site.login;
import DBConnect.PermissionService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminController {
    private AdminView view;
    private String userCode;
    private List<String> currentPermissions;
    private int currentPage = 1;
    private int pageSize = 5;
    private int totalRecords = 0;

    public AdminController(String userCode) {
        this.userCode = userCode;
        view = new AdminView();
        initListeners();
        loadPermissions();
        loadAppointmentsByPage(currentPage);
        view.setVisible(true);
    }

    private void initListeners() {
        view.btn_choose.addActionListener(e -> handleChoosePermission());
        view.btn_refresh.addActionListener(e -> loadAppointmentsByPage(currentPage));
        view.btn_logout.addActionListener(e -> logout());
        view.btn_previous.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadAppointmentsByPage(currentPage);
            }
        });
        view.btn_next.addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            if (currentPage < totalPages) {
                currentPage++;
                loadAppointmentsByPage(currentPage);
            }
        });
    }

    private void loadPermissions() {
        view.cb_choose.removeAllItems();
        currentPermissions = PermissionService.getPermissionsByUserCode(userCode);

        if (currentPermissions.isEmpty()) {
            view.cb_choose.addItem("Không có quyền nào được cấp");
            view.cb_choose.setEnabled(false);
        } else {
            view.cb_choose.setEnabled(true);
            for (String perm : currentPermissions) {
                view.cb_choose.addItem(perm);
            }
        }
    }

    private void loadAppointmentsByPage(int page) {
        AppointmentService service = new AppointmentService();
        var result = service.getAppointmentsByPage(page, pageSize);
        totalRecords = result.total;

        view.display_lichhen.setText("");
        for (var a : result.appointments) {
            view.display_lichhen.append("Mã BN: " + a.getPatientCode()
                + "\nBệnh nhân: " + a.getFullName()
                + "\nNgày: " + a.getDate()
                + "\nGiờ: " + a.getTime()
                + "\nTriệu chứng: " + a.getSymptoms()
                + "\n------------------------\n");
        }
    }

    private void handleChoosePermission() {
        int selectedIndex = view.cb_choose.getSelectedIndex();
        if (currentPermissions == null || currentPermissions.isEmpty() || selectedIndex < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn quyền hợp lệ.");
            return;
        }

        String permission = currentPermissions.get(selectedIndex);
        view.setVisible(false);

        switch (permission) {
            case "Quản lý lịch hẹn" -> new ManageAppointment(view, userCode, "admin").setVisible(true);
            case "Quản lý bệnh nhân" -> new ManagePatient(view, userCode, "admin").setVisible(true);
            case "Quản lý kho" -> new ManageMedicines(view, userCode, "admin").setVisible(true);
            case "Quản lý đơn thuốc" -> new CreateMedicines(view, userCode, "admin").setVisible(true);
            default -> JOptionPane.showMessageDialog(view, "Quyền chưa được hỗ trợ.");
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            view.setVisible(false);
            new login().setVisible(true);
        }
    }
}
