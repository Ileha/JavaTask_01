package app.GUI.Tree;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;

public class BagNode extends IFileNode implements Enumeration {
    private ArrayList<IFileNode> bag = new ArrayList<IFileNode>();
    private int enumeration_iterator = 0;

    public BagNode(String name) {
        super(name);
    }

    @Override
    protected void OnAdd(IFileNode child) {
        bag.add(child);
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
