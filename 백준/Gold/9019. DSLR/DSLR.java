import java.io.*;
import java.util.*;

public class Main {

    static int A, B;
    static String[] ar, dslr = {"D", "S", "L", "R"};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            ar = new String[10000];
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            cal(A);
            sb.append(ar[B]).append("\n");
        }
        System.out.println(sb);
    }

    public static void cal(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        ar[n] = "";
        while(!queue.isEmpty()) {
            int num = queue.poll();
            for(int i=0; i<4; i++) {
                int tmpnum = dslr(num, dslr[i]);
                if(ar[tmpnum] == null) {
                    queue.offer(tmpnum);
                    ar[tmpnum] = ar[num] + dslr[i];
                }
                if(tmpnum == B) return;
            } 
        }
    }

    public static int dslr(int n, String str) {
        switch (str) {
            case "D":
                n = (n*2)%10000;
                break;
            case "S":
                if(n == 0) n = 9999;
                else n = n-1;
                break;
            case "L":
                n = (n%1000*10) + (n/1000);
                 break;
            case "R":
                n = (n%10*1000) + (n/10);
        }
        return n;
    }

}