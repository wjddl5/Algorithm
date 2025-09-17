import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map, dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dist = new int[N][N];

        String str = null;
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], 10000);
            str = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = str.charAt(j) - 48;
            }
        }

        bfs();

        System.out.println(dist[N-1][N-1]);
    }

    static void bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0, 0});
        dist[0][0] = 0;

        while(!queue.isEmpty()) {
            int[] ar = queue.poll();
            int x = ar[0];
            int y = ar[1];
            int count = ar[2];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

                int nCount = map[nx][ny] == 0 ? 1 : 0;
                if(dist[nx][ny] > count + nCount) {
                    dist[nx][ny] = count + nCount;
                    queue.offer(new int[] {nx, ny, count + nCount});
                }
            }
        }
    }
}