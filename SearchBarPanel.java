// File: SearchBarPanel.java
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBarPanel extends JPanel {
    private JLabel applicationTitle, priceValue;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> categoryDropdown;
    private JSlider priceRange;
    
    public SearchBarPanel() {
        setupPanel();
        addComponents();
    }
    
    private void setupPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Application.PANEL_BG);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
    
    private void addComponents() {
        // North section - Title and search bar
        JPanel northPanel = new JPanel(new BorderLayout(15, 0));
        northPanel.setBackground(Application.PANEL_BG);
        
        // Application title
        applicationTitle = new JLabel("BookShelf");
        applicationTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        applicationTitle.setForeground(Application.TEXT_COLOR);
        
        // Search components panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(Application.PANEL_BG);
        
        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Application.ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchButton.setBackground(Application.ACCENT_COLOR);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        northPanel.add(applicationTitle, BorderLayout.WEST);
        northPanel.add(searchPanel, BorderLayout.EAST);
        
        // Center section - Filter options
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        filterPanel.setBackground(Application.PANEL_BG);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        // Category dropdown
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(Application.TEXT_COLOR);
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        String[] categories = {
            "All Categories", "Fiction", "Non-Fiction", "Science", "History", 
            "Biography", "Self-Help", "Business", "Computer Science"
        };
        categoryDropdown = new JComboBox<>(categories);
        categoryDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        categoryDropdown.setPreferredSize(new Dimension(150, 30));
        
        // Price range
        JLabel priceLabel = new JLabel("Max Price:");
        priceLabel.setForeground(Application.TEXT_COLOR);
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        priceRange = new JSlider(0, 100, 50);
        priceRange.setBackground(Application.PANEL_BG);
        priceRange.setForeground(Application.TEXT_COLOR);
        priceRange.setMajorTickSpacing(25);
        priceRange.setPaintTicks(true);
        priceRange.setPreferredSize(new Dimension(150, 30));
        
        priceValue = new JLabel("$50.00");
        priceValue.setForeground(Application.TEXT_COLOR);
        priceValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        priceValue.setPreferredSize(new Dimension(60, 30));
        
        // Rating filter
        JCheckBox fourStarFilter = new JCheckBox("4★ & up");
        fourStarFilter.setForeground(Application.TEXT_COLOR);
        fourStarFilter.setBackground(Application.PANEL_BG);
        fourStarFilter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Advanced search button
        JButton advancedButton = new JButton("Advanced Search");
        advancedButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        advancedButton.setBackground(new Color(60, 60, 60));
        advancedButton.setForeground(Application.TEXT_COLOR);
        advancedButton.setFocusPainted(false);
        advancedButton.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        
        filterPanel.add(categoryLabel);
        filterPanel.add(categoryDropdown);
        filterPanel.add(priceLabel);
        filterPanel.add(priceRange);
        filterPanel.add(priceValue);
        filterPanel.add(fourStarFilter);
        filterPanel.add(Box.createHorizontalStrut(15));
        filterPanel.add(advancedButton);
        
        // Add event listeners
        priceRange.addChangeListener(e -> {
            int value = priceRange.getValue();
            priceValue.setText(String.format("$%d.00", value));
        });
        
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            String category = (String) categoryDropdown.getSelectedItem();
            int price = priceRange.getValue();
            boolean fourStarOnly = fourStarFilter.isSelected();
            
            JOptionPane.showMessageDialog(this, 
                "Search: " + searchText + "\nCategory: " + category + 
                "\nMax Price: $" + price + "\n4★ & up: " + fourStarOnly,
                "Search Criteria", JOptionPane.INFORMATION_MESSAGE);
        });
        
        advancedButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Advanced Search Options", 
                "Advanced Search", JOptionPane.INFORMATION_MESSAGE));
        
        // Add components to main panel
        add(northPanel, BorderLayout.NORTH);
        add(filterPanel, BorderLayout.CENTER);
    }
}