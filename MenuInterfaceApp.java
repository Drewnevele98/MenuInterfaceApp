package com.example.menuapp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class MenuInterfaceApp extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textBox;
    private JPanel mainPanel;
    private JMenuItem dateTimeMenuItem, saveToFileMenuItem, changeColorMenuItem, exitMenuItem;

    public MenuInterfaceApp() {
        // Window setup
        setTitle("Menu Interface App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create main panel to hold text box
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Create and add text area inside scroll pane
        textBox = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textBox);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Menu bar setup
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        // Create menu items
        dateTimeMenuItem = new JMenuItem("Show Date/Time");
        saveToFileMenuItem = new JMenuItem("Save to File");
        changeColorMenuItem = new JMenuItem("Change Green Hue");
        exitMenuItem = new JMenuItem("Exit");

        // Add action listeners
        dateTimeMenuItem.addActionListener(this);
        saveToFileMenuItem.addActionListener(this);
        changeColorMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        // Build menu
        menu.add(dateTimeMenuItem);
        menu.add(saveToFileMenuItem);
        menu.add(changeColorMenuItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Show the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == dateTimeMenuItem) {
            // Display current date/time
            LocalDateTime now = LocalDateTime.now();
            textBox.append("Date/Time: " + now.toString() + "\n");

        } else if (src == saveToFileMenuItem) {
            // Save text to file
            try (FileWriter writer = new FileWriter("log.txt", true)) {
                writer.write(textBox.getText());
                textBox.append("Saved to log.txt\n");
            } catch (IOException ex) {
                textBox.append("Error writing to file.\n");
            }

        } else if (src == changeColorMenuItem) {
            // Set random green hue background
            float hue = 0.25f + new Random().nextFloat() * 0.15f; // green hue range
            Color greenHue = Color.getHSBColor(hue, 0.7f, 0.9f);

            mainPanel.setBackground(greenHue);         // Set panel background
            textBox.setBackground(greenHue);           // Match text area background
            textBox.setForeground(Color.BLACK);        // Keep text readable
            textBox.append(String.format("Changed to green hue (hue=%.2f)\n", hue));

        } else if (src == exitMenuItem) {
            // Exit app
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuInterfaceApp::new);
    }
}
