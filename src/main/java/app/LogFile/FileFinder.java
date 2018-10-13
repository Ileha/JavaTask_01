package app.LogFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import app.GUI.Tree.*;
import jdk.nashorn.internal.ir.IfNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.*;

public class FileFinder {
    private static ExecutorService executor = Executors.newFixedThreadPool(2*Runtime.getRuntime().availableProcessors());

    private static Callable callable(ConcurrentLinkedQueue<FileNode> queue, FindExpression FindFunc) {
        return () -> {
            FileNode poll_node = null;
            while ((poll_node = queue.poll()) != null) {
                try {
                    String threadName = Thread.currentThread().getName();
                    //System.out.printf("Start handler task %s for handle %s\n", threadName, poll_node.toString());
                    FindFunc.Action(poll_node);
                }
                catch (Exception err) {
                    poll_node.Remove();
                }
            }
            return 0;
        };
    }

    public static void GetFiels(IFileNode out, String path, String pattern, FindExpression FindFunc) {
        ConcurrentLinkedQueue<FileNode> queue = new ConcurrentLinkedQueue<FileNode>();
        HelpGetFiels(Pattern.compile(pattern), new File(path), out, queue);
        List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors()*2; i++) {
            callables.add(callable(queue, FindFunc));
        }
        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IsEmpty(out);
    }

    private static boolean IsEmpty(IFileNode node) {
        boolean res = false;
        try {
            ArrayList<IFileNode> empty_array = new ArrayList<IFileNode>();
            for (int i = 0; i < node.getChildCount(); i++) {
                IFileNode check = (IFileNode)node.getChildAt(i);
                if (IsEmpty(check)) {
                    empty_array.add(check);
                }
            }
            for (int i = 0; i < empty_array.size(); i++) {
                empty_array.get(i).Remove();
            }

            if (node.getChildCount() == 0) {
                res = true;
            }
        }
        catch (NotImplementedException err) {}
        return res;
    }

    private static void HelpGetFiels(Pattern pattern, File folder, IFileNode bag, ConcurrentLinkedQueue<FileNode> Queue) {
        for (File item : folder.listFiles()) {
            if (item.isDirectory()) {
                BagNode node = new BagNode(item.getName());
                HelpGetFiels(pattern, item, node, Queue);
                if (node.getChildCount() > 0) {
                    bag.Add(node);
                }
            } else {
                Matcher m = pattern.matcher(item.getName());
                if (m.find()) {
                    FileNode node = new FileNode(item.getName(), item);
                    bag.Add(node);
                    Queue.add(node);
                }
            }
        }

    }
}
