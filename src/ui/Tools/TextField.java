package ui.Tools;

import ui.TodoListUI;

import javax.swing.*;
import java.awt.*;

public abstract class TextField {
    TodoListUI todoList;
    JTextField textField;

    public TextField(TodoListUI todoList, JComponent parent, GridBagConstraints gc) {
        this.todoList = todoList;
        textField = new JTextField(10);
        textField.setFont(todoList.textFieldFont);
        addToParent(parent, gc);
        addListener();
    }

    private void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(textField, gc);
    }

    protected abstract void addListener();

    public String getFieldText() {
        return textField.getText();
    }

    public void setEmpty() {
        textField.setText("");
    }

}
