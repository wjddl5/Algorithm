import java.io.*;
import java.util.*;

public class Main {
    
    static int K, N, M, cnt=Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][][] v;
    static int[] dx={-1,0,1,0, -2,-1,1,2,2,1,-1,-2}, dy={0,1,0,-1, 1,2,2,1,-1,-2,-2,-1};
    static Queue<int[]> q = new ArrayDeque<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[K+1][N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                if(st.nextToken().equals("1")) map[i][j] = 1;
            }
        }
        move();
        System.out.println(cnt==Integer.MAX_VALUE ? -1 : cnt);
    }

    public static void move() {   
        int count = 0;
        q.offer(new int[] {0, 0, K});
        v[K][0][0] = true;

        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int i=0; i<qSize; i++) {
                int[] xy = q.poll();
                int x = xy[0];
                int y = xy[1];
                int j = xy[2];
                
                if(x==N-1 && y==M-1) cnt = Math.min(cnt, count);
                moveOne(x, y, j);
                if(j > 0) jump(x, y, j-1);
            }
            count++;
        }
    }

    public static void moveOne(int x, int y, int j) {
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(!boundCheck(nx, ny, j)) continue;
            q.offer(new int[] {nx, ny, j});
            v[j][nx][ny] = true;
        }
    }

    public static void jump(int x, int y, int j) {
        for(int k=4; k<12; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(!boundCheck(nx, ny, j)) continue;
            q.offer(new int[] {nx, ny, j});
            v[j][nx][ny] = true;
        }
    }

    public static boolean boundCheck(int x, int y, int j) {
        if(x<0 || y<0 || x>=N || y>=M || v[j][x][y] || map[x][y]==1) return false;
        return true;
    }
  
}