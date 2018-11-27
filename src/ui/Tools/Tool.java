package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public abstract class Tool {
    protected JButton button;
    protected TodoListUI todoList;
    private boolean active;

    public Tool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        this.todoList = todoList;
        createButton(parent, gc);
        addToParent(parent, gc);
        active = false;
        addListener();
    }

    // EFFECTS: creates a button for the tool
    protected abstract void createButton(JComponent parent, GridBagConstraints gc);

    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS: adds the button to the parent component with the given constraints
    public void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(button, gc);
    }

}
