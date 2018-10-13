package app.GUI.Text;

import java.io.*;

public class ProcessDoc {
    private Viewer data;

    public ProcessDoc(File file, int count) {
        try {
            data = new Viewer(count, new RandomAccessFile(file, "r"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public long Getlenght() {
        return data.GetFileLenght();
    }

    public String GetBitOfText(double persent) {
        long n_start = (long)(data.GetFileLenght()*persent);
        return data.GetBitOfText(n_start);
    }

    public void Dispose() {
        data.Dispose();
    }

}