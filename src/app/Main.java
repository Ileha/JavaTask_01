package app;

import java.io.*;
import app.GUI.MainGUI;
import app.GUI.Text.*;
import app.SubstringFinder.Finder;
import app.SubstringFinder.SubstringNotFound;

import java.util.*;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {

    static int count = 0;
    static synchronized void incrementSync() {
        count = count + 1;
    }

    static Callable callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            String threadName = Thread.currentThread().getName();
            System.out.printf("Hello %s task res %s\n", threadName, result);
            return result;
        };
    }

    public static void main(String[] args) {
        ExecutorService executor_mater = Executors.newFixedThreadPool(2);

//        IntStream.range(0, 10000).forEach(i -> executor.submit(()->incrementSync()));
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        List<Callable<String>> futures = new ArrayList<Callable<String>>();
//        futures.add(callable("t1", 1));
//        futures.add(callable("t2", 1));
//        futures.add(callable("t3", 1));
//        futures.add(callable("t4", 1));
//        futures.add(callable("t5", 1));
//        List<Callable<String>> futures_m = new ArrayList<Callable<String>>();
//        futures_m.add(callable("m6", 1));
//        futures_m.add(callable("m7", 1));
//        futures_m.add(callable("m8", 1));
//        futures_m.add(callable("m9", 1));
//        futures_m.add(callable("m10", 1));
//        futures_m.add(callable("m11", 1));
//        futures_m.add(callable("m12", 1));
//        executor_mater.submit(()-> {
//            try {
//                List<Future<String>> results = executor.invokeAll(futures);
//                for (Future<String> k:results) {
//                    try {
//                        System.out.printf("%s\n", k.get());
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        executor_mater.submit(()-> {
//            try {
//                List<Future<String>> results = executor.invokeAll(futures_m);
//                for (Future<String> k:results) {
//                    try {
//                        System.out.printf("%s\n", k.get());
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(count);  // 10000
//        executor.shutdown();

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
