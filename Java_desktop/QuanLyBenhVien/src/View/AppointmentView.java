package View;

import Controller.AppointmentController;
import Model.AppointmentModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AppointmentView extends javax.swing.JFrame {

    private javax.swing.JFrame parent;
    private String userCode;
    private String userRole;
    private int currentPage = 1;
    private int pageSize = 5;
    private int totalRecords = 0;
    private AppointmentController controller;

    public AppointmentView(javax.swing.JFrame parent, String userCode, String userRole) {
        this.parent = parent;
        this.userCode = userCode;
        this.userRole = userRole;
        this.controller = new AppointmentController();
        initComponents();
        loadAppointmentsByPage(currentPage);
        setLocationRelativeTo(null);

    }

    private void loadAppointmentsByPage(int page) {
        try {
            totalRecords = controller.getTotalAppointments();
            List<AppointmentModel> appointments = controller.getAppointmentsByPage(page, pageSize);

            DefaultTableModel model = (DefaultTableModel) display_lichhen1.getModel();
            model.setRowCount(0); // Xoá dữ liệu cũ

            for (AppointmentModel appointment : appointments) {
                Object[] row = new Object[]{
                    appointment.getAppointmentId(),
                    appointment.getPatientCode(),
                    appointment.getAppointmentDate(),
                    appointment.getAppointmentTime(),
                    appointment.getSymptoms()
                };
                model.addRow(row);
            }

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < display_lichhen1.getColumnCount(); i++) {
                display_lichhen1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn lịch hẹn: " + ex.getMessage());
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

        jScrollPane2 = new javax.swing.JScrollPane();
        display_lichhen = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        display_lichhen1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lb_patient_code = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cb_date_time = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lb_trieuchung = new javax.swing.JTextArea();
        btn_datlich = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_add_thuoc = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();

        display_lichhen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã bệnh nhân", "Ngày hẹn", "Giờ hẹn", "Triệu chứng"
            }
        ));
        jScrollPane2.setViewportView(display_lichhen);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Đặt lịch hẹn");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N

        display_lichhen1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã bệnh nhân", "Ngày hẹn", "Giờ hẹn", "Triệu chứng"
            }
        ));
        jScrollPane3.setViewportView(display_lichhen1);

        jLabel5.setText("Mã Bệnh Nhân");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel1.setText("Ngày Giờ");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setText("Triệu chứng");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lb_trieuchung.setColumns(20);
        lb_trieuchung.setRows(5);
        jScrollPane1.setViewportView(lb_trieuchung);

        btn_datlich.setText("Đặt lịch");
        btn_datlich.setBackground(new java.awt.Color(0, 153, 255));
        btn_datlich.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_datlich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datlichActionPerformed(evt);
            }
        });

        btn_delete.setText("Hủy lịch");
        btn_delete.setBackground(new java.awt.Color(0, 153, 255));
        btn_delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_add_thuoc.setBackground(new java.awt.Color(0, 153, 255));
        btn_add_thuoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_add_thuoc.setText("Kê đơn");
        btn_add_thuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_thuocActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_first.setText("Đầu");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_back.setText("<");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_next.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_next.setText(">");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_last.setText("Cuối");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lb_patient_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(cb_date_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(btn_datlich, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(btn_add_thuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(168, 168, 168)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_first)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_next)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_last)
                .addGap(220, 220, 220))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(btn_datlich, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_add_thuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lb_patient_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cb_date_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_back)
                    .addComponent(btn_next)
                    .addComponent(btn_last)
                    .addComponent(btn_first))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_datlichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlichActionPerformed
        try {
            // Lấy dữ liệu từ form
            String patientCode = lb_patient_code.getText().trim();
            LocalDate date = cb_date_time.getDatePicker().getDate();
            LocalTime time = cb_date_time.getTimePicker().getTime();
            String symptoms = lb_trieuchung.getText().trim();

            // Kiểm tra dữ liệu nhập vào
            if (patientCode.isEmpty() || date == null || time == null || symptoms.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ các trường!");
                return;
            }

            // Tạo đối tượng AppointmentModel (dùng constructor không có fullName)
            AppointmentModel appointment = new AppointmentModel(patientCode, date, time, symptoms);

            // Gọi Controller để tạo lịch hẹn
            boolean success = controller.createAppointment(appointment);

            if (success) {
                JOptionPane.showMessageDialog(this, "Đặt lịch hẹn thành công!");

                // Reset form
                lb_patient_code.setText("");
                lb_trieuchung.setText("");
                cb_date_time.clear();

                // Load lại danh sách lịch hẹn
                loadAppointmentsByPage(currentPage);
            } else {
                JOptionPane.showMessageDialog(this, "Mã bệnh nhân không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đặt lịch: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_datlichActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        int selectedRow = display_lichhen1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lịch hẹn cần hủy.");
            return;
        }

        String patientCode = display_lichhen1.getValueAt(selectedRow, 0).toString();
        LocalDate date = LocalDate.parse(display_lichhen1.getValueAt(selectedRow, 1).toString());
        LocalTime time = LocalTime.parse(display_lichhen1.getValueAt(selectedRow, 2).toString());

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy lịch hẹn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = controller.deleteAppointment(patientCode, date, time);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Hủy lịch thành công!");
                    loadAppointmentsByPage(currentPage);
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể hủy lịch. Có thể lịch đã bị xóa trước đó.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi hủy lịch: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_add_thuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_thuocActionPerformed
        int selectedRow = display_lichhen1.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) display_lichhen1.getModel();

            try {
                // Lấy dữ liệu đúng thứ tự cột
                int appointmentId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString()); // ID
                String patientCode = model.getValueAt(selectedRow, 1).toString();                  // BN-xxx
                LocalDate date = LocalDate.parse(model.getValueAt(selectedRow, 2).toString());     // Ngày hẹn
                LocalTime time = LocalTime.parse(model.getValueAt(selectedRow, 3).toString());     // Giờ hẹn
                String symptoms = model.getValueAt(selectedRow, 4).toString();                     // Triệu chứng

                // Nếu không hiển thị fullName, để trống hoặc lấy từ DB
                String fullName = ""; // Gợi ý: lấy từ patient_code nếu cần

                // Tạo AppointmentModel
                AppointmentModel selectedAppointment = new AppointmentModel(
                        appointmentId, patientCode, fullName, date, time, symptoms
                );

                // Mở form kê đơn
                CreateMedicinesView view = new CreateMedicinesView(this, userCode, userRole, selectedAppointment);
                view.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ hoặc thiếu. Chi tiết: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch hẹn để kê đơn thuốc.");
        }
    }//GEN-LAST:event_btn_add_thuocActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose(); // Close the current add_benhnhan window
        if (parent != null) {
            parent.setVisible(true); // Show the parent window (benhnhan_manage)
        } else {
            // Fallback: Open a new benhnhan_manage window with userCode and userRole
            new PatientView(null, userCode, userRole).setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        if (currentPage != 1) {
            currentPage = 1;
            loadAppointmentsByPage(currentPage);
        }
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        if (currentPage > 1) {
            currentPage--;
            loadAppointmentsByPage(currentPage);
        }
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (currentPage < totalPages) {
            currentPage++;
            loadAppointmentsByPage(currentPage);
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (currentPage != totalPages) {
            currentPage = totalPages;
            loadAppointmentsByPage(currentPage);
        }
    }//GEN-LAST:event_btn_lastActionPerformed

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
            java.util.logging.Logger.getLogger(AppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_thuoc;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_datlich;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private com.github.lgooddatepicker.components.DateTimePicker cb_date_time;
    private javax.swing.JTable display_lichhen;
    private javax.swing.JTable display_lichhen1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lb_patient_code;
    private javax.swing.JTextArea lb_trieuchung;
    // End of variables declaration//GEN-END:variables
}
