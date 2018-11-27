package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public class ViewCompleteTool extends ViewTool {

    public ViewCompleteTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected String getLabel() {
        return "View Completed";
    }
}
