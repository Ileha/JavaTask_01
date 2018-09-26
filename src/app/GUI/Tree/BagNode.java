package app.GUI.Tree;

import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

public class BagNode extends TreeFielsNode {
    private ArrayList<TreeFielsNode> bag = new ArrayList<TreeFielsNode>();

    public BagNode(String name) {
        super(name);
    }

    @Override
    protected TreeFielsNode TGetChild(int index) {
        //System.out.printf("%s\n", index);
        return bag.get(index);
    }

    @Override
    protected int TGetIndexOfChild(TreeFielsNode child) {
        int res = -1;
        for (int i=0; i<bag.size(); i++) {
            if (bag.get(i) == child){
                res=i;
                break;
            }
        }
        return res;
    }

    @Override
    public void TAdd(TreeFielsNode child) {
        child.root=root;
        bag.add(child);
    }

    @Override
    public void TSetRoot(TreeFielsNode root) {
        super.TSetRoot(root);
        for (int i = 0; i < bag.size(); i++) {
            bag.get(i).TSetRoot(root);
        }
    }

    @Override
    protected int TChildCount() {
        return bag.size();
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }

    @Override
    public boolean isLeaf(Object node) {
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }
}
