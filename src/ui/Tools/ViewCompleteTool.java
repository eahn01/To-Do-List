package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCompleteTool extends ViewTool {

    public ViewCompleteTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new ViewCompleteToolClickHandler());
    }

    // EFFECTS: returns the string "view completed"
    @Override
    protected String getLabel() {
        return "View Completed";
    }

    private class ViewCompleteToolClickHandler implements ActionListener {

        // EFFECTS: when button pressed, print all completed items
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            todoList.viewComplete(todoList.itemList);
        }
    }
}
