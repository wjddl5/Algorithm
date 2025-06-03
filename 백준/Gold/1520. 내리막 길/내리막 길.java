import java.io.*;
import java.util.*;

public class Main {
    static int N, M, res;
    static int[][] map, dp;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;  j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int[] ar : dp) Arrays.fill(ar, -1);

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    static int dfs(int x, int y) {
        if(x == N-1 && y == M-1) return 1;
        if(dp[x][y] > -1) return dp[x][y];

        dp[x][y] = 0;

        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] >= map[x][y]) continue;

            dp[x][y] += dfs(nx, ny);
        }
        return dp[x][y];
    }

}
