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

public class LogsWindow extends JFrame  {
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
        double max = scrollBar.getMaximum();

        scrollBar.addAdjustmentListener(
                new AdjustmentListener() {
                    public void adjustmentValueChanged(AdjustmentEvent event) {
                        int char_count = 10000;
                        double current = event.getValue();

                        String text = doc.GetBitOfText(current/max, char_count);
                        textArea.setText(text);
                        try {
                            int[] current_indexes = Finder.GetEntries(text, data.word);
                            for (int i = 0; i < current_indexes.length; i++) {
                            try {
                                textArea.getHighlighter().addHighlight(current_indexes[i], current_indexes[i]+data.substring_char_count(), highlightPainter);
                            } catch (BadLocationException e) {
                                e.printStackTrace();
                                break;
                            }
                        }
                        } catch (SubstringNotFound substringNotFound) {
                            //substringNotFound.printStackTrace();
                        }
                    }
                });
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
        String max_count = String.format("%s", data.indexes.length);
        current_count = new JTextField(max_count.length());
        this.add(current_count, c);
        ((PlainDocument)current_count.getDocument()).setDocumentFilter(new DigitFilter());

        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(new JLabel(String.format("/%s", max_count)), c);

        c.gridx = 3;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(next, c);
    }

    private void SetCaretPosition(int index) {
        //doc.setCaretPosition(data.indexes.get(index));
    }
}
