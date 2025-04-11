import java.io.*;
import java.util.*;

public class Main {

    static LinkedList<String> list = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());      
        for(int i=0; i<n; i++) {
            sorting(st.nextToken());
        }
        for(String s : list) {
            sb.append(s);
        }
        if(sb.charAt(0) == '0') {
            sb = new StringBuilder();
            sb.append("0");
        }
        System.out.println(sb);
    }

    public static void sorting(String n1) {
        if(list.isEmpty()) {
            list.add(n1);
            return;
        }
        find:
        for(int i=0; i<list.size(); i++) {
            String n2 = list.get(i);
            int length = Math.max(n1.length(), n2.length());
            int idx1 = 0;
            int idx2 = 0;
            for(int j=0; j<length; j++) {
                if(n1.charAt(idx1) > n2.charAt(idx2)) {
                    list.add(i, n1);
                    return;
                } else if(n1.charAt(idx1) < n2.charAt(idx2)) continue find;
                idx1++;
                idx2++;
                if(idx1 >= n1.length()) idx1 = 0;
                if(idx2 >= n2.length()) idx2 = 0;
            }

            idx1 = n1.length() -1;
            idx2 = n2.length() -1;
            for(int j=0; j<length; j++) {
                if(n1.charAt(idx1) > n2.charAt(idx2)) {
                    list.add(i, n1);
                    return;
                } else if(n1.charAt(idx1) < n2.charAt(idx2)) break;
                idx1--;
                idx2--;
                if(idx1 <= 0) idx1 = n1.length() -1;
                if(idx2 <= 0) idx2 = n2.length() -1;
            }
        }
        list.add(n1);
    }

}