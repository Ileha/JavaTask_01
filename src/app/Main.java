package app;
import app.GUI.MainGUI;

public class Main {

    public static void main(String[] args) {
//        Scanner in = null;
//        try (FileReader reader = new FileReader("data.txt")) {
//            in = new Scanner(reader).useDelimiter("\n");
//            String data = "";
//            while (in.hasNext()) {
//                data += in.next();
//            }
//            System.out.printf("%s\n", data);
//            ArrayList<Integer> indexes = Finder.GetEntries(data, "степ");
//            for (int i = 0; i < indexes.size(); i++) {
//                int index = indexes.get(i);
//                System.out.printf("%s, %s\n", index, data.substring(index, index + 10));
//            }
//        }
//        catch(IOException ex) {
//            System.out.println(ex.getMessage());
//        }

        //FileFinder.GetFiels("./", ".*.txt$");
        MainGUI gui = new MainGUI();
        gui.setVisible(true);
    }
}
