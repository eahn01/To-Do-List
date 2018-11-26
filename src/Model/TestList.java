package Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class TestList extends JPanel {

    public TestList() throws MalformedURLException {
        setPreferredSize(new Dimension(1750/3, 800));
        setBackground(Color.WHITE);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        setPreferredSize(new Dimension(1750/3, 800));

        try {
            editorPane.setPage("https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html");
        } catch (IOException e) {
            System.out.println("skdjfkj");
        }
        String txt = "wel then";
        editorPane.setText(txt);

        add(editorPane);

    }
}
