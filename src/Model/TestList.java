package Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class TestList extends JPanel {
    ItemList todoList = new ItemList();

    public TestList() throws IOException {
        loadList(todoList);
        setPreferredSize(new Dimension(1800/3, 800));
        setBackground(Color.WHITE);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        setPreferredSize(new Dimension(1800/3, 800));

        try {
            editorPane.setPage("https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html");
        } catch (IOException e) {
            System.out.println("exception caught");
        }

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(1800/3, 800));
        textArea.setBackground(Color.WHITE);
        String txt = "\nPlease choose an option: ";
        textArea.setText(txt);

        add(editorPane);
        add(textArea);

    }

    private void loadList(ItemList todoList) throws IOException {
        todoList.load(todoList);
    }
}
