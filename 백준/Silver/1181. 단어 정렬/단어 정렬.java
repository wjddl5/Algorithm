import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] ar = new String[n];
        for(int i=0; i<n; i++) {
            ar[i] = br.readLine();
        }

        Arrays.sort(ar, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(s1.length() == s2.length()) return s1.compareTo(s2);
                return s1.length() - s2.length();
            }
        });
             
        sb.append(ar[0]).append("\n");
        for(int i=1; i<n; i++) {
            if(!ar[i].equals(ar[i-1])) sb.append(ar[i]).append("\n");
        }
        System.out.println(sb);
    }

}