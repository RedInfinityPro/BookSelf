// File: MenuComponents.java
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class MenuComponents {
    private JFrame parentFrame;
    private JMenuBar accountBar;
    private JMenu tasks, account, library, safety;
    private String filePath;

    public MenuComponents(JFrame parent) {
        this.parentFrame = parent;
        initializeMenuBar();
    }

    public MenuComponents(String PATH) {
        this.filePath = PATH;
    }

    // Method to read and return the file's contents
    public String readFile() {
        StringBuilder fileContent = new StringBuilder(); // To store all lines of the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n"); // Append each line with a newline character
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return fileContent.toString(); // Return the complete file content
    }

private void initializeMenuBar() {
        accountBar = new JMenuBar();
        accountBar.setBackground(Application.DARK_BG);
        accountBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Tasks Menu
        tasks = createMenu("Tasks", KeyEvent.VK_T);
        addMenuItem(tasks, "Sell Books", KeyEvent.VK_S, e -> showMessage("Sell Books dialog"));
        addMenuItem(tasks, "View Cart", KeyEvent.VK_C, e -> showMessage("Shopping Cart"));
        addMenuItem(tasks, "Borrow Books", KeyEvent.VK_B, e -> showMessage("Borrowing System"));
        
        // Library Menu
        library = createMenu("Library", KeyEvent.VK_L);
        addMenuItem(library, "My Library", KeyEvent.VK_M, e -> showMessage("Your Book Collection"));
        addMenuItem(library, "Borrowed Books", KeyEvent.VK_B, e -> showMessage("Books you've borrowed"));
        addMenuItem(library, "Sold Books History", KeyEvent.VK_H, e -> showMessage("Your sales history"));
        
        // Account Menu
        account = createMenu("Account", KeyEvent.VK_A);
        addMenuItem(account, "Profile", KeyEvent.VK_P, e -> showMessage("Your Profile"));
        addMenuItem(account, "Security Settings", KeyEvent.VK_S, e -> showMessage("Security Settings"));
        addMenuItem(account, "Account Details", KeyEvent.VK_D, e -> showMessage("Account Details"));
        account.addSeparator();
        addMenuItem(account, "Logout", KeyEvent.VK_L, e -> showMessage("Logging out..."));
        
        // Safety Menu
        safety = createMenu("Safety & Filters", KeyEvent.VK_F);
        addMenuItem(safety, "Banned Books", KeyEvent.VK_B, e -> showMessage("Banned Books"));
        addMenuItem(safety, "Adult Content", KeyEvent.VK_A, e -> showMessage("Adult Content Settings"));
        addMenuItem(safety, "Default View", KeyEvent.VK_D, e -> showMessage("Resetting to default view"));
        
        // Add Help menu
        JMenu helpMenu = createMenu("Help", KeyEvent.VK_H);
        MenuComponents reader = new MenuComponents("text files/about.txt");
        addMenuItem(helpMenu, "About", KeyEvent.VK_A, e -> showMessage(reader.readFile()));
        
        // Add menus to menu bar
        accountBar.add(tasks);
        accountBar.add(library);
        accountBar.add(account);
        accountBar.add(safety);
        accountBar.add(Box.createHorizontalGlue());
        accountBar.add(helpMenu);
    }
    
    private JMenu createMenu(String title, int mnemonic) {
        JMenu menu = new JMenu(title);
        menu.setMnemonic(mnemonic);
        menu.setForeground(Application.TEXT_COLOR);
        return menu;
    }
    
    private void addMenuItem(JMenu menu, String title, int mnemonic, ActionListener action) {
        JMenuItem item = new JMenuItem(title);
        item.setMnemonic(mnemonic);
        item.addActionListener(action);
        menu.add(item);
    }
    
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(parentFrame, message, "BookShelf", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public JMenuBar getMenuBar() {
        return accountBar;
    }
}