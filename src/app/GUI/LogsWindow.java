package app.GUI;

import app.GUI.Text.ProcessDoc;
import app.LogFile.FileNode;
import app.SubstringFinder.Finder;
import app.SubstringFinder.SubstringNotFound;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

class DigitFilter extends DocumentFilter {
    private static final String DIGITS = "\\d+";

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches(DIGITS)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
        if (string.matches(DIGITS)) {
            super.replace(fb, offset, length, string, attrs);
        }
    }
}

public class LogsWindow extends JFrame implements MouseWheelListener  {
    private JButton next = new JButton(">>");
    private JButton previous = new JButton("<<");
    private JTextField current_count;//кол-во колонок ограничивается кол-вом знаков
    private int current_index = 0;
    private FileNode data;
    private JTextArea textArea = new JTextArea();
    private JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);
    private ProcessDoc doc = null;
    private DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);


    public LogsWindow(FileNode data, int pos_x, int pos_y) {
        super(data.toString());
        this.data = data;
        doc = new ProcessDoc(data.file);

        this.setBounds(20+pos_x, 20+pos_y, 500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        textArea.setEditable(false);
        add(textArea, c);
        textArea.setLineWrap(true);

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0;
        this.add(scrollBar, c);
        scrollBar.setMinimum(0);
        scrollBar.setValue(1);
        scrollBar.setMaximum((int) doc.Getlenght());

        scrollBar.addAdjustmentListener( e -> {
            int char_count = 9000;
            String text = doc.GetBitOfText(e.getValue()/max(), char_count);
            textArea.setText(text);
            try {
                int[] current_indexes = Finder.GetEntries(text, data.word);
                for (int i = 0; i < current_indexes.length; i++) {
                    try {
                        textArea.getHighlighter().addHighlight(current_indexes[i], current_indexes[i]+data.substring_char_count(), highlightPainter);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                        break;
                    }
                }
            } catch (SubstringNotFound substringNotFound) {
                //substringNotFound.printStackTrace();
            }
        } );
        scrollBar.setValue(0);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weighty = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(previous, c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        String max_count = String.format("%s", data.rel_indexes.length);
        current_count = new JTextField(max_count.length());
        this.add(current_count, c);
        ((PlainDocument)current_count.getDocument()).setDocumentFilter(new DigitFilter());
        current_count.addActionListener(e -> SetCurrentIndex(Integer.parseInt(e.getActionCommand())));
        current_count.setText("1");

        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(new JLabel(String.format("/%s", max_count)), c);

        c.gridx = 3;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(next, c);
        next.addActionListener(e -> SetCurrentIndex(current_index+1));
        previous.addActionListener(e -> SetCurrentIndex(current_index-1));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                doc.Dispose();
            }
        });
        textArea.addMouseWheelListener(this);
    }

    private double max() {
        return scrollBar.getMaximum();
    }

    private void SetScrollBarValue(double relative) {
        scrollBar.setValue((int)(max()*relative));
    }
    private void SetCurrentIndex(int new_current) {
        current_index = new_current;
        current_index = Math.min(data.rel_indexes.length, current_index);
        current_index = Math.max(1, current_index);
        String val = String.valueOf(current_index);
        if (current_count.getText() != val) {
            current_count.setText(val);
            SetScrollBarValue(data.rel_indexes[current_index-1]);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrollBar.setValue(scrollBar.getValue()+e.getWheelRotation()*10);
    }
}
