package View;

import Controller.PrescriptionController;
import DBConnect.DatabaseConnection;
import java.lang.ModuleLayer.Controller;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

    public CreateMedicinesView() {
        initComponents();
        setLocationRelativeTo(null);

    }

    public CreateMedicinesView(javax.swing.JFrame parent, String userCode, String userRole) {
        this.parent = parent;
        this.userCode = userCode;
        this.userRole = userRole;
        initComponents();
        setLocationRelativeTo(null);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_ten_thuoc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        display_don_thuoc = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        btn_done = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lb_total = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_benhnhan_code = new javax.swing.JTextField();
        lb_lieu_luong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lb_notes = new javax.swing.JTextArea();
        btn_search = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ĐƠN THUỐC");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N

        jLabel2.setText("Tên thuốc");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel3.setText("Liều lượng");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btn_add.setText("Thêm vào đơn");
        btn_add.setBackground(new java.awt.Color(0, 153, 255));
        btn_add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        display_don_thuoc.setColumns(20);
        display_don_thuoc.setRows(5);
        jScrollPane2.setViewportView(display_don_thuoc);

        jLabel4.setText("Đơn thuốc");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btn_done.setText("Kê đơn");
        btn_done.setBackground(new java.awt.Color(0, 153, 255));
        btn_done.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doneActionPerformed(evt);
            }
        });

        jLabel5.setText("Tổng tiền:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel7.setText("Mã bệnh nhân");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel8.setText("Ghi chú");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lb_notes.setColumns(20);
        lb_notes.setRows(5);
        jScrollPane3.setViewportView(lb_notes);

        btn_search.setText("Tìm đơn");
        btn_search.setBackground(new java.awt.Color(0, 153, 255));
        btn_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_refresh.setText("Refresh");
        btn_refresh.setBackground(new java.awt.Color(0, 153, 255));
        btn_refresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        btn_back.setText("Back");
        btn_back.setBackground(new java.awt.Color(0, 153, 255));
        btn_back.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(lb_ten_thuoc)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_benhnhan_code, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_search))
                            .addComponent(lb_lieu_luong)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_refresh))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_done, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(btn_refresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lb_total))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_benhnhan_code, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_ten_thuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_lieu_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_done, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doneActionPerformed
        if (dsThuoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có thuốc nào trong đơn.");
            return;
        }

        String notes = lb_notes.getText().trim();
        PrescriptionController controller = new PrescriptionController();
        int result = 0;
        try {
            result = controller.savePrescription(currentPatientCode, notes, totalAmount, dsThuoc);
        } catch (SQLException ex) {
            Logger.getLogger(CreateMedicinesView.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Đã lưu đơn thuốc thành công!");

            // Reset
            currentPatientCode = "";
            dsThuoc.clear();
            display_don_thuoc.setText("");
            lb_total.setText("0.00");
            lb_notes.setText("");
            totalAmount = 0.0;
        } else {
            JOptionPane.showMessageDialog(this, "Lưu đơn thuốc thất bại.");
        }
    }//GEN-LAST:event_btn_doneActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        String tenThuoc = lb_ten_thuoc.getText().trim();
        String lieuLuong = lb_lieu_luong.getText().trim();
        String patientCodeInput = lb_benhnhan_code.getText().trim();

        if (tenThuoc.isEmpty() || lieuLuong.isEmpty() || patientCodeInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã bệnh nhân, tên thuốc và liều lượng.");
            return;
        }

        try {
            PrescriptionController controller = new PrescriptionController();
            Map<String, String> medicineInfo = controller.searchMedicine(tenThuoc);

            if (medicineInfo != null) {
                // Kiểm tra nếu patient_code thay đổi thì reset danh sách thuốc
                if (!patientCodeInput.equals(currentPatientCode)) {
                    currentPatientCode = patientCodeInput;
                    dsThuoc.clear();
                    display_don_thuoc.setText("");
                    totalAmount = 0.0;
                }

                Map<String, String> item = new HashMap<>();
                item.put("medicine_id", medicineInfo.get("medicine_id"));
                item.put("medicine_name", tenThuoc);
                item.put("dosage", lieuLuong);  // vẫn lưu liều lượng để hiển thị
                item.put("price", medicineInfo.get("price"));
                item.put("unit", medicineInfo.get("unit"));
                item.put("quantity", lieuLuong);  // thêm quantity để tính tiền

                dsThuoc.add(item);

                display_don_thuoc.append(
                        "Tên thuốc: " + tenThuoc + "\n"
                        + "Liều lượng: " + lieuLuong + "\n"
                        + "Đơn giá: " + medicineInfo.get("price") + " / " + medicineInfo.get("unit") + "\n"
                        + "------------\n"
                );

                totalAmount = dsThuoc.stream().mapToDouble(t -> {
                    double price = Double.parseDouble(t.get("price"));
                    int quantity = Integer.parseInt(t.get("quantity"));  // Giả sử người nhập số lượng
                    return price * quantity;
                }).sum();

                lb_total.setText(String.format("%.2f", totalAmount));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm thuốc: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_addActionPerformed

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

            if (!data.isEmpty()) {
                int prescriptionId = (int) data.get("prescription_id");
                String notes = (String) data.get("notes");
                double total = (double) data.get("total_amount");
                List<Map<String, String>> thuocList = (List<Map<String, String>>) data.get("medicines");

                currentPatientCode = patientCodeInput;
                dsThuoc.clear();
                display_don_thuoc.setText("");
                lb_notes.setText(notes != null ? notes : "");
                totalAmount = total;
                lb_total.setText(String.format("%.2f", total));

                for (Map<String, String> thuoc : thuocList) {
                    dsThuoc.add(thuoc);
                    display_don_thuoc.append("Bệnh nhân:" + patientCodeInput + "\nTên thuốc: " + thuoc.get("medicine_name")
                            + "\nLiều lượng: " + thuoc.get("dosage")
                            + "\nĐơn vị: " + thuoc.get("unit")
                            + "\nGiá: " + thuoc.get("price") + "\n------------\n");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy đơn thuốc cho bệnh nhân này.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm đơn thuốc.");
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    @SuppressWarnings("unchecked")
    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        try {
            PrescriptionController controller = new PrescriptionController();
            List<Map<String, Object>> prescriptions = controller.getAllPrescriptions();

            display_don_thuoc.setText("");
            totalAmount = 0.0;

            for (Map<String, Object> pres : prescriptions) {
                int prescriptionId = (int) pres.get("prescription_id");
                String patientCode = (String) pres.get("patient_code");
                String notes = (String) pres.get("notes");
                double total = (double) pres.get("total_amount");
                List<Map<String, String>> thuocList = (List<Map<String, String>>) pres.get("medicines");

                display_don_thuoc.append("Đơn thuốc #" + prescriptionId + ":\n");
                display_don_thuoc.append("Mã bệnh nhân: " + patientCode + "\n");
                display_don_thuoc.append("Ghi chú: " + (notes != null ? notes : "") + "\n");
                display_don_thuoc.append("Thuốc:\n");

                for (Map<String, String> item : thuocList) {
                    display_don_thuoc.append("  - Tên thuốc: " + item.get("medicine_name") + "\n"
                            + " - Liều lượng: " + item.get("dosage") + "\n"
                            + " - Đơn vị: " + item.get("unit") + ", \n"
                            + " - Giá: " + item.get("price") + "\n");
                }

                display_don_thuoc.append("Tổng tiền đơn thuốc: " + total + "\n");
                display_don_thuoc.append("-------------------------\n");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách đơn thuốc.");
        }
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        this.dispose(); // đóng cửa sổ hiện tại
        if (parent != null) {
            parent.setVisible(true); // mở lại form letan
        }

        switch (userRole.toLowerCase()) {
            case "admin" -> {
                if (parent != null) {
                    parent.setVisible(true);
                } else {
                    new admin(userCode).setVisible(true);
                }
            }

            case "doctor" -> {
                if (parent != null) {
                    parent.setVisible(true);
                } else {
                    new doctor(userCode).setVisible(true);
                }
            }

            case "letan" -> {
                if (parent != null) {
                    parent.setVisible(true);
                } else {
                    new letan(userCode).setVisible(true);
                }
            }
            default -> {
                if (parent != null) {
                    parent.setVisible(true);
                }
            }
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
            new CreateMedicinesView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_done;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_search;
    private javax.swing.JTextArea display_don_thuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField lb_benhnhan_code;
    private javax.swing.JTextField lb_lieu_luong;
    private javax.swing.JTextArea lb_notes;
    private javax.swing.JTextField lb_ten_thuoc;
    private javax.swing.JTextField lb_total;
    // End of variables declaration//GEN-END:variables
}
