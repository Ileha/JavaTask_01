package app.LogFile;

import app.GUI.Tree.LeafNode;
import java.util.ArrayList;

public class FileNode extends LeafNode {
    public String file;
    public ArrayList<Integer> indexes;
    public int substring_char_count;

    public FileNode(String name, String text, ArrayList<Integer> indexes, int substring_char_count) {
        super(name);
        file = text;
        this.indexes = indexes;
        this.substring_char_count = substring_char_count;
    }
}
