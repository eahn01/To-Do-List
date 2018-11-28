package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public abstract class ViewTool extends Tool {

    public ViewTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: construct a button with the given label
    @Override
    protected void createButton() {
        button = new JButton(getLabel());
    }

    // EFFECTS: returns the label for the button
    protected abstract String getLabel();
}
