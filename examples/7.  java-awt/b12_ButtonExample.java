package a7_java.awt;

import java.awt.*;
import java.awt.event.*;
import javax.accessibility.AccessibleContext;

/* Comprehensive demonstration of all Button class features   
 * Features demonstrated:  
 * - All constructors  
 * - Event management methods  
 * - State and property methods  
 * - Event processing methods  
 * - Accessibility features  */
public class b12_ButtonExample {

    private Frame mainFrame;
    private Panel buttonPanel, statusPanel, controlPanel;
    private Label statusLabel, eventLabel;
    private TextArea eventLog;
    private int clickCount = 0;

    /* Main constructor initializing the demonstration */
    public b12_ButtonExample() {
        prepareGUI();
        demonstrateButtonFeatures();
    }

    public static void main(String[] args) {
        System.out.println("Starting Button Demonstration Application...");
        EventQueue.invokeLater(() -> {
            b12_ButtonExample demo = new b12_ButtonExample();
            demo.showFrame();
        });
    }

    /* Prepares the main GUI components with a professional layout */
    private void prepareGUI() {
        // Main Frame Setup  
        mainFrame = new Frame("Advanced Button Class Demonstration");
        mainFrame.setSize(1024, 768);
        mainFrame.setLayout(new BorderLayout(10, 10));
        mainFrame.setBackground(new Color(240, 240, 240));

        // Window Listener  
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.out.println("Application Terminated");
                System.exit(0);
            }
        });

        // Header Label  
        Label headerLabel = new Label("Button Class Features & Methods Demonstration", Label.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainFrame.add(headerLabel, BorderLayout.NORTH);

        // Button Panel  
        buttonPanel = new Panel(new GridLayout(0, 2, 10, 10));
        buttonPanel.setBackground(new Color(250, 250, 250));

        // Status Panel  
        statusPanel = new Panel(new BorderLayout());
        statusLabel = new Label("Status: Ready", Label.LEFT);
        eventLabel = new Label("Event Log:", Label.LEFT);
        eventLog = new TextArea(10, 50);
        eventLog.setEditable(false);

        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusPanel.add(eventLabel, BorderLayout.CENTER);
        statusPanel.add(eventLog, BorderLayout.SOUTH);

        // Control Panel  
        controlPanel = new Panel(new BorderLayout());
        controlPanel.add(buttonPanel, BorderLayout.CENTER);
        controlPanel.add(statusPanel, BorderLayout.SOUTH);

        // Add to Main Frame  
        mainFrame.add(controlPanel, BorderLayout.CENTER);
    }

    /* Logs events to the event log text area */
    private void logEvent(String event) {
        eventLog.append(event + "\n");
        System.out.println(event);
    }

    /* Creates a custom button with specific properties */
    private Button createCustomButton(String label, Color bgColor) {
        Button button = new Button(label);
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        return button;
    }

    /* Comprehensive demonstration of all Button class features */
    private void demonstrateButtonFeatures() {
        logEvent("Initializing Button demonstrations...\n");

        // Constructor demonstrations  
        demonstrateConstructors();

        // Event management demonstrations  
        demonstrateEventManagement();

        // State and property demonstrations  
        demonstrateStateAndProperties();

        // Event processing demonstrations  
        demonstrateEventProcessing();

        // Accessibility demonstrations  
        demonstrateAccessibility();

        logEvent("\nAll button features have been initialized.");
    }

    /* Demonstrates Button constructors   
     * Methods demonstrated: Constructor 1 and 2 */
    private void demonstrateConstructors() {
        logEvent("CONSTRUCTOR DEMONSTRATION");

        // Constructor 1: Button()  
        Button emptyButton = new Button();
        emptyButton.setLabel("Empty Constructor");
        buttonPanel.add(emptyButton);
        logEvent("1. Created button using empty constructor");

        // Constructor 2: Button(String label)  
        Button labelButton = new Button("Labeled Constructor");
        buttonPanel.add(labelButton);
        logEvent("2. Created button using label constructor");

        // Add basic action listeners  
        emptyButton.addActionListener(e ->
                logEvent("Empty Constructor Button clicked"));

        labelButton.addActionListener(e ->
                logEvent("Labeled Constructor Button clicked"));
    }

    /* Demonstrates event management methods   
     * Methods demonstrated: 1-5 (Event Management Methods) */
    private void demonstrateEventManagement() {
        logEvent("\nEVENT MANAGEMENT DEMONSTRATION");

        // Create button for event management demo  
        Button eventButton = createCustomButton("Event Management", new Color(200, 230, 255));
        buttonPanel.add(eventButton);

        // Method 1: addActionListener  
        ActionListener listener1 = e -> logEvent("Listener 1: Button clicked");
        ActionListener listener2 = e -> logEvent("Listener 2: Button clicked");
        eventButton.addActionListener(listener1);
        eventButton.addActionListener(listener2);
        logEvent("3. Added multiple action listeners");

        // Method 4 & 5: setActionCommand & getActionCommand  
        eventButton.setActionCommand("EVENT_BUTTON_COMMAND");
        logEvent("4. Set action command: " + eventButton.getActionCommand());

        // Method 2: removeActionListener  
        Button removeListenerButton = createCustomButton("Remove Listener", new Color(255, 220, 220));
        buttonPanel.add(removeListenerButton);
        removeListenerButton.addActionListener(e -> {
            eventButton.removeActionListener(listener2);
            logEvent("5. Removed listener2 from Event Management button");
        });

        // Method 3: getActionListeners  
        Button checkListenersButton = createCustomButton("Check Listeners", new Color(220, 255, 220));
        buttonPanel.add(checkListenersButton);
        checkListenersButton.addActionListener(e -> {
            ActionListener[] listeners = eventButton.getActionListeners();
            logEvent("Current listeners count: " + listeners.length);
        });
    }

    /* Demonstrates state and property methods   
     * Methods demonstrated: 6-9 (State and Property Methods) */
    private void demonstrateStateAndProperties() {
        logEvent("\nSTATE AND PROPERTY DEMONSTRATION");

        // Create button for state/property demo  
        Button propertyButton = createCustomButton("Properties", new Color(255, 240, 200));
        buttonPanel.add(propertyButton);

        // Methods 6 & 7: getLabel & setLabel  
        propertyButton.addActionListener(e -> {
            String currentLabel = propertyButton.getLabel();
            propertyButton.setLabel("Label Changed: " + clickCount++);
            logEvent("6-7. Label changed from '" + currentLabel +
                    "' to '" + propertyButton.getLabel() + "'");
        });

        // Method 8: getAccessibleContext  
        Button accessibilityButton = createCustomButton("Get Accessibility", new Color(240, 200, 255));
        buttonPanel.add(accessibilityButton);
        accessibilityButton.addActionListener(e -> {
            AccessibleContext ac = accessibilityButton.getAccessibleContext();
            logEvent("8. AccessibleContext: " + ac.toString());
        });

        // Method 9: Create custom button to demonstrate state information  
        class StateButton extends Button {
            private static final long serialVersionUID = 1L;

            public StateButton(String label) {
                super(label);
            }

            // Make state information publicly accessible  
            public String getStateInfo() {
                return paramString();
            }
        }

        StateButton stateButton = new StateButton("State Info");
        stateButton.setBackground(new Color(220, 220, 255));
        buttonPanel.add(stateButton);
        stateButton.addActionListener(e -> {
            StateButton btn = (StateButton) e.getSource();
            logEvent("9. Button state: " + btn.getStateInfo());
        });
    }

    /* Demonstrates event processing methods   
     * Methods demonstrated: 10-12 (Event Processing Methods) */
    private void demonstrateEventProcessing() {
        logEvent("\nEVENT PROCESSING DEMONSTRATION");

        // Create custom button extending Button to override protected methods  
        class CustomButton extends Button {
            private static final long serialVersionUID = 1L;

            public CustomButton(String label) {
                super(label);
            }

            // Method 10: processActionEvent  
            @Override
            protected void processActionEvent(ActionEvent e) {
                logEvent("10. Processing action event: " + e.toString());
                super.processActionEvent(e);
            }

            // Method 11: processEvent  
            @Override
            protected void processEvent(AWTEvent e) {
                logEvent("11. Processing AWT event: " + e.toString());
                super.processEvent(e);
            }
        }

        CustomButton customButton = new CustomButton("Process Events");
        customButton.setBackground(new Color(200, 255, 200));
        buttonPanel.add(customButton);

        // Method 12: addNotify  
        customButton.addActionListener(e -> {
            customButton.addNotify();
            logEvent("12. Added native peer via addNotify()");
        });
    }

    /* Demonstrates accessibility features */
    private void demonstrateAccessibility() {
        logEvent("\nACCESSIBILITY DEMONSTRATION");

        // Create accessible button  
        Button accessibleButton = new Button("Accessibility Demo") {
            private static final long serialVersionUID = 1L;

            @Override
            public AccessibleContext getAccessibleContext() {
                AccessibleContext ac = super.getAccessibleContext();
                ac.setAccessibleName("Demo Accessible Button");
                ac.setAccessibleDescription("This button demonstrates accessibility features");
                return ac;
            }
        };

        accessibleButton.setBackground(new Color(255, 220, 255));
        buttonPanel.add(accessibleButton);
        accessibleButton.addActionListener(e -> {
            AccessibleContext ac = accessibleButton.getAccessibleContext();
            logEvent("Accessibility Name: " + ac.getAccessibleName());
            logEvent("Accessibility Description: " + ac.getAccessibleDescription());
        });
    }

    /* Displays the main frame */
    private void showFrame() {
        mainFrame.setVisible(true);
        logEvent("GUI Application window is now visible");
    }
}
