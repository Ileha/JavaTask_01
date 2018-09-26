package app.LogFile;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import app.GUI.Tree.*;

public class FileFinder {
    public static IFileNode GetFiels(String path, String pattern) {
        IFileNode res = new TreeFilesNode("root");
        HelpGetFiels(res, path, Pattern.compile(pattern));
        return res;
    }

    private static void HelpGetFiels(IFileNode res, String path, Pattern pattern) {
        File f = new File(path);
        for (File item : f.listFiles()) {
            if (item.isDirectory()) {
                BagNode node = new BagNode(item.getName());
                res.Add(new BagNode(item.getName()));
                HelpGetFiels(node, item.getPath(), pattern);
            } else {
                Matcher m = pattern.matcher(item.getName());
                if (m.find()) {
                    //System.out.printf("%s\tфайл\n", item.getPath());
                    res.Add(new FileNode(item.getName(), new File(item.getPath())));
                }
            }
        }
    }
}
