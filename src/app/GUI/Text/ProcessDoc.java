package app.GUI.Text;

import java.io.*;

class viewer {
    private bytebuffer data;
    private int start_decode = 0;
    private int count_decode;
    private IBlock func;

    public viewer(int buffer_lenght, RandomAccessFile read_file) {
        data = new bytebuffer(buffer_lenght*2, read_file);
        count_decode = buffer_lenght;
        func = (index) -> {
            return (index-(index%buffer_lenght));
        };
    }

    public String GetBitOfText(long index) {
        long start_buffer = func.GetStartBy(index);

        long overload = start_buffer+data.lenght()/2;

        if (overload > data.GetFilelenght()) {
            int value_overload = (int)(overload - data.GetFilelenght());
            start_decode = (int)(index - start_buffer);
            count_decode = data.lenght()/2-value_overload;
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

    /*
    public int find(long x, long[] array) {
        int i = -1;
        if (array != null) {
            int low = 0, high = array.length, mid;
            while (low < high) {
                mid = (low + high)/2; // Можно заменить на: (low + high) >>> 1, чтоб не возникло переполнение целого
                if (x == array[mid]) {
                    i = mid;
                    break;
                } else {
                    if (x <= array[mid]) {
                        high = mid;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        return i;
    }
    */
}

class bytebuffer {
    public byte[] buffer;
    private RandomAccessFile data;
    private long last_index;

    public bytebuffer(int buffer_lenght, RandomAccessFile data) {
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
            System.out.printf("read %s bytes\n", read_count);
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

public class ProcessDoc {
    private viewer data;
    //private RandomAccessFile randomAccessFile;

//    private byte[] data_cash;
//    private long start_count;
//    int real_read_cout;

    public ProcessDoc(File file, int count) {
        try {
            data = new viewer(count, new RandomAccessFile(file, "r"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


//        try {
//            randomAccessFile = new RandomAccessFile(file, "r");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        data_cash = new byte[count];
//        start_count = 0;
//        try {
//            real_read_cout = randomAccessFile.read(data_cash, 0, count);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public long Getlenght() {
        return data.GetFileLenght();
        /*
        long res = 0;
        try {
             res = randomAccessFile.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
        */
    }


    private static void Shift(byte[] array, int shift) {
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

    /*
    public String GetCurrentData() {
        try {
            return new String(data_cash, 0, real_read_cout, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
    */

    public String GetBitOfText(double persent) {
        long n_start = (long)(data.GetFileLenght()*persent);
        return data.GetBitOfText(n_start);

        /*
        long n_start = (long)(((double)Getlenght())*persent);
        if (n_start == start_count) {
            return GetCurrentData();
        }
        long pos = n_start;
        int shift = (int)(n_start - start_count);
        Shift(data_cash, shift);
        int read_count = Math.abs(shift);

        if (shift > 0) {
            pos+=(real_read_cout-shift);
            real_read_cout -= read_count;
        }

        int start_write = 0;
        if (shift > 0) {
            start_write = data_cash.length - read_count;
        }

        try {
            randomAccessFile.seek(pos);
            int real_read = randomAccessFile.read(data_cash, start_write, read_count);
            if (read_count > 0) {
                real_read_cout = Math.min(real_read+real_read_cout, data_cash.length);
            }
            System.out.printf("current read = %s, need read = %s\nread count = %s\n", real_read, read_count, real_read_cout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start_count = n_start;
        return GetCurrentData();
        */

        /*
        String res = null;
        long pos = n_start;
        long shift_long = n_start - start_count;
        int start_write = 0;
        int read_count = 0;
        int data_was_shift = 0;

        if (Math.abs(shift_long) >= real_read_cout) {
            read_count = data_cash.length;
            data_was_shift = 0;
        }
        else {
            int shift = (int)shift_long;
            Shift(data_cash, shift);
            if (shift > 0) {
                pos+=shift;
            }
            read_count = Math.abs(shift);
            if (shift > 0) {
                start_write = data_cash.length - read_count;
            }
            data_was_shift = Math.abs(shift);
        }

        try {

            randomAccessFile.seek(pos);
            int real_read = randomAccessFile.read(data_cash, start_write, read_count);
            real_read_cout -= data_was_shift-real_read;
            res = GetCurrentData();

        } catch (IOException e) {
            e.printStackTrace();
        }

        start_count = n_start;
        return res;
        */


        /*
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
        */
    }

    public void Dispose() {
        data.Dispose();
        /*
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

}