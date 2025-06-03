package site.UserRole;

import javax.swing.*;

public class AdminView extends JFrame {
    public JComboBox<String> cb_choose;
    public JButton btn_choose, btn_refresh, btn_logout, btn_previous, btn_next;
    public JTextArea display_lichhen;
    
    public AdminView() {
        initComponents();
    }

    private void initComponents() {
        cb_choose = new JComboBox<>();
        btn_choose = new JButton("Tiếp tục");
        btn_refresh = new JButton("Refresh");
        btn_logout = new JButton("Logout");
        btn_previous = new JButton("<");
        btn_next = new JButton(">");
        display_lichhen = new JTextArea(15, 30);
        JScrollPane scrollPane = new JScrollPane(display_lichhen);

        JLabel titleLabel = new JLabel("ADMIN SITE", SwingConstants.LEFT);
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36));

        JLabel lichhenLabel = new JLabel("LỊCH HẸN");

        JPanel topPanel = new JPanel();
        topPanel.add(titleLabel);
        topPanel.add(btn_logout);

        JPanel midPanel = new JPanel();
        midPanel.add(cb_choose);
        midPanel.add(btn_choose);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(lichhenLabel);
        bottomPanel.add(btn_refresh);
        bottomPanel.add(btn_previous);
        bottomPanel.add(btn_next);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(midPanel);
        mainPanel.add(bottomPanel);
        mainPanel.add(scrollPane);

        this.add(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
