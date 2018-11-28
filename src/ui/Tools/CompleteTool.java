package ui.Tools;

import Exceptions.ItemNotFound;
import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompleteTool extends Tool {

    public CompleteTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new button and disables it initially
    @Override
    public void createButton() {
        button = new JButton("Complete Item");
        button.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new CompleteToolCLickHandler());
    }

    private class CompleteToolCLickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, complete the item with the given name and clear text field
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String name = todoList.getCompleteName();
            todoList.completeItem(name);
            todoList.setCompleteNameField();
        }
    }
}
