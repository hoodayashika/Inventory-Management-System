import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InventoryManagementSystem extends JFrame {
    private List<InventoryItem> inventory;
    private DefaultTableModel tableModel;

    public InventoryManagementSystem() {
        inventory = new ArrayList<>();

        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table to display inventory items
        String[] columnNames = {"ID", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField priceField = new JTextField();
        JButton addButton = new JButton("Add Item");
        JButton updateButton = new JButton("Update Item");
        JButton deleteButton = new JButton("Delete Item");

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.NORTH);

        // Add item action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                InventoryItem item = new InventoryItem(id, name, quantity, price);
                inventory.add(item);
                refreshTable();
            }
        });

        // Update item action
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                for (InventoryItem item : inventory) {
                    if (item.getId().equals(id)) {
                        item.setName(name);
                        item.setQuantity(quantity);
                        item.setPrice(price);
                        break;
                    }
                }
                refreshTable();
            }
        });

        // Delete item action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                inventory.removeIf(item -> item.getId().equals(id));
                refreshTable();
            }
        });

        // Initial data
        inventory.add(new InventoryItem("1", "Item1", 10, 100.0));
        inventory.add(new InventoryItem("2", "Item2", 20, 200.0));
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (InventoryItem item : inventory) {
            Object[] row = {item.getId(), item.getName(), item.getQuantity(), item.getPrice()};
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventoryManagementSystem().setVisible(true);
            }
        });
    }
}

class InventoryItem {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public InventoryItem(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Quantity: %d, Price: %.2f", id, name, quantity, price);
    }
}

//Compile using: javac InventoryManagementSystem.java
//Run using: java InventoryManagementSystem