import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max;
    static int[][] orimap; 
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    static ArrayList<int[]> virus = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        orimap = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int n = Integer.parseInt(st.nextToken());
                orimap[i][j] = n;
                if(n == 2) virus.add(new int[] {i, j});
            }
        }
        
        dfs(0);
        System.out.println(max);
    }

    static void dfs(int depth) {
        if(depth == 3) {
            bfs();
            return;
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(orimap[i][j] == 0) {
                    orimap[i][j] = 1;
                    dfs(depth + 1);
                    orimap[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        int[][] map = copyMap();
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];

        for(int[] v : virus) {
            queue.offer(v);
            visit[v[0]][v[1]] = true;
        }

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny] || map[nx][ny] != 0) continue;

                queue.offer(new int[] {nx, ny});
                map[nx][ny] = 2;
                visit[nx][ny] = true;             
            }
        }
        max = Math.max(max, getSafeZone(map));
    }

    static int getSafeZone(int[][] map) {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    static int[][] copyMap() {
        int[][] map = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = orimap[i][j];
            }
        }
        return map;
    }

}