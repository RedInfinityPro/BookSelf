// File: BooksDisplay.java
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BooksDisplay extends JPanel {
    private JFrame parentFrame;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JPanel controlPanel;
    private String[] genres = {"Fiction", "Fantasy", "Mystery", "Sci-Fi", "Romance", "History", "Biography", "Self-Help"};
    private String[] authors = {"J.K. Rowling", "Stephen King", "George R.R. Martin", "J.R.R. Tolkien", 
                              "Agatha Christie", "James Patterson", "Dan Brown", "Margaret Atwood"};
    private Color[] bookColors = {
        new Color(120, 40, 140),    // Purple
        new Color(20, 80, 120),     // Navy Blue
        new Color(140, 80, 20),     // Brown
        new Color(20, 100, 80),     // Teal
        new Color(140, 30, 30),     // Dark Red
        new Color(30, 100, 30),     // Green
        new Color(100, 60, 20),     // Bronze
        new Color(60, 60, 100)      // Slate Blue
    };
    
    public BooksDisplay(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new BorderLayout(0, 10));
        setBackground(Application.DARK_BG);
        
        // Create control panel
        createControlPanel();
        
        // Create book display panel
        createBookPanel();
        
        // Add components to main panel
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void createControlPanel() {
        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(Application.PANEL_BG);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Left side - Title and book count
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftPanel.setBackground(Application.PANEL_BG);
        
        JLabel titleLabel = new JLabel("Browse Books");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Application.TEXT_COLOR);
        
        JLabel countLabel = new JLabel("1000 books available");
        countLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        countLabel.setForeground(Application.TEXT_COLOR);
        
        leftPanel.add(titleLabel);
        leftPanel.add(countLabel);
        
        // Right side - View controls
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Application.PANEL_BG);
        
        String[] viewOptions = {"Grid View", "List View", "Compact View"};
        JComboBox<String> viewSelector = new JComboBox<>(viewOptions);
        viewSelector.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        String[] sortOptions = {"Bestselling", "New Releases", "Price: Low to High", "Price: High to Low", "Avg. Customer Review"};
        JComboBox<String> sortSelector = new JComboBox<>(sortOptions);
        sortSelector.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel sortLabel = new JLabel("Sort by:");
        sortLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sortLabel.setForeground(Application.TEXT_COLOR);
        
        rightPanel.add(viewSelector);
        rightPanel.add(sortLabel);
        rightPanel.add(sortSelector);
        
        controlPanel.add(leftPanel, BorderLayout.WEST);
        controlPanel.add(rightPanel, BorderLayout.EAST);
    }
    
    private void createBookPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Application.DARK_BG);
        buttonPanel.setLayout(new GridLayout(0, 5, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Random random = new Random();
        
        for (int i = 1; i <= 100; i++) {
            // Generate random book attributes
            String genre = genres[random.nextInt(genres.length)];
            String author = authors[random.nextInt(authors.length)];
            String title = genre + " " + (i <= 50 ? "Classic" : "Adventure") + " " + i;
            double price = 4.99 + (random.nextDouble() * 25);
            String priceStr = String.format("$%.2f", price);
            String rating = (3 + random.nextInt(3)) + "★";
            Color bookColor = bookColors[random.nextInt(bookColors.length)];
            
            // Create book button
            BookButton book = new BookButton(title, author, priceStr, rating, bookColor);
            
            // Make each book button display complete information when clicked
            book.addActionListener(e -> showBookDetails(title, author, genre, priceStr, rating));
            
            buttonPanel.add(book);
        }
        
        // Wrap the button panel inside a JScrollPane
        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Customize the scroll pane look
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
    }
    
    private void showBookDetails(String title, String author, String genre, String price, String rating) {
        // Create a custom dialog for book details
        JDialog dialog = new JDialog(parentFrame, "Book Details", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setLayout(new BorderLayout());
        
        // Create a panel for details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(240, 240, 240));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Book title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Author
        JLabel authorLabel = new JLabel("by " + author);
        authorLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Genre
        JLabel genreLabel = new JLabel("Genre: " + genre);
        genreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        genreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Rating
        JLabel ratingLabel = new JLabel("Rating: " + rating);
        ratingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ratingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Price
        JLabel priceLabel = new JLabel("Price: " + price);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add all labels with spacing
        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(authorLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detailsPanel.add(genreLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        detailsPanel.add(ratingLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detailsPanel.add(priceLabel);
        detailsPanel.add(Box.createVerticalGlue());
        
        // Add buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(Application.ACCENT_COLOR);
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setFocusPainted(false);
        
        JButton closeButton = new JButton("Close");
        
        buttonPanel.add(addToCartButton);
        buttonPanel.add(closeButton);
        
        // Add actions
        closeButton.addActionListener(e -> dialog.dispose());
        addToCartButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, title + " added to your cart!");
            dialog.dispose();
        });
        
        // Add components to dialog
        dialog.add(detailsPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    // Custom ScrollBar UI class
    class CustomScrollBarUI extends Basic