package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public class EnterTool extends Tool{

    public EnterTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    public void createButton(JComponent parent, GridBagConstraints gc) {
        button = new JButton("Enter");
        addToParent(parent, gc);
    }

    @Override
    protected void addListener() {

    }
}
