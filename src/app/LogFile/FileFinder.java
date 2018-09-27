package app.LogFile;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import app.GUI.Tree.*;
import app.*;

import javax.swing.*;

public class FileFinder {
    public static void GetFiels(IFileNode out, String path, String pattern, FindExpression FindFunc) {
        HelpGetFiels(out, path, Pattern.compile(pattern), FindFunc);
    }

    private static void HelpGetFiels(IFileNode res, String path, Pattern pattern, FindExpression FindFunc) {
        File f = new File(path);
        for (File item : f.listFiles()) {
            if (item.isDirectory()) {
                BagNode node = new BagNode(item.getName());
                HelpGetFiels(node, item.getPath(), pattern, FindFunc);
                if (node.getChildCount() > 0) {
                    res.Add(node);
                }
            } else {
                Matcher m = pattern.matcher(item.getName());
                if (m.find()) {
                    //System.out.printf("%s\tфайл\n", item.getPath());
                    try {
                        IFileNode add = FindFunc.Action(item);
                        res.Add(FindFunc.Action(item));
                    }
                    catch (Exception err) {

                    }
                }
            }
        }
    }
}
