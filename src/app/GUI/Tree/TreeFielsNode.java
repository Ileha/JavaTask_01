package app.GUI.Tree;

import javax.swing.tree.TreePath;
import javax.swing.tree.TreeModel;
import javax.swing.event.TreeModelListener;

public abstract class TreeFielsNode implements TreeModel {
    protected TreeFielsNode root;
    private String name;

    public TreeFielsNode(String name) {
        this.root=this;
        this.name=name;
    }

    @Override
    public Object getRoot() {
        return root;
    }
    protected abstract TreeFielsNode TGetChild(int index);
    protected abstract int TGetIndexOfChild(TreeFielsNode child);
    public abstract void TAdd(TreeFielsNode child);
    public void TSetRoot(TreeFielsNode root) {
        this.root = root;
    }
    protected abstract int TChildCount();

    @Override
    public int getChildCount(Object node) {
        return ((TreeFielsNode)node).TChildCount();
    }
    @Override
    public Object getChild(Object node, int index) {
        return ((TreeFielsNode)node).TGetChild(index);
    }
    @Override
    public int getIndexOfChild(Object node, Object child){
        return ((TreeFielsNode)node).TGetIndexOfChild((TreeFielsNode)child);
    }
    @Override
    public abstract boolean isLeaf(Object node);

    @Override
    public String toString(){
        return name;
    }

    /*
    @Override
    public void valueForPathChanged(TreePath path, Object value) {}
    @Override
    public void addTreeModelListener(TreeModelListener tml) {}
    @Override
    public void removeTreeModelListener(TreeModelListener tml) {}
    */
}
