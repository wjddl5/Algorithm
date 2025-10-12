import java.io.*;
import java.util.*;

public class Main {
    
    static int count, N, K;
    static int[] kits;
    static boolean[] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kits = new int[N];
        visit = new boolean[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        find(0, 500);
        
        System.out.println(count);
    }

    static void find(int depth, int samde) {
        if(samde < 500) return;
        if(depth == N) {
            count++;
            return;
        }

        for(int i=0; i<N; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            find(depth + 1, samde - K + kits[i]);
            visit[i] = false;
        }
    }
}