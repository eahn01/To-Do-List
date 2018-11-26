package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public class ViewCurrentTool extends ViewTool {

    public ViewCurrentTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    protected String getLabel() {
        return "View Current";
    }
}
