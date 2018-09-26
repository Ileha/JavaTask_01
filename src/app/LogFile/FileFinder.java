package app.LogFile;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFinder {
    public static ArrayList<File> GetFiels(String path, String pattern) {
        ArrayList<File> res = new ArrayList<File>();
        HelpGetFiels(res, path, Pattern.compile(pattern));
        return res;
    }

    private static void HelpGetFiels(ArrayList<File> res, String path, Pattern pattern) {
        File f = new File(path);
        for (File item : f.listFiles()) {
            if (item.isDirectory()) {
                HelpGetFiels(res, item.getPath(), pattern);
            } else {
                Matcher m = pattern.matcher(item.getName());
                if (m.find()) {
                    //System.out.printf("%s\tфайл\n", item.getPath());
                    res.add(new File(item.getPath()));
                }
            }
        }
    }
}
