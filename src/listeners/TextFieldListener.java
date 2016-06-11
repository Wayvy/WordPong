package listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * A Listener, which sets a default Text to a TextField, when its empty not not
 * focused
 * 
 * @author Wavy
 * @version 1.00
 */
public class TextFieldListener implements FocusListener {

	String defaultText;
	JTextField textField;

	/**
	 * Constructs the Attributes, the Object need
	 * 
	 * @param defaultText
	 *            - The String, to which the textField will jump if it is in its
	 *            default state
	 * @param textField
	 *            - The textField
	 */
	public TextFieldListener(String defaultText, JTextField textField) {
		this.defaultText = defaultText;
		this.textField = textField;
	}

	/**
	 * Empties the textField, when the textField was in default state
	 */
	public void focusGained(FocusEvent e) {
		if (textField.getText().equals(defaultText))
			textField.setText("");
	}

	/**
	 * When the textField is empty and the focus is lost, the textField jumps in
	 * its default state
	 */
	public void focusLost(FocusEvent e) {
		if (textField.getText().equals("")) {
			textField.setText(defaultText);
		}
	}

}
