package app;
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
    public static ArrayList<Integer> GetEntries(String source, String template) {
        int sourceLen = source.length();
        int templateLen = template.length();
        if (templateLen > sourceLen) {
            return null;
        }
        IndexesTable offsetTable = new IndexesTable(template);
        //HashMap<Character, Integer> offsetTable = new HashMap<Character, Integer>();
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
        return entery_indexes;
//        for(Entry<Character, Integer> item : offsetTable.entrySet()){
//            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
//        }
    }
}
