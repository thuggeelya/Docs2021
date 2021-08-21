package gui;
import javax.swing.*;
import javax.swing.border.LineBorder;

import system.Docs;
import system.Threads;

import java.awt.*;

public class MediatorClass implements Mediator {
    private Title1 title1;
    private Title2 title2;
    private Title3 title3;
    private Title4 title4;
    private TitleMin titleMin;
    private TitlePosMin titlePosMin;
    private TitleMax titleMax;
    private TitleDen titleDen;
    public TextBox textBox;
    private AddButton add;
    private DeleteButton del;
    private SaveButton save;
    private List list;
    private Filter filter;

    private JLabel titleLabel1 = new JLabel("Путь 1");
    private JLabel titleLabel2 = new JLabel("Путь 2");
    private JLabel titleLabel3 = new JLabel("Кол-во");
    private JLabel titleLabel4 = new JLabel("Ключ");
    public JLabel titleLabel5 = new JLabel("MIN");
    public JLabel titleLabel6 = new JLabel("MAX");
    public JLabel titleLabel7 = new JLabel("DEN");
    public JLabel titleLabel8 = new JLabel("POS");
    private JLabel titleLabel9 = new JLabel("Название");
    private JLabel textLabel = new JLabel("Результат");
    private JLabel label = new JLabel("Добавляйте новые документы");

    @Override
    public void registerComponent(Component component) {
        component.setMediator(this);
        switch (component.getName()) {
            case "AddButton":
                add = (AddButton)component;
                break;
            case "DelButton":
                del = (DeleteButton)component;
                break;
            case "Filter":
                filter = (Filter)component;
                break;
            case "List":
                list = (List)component;
                this.list.addListSelectionListener(listSelectionEvent -> {
                    Note note = (Note)list.getSelectedValue();
                    if (note != null) {
                        getInfoFromList(note);
                    } else {
                        clear();
                    }
                });
                break;
            case "SaveButton":
                save = (SaveButton)component;
                break;
            case "TextBox":
                textBox = (TextBox)component;
                break;
            case "Title1":
                title1 = (Title1)component;
                break;
            case "Title2":
                title2 = (Title2)component;
                break;
            case "Title3":
                title3 = (Title3)component;
                break;
            case "Title4":
                title4 = (Title4)component;
                break;
            case "TitleMin":
                titleMin = (TitleMin)component;
                break;
            case "TitleMax":
                titleMax = (TitleMax)component;
                break;
            case "TitleDen":
                titleDen = (TitleDen)component;
                break;
            case "TitlePosMin":
                titlePosMin = (TitlePosMin)component;
                break;
        }
    }

    @Override
    public void addNewNote(Note note) {
        title1.setText("");
        title2.setText("");
        title3.setText("");
        title4.setText("");
        titleMin.setText("");
        titleMax.setText("");
        titleDen.setText("");
        titlePosMin.setText("");
        textBox.setText("");
        list.addElement(note);
        Threads.setEnd(false);
    }

    @Override
    public void deleteNote() {
        list.deleteElement();
    }

    @Override
    public void getInfoFromList(Note note) {
    	textBox.setText(note.getName().replace('*', ' '));
        title1.setText(note.getD_());
        title2.setText(note.getT_());
        title3.setText(note.getC_());
        title4.setText(note.getK_());
        titleMin.setText(note.getMIN());
        titleMax.setText(note.getMAX());
        titleDen.setText(note.getDEN());
        titlePosMin.setText(note.getPOS());
    }

    @Override
    public void saveChanges() {
        try {
            Note note = (Note) list.getSelectedValue();
            note.setD_(title1.getText());
            note.setT_(title2.getText());
            note.setC_(title3.getText());
            note.setK_(title4.getText());
            note.setName(textBox.getText());
            Docs doc = new Docs(note.getD_(), note.getT_(), Integer.valueOf(note.getC_()), note.getK_());
            Threads thread = new Threads(doc);
    		thread.run();
    		while(!Threads.endAnalysis) {
    			//wait
    		}
    		titleMin.setText(""+thread.actualMin);
    		titleMax.setText(""+thread.actualMax);
    		titleDen.setText(""+thread.density);
    		titlePosMin.setText(""+thread.possibleMin);
    		note.setMIN(titleMin.getText());
            note.setMAX(titleMax.getText());
            note.setDEN(titleDen.getText());
            note.setPOS(titlePosMin.getText());
            list.repaint();
        } catch (NullPointerException ignored) {}
    }

    @Override
    public void markNote() {
        try {
            Note note = list.getCurrentElement();
            String name = note.getName();
            if (!name.endsWith("*")) {
                note.setName(note.getName() + "*");
            }
            list.repaint();
        } catch (NullPointerException ignored) {}
    }

