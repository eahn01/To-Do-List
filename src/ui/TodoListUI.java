package ui;

import Model.ItemList;
import ui.Tools.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class TodoListUI extends JFrame {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 800;
    public ItemList itemList = new ItemList();
    public JTextArea textArea = new JTextArea();
    private JPanel textPanel = new JPanel();

    private List<Tool> tools = new ArrayList<>();

    // EFFECTS: creates the window for the to do list
    public TodoListUI() throws IOException {
        super("To-do List");
        loadList(itemList);
        initializeGraphics();
    }

    private void initializeInteraction() {

    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window for the to do list, and makes the tools that will work on the to do list
    private void initializeGraphics() throws IOException {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        viewCurrent(itemList);
        createTextPanel();
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTextPanel() throws MalformedURLException {
        textPanel.setPreferredSize(new Dimension(WIDTH*2/3, HEIGHT));
        textPanel.setBackground(Color.WHITE);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        setPreferredSize(new Dimension(WIDTH*2/3, HEIGHT));

        try {
            editorPane.setPage("https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html");
        } catch (IOException e) {
            System.out.println("exception caught");
        }

        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(WIDTH*2/3, HEIGHT));
        textArea.setBackground(Color.WHITE);
        String txt = "\nPlease choose an option: ";
        textArea.append(txt);

        textPanel.add(editorPane);
        textPanel.add(textArea);

        add(textPanel, BorderLayout.WEST);
    }

    public void setTextArea(String txt) {
        textArea.append(txt);
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all the tool buttons on the RH-side of the window
    private void createTools() {
        Container toolContainer = getContentPane();
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
        toolArea.setBackground(Color.LIGHT_GRAY);
        toolContainer.add(toolArea, BorderLayout.EAST);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 5;

        gc.anchor = GridBagConstraints.LINE_END;
        JTextField textField = new JTextField(10);
        Font textFieldFont = new Font("Helvetica",Font.PLAIN,14);
        textField.setFont(textFieldFont);
        gc.gridx = 0;
        gc.gridy = 0;
        toolArea.add(textField, gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        EnterTool Enter = new EnterTool(this, toolArea, gc);
        tools.add(Enter);

        gc.weighty = 0.5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        AddTool add = new AddTool(this, toolArea, gc);
        tools.add(add);

        gc.gridy = 2;
        CompleteTool complete = new CompleteTool(this, toolArea, gc);
        tools.add(complete);

        gc.gridy = 3;
        ViewTool viewCurrent = new ViewCurrentTool(this, toolArea, gc);
        tools.add(viewCurrent);

        gc.weighty = 15;
        gc.gridy = 4;
        ViewTool viewComplete = new ViewCompleteTool(this, toolArea, gc);
        tools.add(viewComplete);
    }

    public String viewCurrent(ItemList todoList) {
        return todoList.printCurrentItems();
    }

    private void loadList(ItemList todoList) throws IOException{
        todoList.load(todoList);
    }

    public static void main(String[] args) throws IOException {
        new TodoListUI();
//        ToDoList toDoList = new ToDoList();
//        toDoList.run();
    }
}
