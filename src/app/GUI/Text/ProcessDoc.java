package app.GUI.Text;

import java.io.*;

public class ProcessDoc {
    private RandomAccessFile randomAccessFile;
    private StringBuilder data;

    public ProcessDoc(File file) {
        data = new StringBuilder();
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public long Getlenght() {
        long res = 0;
        try {
             res = randomAccessFile.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String GetBitOfText(double persent, int count) {
        data.delete(0, data.length());
        long pos = (long)(((double)Getlenght())*persent);
        try {
            randomAccessFile.seek(pos);
            byte[] buffer = new byte[count];
            int c = 0;
            int read_count = count;

            while (read_count > 0) {
                c = randomAccessFile.read(buffer, 0, buffer.length);
                if (c==-1) { break; }
                String add_str = new String(buffer,0, c, "UTF-8");
                int str_lenght = add_str.length();
                read_count -= str_lenght;
                if (read_count < 0) {
                    str_lenght+=read_count;
                }
                data.append(add_str.substring(0,str_lenght));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public void Dispose() {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*

    |........|............|...............|
    |....|...........|....................|
    |..................|..|......|........|
    */

    /*
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
    */
}
