import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class SubjectViewerApp extends JFrame {
    private ArrayList<SubjectInterface> database;
    private JPanel mainPanel;

    public SubjectViewerApp() {
        setTitle("LW 7");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        database = new ArrayList<SubjectInterface>();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        createMenuBar();
        createLookAndFeelSelector();
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // JMenu loadMenu = new JMenu("Load data");
        JMenuItem loadItem = new JMenuItem("Load data from File");
        JMenuItem genItem = new JMenuItem("Generate data");

        loadItem.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                try (FileReader inputReader = new FileReader(selectedFile)) {
                    database.clear();
                    int len = inputReader.read() - '0';
                    inputReader.read();
                    for (int i = 0; i < len; i++) {
                        SubjectInterface subject = SubjectIO.read(inputReader);
                        database.add(subject);
                    }
                    displayDatabase();
                    showMessage("Database loaded from file");
                } catch (Exception err) {
                    err.printStackTrace();
                    showMessage("Error loading from file");
                }
            }
        });

        genItem.addActionListener(e -> {
            database.clear();
            for (int i = 0; i < 10; i++) {
                database.add(SubjectIO.createInstance());
            }
            displayDatabase();
            showMessage("Database generated");
        });

        // loadMenu.add(loadItem);
        // loadMenu.add(genItem);
        // menuBar.add(loadMenu);
        menuBar.add(loadItem);
        menuBar.add(genItem);
        setJMenuBar(menuBar);
    }

    private void displayDatabase() {
        mainPanel.removeAll();
        for (int i = 0; i < database.size(); i++) {
            SubjectInterface subject = database.get(i);
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEtchedBorder());
            panel.setPreferredSize(new Dimension(200, 50));
            String message = "Дисциплина #" + Integer.toString(i + 1) + '\n' + subject.toString();
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showMessage(message);
                }
            });

            JLabel label = new JLabel("Дисциплина #" + (i + 1) + " - " + subject.getName());
            panel.add(label);
            mainPanel.add(panel);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void createLookAndFeelSelector() {
        JPanel lookAndFeelPanel = new JPanel();
        lookAndFeelPanel.setBorder(BorderFactory.createTitledBorder("Выберите тему оформления:"));

        UIManager.LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
        ButtonGroup group = new ButtonGroup();

        for (UIManager.LookAndFeelInfo laf : lookAndFeels) {
            JRadioButton radioButton = new JRadioButton(laf.getName());
            radioButton.addActionListener(new LookAndFeelListener(laf.getClassName()));
            group.add(radioButton);
            lookAndFeelPanel.add(radioButton);
        }

        getContentPane().add(lookAndFeelPanel, BorderLayout.NORTH);
    }

    private class LookAndFeelListener implements ActionListener {
        private String className;

        public LookAndFeelListener(String className) {
            this.className = className;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(SubjectViewerApp.this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}