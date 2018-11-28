package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCompleteTool extends ViewTool {

    public ViewCompleteTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ViewCompleteToolClickHandler());
    }

    @Override
    protected String getLabel() {
        return "View Completed";
    }

    private class ViewCompleteToolClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            todoList.viewComplete(todoList.itemList);
        }
    }
}
