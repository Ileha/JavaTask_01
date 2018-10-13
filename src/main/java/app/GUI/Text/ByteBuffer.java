package app.GUI.Text;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ByteBuffer {
    public byte[] buffer;
    private RandomAccessFile data;
    private long last_index;

    public ByteBuffer(int buffer_lenght, RandomAccessFile data) {
        buffer = new byte[buffer_lenght];
        this.data = data;
        last_index = 0;
        try {
            data.read(buffer, 0, buffer_lenght);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Shift(byte[] array, int shift) {
        if (Math.abs(shift) >= array.length){ return; }
        if (shift > 0) {
            for (int i = 0; i < array.length - shift; i++) {
                array[i] = array[i + shift];
            }
        }
        else if (shift < 0) {
            int normal_data = -shift;
            for (int i = array.length - 1; i >= normal_data; i--) {
                array[i] = array[i-normal_data];
            }
        }
        else {return;}
    }

    public void SetBuffer(long start) {
        if (start == last_index) { return; }

        long pos = start;
        long Max_shift = (start - last_index);

        int read_count = 0;
        int start_write = 0;

        if (Math.abs(Max_shift) > buffer.length) {
            read_count = buffer.length;
            start_write = 0;
        }
        else {
            int shift = (int)Max_shift;
            Shift(buffer, shift);
            read_count = Math.abs(shift);

            if (shift > 0) {
                pos += (buffer.length - shift);
            }

            start_write = 0;
            if (shift > 0) {
                start_write = buffer.length - read_count;
            }
        }

        try {
            data.seek(pos);
            //System.out.printf("read %s bytes\n", read_count);
            data.read(buffer, start_write, read_count);
            last_index = start;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int lenght() { return buffer.length; }

    public long GetFilelenght() {
        long res = 0;
        try {
            res = data.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void Dispose() {
        try {
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
