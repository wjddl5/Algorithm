import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count, time;
    static int[][] map;
    static int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 1) {
                    map[i][j] = 1;
                    count ++;
                }
            }
        }
        while(count > 0) {
            air();
            dis();
            time++;
        }
        System.out.println(time);
    }

    static void air() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];

        queue.offer(new int[] {0, 0});
        visit[0][0] = true;
        map[0][0] = 2;

        while(!queue.isEmpty()) {
            int[] xy = queue.poll();
            int x = xy[0];
            int y = xy[1];
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny] || map[nx][ny]==1) continue;
                queue.offer(new int[] {nx, ny});
                visit[nx][ny] = true;
                map[nx][ny] = 2;
            }
        }
    }

    static void dis() {
        for(int i=1; i<N-1; i++) {
            for(int j=1; j<M-1; j++) {
                if(map[i][j] != 1) continue;
                int cnt = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(map[nx][ny] == 2) cnt ++;
                }
                if(cnt >= 2) {
                    map[i][j] = 0;
                    count --;
                }
            }
        }
    }
   

}
