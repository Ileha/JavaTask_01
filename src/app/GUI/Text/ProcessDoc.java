package app.GUI.Text;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.*;

public class ProcessDoc {
    private RandomAccessFile file;
    private StringBuilder data;
    private int offset;

    public ProcessDoc(File file) throws IOException {
        this.file = new RandomAccessFile(file, "r");
        data = new StringBuilder();
        /*
        byte[] buffer = new byte[1024];
        int c = 0;

        while ((c = this.file.read(buffer, 0, buffer.length))!=-1) {
            data.append(new String(buffer,0, c, "UTF-8"));
        }
        text = data.toString();
        */
    }

    public long Getlenght() {
        long res = 0;
        try {
             res = file.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /*

    |........|............|...............|
    |....|...........|....................|
    |..................|..|......|........|
    */

    public String GetStringData(int start, int lengt) {
        int start_r_r = -(start-offset);
        int lenght_r_r = (start+lengt)-(offset+data.length());

        try {
            if (start_r_r>0){
                file.seek(start);
                //read
            }
            else {
                data.delete(0, Math.abs(start_r_r));
            }

            if(lenght_r_r > 0) {
                file.seek(offset+data.length());
            }
            else {

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
