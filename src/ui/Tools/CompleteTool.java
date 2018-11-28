package ui.Tools;

import Exceptions.ItemNotFound;
import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompleteTool extends Tool {

    public CompleteTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    public void createButton() {
        button = new JButton("Complete Item");
        button.setEnabled(false);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new CompleteToolCLickHandler());
    }

    private class CompleteToolCLickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String name = todoList.getCompleteName();
            todoList.completeItem(name);
            todoList.setCompleteNameField();
        }
    }
}
