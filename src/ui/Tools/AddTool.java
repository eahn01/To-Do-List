package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public class AddTool extends Tool {

    public AddTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    public void createButton(JComponent parent, GridBagConstraints gc) {
        button = new JButton("Add an item");
        addToParent(parent, gc);
    }
}
