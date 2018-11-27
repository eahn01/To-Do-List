package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public class CompleteTool extends Tool {

    public CompleteTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    public void createButton(JComponent parent, GridBagConstraints gc) {
        button = new JButton("Complete an Item");
        addToParent(parent, gc);
    }

    @Override
    protected void addListener() {

    }
}
