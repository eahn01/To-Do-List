package ui.Tools;

import Model.Item;
import Model.RegularItem;
import Model.UrgentItem;
import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTool extends Tool {
    private Item item;

    public AddTool(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    @Override
    public void createButton() {
        button = new JButton("Add item");
        button.setEnabled(false);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AddToolClickHandler());
    }

    private class AddToolClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String name = todoList.getAddName();
            String selected = todoList.getSelected();
            String dueDate = todoList.getDueDate();

            if (selected.equals(todoList.URGENT)) {
                item = new UrgentItem(name);
                item.setStatus();
                item.setDueDate("");
                todoList.addItem(item);
            }
            else if (selected.equals(todoList.REGULAR)) {
                item = new RegularItem(name);
                item.setStatus();
                item.setDueDate(dueDate);
                todoList.addItem(item);
            }
            todoList.setAddNameField();
            todoList.setItemType();
            todoList.setDateField();
        }
    }
}
