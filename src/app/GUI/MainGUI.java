package app.GUI;

import app.GUI.Tree.*;
import app.LogFile.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import app.SubstringFinder.Finder;
import java.util.concurrent.*;

public class MainGUI extends JFrame {

    private JTextField puth = new JTextField("");
    private JTextField extensions = new JTextField(".txt");
    private JTextField substring = new JTextField("");
    private JButton button = new JButton("Find");
    private TreeFilesNode main_node = new TreeFilesNode("root");
    private JTree tree = new JTree((TreeModel) main_node);
    private JLabel process = new JLabel("<html>Choose path, indicate extension,<br> set text for find and press Find</html>");
    private ExecutorService executor = Executors.newFixedThreadPool(1);

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
        c.fill = GridBagConstraints.NONE;
        add(process, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(button, c);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executor.submit(() -> {
                    process.setText("please wait finding...");

                    main_node.RemoveAll();
                    BagNode find_res = new BagNode("results");
                    FileFinder.GetFiels(find_res, puth.getText(), extensions.getText(), (file_node) -> {
                        FileReader reader = new FileReader(file_node.file);
                        try {
                            file_node.rel_indexes = Finder.GetEntriesRelative(reader, substring.getText());
                            file_node.word = new String(substring.getText());
                        }
                        catch (Exception err) {
                            throw err;
                        }
                        finally {
                            reader.close();
                        }
                        //return new FileNode(file.getName(), file, indexes, substring.getText());
                    });
                    main_node.Add(find_res);
                    process.setText("done");
                });
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 5;
        c.weighty = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 2) {
                        System.out.printf("double - %s\n", selPath.getLastPathComponent().toString());
                        IFileNode click = (IFileNode)selPath.getLastPathComponent();
                        if (click.isLeaf()) {
                            LogsWindow win = new LogsWindow((FileNode) click, getX(), getY());
                            win.setVisible(true);
                        }
                        //java.awt.EventQueue.invokeLater(() -> {
                        //});
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, c);
    }
}