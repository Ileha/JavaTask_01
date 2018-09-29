package app.LogFile;

import app.GUI.Tree.LeafNode;

import java.io.File;
import java.util.ArrayList;

public class FileNode extends LeafNode {
    public File file;
    public int[] indexes;
    public String word;

    public FileNode(String name, File data, int[] indexes, String word) {
        super(name);
        file = data;
        this.indexes = indexes;
        this.word = word;
    }

    public int substring_char_count() {
        return word.length();
    }
}
