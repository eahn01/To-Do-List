package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CompleteItemNameField extends TextField {

    public CompleteItemNameField(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new CompleteItemNameHandler());
    }

    private class CompleteItemNameHandler implements DocumentListener {
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

        public void changed() {
            String name = todoList.getCompleteName();
            if (!name.isEmpty()) {
                todoList.complete.setEnabled(true);
            }
            else todoList.complete.setEnabled(false);
        }
    }
}
