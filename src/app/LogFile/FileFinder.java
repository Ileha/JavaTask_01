package app.LogFile;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import app.GUI.Tree.*;

public class FileFinder {
    public static TreeFielsNode GetFiels(String path, String pattern) {
        BagNode res = new BagNode("root");
        HelpGetFiels(res, path, Pattern.compile(pattern));
        return res;
    }

    private static void HelpGetFiels(TreeFielsNode res, String path, Pattern pattern) {
        File f = new File(path);
        for (File item : f.listFiles()) {
            if (item.isDirectory()) {
                BagNode node = new BagNode(item.getName());
                res.TAdd(node);
                HelpGetFiels(node, item.getPath(), pattern);
            } else {
                Matcher m = pattern.matcher(item.getName());
                if (m.find()) {
                    //System.out.printf("%s\tфайл\n", item.getPath());
                    res.TAdd(new FileNode(item.getName(), new File(item.getPath())));
                }
            }
        }
    }
}
