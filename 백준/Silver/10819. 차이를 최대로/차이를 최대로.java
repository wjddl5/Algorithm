import java.io.*;
import java.util.*;

public class Main {

    static int N, max;
    static int[] init_ar, ar;
    static boolean[] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        init_ar = new int[N];
        ar = new int[N];
        visit = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            init_ar[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(max);
    }

    static void dfs(int depth) {
        if(depth == N) {
            getMax();
            return;
        }

        for(int i=0; i<N; i++) {
            if(visit[i]) continue;

            visit[i] = true;
            ar[depth] = init_ar[i];
            dfs(depth+1);
            visit[i] = false;
        }

    }

    static void getMax() {
        int sum = 0;
        for(int i=1; i<N; i++) {
            sum += Math.abs(ar[i-1] - ar[i]);
        }
        max = Math.max(max, sum);
    }
  
}