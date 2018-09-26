package app.GUI.Tree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

public class LeafNode extends TreeFielsNode {
    public LeafNode(String name){
        super(name);
    }

    @Override
    protected TreeFielsNode TGetChild(int index) {
        return null;
    }

    @Override
    protected int TGetIndexOfChild(TreeFielsNode child) {
        return -1;
    }

    @Override
    public void TAdd(TreeFielsNode child) {
        throw new NotImplementedException();
    }

    @Override
    protected int TChildCount() {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }

    @Override
    public boolean isLeaf(Object node) {
        return true;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }
}
