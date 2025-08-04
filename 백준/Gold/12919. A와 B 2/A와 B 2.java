import java.io.*;
import java.util.*;

public class Main {

    static String s;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        s = br.readLine();
        String t = br.readLine();

        dfs(t);

        System.out.println(0);
    }

    static void dfs(String t) {
        if(t.length() < s.length()) return;
        if(t.equals(s)) {
            System.out.println(1);
            System.exit(0);
        }
        if(t.endsWith("A")) {
            String str = t.substring(0, t.length()-1);
            dfs(str);
        }

        if(t.startsWith("B")) {
            String str = reverse(t.substring(1,  t.length()));
            dfs(str);
        }
    }

    static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

}