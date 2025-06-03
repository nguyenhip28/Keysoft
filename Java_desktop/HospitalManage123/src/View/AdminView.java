package view;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame {
    public JComboBox<String> cb_choose;
    public JButton btn_choose, btn_refresh, btn_logout, btn_previous, btn_next;
    public JTextArea display_lichhen;

    public AdminView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("ADMIN SITE");

        cb_choose = new JComboBox<>();
        btn_choose = new JButton("Tiếp tục");
        btn_refresh = new JButton("Refresh");
        btn_logout = new JButton("Logout");
        btn_previous = new JButton("<");
        btn_next = new JButton(">");

        display_lichhen = new JTextArea(15, 30);
        JScrollPane scroll = new JScrollPane(display_lichhen);

        JPanel top = new JPanel();
        top.add(new JLabel("ADMIN SITE"));
        top.add(btn_logout);

        JPanel control = new JPanel();
        control.add(cb_choose);
        control.add(btn_choose);

        JPanel bottom = new JPanel();
        bottom.add(new JLabel("LỊCH HẸN"));
        bottom.add(btn_refresh);
        bottom.add(btn_previous);
        bottom.add(btn_next);

        add(top, BorderLayout.NORTH);
        add(control, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
