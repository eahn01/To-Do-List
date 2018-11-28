package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public abstract class ViewTool extends Tool {

    public ViewTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    protected void createButton() {
        button = new JButton(getLabel());
    }

    protected abstract String getLabel();
}
