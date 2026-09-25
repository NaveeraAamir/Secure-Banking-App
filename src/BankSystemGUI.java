import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BankSystemGUI extends JFrame {
    private Bank oopBank;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private DefaultTableModel adminTableModel;
    private JLabel totalBankBalanceLabel;

    private JTextField adminUserField, userCnicField, searchField;
    private JPasswordField adminPassField, userPhoneField;

    public BankSystemGUI() {
        oopBank = new Bank("OOP Bank");
        FileHandler.loadData(oopBank);

        setTitle("Secure Bank App by Naveera and Afnan");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLandingPage(), "LANDING");
        mainPanel.add(createAdminLoginPage(), "ADMIN_LOGIN");
        mainPanel.add(createUserLoginPage(), "USER_LOGIN");
        mainPanel.add(createAdminDashboard(), "ADMIN_DASHBOARD");

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(160, 38));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder());
        return btn;
    }

    private JPanel createLandingPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 247, 250));
        JLabel title = new JLabel("OOP BANK");
        title.setFont(new Font("Serif", Font.BOLD, 48));
        title.setForeground(new Color(44, 62, 80));

        JButton adminBtn = createStyledButton("Admin Access", new Color(52, 73, 94));
        JButton userBtn = createStyledButton("User Access", new Color(41, 128, 185));

        adminBtn.addActionListener(e -> { clearLoginFields(); cardLayout.show(mainPanel, "ADMIN_LOGIN"); });
        userBtn.addActionListener(e -> { clearLoginFields(); cardLayout.show(mainPanel, "USER_LOGIN"); });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(0, 0, 50, 0);
        panel.add(title, gbc);
        JPanel bP = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        bP.setOpaque(false); bP.add(adminBtn); bP.add(userBtn);
        gbc.gridy = 1; panel.add(bP, gbc);
        return panel;
    }


    private JPanel createAdminLoginPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        adminUserField = new JTextField(15); adminPassField = new JPasswordField(15);
        JButton login = createStyledButton("Login", new Color(46, 204, 113));
        JButton back = createStyledButton("Back", new Color(149, 165, 166));
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Admin ID:"), gbc);
        gbc.gridx = 1; panel.add(adminUserField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; panel.add(adminPassField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(login, gbc);
        gbc.gridy = 3; panel.add(back, gbc);

        login.addActionListener(e -> {
            if (adminUserField.getText().equals("admin") && new String(adminPassField.getPassword()).equals("admin")) {
                refreshAdminTable(null); cardLayout.show(mainPanel, "ADMIN_DASHBOARD");
            } else { JOptionPane.showMessageDialog(this, "Admin Auth Failed"); }
        });
        back.addActionListener(e -> cardLayout.show(mainPanel, "LANDING"));
        return panel;
    }


    private JPanel createUserLoginPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        userCnicField = new JTextField(15); userPhoneField = new JPasswordField(15);
        JButton login = createStyledButton("Secure Login", new Color(46, 204, 113));
        JButton back = createStyledButton("Back", new Color(149, 165, 166));
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("CNIC:"), gbc);
        gbc.gridx = 1; panel.add(userCnicField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; panel.add(userPhoneField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(login, gbc);
        gbc.gridy = 3; panel.add(back, gbc);

        login.addActionListener(e -> {
            Client c = oopBank.searchCustomerDetail(userCnicField.getText());
            if (c != null && c.getPersonDetails().getPhoneNo().equals(new String(userPhoneField.getPassword()))) {
                mainPanel.add(createUserDashboard(c), "USER_DASHBOARD");
                cardLayout.show(mainPanel, "USER_DASHBOARD");
            } else { JOptionPane.showMessageDialog(this, "Invalid User Credentials"); }
        });
        back.addActionListener(e -> cardLayout.show(mainPanel, "LANDING"));
        return panel;
    }

    private JPanel createAdminDashboard() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        JPanel reg = new JPanel(new GridLayout(6, 2, 5, 12));
        reg.setBorder(BorderFactory.createTitledBorder("Registration"));
        JTextField n = new JTextField(); JTextField cn = new JTextField();
        JTextField ph = new JTextField(); JTextField am = new JTextField();
        JButton save = createStyledButton("Add Client", new Color(52, 152, 219));
        reg.add(new JLabel("Name:")); reg.add(n);
        reg.add(new JLabel("CNIC:")); reg.add(cn);
        reg.add(new JLabel("Phone:")); reg.add(ph);
        reg.add(new JLabel("Initial Deposit:")); reg.add(am);
        reg.add(new JLabel("")); reg.add(save);

        save.addActionListener(e -> {
            Person p = new Person(n.getText(), cn.getText(), ph.getText());
            Client c = oopBank.addClient(p); oopBank.addAccount(Float.parseFloat(am.getText()), c);
            FileHandler.saveAllData(oopBank); refreshAdminTable(null);
            JOptionPane.showMessageDialog(this, "Registered!");
        });


        JPanel center = new JPanel(new BorderLayout());
        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search (CNIC/Phone)");
        JButton resetBtn = new JButton("Reset");
        JButton deleteBtn = createStyledButton("Delete Selected", new Color(192, 57, 43));

        searchBar.add(new JLabel("Find:")); searchBar.add(searchField);
        searchBar.add(searchBtn); searchBar.add(resetBtn);

        adminTableModel = new DefaultTableModel(new String[]{"Acc No", "Name", "CNIC", "Phone", "PKR Balance"}, 0);
        JTable table = new JTable(adminTableModel);

        center.add(searchBar, BorderLayout.NORTH);
        center.add(new JScrollPane(table), BorderLayout.CENTER);

        totalBankBalanceLabel = new JLabel("Total Assets: PKR 0.00  ", JLabel.RIGHT);
        center.add(totalBankBalanceLabel, BorderLayout.SOUTH);

        searchBtn.addActionListener(e -> refreshAdminTable(searchField.getText()));
        resetBtn.addActionListener(e -> { searchField.setText(""); refreshAdminTable(null); });
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String idToRemove = (String) adminTableModel.getValueAt(row, 2); // Get CNIC
                Client c = oopBank.searchCustomerDetail(idToRemove);
                if (c != null) {
                    oopBank.removeClient(c.getId());
                    FileHandler.saveAllData(oopBank); refreshAdminTable(null);
                    JOptionPane.showMessageDialog(this, "Client Removed.");
                }
            }
        });

        JButton logout = createStyledButton("Logout", Color.GRAY);
        logout.addActionListener(e -> cardLayout.show(mainPanel, "LANDING"));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(deleteBtn, BorderLayout.WEST); bottom.add(logout, BorderLayout.EAST);

        panel.add(reg, BorderLayout.WEST);
        panel.add(center, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }


    private JPanel createUserDashboard(Client c) {
        JPanel panel = new JPanel(new BorderLayout(25, 25));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        Account acc = c.getAcList().get(0);

        JLabel welcome = new JLabel("Welcome, " + c.getPersonDetails().getName(), JLabel.CENTER);
        JLabel balance = new JLabel("PKR " + acc.getAmount(), JLabel.CENTER);
        balance.setFont(new Font("Monospaced", Font.BOLD, 36));

        JPanel actions = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints(); g.insets = new Insets(10,10,10,10);
        JTextField amt = new JTextField(10); JTextField targetAcc = new JTextField(10);
        JButton wd = createStyledButton("Withdraw", new Color(231, 76, 60));
        JButton dp = createStyledButton("Deposit", new Color(46, 204, 113));
        JButton tr = createStyledButton("Transfer", new Color(241, 196, 15));
        JButton hs = createStyledButton("History", new Color(52, 73, 94));

        wd.addActionListener(e -> { acc.withdraw(Float.parseFloat(amt.getText())); balance.setText("PKR " + acc.getAmount()); FileHandler.saveAllData(oopBank); });
        dp.addActionListener(e -> { acc.deposit(Float.parseFloat(amt.getText())); balance.setText("PKR " + acc.getAmount()); FileHandler.saveAllData(oopBank); });
        tr.addActionListener(e -> {
            Account target = oopBank.searchAccount(targetAcc.getText());
            float amount = Float.parseFloat(amt.getText());
            if (target != null && acc.getAmount() >= amount) {
                acc.withdraw(amount); target.deposit(amount);
                acc.getHistory().add("Transferred PKR " + amount + " to " + target.getNumber());
                target.getHistory().add("Received PKR " + amount + " from " + acc.getNumber());
                balance.setText("PKR " + acc.getAmount()); FileHandler.saveAllData(oopBank);
                JOptionPane.showMessageDialog(this, "Transfer Successful!");
            } else { JOptionPane.showMessageDialog(this, "Transfer Failed. Check balance/Acc No."); }
        });
        hs.addActionListener(e -> JOptionPane.showMessageDialog(this, String.join("\n", acc.getHistory())));

        g.gridx=0; g.gridy=0; actions.add(new JLabel("Amount:"), g); g.gridx=1; actions.add(amt, g);
        g.gridx=0; g.gridy=1; actions.add(new JLabel("Target Acc:"), g); g.gridx=1; actions.add(targetAcc, g);
        g.gridx=0; g.gridy=2; actions.add(wd, g); g.gridx=1; actions.add(dp, g);
        g.gridx=0; g.gridy=3; g.gridwidth=2; actions.add(tr, g);
        g.gridy=4; actions.add(hs, g);

        JButton logout = createStyledButton("Logout", Color.GRAY);
        logout.addActionListener(e -> cardLayout.show(mainPanel, "LANDING"));

        panel.add(welcome, BorderLayout.NORTH); panel.add(balance, BorderLayout.CENTER);
        panel.add(actions, BorderLayout.EAST); panel.add(logout, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshAdminTable(String query) {
        adminTableModel.setRowCount(0);
        float total = 0;
        for (Client c : oopBank.getClList()) {
            Person p = c.getPersonDetails();
            if (query == null || p.getCnic().contains(query) || p.getPhoneNo().contains(query)) {
                for (Account a : c.getAcList()) {
                    adminTableModel.addRow(new Object[]{a.getNumber(), p.getName(), p.getCnic(), p.getPhoneNo(), a.getAmount()});
                    total += a.getAmount();
                }
            }
        }
        totalBankBalanceLabel.setText("Total Bank Assets: PKR " + total + "  ");
    }

    private void clearLoginFields() {
        adminUserField.setText(""); adminPassField.setText("");
        userCnicField.setText(""); userPhoneField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankSystemGUI().setVisible(true));
    }
}