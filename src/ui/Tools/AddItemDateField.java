package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AddItemDateField extends TextField {

    public AddItemDateField(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: sets text field to visible
    public void setVisible(boolean b) {
        textField.setVisible(b);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddItemDateHandler());
    }

    private class AddItemDateHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed();
        }

        // MODIFIES: todoList
        // EFFECTS: enables the add button if the text field is not empty and is the correct type of item
        public void changed() {
            String date = textField.getText();
            String selected = todoList.getSelected();

            if (!date.isEmpty() && selected.equals(todoList.REGULAR)) {
                todoList.add.setEnabled(true);
            }
            else todoList.add.setEnabled(false);
        }
    }
}
