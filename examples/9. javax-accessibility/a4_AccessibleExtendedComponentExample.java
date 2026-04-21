package a9_javax.accessibility;

import javax.accessibility.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/** * This is a fully-fledged example program that demonstrates the
 * implementation of the AccessibleExtendedComponent interface. Highlights:  
 * - Implements all methods from AccessibleExtendedComponent:  
 * - `getToolTipText()` 
 * - `getTitledBorderText()`  
 * - `getAccessibleKeyBinding()`  
 * - Practical application featuring buttons, tooltips, titled borders, and keyboard shortcuts.  
 * - Enhanced accessibility logs for real-time interaction and assistive technology support.  
 * - Educational in design and structured for maximum clarity.  
 */
public class a4_AccessibleExtendedComponentExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    /* Builds and displays the GUI for demonstrating AccessibleExtendedComponent features. */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("AccessibleExtendedComponent - Most Advanced Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Create the main user panel with a titled border  
        JPanel userPanel = new JPanel();
        TitledBorder border = new TitledBorder("Account Settings");
        userPanel.setBorder(border);
        userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add custom "Accessible Buttons"  
        AccessibleExtendedButton saveButton = new AccessibleExtendedButton("Save");
        saveButton.setToolTipText("Click to save your account settings (Shortcut: Alt + S)");
        saveButton.setMnemonic(KeyEvent.VK_S);

        AccessibleExtendedButton deleteButton = new AccessibleExtendedButton("Delete");
        deleteButton.setToolTipText("Click to delete the account permanently (Shortcut: Alt + D)");
        deleteButton.setMnemonic(KeyEvent.VK_D);

        AccessibleExtendedButton cancelButton = new AccessibleExtendedButton("Cancel");
        cancelButton.setToolTipText("Click to cancel changes and close the settings (Shortcut: Alt + C)");
        cancelButton.setMnemonic(KeyEvent.VK_C);

        // Add buttons to the main panel  
        userPanel.add(saveButton);
        userPanel.add(deleteButton);
        userPanel.add(cancelButton);

        // Log area at the bottom for detailed accessibility information  
        JTextArea logArea = new JTextArea(10, 80);
        logArea.setEditable(false);
        logArea.setBorder(BorderFactory.createTitledBorder("Accessibility Logs"));

        // Scroll pane for logging activity  
        JScrollPane logScrollPane = new JScrollPane(logArea);

        // Add action listeners to log interactions and accessibility information  
        saveButton.addActionListener(e -> logInteraction(logArea, saveButton, "Save button clicked"));
        deleteButton.addActionListener(e -> logInteraction(logArea, deleteButton, "Delete button clicked"));
        cancelButton.addActionListener(e -> logInteraction(logArea, cancelButton, "Cancel button clicked"));

        // Add components to the frame  
        frame.add(userPanel, BorderLayout.CENTER);
        frame.add(logScrollPane, BorderLayout.SOUTH);

        // Display the frame  
        frame.setVisible(true);
    }

    /* Logs interactions for a given button and displays their accessibility information.   
     * @param logArea           The log area to display information.  
     * @param component         The button implementing AccessibleExtendedComponent.  
     * @param actionDescription Description of the action performed. */
    private static void logInteraction(JTextArea logArea, AccessibleExtendedButton component, String actionDescription) {
        logArea.append(actionDescription + "\n");
        AccessibleContext accessibleContext = component.getAccessibleContext();

        if (accessibleContext instanceof AccessibleExtendedComponent) {
            // Safe cast to AccessibleExtendedComponent after instanceof check  
            AccessibleExtendedComponent accessibleComponent = (AccessibleExtendedComponent) accessibleContext;

            // Log tooltip text (from `getToolTipText()`)  
            String tooltip = accessibleComponent.getToolTipText();
            logArea.append("Tooltip: " + (tooltip != null ? tooltip : "None") + "\n");

            // Log titled border text (from `getTitledBorderText()`)  
            String titledBorderText = accessibleComponent.getTitledBorderText();
            logArea.append("Titled Border: " + (titledBorderText != null ? titledBorderText : "None") + "\n");

            // Log key bindings (from `getAccessibleKeyBinding()`)  
            AccessibleKeyBinding keyBinding = accessibleComponent.getAccessibleKeyBinding();
            if (keyBinding != null) {
                logArea.append("Key Bindings: ");
                for (int i = 0; i < keyBinding.getAccessibleKeyBindingCount(); i++) {
                    logArea.append(keyBinding.getAccessibleKeyBinding(i) +
                            (i < keyBinding.getAccessibleKeyBindingCount() - 1 ? ", " : ""));
                }
                logArea.append("\n");
            } else {
                logArea.append("Key Bindings: None\n");
            }
        } else {
            logArea.append("Component does not implement AccessibleExtendedComponent.\n");
        }
        logArea.append("----------\n");
    }

    /* A custom JButton class implementing AccessibleExtendedComponent. */
    static class AccessibleExtendedButton extends JButton {
        private static final long serialVersionUID = 1L;

        public AccessibleExtendedButton(String text) {
            super(text);
        }

        @Override
        public AccessibleContext getAccessibleContext() {
            if (accessibleContext == null) {
                accessibleContext = new AccessibleExtendedButtonContext();
            }
            return accessibleContext;
        }

        /* Custom AccessibleContext implementation for the AccessibleButton. */
        protected class AccessibleExtendedButtonContext extends AccessibleJComponent implements AccessibleExtendedComponent {
            private static final long serialVersionUID = 1L;

            /* (1) Obtain the tooltip text for the button. */
            @Override
            public String getToolTipText() {
                return AccessibleExtendedButton.this.getToolTipText();
            }

            /* (2) Obtain the text of the titled border where the button resides. */
            @Override
            public String getTitledBorderText() {
                Container parent = AccessibleExtendedButton.this.getParent();
                if (parent instanceof JComponent) {
                    Border border = ((JComponent) parent).getBorder();
                    if (border instanceof TitledBorder) {
                        return ((TitledBorder) border).getTitle();
                    }
                }
                return null; // Return null if no titled border is defined  
            }

            /* (3) Provides information about associated keyboard shortcuts (mnemonics). */
            @Override
            public Object getAccessibleKeyBinding() {
                int mnemonicKey = AccessibleExtendedButton.this.getMnemonic();
                if (mnemonicKey != 0) {
                    return new AccessibleKeyBinding() {
                        @Override
                        public int getAccessibleKeyBindingCount() {
                            return 1; // A single key binding per button  
                        }

                        @Override
                        public Object getAccessibleKeyBinding(int i) {
                            return i == 0 ? KeyEvent.getKeyText(mnemonicKey) : null;
                        }
                    };
                }
                return null; // Return null if no mnemonic is assigned  
            }
        }
    }
}
