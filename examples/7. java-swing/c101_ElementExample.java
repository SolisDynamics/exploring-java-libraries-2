package a8_javax.swing;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/* A comprehensive demonstration of the Element interface and its methods. Purpose:  
 * - This program demonstrates how to use the Element interface in Java Swing  
 * to access and manipulate the structure and attributes of styled text components.  
 * - All methods described in the specification are implemented and explained. */
public class c101_ElementExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(c101_ElementExample::createAndShowGUI);
    }

    /* Creates and displays the GUI for demonstrating the Element interface. */
    private static void createAndShowGUI() {
        // Step 1: Create the JFrame  
        JFrame frame = new JFrame("Element Interface Demonstration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        // Step 2: Create a JTextPane and retrieve its styled document  
        JTextPane textPane = new JTextPane();
        textPane.setText("This is an example document.\nNew paragraph starts here.\nAnd another paragraph.");
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
        StyledDocument styledDocument = textPane.getStyledDocument();

        // Step 3: Logging/Text Output Area  
        JTextArea logArea = new JTextArea(15, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("Logs"));

        // Step 4: Add buttons to demonstrate each Element method  
        JPanel controlPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Method #1: getAttributes() - Fetch the attributes of an element  
        JButton attributesButton = new JButton("Get Attributes");
        attributesButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement(); // Access root  
            AttributeSet attributes = rootElement.getAttributes(); // Fetch attributes  
            logArea.append("[Method #1] Attributes: " + attributes + "\n");
        });

        // Method #2: getDocument() - Fetch the document associated with the element  
        JButton documentButton = new JButton("Get Document");
        documentButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            Document doc = rootElement.getDocument(); // Get document  
            logArea.append("[Method #2] Associated Document: " + doc + "\n");
        });

        // Method #3: getElement(int index) - Fetch a child element by index  
        JButton getElementButton = new JButton("Get Child Element");
        getElementButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            if (rootElement.getElementCount() > 0) {
                Element child = rootElement.getElement(0); // Access first child element  
                logArea.append("[Method #3] First Child: " + child + "\n");
            } else {
                logArea.append("[Method #3] No child elements found.\n");
            }
        });

        // Method #4: getElementCount() - Get the number of child elements  
        JButton elementCountButton = new JButton("Get Element Count");
        elementCountButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            int elementCount = rootElement.getElementCount(); // Get number of child elements  
            logArea.append("[Method #4] Number of Child Elements: " + elementCount + "\n");
        });

        // Method #5: getElementIndex(int offset) - Get child element index by offset  
        JButton elementIndexButton = new JButton("Get Element Index");
        elementIndexButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            int index = rootElement.getElementIndex(15); // Use offset of 15  
            logArea.append("[Method #5] Element Index at Offset 15: " + index + "\n");
        });

        // Method #6: getEndOffset() - Get the end offset of an element  
        JButton endOffsetButton = new JButton("Get End Offset");
        endOffsetButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            int endOffset = rootElement.getEndOffset(); // Get end offset  
            logArea.append("[Method #6] End Offset: " + endOffset + "\n");
        });

        // Method #7: getStartOffset() - Get the start offset of an element  
        JButton startOffsetButton = new JButton("Get Start Offset");
        startOffsetButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            int startOffset = rootElement.getStartOffset(); // Get start offset  
            logArea.append("[Method #7] Start Offset: " + startOffset + "\n");
        });

        // Method #8: getParentElement() - Get the parent of an element  
        JButton parentElementButton = new JButton("Get Parent Element");
        parentElementButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            Element child = rootElement.getElement(0); // Access a child element  
            Element parent = child.getParentElement(); // Get parent  
            logArea.append("[Method #8] Parent Element: " + parent + "\n");
        });

        // Method #9: isLeaf() - Check if an element is a leaf  
        JButton isLeafButton = new JButton("Is Leaf Element");
        isLeafButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            boolean isLeaf = rootElement.isLeaf(); // Check if root is a leaf  
            logArea.append("[Method #9] Is Root Element a Leaf? " + isLeaf + "\n");
        });

        // Method #10: getName() - Get the name of the element  
        JButton getNameButton = new JButton("Get Element Name");
        getNameButton.addActionListener(e -> {
            Element rootElement = styledDocument.getDefaultRootElement();
            String name = rootElement.getName(); // Get element name  
            logArea.append("[Method #10] Element Name: " + name + "\n");
        });

        // Add all buttons to the control panel  
        controlPanel.add(attributesButton);
        controlPanel.add(documentButton);
        controlPanel.add(getElementButton);
        controlPanel.add(elementCountButton);
        controlPanel.add(elementIndexButton);
        controlPanel.add(endOffsetButton);
        controlPanel.add(startOffsetButton);
        controlPanel.add(parentElementButton);
        controlPanel.add(isLeafButton);
        controlPanel.add(getNameButton);

        // Step 5: Add components to the JFrame  
        frame.add(new JScrollPane(textPane), BorderLayout.CENTER); // Center: Main TextPane  
        frame.add(logScrollPane, BorderLayout.SOUTH);              // Bottom: Log Area  
        frame.add(controlPanel, BorderLayout.NORTH);               // Top: Buttons  

        // Step 6: Display the JFrame  
        frame.setVisible(true);
    }
}
