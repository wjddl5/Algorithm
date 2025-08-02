import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] ar, resAr;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        ar = new int[N];
        resAr = new int[N];
        visit = new boolean[N];

        for(int i=0; i<N; i++) {
            ar[i] = i + 1;
        }

        dfs(0);
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if(depth == N) {
            for(int i : resAr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++) {
            if(visit[i]) continue;

            resAr[depth] = ar[i];
            visit[i] = true;
            dfs(depth + 1);
            visit[i] = false;
        }
    }

}