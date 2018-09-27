package app.SubstringFinder;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

class IndexesTable {
    private HashMap<Character, Integer> offsetTable;
    private String sourse;
    private int _lenght;

    public IndexesTable(String template){
        offsetTable = new HashMap<Character, Integer>();
        _lenght = template.length();
        sourse = template;

        for (int i = _lenght-2; i >= 0; i--) {
            Add(template.charAt(i), _lenght - i - 1);
        }
        Add(template.charAt(_lenght-1), _lenght);
    }

    private void Add(char _char, int position) {
        if (!offsetTable.containsKey(_char)) {
            offsetTable.put(_char, position);
        }
    }
    public int Get(char _char) {
        if (offsetTable.containsKey(_char)) {
            return offsetTable.get(_char);
        }
        else {
            return _lenght;
        }
    }

}

public class Finder {
    public static ArrayList<Integer> GetEntries(String source, String template) throws SubstringNotFound {
        int sourceLen = source.length();
        int templateLen = template.length();
        if (templateLen > sourceLen) {
            throw new SubstringNotFound("small text");
        }
        IndexesTable offsetTable = new IndexesTable(template);
        ArrayList<Integer> entery_indexes = new ArrayList<Integer>();
        int sourse_index = templateLen-1;
        int offset_at_last = 0;
        while (sourse_index<=sourceLen-1) {
            while(source.charAt(sourse_index-offset_at_last) == template.charAt(templateLen-(1+offset_at_last))){
                offset_at_last++;
                if (offset_at_last == templateLen){
                    entery_indexes.add(sourse_index-templateLen+1);
                    offset_at_last=0;
                    break;
                }
            }
            sourse_index+=offsetTable.Get(source.charAt(sourse_index));
            offset_at_last=0;
        }
        if (entery_indexes.size() == 0) { throw new SubstringNotFound("hasn`t substring"); }
        return entery_indexes;


//        for(Entry<Character, Integer> item : offsetTable.entrySet()){
//            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
//        }
    }

    private static int ReadShift(char[] array, int shift, InputStreamReader source) {
        int res = 0;
        for (int i = 0; i < array.length-shift; i++) {
            array[i] = array[i+shift];
        }
        try {
            res = source.read(array, array.length-shift, shift);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static ArrayList<Integer> GetEntries(InputStreamReader source, String template) throws SubstringNotFound {
        char[] template_arr = template.toCharArray();
        char[] source_buff = new char[template_arr.length];
        int read_count = ReadShift(source_buff, source_buff.length, source);
        if (read_count < source_buff.length) {throw new SubstringNotFound("small text");}

        IndexesTable offsetTable = new IndexesTable(template);
        ArrayList<Integer> entery_indexes = new ArrayList<Integer>();

        int sourse_index = read_count;
        int offset_at_last = 0;
        while (true) {
            while(source_buff[source_buff.length-(1+offset_at_last)] == template_arr[template_arr.length-(1+offset_at_last)]) {
                offset_at_last++;
                if (offset_at_last == template_arr.length){
                    entery_indexes.add(sourse_index-template_arr.length);
                    offset_at_last=0;
                    break;
                }
            }
            int shift = offsetTable.Get(source_buff[source_buff.length-(1+offset_at_last)]);
            read_count = ReadShift(source_buff, shift, source);
            if (read_count != shift) { break; }
            sourse_index+=read_count;
            offset_at_last=0;
        }
        if (entery_indexes.size() == 0) { throw new SubstringNotFound("hasn`t substring"); }
        return entery_indexes;
    }
}
