import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] house, cost;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        house = new int[N][3];
        cost = new int[N][3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cost[0][0] = house[0][0];
        cost[0][1] = house[0][1];
        cost[0][2] = house[0][2];

        int min = Math.min(Math.min(dp(N-1,0), dp(N-1,1)), dp(N-1,2));
        System.out.println(min);
    }

    static int dp(int n, int color) {
        if(cost[n][color] == 0) {
            switch (color) {
                case 0:
                    return cost[n][color] = house[n][color] + Math.min(dp(n-1, 1), dp(n-1, 2));
                case 1:
                    return cost[n][color] = house[n][color] + Math.min(dp(n-1, 0), dp(n-1, 2));
                case 2:
                    return cost[n][color] = house[n][color] + Math.min(dp(n-1, 0), dp(n-1, 1));

            }
        }
        return cost[n][color];
    }

}