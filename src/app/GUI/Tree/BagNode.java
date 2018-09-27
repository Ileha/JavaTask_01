package app.GUI.Tree;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.*;


public class BagNode extends IFileNode implements Enumeration {
    protected ArrayList<IFileNode> bag = new ArrayList<IFileNode>();
    private int enumeration_iterator = 0;

    public BagNode(String name) {
        super(name);
    }

    @Override
    protected void OnRemove(IFileNode node) {
        int[] indexes = new int[1];
        indexes[0] = getIndex(node);
        if (indexes[0] == -1) { return; }
        TreePath path = new TreePath(this);
        Object[] childs = new Object[1];
        childs[0] = node;
        TreeModelEvent e = new TreeModelEvent(this, path, indexes ,childs);
        TGetRoot().ExecuteEvent((listener) -> listener.treeNodesRemoved(e));
    }

    @Override
    protected void OnAdd(IFileNode child) {
        bag.add(child);
        TreePath path = new TreePath(this);
        int[] indexes = new int[1];
        indexes[0] = bag.size()-1;
        Object[] childs = new Object[1];
        childs[0] = child;
        TreeModelEvent e = new TreeModelEvent(this, path, indexes ,childs);
        TGetRoot().ExecuteEvent((listener) -> listener.treeNodesInserted(e));
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return bag.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return bag.size();
    }

    @Override
    public int getIndex(TreeNode node) {
        int res = -1;
        for (int i=0; i<bag.size(); i++) {
            if (bag.get(i) == node){
                res=i;
                break;
            }
        }
        return res;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration children() {
        enumeration_iterator = 0;
        return this;
    }

    @Override
    public boolean hasMoreElements() {
        if (enumeration_iterator == bag.size()) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public Object nextElement() {
        IFileNode res = bag.get(enumeration_iterator);
        enumeration_iterator++;
        return res;
    }
}
