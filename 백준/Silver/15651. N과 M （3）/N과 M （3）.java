import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] visit;
    static int[] ar;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ar = new int[M];
        visit = new boolean[M];

        find(0);
        System.out.println(sb);

        br.close();
    }

    public static void find(int depth) {
        if(depth == M) {
            for(int i : ar) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
            for(int num=1; num<=N; num++) {
                ar[depth] = num;
                find(depth+1);
            }
    }
}