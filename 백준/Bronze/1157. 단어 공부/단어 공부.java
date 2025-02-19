import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader sb = new BufferedReader(new InputStreamReader(System.in));

        String str = sb.readLine().toUpperCase();
        char[] ar = new char[str.length()];

        for(int i=0; i<str.length(); i++) {
            ar[i] =  str.charAt(i);
        }

        Map<Character, Integer> map = new HashMap<>();
        for(char c : ar) {
            if(map.containsKey(c)) {
                int i =map.get(c);
                map.put(c, i+1);
            }else {
                map.put(c, 1);
            }
        }

        int temp = 0;
        boolean chk = false;
        char res = ' ';
        for(Map.Entry<Character, Integer> en : map.entrySet()){
            int val = en.getValue();
            if(val > temp) {
                temp = val;
                chk = false;
                res = en.getKey();
            }else if (val == temp){
                chk = true;
            }
        }

        if(chk) System.out.println("?");
        else System.out.println(res);
    }

}