// File: BookButton.java
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BookButton extends JButton {
    private String title;
    private String author;
    private String price;
    private String rating;
    private Color bookColor;
    private static final int SPINE_WIDTH = 15;
    private boolean isHovered = false;
    
    public BookButton(String title, String author, String price, String rating, Color bookColor) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.bookColor = bookColor;
        
        setPreferredSize(new Dimension(150, 200));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add mouse listeners for hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                repaint();
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Enable antialiasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        // Draw book spine
        g2d.setColor(bookColor.darker());
        g2d.fillRect(0, 0, SPINE_WIDTH, height);
        
        // Add spine details
        g2d.setColor(bookColor.darker().darker());
        g2d.fillRect(SPINE_WIDTH/3, 15, SPINE_WIDTH/3, height-30);
        
        // Draw main book cover
        g2d.setColor(bookColor);
        g2d.fillRect(SPINE_WIDTH, 0, width - SPINE_WIDTH, height);
        
        // Add shadow effect
        g2d.setColor(new Color(0, 0, 0, 40));
        g2d.fillRect(SPINE_WIDTH + 5, 5, width - SPINE_WIDTH - 10, height - 10);
        
        // Add cover pattern/design
        addCoverDesign(g2d, width, height);
        
        // Draw title
        drawCenteredString(g2d, title, new Rectangle(SPINE_WIDTH + 5, 30, width - SPINE_WIDTH - 10, height / 3),
                new Font("Segoe UI", Font.BOLD, 14), Color.WHITE);
        
        // Draw author
        drawCenteredString(g2d, "by " + author, new Rectangle(SPINE_WIDTH + 5, height/2 - 10, width - SPINE_WIDTH - 10, height / 4),
                new Font("Segoe UI", Font.ITALIC, 12), Color.WHITE);
        
        // Draw price and rating at the bottom
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 12));
        g2d.setColor(Color.WHITE);
        
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(price, SPINE_WIDTH + 10, height - 25);
        g2d.drawString(rating, width - fm.stringWidth(rating) - 10, height - 25);
        
        // Add hover effect - glow border
        if (isHovered) {
            g2d.setColor(new Color(255, 255, 200, 120));
            g2d.setStroke(new BasicStroke(3f));
            g2d.drawRect(2, 2, width - 4, height - 4);
        }
        
        g2d.dispose();
    }
    
    private void addCoverDesign(Graphics2D g2d, int width, int height) {
        // Add decorative elements to the cover
        g2d.setColor(bookColor.brighter());
        
        // Add a decorative stripe at the top
        g2d.fillRect(SPINE_WIDTH, 10, width - SPINE_WIDTH, 5);
        
        // Add a decorative rectangle/frame
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawRect(SPINE_WIDTH + 10, 25, width - SPINE_WIDTH - 20, height - 50);
    }
    
    private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect, Font font, Color color) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        
        // Break text into multiple lines if too long
        String[] words = text.split(" ");
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        
        for (String word : words) {
            if (metrics.stringWidth(currentLine + " " + word) < rect.width - 10) {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            } else {
                if (currentLine.length() > 0) {
                    lines.add(currentLine.toString());
                }
                currentLine = new StringBuilder(word);
            }
        }
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        
        g2d.setFont(font);
        g2d.setColor(color);
        
        int lineHeight = metrics.getHeight();
        int y = rect.y + (rect.height - (lines.size() * lineHeight)) / 2 + metrics.getAscent();
        
        for (String line : lines) {
            int x = rect.x + (rect.width - metrics.stringWidth(line)) / 2;
            g2d.drawString(line, x, y);
            y += lineHeight;
        }
    }
}