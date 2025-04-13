import java.io.*;
import java.util.*;

public class Main {

    static int N, M, max=0;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }  
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                visit[i][j] = true;
                dfs(i, j, 0, map[i][j]); 
                visit[i][j] = false;
                bfs(i, j, map[i][j]);
            }
        }
        System.out.println(max);
    }

    public static void dfs(int x, int y, int depth, int sum) {
        if(depth == 3) {
            max = Math.max(max, sum);
            return;
        }
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny]) continue;

            visit[nx][ny] = true;
            dfs(nx, ny, depth+1, sum+map[nx][ny]);
            visit[nx][ny] = false;
        }
    }

    public static void bfs(int x, int y, int sum) {
        int cnt = 0;
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

            sum += map[nx][ny];
            cnt++;
        }
        if(cnt<3) return;
        if(cnt==3) {
            max = Math.max(max, sum);
            return;
        }
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            
            sum -= map[nx][ny];
            max = Math.max(max, sum);
            sum += map[nx][ny];
        }
    }

}