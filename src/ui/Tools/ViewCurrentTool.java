package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCurrentTool extends ViewTool {

    public ViewCurrentTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new ViewCurrentToolClickHandler());
    }

    // EFFECTS: returns the string "view current"
    @Override
    protected String getLabel() {
        return "View Current";
    }

    private class ViewCurrentToolClickHandler implements ActionListener {

        // EFFECTS: when button pressed, print all current items
        @Override
        public void actionPerformed(ActionEvent e) {
           Toolkit.getDefaultToolkit().beep();
           todoList.viewCurrent(todoList.itemList);
        }
    }
}
