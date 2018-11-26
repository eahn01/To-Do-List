package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public abstract class ViewTool extends Tool {

    public ViewTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    public void createButton(JComponent parent, GridBagConstraints gc) {
        button = new JButton(getLabel());
    }

    protected abstract String getLabel();
}
