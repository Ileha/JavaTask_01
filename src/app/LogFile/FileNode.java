package app.LogFile;

import app.GUI.Tree.LeafNode;
import java.io.File;

public class FileNode extends LeafNode {
    public String file;

    public FileNode(String name, String text) {
        super(name);
        file = text;
    }
}
