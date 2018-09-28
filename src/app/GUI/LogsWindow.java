package app.GUI;

import app.GUI.Text.ProcessDoc;
import app.LogFile.FileNode;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

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
    //private JTextPane doc = new JTextPane();
    private int current_index = 0;
    private FileNode data;
    private JTextArea textArea = new JTextArea();
    private JScrollBar scrollBar = new JScrollBar(Adjustable.VERTICAL);

    public LogsWindow(FileNode data, int pos_x, int pos_y) {
        super(data.toString());
        this.data = data;
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
        //textArea.getRows()


        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0;
        this.add(scrollBar, c);
        scrollBar.setMinimum(0);
        scrollBar.setValue(1); // меняем число до установки слушателя
        scrollBar.setMaximum(100);

        scrollBar.addAdjustmentListener(
                new AdjustmentListener() {
                    public void adjustmentValueChanged(AdjustmentEvent event) {
                        System.out.printf("%s\n", event.getValue());
                        //textArea.setText("");
                    }
                });

        //scrollPane.setValue(0);
        //this.add(doc, c);
        //JScrollPane scrollPane = new JScrollPane(doc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //this.add(scrollPane, c);

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
        String max_count = String.format("%s", data.indexes.size());
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

        /*
        doc.setEditable(false);
        try {
            //DefaultStyledDocument
            Document d = doc.getDocument();
            //doc.setDocument(d);
            Style normal = doc.addStyle("normal", null);
            StyleConstants.setFontFamily(normal, "Times New Roman");
//            StyleConstants.setFontSize(normal, 16);
//            d.insertString(d.getLength(), data.file, normal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

//        SimpleAttributeSet sas = new SimpleAttributeSet();
//        StyleConstants.setForeground(sas, Color.YELLOW);

        //DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        /*
        for (int i = 0; i < data.indexes.size(); i++) {
            try {
                doc.getHighlighter().addHighlight(data.indexes.get(i), data.indexes.get(i)+data.substring_char_count, highlightPainter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        */
    }

    private void SetCaretPosition(int index) {
        //doc.setCaretPosition(data.indexes.get(index));
    }
}
