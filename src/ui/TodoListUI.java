package ui;

import Exceptions.ItemNotFound;
import Exceptions.TooManyItems;
import Model.Item;
import Model.ItemList;
import com.sun.deploy.panel.JavaPanel;
import com.sun.org.apache.regexp.internal.RE;
import ui.Tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoListUI extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;
    public ItemList itemList;
    private JTextArea textArea;
    private JPanel textPanel;
    private JPanel toolArea;
    public Font textFieldFont = new Font("Helvetica",Font.PLAIN,14);
    private List<Tool> tools = new ArrayList<>();
    private JLabel name;
    private AddItemNameField addNameField;
    private JComboBox itemType;
    private String SELECT = "--Select--";
    public String REGULAR = "Regular";
    public String URGENT = "Urgent";
    private String[] types = {SELECT, REGULAR, URGENT};
    private JLabel dueDate;
    private AddItemDateField dateField;
    private CompleteItemNameField completeNameField;
    public AddTool add;
    public CompleteTool complete;

    // EFFECTS: creates the window for the to do list
    public TodoListUI() throws IOException {
        super("To-do List");
        itemList = new ItemList();
        loadList(itemList);
        initializeGraphics();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    itemList.save(itemList);
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window for the to do list, and makes the tools that will work on the to do list
    private void initializeGraphics() throws IOException {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        createTextPanel();
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the area where all messages will be printed to,
    // has a title taken from the website and begins with current to do Items
    private void createTextPanel() {
        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(textFieldFont);
        textPanel.setPreferredSize(new Dimension(WIDTH*2/3, HEIGHT));
        Color c = new Color(130,177,230);
        textPanel.setBackground(c);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        setPreferredSize(new Dimension(WIDTH*2/3, HEIGHT));

        try {
            editorPane.setPage("https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html");
        } catch (IOException e) {
            System.out.println("exception caught");
        }

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(WIDTH*2/3-10, HEIGHT-100));

        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        viewCurrent(itemList);

        textPanel.add(editorPane);
        textPanel.add(scroll);

        add(textPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: sets the text in the text area to the given text
    public void setText(String txt) {
        textArea.setText(txt);
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all the tool buttons and add/complete areas on the RH-side of the window
    private void createTools() {
        Container toolContainer = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
        toolContainer.add(toolArea, BorderLayout.EAST);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        JPanel addPanel = new JPanel();
        createAddPanel(addPanel);
        toolArea.add(addPanel, gc);

        gc.gridy = 2;
        JPanel completePanel = new JPanel();
        createCompletePanel(completePanel);
        toolArea.add(completePanel, gc);

        gc.gridy = 3;
        ViewTool viewCurrent = new ViewCurrentTool(this, toolArea, gc);
        tools.add(viewCurrent);

        gc.anchor = GridBagConstraints.BASELINE;
        gc.gridy = 4;
        ViewTool viewComplete = new ViewCompleteTool(this, toolArea, gc);
        tools.add(viewComplete);
    }

    // MODIFIES: this
    // EFFECTS: constructs the area where users can create the item to be added
    private void createAddPanel(JPanel addPanel) {
        addPanel.setLayout(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Add an Item:"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints addPanelConstraints = new GridBagConstraints();
        addPanelConstraints.weightx = 0.5;
        addPanelConstraints.weighty = 0.5;
        addPanelConstraints.insets = new Insets(0,0,10,0);

        name = new JLabel("Name: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 0;
        addPanel.add(name, addPanelConstraints);

        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        addNameField = new AddItemNameField(this, addPanel, addPanelConstraints);

        itemType = new JComboBox(types);
        itemType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox typeBox = (JComboBox)e.getSource();
                String selected = (String)typeBox.getSelectedItem();
                if (selected.equals(REGULAR)) {
                    dueDate.setVisible(true);
                    dateField.setVisible(true);
                    add.setEnabled(false);
                }
                else if (selected.equals(URGENT)) {
                    dueDate.setVisible(false);
                    dateField.setVisible(false);
                    if (addNameField.getFieldText().isEmpty()) {
                        add.setEnabled(false);
                    } else add.setEnabled(true);

                }
                else if (selected.equals(SELECT)) {
                    dueDate.setVisible(false);
                    dateField.setVisible(false);
                    add.setEnabled(false);
                }
            }
        });
        addPanelConstraints.anchor = GridBagConstraints.BASELINE;
        addPanelConstraints.gridy = 1;
        addPanel.add(itemType, addPanelConstraints);

        dueDate = new JLabel("DueDate: ");
        dueDate.setVisible(false);
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 2;
        addPanel.add(dueDate, addPanelConstraints);


        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        dateField = new AddItemDateField(this, addPanel, addPanelConstraints);
        dateField.setVisible(false);

        addPanelConstraints.gridy = 3;
        add = new AddTool(this, addPanel, addPanelConstraints);

    }

    // MODIFIES: this
    // EFFECTS: constructs the area where the user can input the item they want to be completed
    private void createCompletePanel(JPanel completePanel) {
        completePanel.setLayout(new GridBagLayout());
        completePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Complete an Item:"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints completePanelConstraints = new GridBagConstraints();
        completePanelConstraints.weightx = 0.5;
        completePanelConstraints.weighty = 0.5;
        completePanelConstraints.insets = new Insets(0,0,10,0);

        name = new JLabel("Name: ");
        completePanelConstraints.anchor = GridBagConstraints.LINE_END;
        completePanelConstraints.gridx = 0;
        completePanelConstraints.gridy = 0;
        completePanel.add(name, completePanelConstraints);

        completePanelConstraints.anchor = GridBagConstraints.LINE_START;
        completePanelConstraints.gridx = 1;
        completeNameField = new CompleteItemNameField(this, completePanel, completePanelConstraints);

        completePanelConstraints.gridy = 1;
        complete = new CompleteTool(this, completePanel, completePanelConstraints);

    }

    // MODIFIES: this
    // EFFECTS: adds the given item to the list
    public void addItem(Item i){
        try {
            itemList.addItem(i);
            setText("The following item has been added to the list: " +itemList.printItem(i));
        } catch (TooManyItems tooManyItems) {
            setText("There are too many items on the list. Please complete something.");
        }
    }

    // MODIFIES: this
    // EFFECTS: completes the item with the given name
    public void completeItem(String name) {
        try {
            itemList.completeItem(name);
            setText("The following item has been completed: " +itemList.printCompletedItem(name));
        } catch (ItemNotFound itemNotFound) {
            setText("Item could not be found. Please try again.");
        }
    }

    // EFFECTS: prints the current to do list items
    public void viewCurrent(ItemList itemList) {
        String currentItems = "Current items to do: " +itemList.printCurrentItems();
        setText(currentItems);
    }

    // EFFECTS: prints the completed items
    public void viewComplete(ItemList itemList) {
        String completedItems = "Completed items: " +itemList.printCompletedItems();
        setText(completedItems);
    }

    // MODIFIES: this
    // EFFECTS: loads the to do list
    private void loadList(ItemList todoList) throws IOException{
        todoList.load(todoList);
    }

    // EFFECTS: gets the text in the name field in the add area
    public String getAddName() {
        return addNameField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the name field in the add area to empty
    public void setAddNameField() {
        addNameField.setEmpty();
    }

    // EFFECTS: gets the text in the name field in the complete area
    public String getCompleteName() {
        return completeNameField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the name field in the complete area to empty
    public void setCompleteNameField() {
        completeNameField.setEmpty();
    }

    // EFFECTS: gets the item type the combo box is set on
    public String getSelected() {
        return (String)itemType.getSelectedItem();
    }

    // MODIFIES: this
    // EFFECTS: resets the combobox value
    public void setItemType() {
        itemType.setSelectedIndex(0);
    }

    // EFFECTS: gets the text in the date field in the add area
    public String getDueDate() {
        return dateField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the date field in the add area to empty
    public void setDateField() {
        dateField.setEmpty();
    }

    public static void main(String[] args) throws IOException {
        new TodoListUI();
    }
}
