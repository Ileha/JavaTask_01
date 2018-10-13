package app.GUI.Text;

import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

public class Viewer {
    private ByteBuffer data;
    private int start_decode = 0;
    private int count_decode;
    private IBlock func;

    public Viewer(int buffer_lenght, RandomAccessFile read_file) {
        data = new ByteBuffer(buffer_lenght*2, read_file);
        count_decode = buffer_lenght;
        long block_count = Math.max (0, ((data.GetFilelenght() - (data.GetFilelenght()%buffer_lenght))/buffer_lenght)-1);
        func = (index) -> {
            long current = (index-(index%buffer_lenght))/buffer_lenght;
            return Math.min(current, block_count)*buffer_lenght;
        };
    }

    public String GetBitOfText(long index) {
        long start_buffer = func.GetStartBy(index);
        long file_l = data.GetFilelenght();
        long overload = index+data.lenght()/2;

        if (overload > data.GetFilelenght()) {
            int value_overload = (int)(overload - data.GetFilelenght());
            start_decode = (int)(index - start_buffer);
            count_decode = (data.lenght()/2)-value_overload;
        }
        else  {
            start_decode = (int)(index - start_buffer);
            count_decode = data.lenght()/2;
        }

        data.SetBuffer(start_buffer);
        return GetCurrentData();
    }

    public long GetFileLenght() {
        return data.GetFilelenght();
    }

    public String GetCurrentData() {
        try {
            return new String(data.buffer, start_decode, count_decode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void Dispose() {
        data.Dispose();
    }
}
