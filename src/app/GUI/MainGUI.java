package app.GUI;

import app.GUI.Tree.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class MainGUI extends JFrame {

    private JTextField puth = new JTextField("");
    private JTextField extensions = new JTextField("log");
    private JTextField substring = new JTextField("");
    private JButton button = new JButton("Find");
    private JTree tree;

    public MainGUI() {
        super("Program");
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.weighty = 0;
        this.add(new JLabel("Puth: "), c);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(puth, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.weighty = 0;
        this.add(new JLabel("Extension: "), c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(extensions, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        this.add(new JLabel("Substring: "), c);

        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(substring, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        this.add(button, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 4;
        c.weighty = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;
        TreeFilesNode root = new TreeFilesNode("root");
        root.Add(new LeafNode("file 1"));
        root.Add(new LeafNode("file 2"));
        BagNode bag = new BagNode("dir 1");
        root.Add(bag);
        bag.Add(new LeafNode("file 123"));
        bag.Add(new LeafNode("file 321"));
        tree = new JTree((TreeModel)root);
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 2) {

                        System.out.printf("double - %s\n", selPath.getLastPathComponent().toString());
                    }
                }
            }
        });

        this.add(tree, c);

        JScrollPane scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);
    }
}