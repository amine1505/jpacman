package nl.tudelft.jpacman.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A panel containing a button for every registered action.
 *
 * @author Jeroen Roosen 
 */
class ButtonPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Map of button captions to their JButton instances.
     */
    private final Map<String, JButton> buttonMap;

    /**
     * Create a new button panel with a button for every action.
     * @param buttons The map of caption - action for each button.
     * @param parent The parent frame, used to return window focus.
     */
    ButtonPanel(final Map<String, Action> buttons, final JFrame parent) {
        super();
        assert buttons != null;
        assert parent != null;

        this.buttonMap = new HashMap<>();

        for (final String caption : buttons.keySet()) {
            JButton button = new JButton(caption);
            button.addActionListener(e -> {
                buttons.get(caption).doAction();
                parent.requestFocusInWindow();
            });
            add(button);
            buttonMap.put(caption, button);
        }
    }

    /**
     * Enable or disable a button by its caption.
     * @param caption The caption of the button.
     * @param enabled True to enable, false to disable.
     */
    void setButtonEnabled(String caption, boolean enabled) {
        JButton button = buttonMap.get(caption);
        if (button != null) {
            button.setEnabled(enabled);
        }
    }
}
