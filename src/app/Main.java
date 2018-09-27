package app;

import java.io.*;
import app.GUI.MainGUI;
import app.GUI.Text.*;
import app.SubstringFinder.Finder;
import app.SubstringFinder.SubstringNotFound;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        ProcessDoc doc = null;
//        try {
//            doc = new ProcessDoc(new File("task.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.printf("%s", doc.text);

//        ArrayList<Integer> indexed = null;
//        try(FileReader reader = new FileReader("task.txt"))
//        {
//            try {
//                indexed = Finder.GetEntries(reader, "па");
//            } catch (SubstringNotFound substringNotFound) {
//                substringNotFound.printStackTrace();
//            }
//        }
//        catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }
//
//        try(FileReader reader = new FileReader("task.txt"))
//        {
//            Scanner in = new Scanner(reader);
//            String res = "";
//            while (in.hasNextLine()) {
//                res += in.nextLine()+"\n";
//            }
//            for (Integer i : indexed) {
//                System.out.printf("%s\n", res.substring(i,i+10));
//            }
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }

        //FileFinder.GetFiels("./", ".*.txt$");
        MainGUI gui = new MainGUI();
        gui.setVisible(true);
    }
}
