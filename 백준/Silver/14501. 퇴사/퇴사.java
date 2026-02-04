import java.io.*;
import java.util.*;

class Main {

    static int N, max;
    static int[][] works;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        works = new int[N][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            works[i][0] = Integer.parseInt(st.nextToken());
            works[i][1] = Integer.parseInt(st.nextToken());
        }

        find(0, 0);

        System.out.println(max);
    }

    static void find(int d, int p) {
        if(d > N) return;
        if(d == N) {
            max = Math.max(max, p);
            return;
        }
        find(d+works[d][0], p+works[d][1]);
        find(d+1, p);
    }

}