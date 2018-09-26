package app.GUI.Tree;

import javax.swing.tree.TreePath;
import javax.swing.tree.TreeModel;
import javax.swing.event.TreeModelListener;

public class TreeFilesNode extends BagNode implements TreeModel {

    public TreeFilesNode(String name) {
        super(name);
    }

    @Override
    public Object getRoot() {
        return this;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((IFileNode)parent).getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((IFileNode)parent).getChildCount();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((IFileNode)node).isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        IFileNode curr_node = (IFileNode)parent;
        int res = -1;
        for (int i = 0; i < curr_node.getChildCount(); i++) {
            if (child == curr_node.getChildAt(i)) {
                res = i;
                break;
            }
        }
        return res;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
