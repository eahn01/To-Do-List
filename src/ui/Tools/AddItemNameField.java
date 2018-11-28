package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AddItemNameField extends TextField {

    public AddItemNameField(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddItemNameHandler());
    }

    private class AddItemNameHandler implements DocumentListener {
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
        // EFFECTS: enables the add button when text field is not empty and other conditions
        private void changed() {
            String name = textField.getText();
            String selected = todoList.getSelected();

            if (!name.isEmpty() && selected.equals(todoList.URGENT)) {
                todoList.add.setEnabled(true);
            }
            else if (!name.isEmpty() && selected.equals(todoList.REGULAR) && !todoList.getDueDate().isEmpty()) {
                todoList.add.setEnabled(true);
            }
            else todoList.add.setEnabled(false);
        }
    }
}
