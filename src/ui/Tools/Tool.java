package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public abstract class Tool {
    protected JButton button;
    protected TodoListUI todoList;

    public Tool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        this.todoList = todoList;
        createButton();
        addToParent(parent, gc);
        addListener();
    }

    // EFFECTS: creates a button for the tool
    protected abstract void createButton();

    // EFFECTS: adds listener to the button
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS: adds the button to the parent component with the given constraints
    public void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(button, gc);
    }

    // MODIFIES: this
    // EFFECTS: sets the button to enabled to disabled
    public void setEnabled(boolean b){
        button.setEnabled(b);
    }

}
