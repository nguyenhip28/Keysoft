package View;

import Controller.PrescriptionController;
import DBConnect.DatabaseConnection;
import Model.AppointmentModel;
import java.lang.ModuleLayer.Controller;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vu Nguyen
 */
public class CreateMedicinesView extends javax.swing.JFrame {

    private javax.swing.JFrame parent;
    private String userCode;
    private String userRole;
    /**
     * Creates new form don_thuoc
     */

    private final List<Map<String, String>> dsThuoc = new ArrayList<>();
    private String currentPatientCode = "";
    private double totalAmount = 0.0;
    private AppointmentModel selectedAppointment;

    public CreateMedicinesView() {
        initComponents();
        setLocationRelativeTo(null);
        loadAllPrescriptions();
        this.selectedAppointment = selectedAppointment;
        this.pack();

    }

    public CreateMedicinesView(javax.swing.JFrame parent, String userCode, String userRole, AppointmentModel selectedAppointment) {
        this.parent = parent;
        this.userCode = userCode;
        this.userRole = userRole;
        this.selectedAppointment = selectedAppointment;
        initComponents();
        setLocationRelativeTo(null);
        loadAllPrescriptions();

    }

    public CreateMedicinesView(javax.swing.JFrame parent, String userCode, String userRole) {
        this.parent = parent;
        this.userCode = userCode;
        this.userRole = userRole;
        this.selectedAppointment = null; // Gán null mặc định
        initComponents();
        setLocationRelativeTo(null);
        loadAllPrescriptions();
    }

