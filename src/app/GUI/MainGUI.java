package app.GUI;

import app.GUI.Tree.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
        TreeFielsNode root = new BagNode("root");

        TreeFielsNode underroot = new BagNode("folder 1");
        underroot.TAdd(new LeafNode("contain"));
        root.TAdd(underroot);
        root.TAdd(new LeafNode("file"));
        tree = new JTree(root);
        this.add(tree, c);
    }
}