    @Override
    public void clear() {
        title1.setText("");
        title2.setText("");
        title3.setText("");
        title4.setText("");
        titleMin.setText("");
        titleMax.setText("");
        titleDen.setText("");
        titlePosMin.setText("");
        textBox.setText("");
    }

    @Override
    public void sendToFilter(@SuppressWarnings("rawtypes") ListModel listModel) {
        filter.setList(listModel);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setElementsList(@SuppressWarnings("rawtypes") ListModel list) {
        this.list.setModel(list);
        this.list.repaint();
    }

    @Override
    public void hideElements(boolean flag) {
        titleLabel1.setVisible(!flag);
        titleLabel2.setVisible(!flag);
        titleLabel3.setVisible(!flag);
        titleLabel4.setVisible(!flag);
        titleLabel5.setVisible(!flag);
        titleLabel6.setVisible(!flag);
        titleLabel7.setVisible(!flag);
        titleLabel8.setVisible(!flag);
        titleLabel9.setVisible(!flag);
        textLabel.setVisible(!flag);
        title1.setVisible(!flag);
        title2.setVisible(!flag);
        title3.setVisible(!flag);
        title4.setVisible(!flag);
        titleMin.setVisible(!flag);
        titleMax.setVisible(!flag);
        titleDen.setVisible(!flag);
        titlePosMin.setVisible(!flag);
        textBox.setVisible(!flag);
        save.setVisible(!flag);
        label.setVisible(flag);
    }

    @Override
    public void createGUI() {
        JFrame notes = new JFrame("Документы");
        notes.setSize(960, 600);
        notes.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel left = new JPanel();
        left.setBorder(new LineBorder(Color.BLACK));
        left.setSize(320, 600);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Найти:"));
        filter.setColumns(20);
        filterPanel.add(filter);
        filterPanel.setPreferredSize(new Dimension(280, 40));
        JPanel listPanel = new JPanel();
        list.setFixedCellWidth(260);
        listPanel.setSize(320, 470);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(275, 410));
        listPanel.add(scrollPane);
        JPanel buttonPanel = new JPanel();
        add.setPreferredSize(new Dimension(85, 25));
        buttonPanel.add(add);
        del.setPreferredSize(new Dimension(85, 25));
        buttonPanel.add(del);
        buttonPanel.setLayout(new FlowLayout());
        left.add(filterPanel);
        left.add(listPanel);
        left.add(buttonPanel);
        JPanel right = new JPanel();
        right.setLayout(null);
        right.setSize(640, 600);
        right.setLocation(320, 0);
        right.setBorder(new LineBorder(Color.BLACK));
        titleLabel1.setBounds(20, 4, 50, 20);
        titleLabel2.setBounds(20, 26, 50, 20);
        titleLabel3.setBounds(20, 48, 50, 20);
        titleLabel4.setBounds(20, 70, 50, 20);
        title1.setBounds(80, 5, 535, 20);
        title2.setBounds(80, 27, 535, 20);
        title3.setBounds(80, 49, 380, 20);
        title4.setBounds(80, 71, 380, 20);
        titleMin.setBounds(80, 100, 535, 20);
        titleMax.setBounds(80, 132, 535, 20);
        titleDen.setBounds(80, 164, 535, 20);
        titlePosMin.setBounds(80, 196, 535, 20);
        titleLabel5.setBounds(20, 100, 600, 30);
        titleLabel6.setBounds(20, 132, 600, 30);
        titleLabel7.setBounds(20, 164, 600, 30);
        titleLabel8.setBounds(20, 196, 600, 30);
        textLabel.setBounds(540, 25, 70, 130);
        titleLabel9.setBounds(20, 500, 70, 20);
        textBox.setBorder(new LineBorder(Color.DARK_GRAY));
        textBox.setBounds(100, 500, 515, 20);
        save.setBounds(270, 535, 80, 25);
        label.setFont(new Font("Verdana", Font.PLAIN, 22));
        label.setBounds(100, 240, 500, 100);
        right.add(label);
        right.add(titleLabel1);
        right.add(titleLabel2);
        right.add(titleLabel3);
        right.add(titleLabel4);
        right.add(title1);
        right.add(title2);
        right.add(title3);
        right.add(title4);
        right.add(titleMin);
        right.add(titleMax);
        right.add(titleDen);
        right.add(titlePosMin);
        right.add(titleLabel5);
        right.add(titleLabel6);
        right.add(titleLabel7);
        right.add(titleLabel8);
        right.add(titleLabel9);
        right.add(textLabel);
        right.add(textBox);
        right.add(save);
        notes.setLayout(null);
        notes.getContentPane().add(left);
        notes.getContentPane().add(right);
        notes.setResizable(false);
        notes.setLocationRelativeTo(null);
        notes.setVisible(true);
    }
}