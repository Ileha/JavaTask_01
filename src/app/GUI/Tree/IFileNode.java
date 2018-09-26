package app.GUI.Tree;

import javax.swing.tree.TreeNode;
import java.awt.event.*;
import java.util.Enumeration;


public abstract class IFileNode implements TreeNode {
    private String name;
    private IFileNode _parent;

    public IFileNode(String name) {
        this.name = name;
    }

    @Override
    public TreeNode getParent() {
        return _parent;
    }

    public final void Add(IFileNode child) {
        try {
            OnAdd(child);
            child._parent = this;
        }
        catch (Exception err) {}
    }

    protected abstract void OnAdd(IFileNode child);
    @Override
    public abstract TreeNode getChildAt(int childIndex);
    @Override
    public abstract int getChildCount();
    @Override
    public abstract int getIndex(TreeNode node);
    @Override
    public abstract boolean getAllowsChildren();
    @Override
    public abstract boolean isLeaf();
    @Override
    public abstract Enumeration children();

    @Override
    public String toString(){
        return name;
    }
}
