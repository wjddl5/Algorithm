import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max;
    static int[][] map;
    static int[] dx={-1,-1,0,1,1,1,0,-1},dy={0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        for(int i=0; i<N; i++) {
            st =  new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        find();
        System.out.println(max);
    }
    static void find() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }
    }

    static void bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];

        queue.offer(new int[] {i, j, 0});
        visit[i][j] = true;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int cnt = tmp[2];

            for(int k=0; k<8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny]) continue;

                if(map[nx][ny] == 1) {
                    max = Math.max(max, cnt + 1);
                    return;
                }

                queue.offer(new int[] {nx, ny, cnt+1});
                visit[nx][ny] = true;
            }
        }
    }

}