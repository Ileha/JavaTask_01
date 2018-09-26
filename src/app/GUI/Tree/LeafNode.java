package app.GUI.Tree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class LeafNode extends IFileNode {
    public LeafNode(String name){
        super(name);
    }

    @Override
    protected void OnAdd(IFileNode child) {
        throw new NotImplementedException();
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        throw new NotImplementedException();
    }

    @Override
    public int getChildCount() {
        throw new NotImplementedException();
    }

    @Override
    public int getIndex(TreeNode node) {
        throw new NotImplementedException();
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration children() {
        throw new NotImplementedException();
    }
}
