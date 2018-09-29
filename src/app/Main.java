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
        //apachelog.log
//        try {
//            FileReader reader = new FileReader("data.txt");
//            double[] relative = Finder.GetEntriesRelative(reader,"со");
//            reader.close();
//            ProcessDoc doc = new ProcessDoc(new File("data.txt"));
//
//            for (int i = 0; i < relative.length; i++) {
//                System.out.printf("%s\n", doc.GetBitOfText(relative[i], 50));
//            }
//        } catch (SubstringNotFound substringNotFound) {
//            substringNotFound.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //System.out.printf("%s", doc.GetLine(1, 10));
//        System.out.printf("%s", doc.GetBitOfText(0.5, 5));

//        ArrayList<Integer> indexed = null;
//        try(FileReader reader = new FileReader("task.txt"))
//        {
//            try {
//                indexed = Finder.GetEntries(reader, "\n");
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
//                System.out.printf("%s\n", res.substring(i-5,i));
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
