package app.GUI.Tree;

import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.*;
import java.util.Enumeration;


public abstract class IFileNode implements TreeNode {
    private String name;
    private IFileNode _parent;

    public IFileNode(String name) {
        this.name = name;
    }

    protected final TreeFilesNode TGetRoot() throws ClassCastException {
        IFileNode node = this;
        while (node._parent != null) {
            node = node._parent;
        }
        return (TreeFilesNode)node;
    }

    @Override
    public IFileNode getParent() {
        return _parent;
    }

    public synchronized final void Add(IFileNode child) {
        try {
            OnAdd(child);
            child._parent = this;
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
    public final void Remove() {
        try {
            _parent.OnRemove(this);
            _parent = null;
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }

    protected abstract void OnRemove(IFileNode node);
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
