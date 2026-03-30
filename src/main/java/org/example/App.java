package org.example;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class App extends JFrame {

    private JCheckBox sauceBox;
    private JCheckBox venisonBox;
    private JCheckBox berryBox;
    private JCheckBox sconeBox;

    private JLabel descriptionLabel;
    private JLabel totalLabel;
    private DefaultListModel<String> historyModel;

    private List<JCheckBox> allBoxes;

    public App() {
        setTitle("Руническая скрижаль");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        sauceBox   = new JCheckBox("Огненный соус     (+10 септимов)");
        venisonBox = new JCheckBox("Двойная оленина   (+20 септимов)");
        berryBox   = new JCheckBox("Снежные ягоды     (+6 септимов)");
        sconeBox   = new JCheckBox("Нордская лепёшка  (+7 септимов)");

        allBoxes = List.of(sauceBox, venisonBox, berryBox, sconeBox);

        historyModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>(historyModel);
        historyList.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setPreferredSize(new Dimension(300, 0));
        scrollPane.setBorder(new TitledBorder("Свиток заказов"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new TitledBorder("Добавки (не более 3 из 4)"));
        rightPanel.setPreferredSize(new Dimension(270, 0));

        JLabel baseLabel = new JLabel("Нордское рагу — 50 септимов");
        baseLabel.setFont(new Font("Arial", Font.BOLD, 13));
        baseLabel.setBorder(new EmptyBorder(8, 8, 12, 8));
        rightPanel.add(baseLabel);

        for (JCheckBox cb : allBoxes) {
            cb.setFont(new Font("Arial", Font.PLAIN, 13));
            cb.setBorder(new EmptyBorder(4, 8, 4, 8));
            cb.addActionListener(e -> {
                updateOrder();
                enforceToppingLimit();
            });
            rightPanel.add(cb);
        }

        descriptionLabel = new JLabel("Нордское рагу");
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 13));

        totalLabel = new JLabel("Итого: 50 септимов");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton orderBtn = new JButton("Оформить заказ");
        orderBtn.setFont(new Font("Arial", Font.BOLD, 14));
        orderBtn.addActionListener(e -> placeOrder());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(descriptionLabel);
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(totalLabel);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        bottomPanel.setBorder(new EmptyBorder(8, 12, 12, 12));
        bottomPanel.add(infoPanel, BorderLayout.CENTER);
        bottomPanel.add(orderBtn, BorderLayout.EAST);

        add(scrollPane, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setSize(650, 380);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void enforceToppingLimit() {
        long selected = allBoxes.stream().filter(JCheckBox::isSelected).count();
        for (JCheckBox cb : allBoxes) {
            if (!cb.isSelected()) {
                cb.setEnabled(selected < 3);
            }
        }
    }

    private void updateOrder() {
        Dish dish = buildDish();
        descriptionLabel.setText(dish.getDescription());
        totalLabel.setText("Итого: " + dish.getPrice() + " септимов");
    }

    private Dish buildDish() {
        Dish dish = new Ragu();
        if (sauceBox.isSelected())   dish = new Sauce(dish);
        if (venisonBox.isSelected()) dish = new Venison(dish);
        if (berryBox.isSelected())   dish = new Berry(dish);
        if (sconeBox.isSelected())   dish = new Scone(dish);
        return dish;
    }

    private void placeOrder() {
        Dish dish = buildDish();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        historyModel.addElement("[" + time + "]  " + dish.getDescription() + "  —  " + dish.getPrice() + " септимов");

        for (JCheckBox cb : allBoxes) {
            cb.setSelected(false);
            cb.setEnabled(true);
        }
        updateOrder();

        JOptionPane.showMessageDialog(this,
                dish.getDescription() + "\nСумма: " + dish.getPrice() + " септимов",
                "Заказ принят!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}