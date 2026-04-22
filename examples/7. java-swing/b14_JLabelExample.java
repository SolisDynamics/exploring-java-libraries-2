package a8_javax.swing;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.LabelUI;
import java.awt.*;

/* JLabel Example:  
 * A comprehensive demonstration implementing all constructors, fields, methods, 
 * and functionalities for JLabel. */
public class b14_JLabelExample {

    public static void main(String[] args) {
        // Master JFrame for JLabel demonstration  
        JFrame frame = new JFrame("Ultimate JLabel Comprehensive Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLayout(new BorderLayout());

        // Main JPanel to hold the examples  
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(20, 1, 10, 10)); // Enough rows for all examples  

        // Add all comprehensive examples  
        demonstrateAllConstructors(labelPanel);          // All JLabel constructors  
        demonstrateTextAndIconGap(labelPanel);            // Demonstrate text/icon gap  
        demonstrateAlignment(labelPanel);                // All alignment methods  
        demonstrateDisabledIcon(labelPanel);             // Disabled icon usage  
        demonstrateInteractiveFieldUsage(labelPanel);    // JLabel's "labelFor" field example  
        demonstrateAccessibilityDetails(labelPanel);     // Accessibility and Look and Feel details  
        demonstrateCustomGraphicsAndUpdate(labelPanel);  // Custom `update()` repaint  
        demonstrateAllIconFunctions(labelPanel);          // All icon-related methods  
        demonstrateParamStringUsage(labelPanel);          // `paramString()` method usage  

        // Scrollable content  
        JScrollPane scrollPane = new JScrollPane(labelPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // User-friendly description  
        JTextArea description = new JTextArea(
                "Welcome to the Ultimate JLabel Example Application.\n\n" +
                "This program showcases:\n" +
                "- All JLabel constructors and configurations.\n" +
                "- Accessibility, Look and Feel, alignment, and icon properties.\n" +
                "- Comprehensive coverage of JLabel's methods for custom development needs.\n\n" +
                "Each example is carefully crafted to cover theoretical and practical aspects of JLabel."
        );
        description.setEditable(false);
        description.setBorder(new TitledBorder("Description"));
        frame.add(description, BorderLayout.SOUTH);

        // Display the frame  
        frame.setLocationRelativeTo(null); // Center the frame  
        frame.setVisible(true);
    }

    /* Demonstrates all JLabel constructors. */
    private static void demonstrateAllConstructors(JPanel panel) {
        // JLabel(): Empty label  
        JLabel emptyLabel = new JLabel();
        emptyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emptyLabel.setText("1. JLabel(): Empty label with text set later.");
        emptyLabel.setHorizontalAlignment(JLabel.CENTER);
        emptyLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(emptyLabel);

        // JLabel(String text): Label with only text  
        JLabel textLabel = new JLabel("2. JLabel(String text): Label with text only");
        textLabel.setFont(new Font("Arial", Font.BOLD, 16));
        textLabel.setForeground(Color.BLUE);
        panel.add(textLabel);

        // JLabel(String text, int horizontalAlignment): Text with alignment  
        JLabel alignedLabel = new JLabel("3. JLabel(String text, int horizontalAlignment): Right-aligned text", JLabel.RIGHT);
        alignedLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        panel.add(alignedLabel);

        // JLabel(String text, Icon icon, int horizontalAlignment)  
        Icon infoIcon = UIManager.getIcon("OptionPane.informationIcon");
        JLabel textIconLabel = new JLabel("4. Text + Icon (Right-Aligned)", infoIcon, JLabel.RIGHT);
        textIconLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        textIconLabel.setBackground(Color.LIGHT_GRAY);
        textIconLabel.setOpaque(true);
        panel.add(textIconLabel);

        // JLabel(Icon image): Icon only  
        Icon warningIcon = UIManager.getIcon("OptionPane.warningIcon");
        JLabel iconOnlyLabel = new JLabel(warningIcon);
        iconOnlyLabel.setHorizontalAlignment(JLabel.CENTER);
        iconOnlyLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(iconOnlyLabel);

        // JLabel(Icon image, int horizontalAlignment): Icon with alignment  
        Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
        JLabel iconAlignLabel = new JLabel(errorIcon, JLabel.LEFT);
        panel.add(iconAlignLabel);
    }

    /* Demonstrates the `setIconTextGap` method for controlling the spacing between icons and text. */
    private static void demonstrateTextAndIconGap(JPanel panel) {
        Icon questionIcon = UIManager.getIcon("OptionPane.questionIcon");
        JLabel gapLabel = new JLabel("Icon-Text Gap Example", questionIcon, JLabel.LEFT);
        gapLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gapLabel.setIconTextGap(50); // Set custom gap between text and icon  
        gapLabel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        panel.add(gapLabel);
    }

    /* Demonstrates all alignment methods: horizontal and vertical alignments. */
    private static void demonstrateAlignment(JPanel panel) {
        JLabel alignmentLabel = new JLabel("Alignment Example");
        alignmentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        alignmentLabel.setHorizontalAlignment(JLabel.CENTER); // Horizontal alignment  
        alignmentLabel.setVerticalAlignment(JLabel.BOTTOM);   // Vertical alignment  
        alignmentLabel.setBorder(BorderFactory.createTitledBorder("Custom Alignments"));
        panel.add(alignmentLabel);
    }

    /* Demonstrates the disabled icon functionality. */
    private static void demonstrateDisabledIcon(JPanel panel) {
        Icon enabledIcon = UIManager.getIcon("OptionPane.informationIcon");
        Icon disabledIcon = UIManager.getIcon("OptionPane.errorIcon");
        JLabel disabledLabel = new JLabel("Disabled Icon Example", enabledIcon, JLabel.CENTER);
        disabledLabel.setDisabledIcon(disabledIcon); // Set the disabled version of the icon  
        disabledLabel.setEnabled(false); // Disable the label to observe the effect  
        disabledLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        disabledLabel.setBorder(BorderFactory.createDashedBorder(Color.RED));
        panel.add(disabledLabel);
    }

    /* Demonstrates the JLabel "labelFor" field with a form component. */
    private static void demonstrateInteractiveFieldUsage(JPanel panel) {
        JLabel labelFor = new JLabel("Enter your username: ");
        JTextField textField = new JTextField(20);
        labelFor.setLabelFor(textField); // Associate the label with the text field  
        JPanel formPanel = new JPanel();
        formPanel.add(labelFor);
        formPanel.add(textField);
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Field Example"));
        panel.add(formPanel);
    }

    /* Demonstrates accessibility methods and Look and Feel features. */
    private static void demonstrateAccessibilityDetails(JPanel panel) {
        JLabel accessibilityLabel = new JLabel("Accessibility Example");
        accessibilityLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Retrieve Accessibility Context  
        AccessibleContext ac = accessibilityLabel.getAccessibleContext();
        System.out.println("AccessibleContext: " + ac);

        // Look and Feel  
        LabelUI ui = accessibilityLabel.getUI();
        System.out.println("LabelUI: " + ui);
        String uiClassID = accessibilityLabel.getUIClassID();
        System.out.println("UIClassID: " + uiClassID);

        panel.add(accessibilityLabel);
    }

    /* Demonstrates custom repainting with `update(Graphics)` and custom painting. */
    @SuppressWarnings("serial")
    private static void demonstrateCustomGraphicsAndUpdate(JPanel panel) {
        JLabel customLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawString("Custom Painted Text", 25, 25);
            }
        };
        customLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        customLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(customLabel);
    }

    /* Demonstrates icon management methods. */
    private static void demonstrateAllIconFunctions(JPanel panel) {
        Icon originalIcon = UIManager.getIcon("OptionPane.informationIcon");
        JLabel iconLabel = new JLabel("Icon Management Example");
        iconLabel.setIcon(originalIcon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(iconLabel);
    }

    /* Demonstrates the usage of the `paramString()` method with a custom class. */
    private static void demonstrateParamStringUsage(JPanel panel) {
        // Create a custom UILabel that exposes the paramString() method  
        CustomJLabel paramStringLabel = new CustomJLabel("paramString() Method Example");
        paramStringLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Print paramString in terminal using the new getParamString() method  
        System.out.println(paramStringLabel.getParamString());
        panel.add(paramStringLabel);
    }

    // Create a custom JLabel subclass to expose the protected paramString() method  
    @SuppressWarnings("serial")
    private static class CustomJLabel extends JLabel {
        public CustomJLabel(String text) {
            super(text);
        }

        public String getParamString() {
            // Access the protected method  
            return paramString();
        }
    }
}
