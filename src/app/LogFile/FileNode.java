package app.LogFile;

import app.GUI.Tree.LeafNode;

import java.io.File;
import java.util.ArrayList;

public class FileNode extends LeafNode {
    public File file;
    public ArrayList<Integer> indexes;
    public int substring_char_count;

    public FileNode(String name, File data, ArrayList<Integer> indexes, int substring_char_count) {
        super(name);
        file = data;
        this.indexes = indexes;
        this.substring_char_count = substring_char_count;
    }
}
