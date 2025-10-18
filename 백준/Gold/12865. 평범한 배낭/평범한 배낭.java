import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[][] items, bag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new int[N+1][2];
        bag = new int[N+1][K+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        dp();

        System.out.println(bag[N][K]);
    }

    static void dp() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {

                if(items[i][0] > j) {
                    bag[i][j] = bag[i-1][j];
                } else {
                    bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j - items[i][0]] + items[i][1]);
                }
            }
        }
    }

}
