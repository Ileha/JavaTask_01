package app.GUI.Tree;

import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeModel;
import javax.swing.event.TreeModelListener;
import java.util.ArrayList;

interface TypeEvent {
    void Action(TreeModelListener listener);
}

public class TreeFilesNode extends BagNode implements TreeModel {
    private ArrayList<TreeModelListener> eventLisner = new ArrayList<TreeModelListener>();

    public TreeFilesNode(String name) {
        super(name);
    }

    public void ExecuteEvent(TypeEvent event) {
        for (int i = 0; i < eventLisner.size(); i++) {
            event.Action(eventLisner.get(i));
        }
    }

    public void RemoveAll() {
        int[] indexes = new int[bag.size()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        TreeModelEvent e = new TreeModelEvent(this, new TreePath(this), indexes ,bag.toArray());
        ExecuteEvent((listener) -> listener.treeNodesRemoved(e));
        bag.clear();
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
        eventLisner.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        eventLisner.remove(l);
    }
}
