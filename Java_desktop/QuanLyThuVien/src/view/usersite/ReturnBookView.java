package view.usersite;

import controller.BorrowDetailController;
import controller.LateFeeRuleDAO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.BorrowDetailModel;
import view.LoginView;

/**
 *
 * @author Vu Nguyen
 */
public class ReturnBookView extends javax.swing.JFrame {

    private String userCode;

    /**
     * Creates new form ReturnBookView
     */
    public ReturnBookView() {
        initComponents();
        loadBorrowedBooks();
    }

    public ReturnBookView(String userCode) {
        this.userCode = userCode;
        initComponents();
        setLocationRelativeTo(null);
        loadBorrowedBooks();
    }

    private void loadBorrowedBooks() {
        BorrowDetailController controller = new BorrowDetailController();
        List<BorrowDetailModel> list = controller.getBorrowDetailsByUserCode(userCode);

        // ⚡️ Sắp xếp: "Đang mượn" lên đầu, sau đó theo ngày mượn giảm dần
        list.sort((a, b) -> {
            if (a.getStatus().equals("Đang mượn") && !b.getStatus().equals("Đang mượn")) {
                return -1;
            }
            if (!a.getStatus().equals("Đang mượn") && b.getStatus().equals("Đang mượn")) {
                return 1;
            }
            return b.getBorrowDate().compareTo(a.getBorrowDate());
        });

        DefaultTableModel model = (DefaultTableModel) display_borrow.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        int stt = 1;
        for (BorrowDetailModel detail : list) {
            LocalDate borrowDate = detail.getBorrowDate().toLocalDateTime().toLocalDate();
            LocalDate expectedReturn = detail.getExpectedReturnDate().toLocalDateTime().toLocalDate();
            LocalDate actualReturn = (detail.getActualReturnDate() != null)
                    ? detail.getActualReturnDate().toLocalDateTime().toLocalDate()
                    : null;

            LocalDate today = LocalDate.now();
            long lateDays = 0;
            long penaltyFee = 0;

            // Tính số ngày trễ và phí phạt nếu đang mượn và quá hạn
            if (detail.getStatus().equals("Đang mượn") && today.isAfter(expectedReturn)) {
                lateDays = ChronoUnit.DAYS.between(expectedReturn, today);
                BigDecimal feePerDay = LateFeeRuleDAO.getFeePerDay((int) lateDays);
                penaltyFee = feePerDay.multiply(BigDecimal.valueOf(lateDays)).longValue();
            }

            String returnTimeStr = (actualReturn != null) ? actualReturn.toString() : "";
            String penaltyDisplay = (penaltyFee > 0) ? penaltyFee + " đ" : "0 đ";

            model.addRow(new Object[]{
                stt++,
                detail.getBookTitle(),
                borrowDate,
                expectedReturn,
                returnTimeStr,
                detail.getStatus(),
                penaltyDisplay
            });
        }

        // Căn giữa toàn bộ cột trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < display_borrow.getColumnCount(); i++) {
            display_borrow.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_menu = new javax.swing.JButton();
        btn_bookstore = new javax.swing.JButton();
        btn_muon_tra_sach = new javax.swing.JButton();
        btn_dntBook = new javax.swing.JButton();
        btn_profile = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        display_borrow = new javax.swing.JTable();
        btn_first = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_return = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel2.setText("E-BOOK  STORE");

        btn_menu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_menu.setText("Trang chủ");
        btn_menu.setToolTipText("");
        btn_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menuActionPerformed(evt);
            }
        });

        btn_bookstore.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_bookstore.setText("Thư viện");
        btn_bookstore.setMaximumSize(new java.awt.Dimension(176, 43));
        btn_bookstore.setMinimumSize(new java.awt.Dimension(176, 43));
        btn_bookstore.setPreferredSize(new java.awt.Dimension(176, 43));
        btn_bookstore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bookstoreActionPerformed(evt);
            }
        });

        btn_muon_tra_sach.setBackground(new java.awt.Color(0, 153, 255));
        btn_muon_tra_sach.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_muon_tra_sach.setText("Trả sách");
        btn_muon_tra_sach.setMaximumSize(new java.awt.Dimension(176, 43));
        btn_muon_tra_sach.setMinimumSize(new java.awt.Dimension(176, 43));
        btn_muon_tra_sach.setPreferredSize(new java.awt.Dimension(176, 43));

        btn_dntBook.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_dntBook.setText("Góp sách");
        btn_dntBook.setMaximumSize(new java.awt.Dimension(176, 43));
        btn_dntBook.setMinimumSize(new java.awt.Dimension(176, 43));
        btn_dntBook.setPreferredSize(new java.awt.Dimension(176, 43));

        btn_profile.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_profile.setText("Thông tin cá nhân");
        btn_profile.setMaximumSize(new java.awt.Dimension(176, 43));
        btn_profile.setMinimumSize(new java.awt.Dimension(176, 43));
        btn_profile.setPreferredSize(new java.awt.Dimension(176, 43));
        btn_profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_profileActionPerformed(evt);
            }
        });

        btn_logout.setBackground(new java.awt.Color(255, 102, 102));
        btn_logout.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_logout.setText("Đăng xuất");
        btn_logout.setMaximumSize(new java.awt.Dimension(176, 43));
        btn_logout.setMinimumSize(new java.awt.Dimension(176, 43));
        btn_logout.setPreferredSize(new java.awt.Dimension(176, 43));
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 14, Short.MAX_VALUE))
            .addComponent(btn_bookstore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_muon_tra_sach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_dntBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_profile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_bookstore, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_muon_tra_sach, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_dntBook, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_profile, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 600));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Xin chào khách hàng!");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 490, 60));

        display_borrow.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        display_borrow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên sách", "Ngày mượn", "Dự kiến trả", "Thời gian trả", "Trạng thái", "Phí phạt"
            }
        ));
        jScrollPane1.setViewportView(display_borrow);
        if (display_borrow.getColumnModel().getColumnCount() > 0) {
            display_borrow.getColumnModel().getColumn(0).setMinWidth(70);
            display_borrow.getColumnModel().getColumn(0).setPreferredWidth(70);
            display_borrow.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        btn_first.setText("Đầu");

        btn_back.setText("<");

        btn_next.setText(">");

        btn_last.setText("Cuối");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reader-1713700-1453871.png"))); // NOI18N
        jLabel3.setText("Mượn, trả sách");

        btn_return.setBackground(new java.awt.Color(0, 153, 255));
        btn_return.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_return.setText("Trả sách");
        btn_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_returnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(btn_first)
                        .addGap(18, 18, 18)
                        .addComponent(btn_back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_next)
                        .addGap(18, 18, 18)
                        .addComponent(btn_last))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_return, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btn_return, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_last)
                    .addComponent(btn_next)
                    .addComponent(btn_back)
                    .addComponent(btn_first))
                .addGap(11, 11, 11))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 830, 430));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ảnh nền.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 0, 870, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_bookstoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bookstoreActionPerformed
        new BookView(userCode).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_bookstoreActionPerformed

    private void btn_profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_profileActionPerformed
        new ProfileView(userCode).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_profileActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn đăng xuất không?",
                "Xác nhận đăng xuất",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            new LoginView().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menuActionPerformed
        new UserView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_menuActionPerformed

    private void btn_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_returnActionPerformed
        int selectedRow = display_borrow.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để trả sách.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) display_borrow.getModel();
        Object bookTitleObj = model.getValueAt(selectedRow, 1);
        Object statusObj = model.getValueAt(selectedRow, 5);

        if (bookTitleObj == null || statusObj == null) {
            JOptionPane.showMessageDialog(this, "Thông tin sách không hợp lệ.");
            return;
        }

        String bookTitle = bookTitleObj.toString();
        String status = statusObj.toString();

        if (status.equals("Đã trả")) {
            JOptionPane.showMessageDialog(this, "Cuốn sách này đã được trả.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn trả sách \"" + bookTitle + "\"?",
                "Xác nhận trả sách",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            BorrowDetailController controller = new BorrowDetailController();
            boolean success = controller.returnBookByTitle(userCode, bookTitle);

            if (success) {
                JOptionPane.showMessageDialog(this, "Trả sách thành công.");
                loadBorrowedBooks();
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi trả sách.");
            }
        }
    }//GEN-LAST:event_btn_returnActionPerformed

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
            java.util.logging.Logger.getLogger(ReturnBookView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBookView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBookView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBookView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBookView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_bookstore;
    private javax.swing.JButton btn_dntBook;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_menu;
    private javax.swing.JButton btn_muon_tra_sach;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_profile;
    private javax.swing.JButton btn_return;
    private javax.swing.JTable display_borrow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
