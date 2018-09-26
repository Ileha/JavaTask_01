package app.LogFile;

import app.GUI.Tree.LeafNode;
import java.io.File;

public class FileNode extends LeafNode {
    public File file;

    public FileNode(String name, File log_file) {
        super(name);
        file = log_file;
    }
}
