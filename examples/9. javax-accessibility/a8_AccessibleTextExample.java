package a9_javax.accessibility;

import javax.accessibility.AccessibleText;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class a8_AccessibleTextExample extends JFrame implements AccessibleText {

    // Comprehensive text content  
    private final String fullText =
            "Accessibility is crucial in modern software design.\n" +
            "This comprehensive example demonstrates the power of the AccessibleText interface.\n" +
            "Developers can create more inclusive applications by understanding and implementing\n" +
            "advanced text accessibility features. The goal is to ensure equal access to information\n" +
            "for users with different abilities.\n";

    // UI components  
    private JTextPane textPane;
    private JTextArea analysisArea;
    private JPanel controlPanel;

    // Constants for text part types  
    private static final int CHARACTER_MODE = 0;
    private static final int WORD_MODE = 1;
    private static final int SENTENCE_MODE = 2;

    // Text processing utilities  
    private List<String> wordTokens;
    private List<String> sentenceTokens;

    public a8_AccessibleTextExample() {
        initializeComponents();
        configureLayout();
        setupEventListeners();
        preprocessText();
    }

    private void preprocessText() {
        // Tokenizing the text into words and sentences  
        wordTokens = Arrays.stream(fullText.split("\\s+"))
                .collect(Collectors.toList());

        sentenceTokens = Arrays.stream(fullText.split("\\."))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private void initializeComponents() {
        setTitle("AccessibleText Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Text pane for displaying and editing text  
        textPane = new JTextPane();
        textPane.setText(fullText);
        textPane.setEditable(true);
        textPane.setFont(new Font("Arial", Font.PLAIN, 16));

        // Analysis area for displaying information  
        analysisArea = new JTextArea();
        analysisArea.setEditable(false);
        analysisArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Control panel with buttons  
        controlPanel = createControlPanel();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));

        // Button names and their corresponding actions  
        String[] methodNames = {
                "Get After Index",
                "Get At Index",
                "Get Before Index",
                "Caret Position",
                "Char Count",
                "Selected Text",
                "Selection Start",
                "Selection End",
                "Char at Point"
        };

        ActionListener[] actions = {
                e -> demonstrateAfterIndex(),
                e -> demonstrateAtIndex(),
                e -> demonstrateBeforeIndex(),
                e -> showCaretPosition(),
                e -> showCharCount(),
                e -> showSelectedText(),
                e -> showSelectionStart(),
                e -> showSelectionEnd(),
                e -> showCharAtPoint()
        };

        for (int i = 0; i < methodNames.length; i++) {
            JButton button = new JButton(methodNames[i]);
            button.addActionListener(actions[i]);
            panel.add(button);
        }
        return panel;
    }

    private void configureLayout() {
        setLayout(new BorderLayout(10, 10));
        add(new JScrollPane(textPane), BorderLayout.CENTER);
        add(new JScrollPane(analysisArea), BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        textPane.addCaretListener(e ->
                updateAnalysisArea("Current Caret Position: " + e.getDot())
        );
    }

    private void updateAnalysisArea(String message) {
        analysisArea.append(message + "\n");
    }

    // Implementing AccessibleText methods  
    @Override
    public String getAfterIndex(int part, int index) {
        switch (part) {
            case WORD_MODE:
                return wordTokens.stream()
                        .skip(index + 1)
                        .collect(Collectors.joining(" "));
            case SENTENCE_MODE:
                return sentenceTokens.stream()
                        .skip(index + 1)
                        .collect(Collectors.joining(". "));
            case CHARACTER_MODE:
                return fullText.substring(Math.min(index + 1, fullText.length()));
            default:
                return "";
        }
    }

    @Override
    public String getAtIndex(int part, int index) {
        switch (part) {
            case WORD_MODE:
                return index < wordTokens.size() ? wordTokens.get(index) : "";
            case SENTENCE_MODE:
                return index < sentenceTokens.size() ? sentenceTokens.get(index) : "";
            case CHARACTER_MODE:
                return index < fullText.length() ?
                        String.valueOf(fullText.charAt(index)) : "";
            default:
                return "";
        }
    }

    @Override
    public String getBeforeIndex(int part, int index) {
        switch (part) {
            case WORD_MODE:
                return wordTokens.stream()
                        .limit(index)
                        .collect(Collectors.joining(" "));
            case SENTENCE_MODE:
                return sentenceTokens.stream()
                        .limit(index)
                        .collect(Collectors.joining(". "));
            case CHARACTER_MODE:
                return fullText.substring(0, Math.min(index, fullText.length()));
            default:
                return "";
        }
    }

    @Override
    public int getCaretPosition() {
        return textPane.getCaretPosition();
    }

    private void showCaretPosition() {
        JOptionPane.showMessageDialog(this,
                "Caret Position: " + getCaretPosition());
    }

    @Override
    public AttributeSet getCharacterAttribute(int i) {
        return textPane.getStyledDocument().getCharacterElement(i).getAttributes();
    }

    @Override
    public Rectangle getCharacterBounds(int i) {
        try {
            return textPane.modelToView2D(i).getBounds();
        } catch (Exception e) {
            return new Rectangle();
        }
    }

    @Override
    public int getCharCount() {
        return fullText.length();
    }

    private void showCharCount() {
        JOptionPane.showMessageDialog(this,
                "Total Character Count: " + getCharCount());
    }

    @Override
    public int getIndexAtPoint(Point p) {
        return textPane.viewToModel2D(p);
    }

    private void showCharAtPoint() {
        Point point = textPane.getMousePosition();
        if (point != null) {
            int index = getIndexAtPoint(point);
            JOptionPane.showMessageDialog(this,
                    "Character at Mouse Point: " + getAtIndex(CHARACTER_MODE, index));
        }
    }

    @Override
    public String getSelectedText() {
        return textPane.getSelectedText();
    }

    private void showSelectedText() {
        JOptionPane.showMessageDialog(this,
                "Selected Text: " + getSelectedText());
    }

    @Override
    public int getSelectionEnd() {
        return textPane.getSelectionEnd();
    }

    private void showSelectionEnd() {
        JOptionPane.showMessageDialog(this,
                "Selection End Position: " + getSelectionEnd());
    }

    @Override
    public int getSelectionStart() {
        return textPane.getSelectionStart();
    }

    private void showSelectionStart() {
        JOptionPane.showMessageDialog(this,
                "Selection Start Position: " + getSelectionStart());
    }

    // Demonstration methods for each AccessibleText method  
    private void demonstrateAfterIndex() {
        String result = getAfterIndex(WORD_MODE, 2);
        JOptionPane.showMessageDialog(this,
                "Text After Word Index 2: " + result);
    }

    private void demonstrateAtIndex() {
        String result = getAtIndex(CHARACTER_MODE, 5);
        JOptionPane.showMessageDialog(this,
                "Character at Index 5: " + result);
    }

    private void demonstrateBeforeIndex() {
        String result = getBeforeIndex(SENTENCE_MODE, 0);
        JOptionPane.showMessageDialog(this,
                "Text Before Sentence Index 0: " + result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            a8_AccessibleTextExample app = new a8_AccessibleTextExample();
            app.setVisible(true);
        });
    }
}