    private void loadAllPrescriptions() {
        try {
            PrescriptionController controller = new PrescriptionController();
            List<Map<String, Object>> allPrescriptions = controller.getAllPrescriptions();

            DefaultTableModel model = (DefaultTableModel) display_don_thuoc.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ nếu có

            for (Map<String, Object> prescription : allPrescriptions) {
                String maBN = (String) prescription.get("patient_code");
                String ghiChu = (String) prescription.get("notes");
                double tongTien = (double) prescription.get("total_amount");
                @SuppressWarnings("unchecked")
                List<Map<String, String>> thuocList = (List<Map<String, String>>) prescription.get("medicines");

                for (Map<String, String> thuoc : thuocList) {
                    model.addRow(new Object[]{
                        maBN,
                        thuoc.get("medicine_name"),
                        thuoc.get("dosage"),
                        ghiChu,
                        String.format("%.2f", tongTien)
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách đơn thuốc: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_search = new javax.swing.JButton();
        btn_done = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_ten_thuoc = new javax.swing.JTextField();
        lb_benhnhan_code = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lb_lieu_luong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lb_notes = new javax.swing.JTextArea();
        lb_total = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        display_don_thuoc = new javax.swing.JTable();
        btn_add = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("<");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setSize(new java.awt.Dimension(760, 646));
        getContentPane().add(jLabel6, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_search.setBackground(new java.awt.Color(0, 153, 255));
        btn_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_search.setText("Tìm đơn");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_done.setBackground(new java.awt.Color(0, 153, 255));
        btn_done.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_done.setText("Kê đơn");
        btn_done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doneActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tổng tiền:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("ĐƠN THUỐC");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên thuốc");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mã bệnh nhân");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Liều lượng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Ghi chú");

        lb_notes.setColumns(20);
        lb_notes.setRows(5);
        jScrollPane3.setViewportView(lb_notes);

        display_don_thuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã BN", "Tên thuốc", "Liều lượng", "Ghi chú", "Tổng tiền"
            }
        ));
        jScrollPane1.setViewportView(display_don_thuoc);

        btn_add.setBackground(new java.awt.Color(0, 153, 255));
        btn_add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_add.setText("Thêm vào đơn");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_back.setBackground(new java.awt.Color(51, 153, 255));
        btn_back.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_back.setText("<");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(219, 219, 219)
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lb_ten_thuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(lb_benhnhan_code, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_total)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(lb_lieu_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_done, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_back))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lb_lieu_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_done, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_benhnhan_code, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_ten_thuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doneActionPerformed
        currentPatientCode = lb_benhnhan_code.getText().trim();

        if (currentPatientCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân đang trống!");
            return;
        }

        if (dsThuoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có thuốc nào trong đơn.");
            return;
        }

        String notes = lb_notes.getText().trim();
        PrescriptionController controller = new PrescriptionController();

        try {
            // 
            if (selectedAppointment == null) {
                JOptionPane.showMessageDialog(this, "Chưa chọn lịch hẹn để gán đơn thuốc.");
                return;
            }

            int appointmentId = selectedAppointment.getAppointmentId();  // ✅ Lấy ID lịch hẹn từ model

            int result = controller.savePrescription(currentPatientCode, appointmentId, notes, totalAmount, dsThuoc);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Đã lưu đơn thuốc thành công!");

                // Reset
                currentPatientCode = "";
                dsThuoc.clear();
                totalAmount = 0.0;

                lb_total.setText("0.00");
                lb_notes.setText("");
                lb_benhnhan_code.setText("");
                lb_ten_thuoc.setText("");
                lb_lieu_luong.setText("");
                selectedAppointment = null;

                // Load lại tất cả đơn thuốc
                List<Map<String, Object>> allPrescriptions = controller.getAllPrescriptions();
                DefaultTableModel model = (DefaultTableModel) display_don_thuoc.getModel();
                model.setRowCount(0);

                for (Map<String, Object> prescription : allPrescriptions) {
                    String maBN = (String) prescription.get("patient_code");
                    String ghiChu = (String) prescription.get("notes");
                    double tongTien = (double) prescription.get("total_amount");
                    @SuppressWarnings("unchecked")
                    List<Map<String, String>> thuocList = (List<Map<String, String>>) prescription.get("medicines");

                    for (Map<String, String> thuoc : thuocList) {
                        model.addRow(new Object[]{
                            maBN,
                            thuoc.get("medicine_name"),
                            thuoc.get("dosage"),
                            ghiChu,
                            String.format("%.2f", tongTien)
                        });
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Lưu đơn thuốc thất bại.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu đơn thuốc: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_doneActionPerformed

    @SuppressWarnings("unchecked")
    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String patientCodeInput = lb_benhnhan_code.getText().trim();

        if (patientCodeInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã bệnh nhân.");
            return;
        }

        try {
            PrescriptionController controller = new PrescriptionController();
            Map<String, Object> data = controller.getLatestPrescriptionByPatientCode(patientCodeInput);

            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy đơn thuốc cho bệnh nhân này.");
                return;
            }

            currentPatientCode = patientCodeInput;
            dsThuoc.clear();

            String notes = (String) data.get("notes");
            double total = (double) data.get("total_amount");
            List<Map<String, String>> thuocList = (List<Map<String, String>>) data.get("medicines");

            lb_notes.setText(notes != null ? notes : "");
            lb_total.setText(String.format("%.2f", total));
            totalAmount = total;

            DefaultTableModel model = (DefaultTableModel) display_don_thuoc.getModel();
            model.setRowCount(0);

            for (Map<String, String> thuoc : thuocList) {
                dsThuoc.add(thuoc);

                double donGia = Double.parseDouble(thuoc.get("price"));
                int soLuong = Integer.parseInt(thuoc.get("quantity"));
                double thanhTien = donGia * soLuong;

                model.addRow(new Object[]{
                    thuoc.get("medicine_name"),
                    thuoc.get("dosage"),
                    thuoc.get("unit"),
                    donGia,
                    soLuong,
                    thanhTien
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm đơn thuốc: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        currentPatientCode = lb_benhnhan_code.getText().trim();

        if (currentPatientCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã bệnh nhân trước khi thêm thuốc.");
            return;
        }

        String tenThuoc = lb_ten_thuoc.getText().trim();
        String lieuLuong = lb_lieu_luong.getText().trim();
        String notes = lb_notes.getText().trim(); // Lấy ghi chú

        if (tenThuoc.isEmpty() || lieuLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thuốc và liều lượng.");
            return;
        }

        try {
            PrescriptionController controller = new PrescriptionController();
            Map<String, String> medInfo = controller.searchMedicine(tenThuoc);

            if (medInfo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thuốc.");
                return;
            }

            Map<String, String> thuoc = new HashMap<>();
            thuoc.put("medicine_id", medInfo.get("medicine_id"));
            thuoc.put("medicine_name", medInfo.get("medicine_name"));
            thuoc.put("dosage", lieuLuong);
            thuoc.put("unit", medInfo.get("unit"));
            thuoc.put("price", medInfo.get("price"));
            thuoc.put("quantity", "1");

            dsThuoc.add(thuoc);

            // Hiển thị thuốc lên bảng
            DefaultTableModel model = (DefaultTableModel) display_don_thuoc.getModel();
            double price = Double.parseDouble(medInfo.get("price"));
            double amount = price * 1;

            model.addRow(new Object[]{
                currentPatientCode,
                medInfo.get("medicine_name"),
                lieuLuong,
                notes, // Ghi chú được thêm vào bảng
                String.format("%.2f", amount)
            });

            totalAmount += amount;
            lb_total.setText(String.format("%.2f", totalAmount));

            lb_ten_thuoc.setText("");
            lb_lieu_luong.setText("");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm thuốc: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        this.dispose(); // Close the current add_benhnhan window
        if (parent != null) {
            parent.setVisible(true); // Show the parent window (benhnhan_manage)
        } else {
            // Fallback: Open a new benhnhan_manage window with userCode and userRole
            new PatientView(null, userCode, userRole).setVisible(true);
        }
    }//GEN-LAST:event_btn_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateMedicinesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateMedicinesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateMedicinesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateMedicinesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_done;
    private javax.swing.JButton btn_search;
    private javax.swing.JTable display_don_thuoc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField lb_benhnhan_code;
    private javax.swing.JTextField lb_lieu_luong;
    private javax.swing.JTextArea lb_notes;
    private javax.swing.JTextField lb_ten_thuoc;
    private javax.swing.JTextField lb_total;
    // End of variables declaration//GEN-END:variables
}
