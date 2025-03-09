// File: Application.java
import java.awt.*;
import javax.swing.*;

public class Application extends JFrame {
    private Container cp;
    private JMenuBar accountBar;
    private MenuComponents menuComponents;
    //private SearchBarPanel searchBarPanel;
    //private BooksDisplay booksDisplay;

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 800;
    public static final Color DARK_BG = Color.darkGray;
    public static final Color PANEL_BG = Color.gray;
    public static final Color ACCENT_COLOR = Color.WHITE;
    public static final Color TEXT_COLOR = Color.black;

    public Application() {
        // Setup frame
        setTitle("BookShelf - Digital Library");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Setup container
        cp = getContentPane();
        cp.setLayout(new BorderLayout(10, 10));
        cp.setBackground(DARK_BG);
        ((JPanel)cp).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Initialize components
        menuComponents = new MenuComponents(this);
        //searchBarPanel = new SearchBarPanel();
        //booksDisplay = new BooksDisplay(this);
        
        // Set menu bar
        setJMenuBar(menuComponents.getMenuBar());
        
        // Add components to frame
        //cp.add(searchBarPanel, BorderLayout.NORTH);
        //cp.add(booksDisplay, BorderLayout.CENTER);
        
        // Add status bar
        JPanel statusBar = createStatusBar();
        cp.add(statusBar, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(PANEL_BG);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel statusLabel = new JLabel("Connected to BookShelf Library | 1000 books available");
        statusLabel.setForeground(TEXT_COLOR);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusBar.add(statusLabel, BorderLayout.WEST);
        
        JLabel versionLabel = new JLabel("v1.0.2");
        versionLabel.setForeground(TEXT_COLOR);
        versionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusBar.add(versionLabel, BorderLayout.EAST);
        
        return statusBar;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Set custom UI properties
            UIManager.put("Menu.foreground", TEXT_COLOR);
            UIManager.put("MenuItem.foreground", TEXT_COLOR);
            UIManager.put("Menu.selectionBackground", ACCENT_COLOR);
            UIManager.put("MenuItem.selectionBackground", ACCENT_COLOR);
            UIManager.put("MenuItem.selectionForeground", TEXT_COLOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(Application::new);
    }
}