package app.GUI.Text;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.*;

public class ProcessDoc implements Document {
    private RandomAccessFile file;
    public String text;

    public ProcessDoc(File file) throws IOException {
        this.file = new RandomAccessFile(file, "r");

        StringBuilder data = new StringBuilder();

        byte[] buffer = new byte[1024];
        int c = 0;

        while ((c = this.file.read(buffer, 0, buffer.length))!=-1) {
            data.append(new String(buffer,0, c, "UTF-8"));
        }
        text = data.toString();
    }

    @Override
    public int getLength() {
        int res = -1;
        try {
            res = (int)file.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void addDocumentListener(DocumentListener listener) {

    }

    @Override
    public void removeDocumentListener(DocumentListener listener) {

    }

    @Override
    public void addUndoableEditListener(UndoableEditListener listener) {

    }

    @Override
    public void removeUndoableEditListener(UndoableEditListener listener) {

    }

    @Override
    public Object getProperty(Object key) {
        return null;
    }

    @Override
    public void putProperty(Object key, Object value) {

    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {

    }

    @Override
    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {

    }

    @Override
    public String getText(int offset, int length) throws BadLocationException {
        return text.substring(offset, offset+length);
    }

    @Override
    public void getText(int offset, int length, Segment txt) throws BadLocationException {
        text = getText(offset, length);
    }

    @Override
    public Position getStartPosition() {
        return null;
    }

    @Override
    public Position getEndPosition() {
        return null;
    }

    @Override
    public Position createPosition(int offs) throws BadLocationException {
        return null;
    }

    @Override
    public Element[] getRootElements() {
        return new Element[0];
    }

    @Override
    public Element getDefaultRootElement() {
        return null;
    }

    @Override
    public void render(Runnable r) {

    }
}
