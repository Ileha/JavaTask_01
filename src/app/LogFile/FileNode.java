package app.LogFile;

import app.GUI.Tree.LeafNode;

import java.io.File;
import java.util.ArrayList;

public class FileNode extends LeafNode {
    public File file;
    public double[] rel_indexes;
    public String word;

    public FileNode(String name, File data) {
        super(name);
        file = data;
//        this.rel_indexes = rel_indexes;
//        this.word = word;
    }

    public int substring_char_count() {
        return word.length();
    }
}
