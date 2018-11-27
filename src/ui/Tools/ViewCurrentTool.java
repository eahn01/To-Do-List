package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCurrentTool extends ViewTool {

    public ViewCurrentTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ViewToolClickHandler());
    }

    @Override
    protected String getLabel() {
        return "View Current";
    }

    private class ViewToolClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = todoList.viewCurrent(todoList.itemList);
            todoList.setTextArea(text);
        }
    }
}
