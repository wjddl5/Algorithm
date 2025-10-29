import java.io.*;
import java.util.*;

public class Main {

    static int[][] count;
    static String word;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        word = br.readLine();
        count = new int[word.length() + 1][26];

        setCount();
        
        int q = Integer.parseInt(br.readLine());

        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int cnt = find(s, start + 1, end + 1);
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void setCount() {
        int length = word.length();
        for(int i=1; i<=length; i++) {
            char c = word.charAt(i-1);
            int idx = c - 97;

            count[i][idx] = 1;
            for(int j=0; j<26; j++) {
                count[i][j] += count[i-1][j];
            }
        }
    }

    static int find(String s, int start, int end) {
        int idx = s.charAt(0) - 97;
        int cnt = count[end][idx] - count[start - 1][idx];
        
        return cnt;
    }

}