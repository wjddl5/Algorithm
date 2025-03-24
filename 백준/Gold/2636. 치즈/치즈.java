import java.io.*;
import java.util.*;

public class Main {

    static int[][] ar;
    static boolean[][] visit;
    static int N, M, count, resCnt, resTime;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ar = new int[N][M];  

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int n = Integer.parseInt(st.nextToken());
                ar[i][j] = n; 
                if(n==1) count++;
            }
        }

        while(count>0){
            bfs();
        }
        System.out.println(resTime);
        System.out.println(resCnt);
    }

    public static void bfs() {
        resCnt = count;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        visit = new boolean[N][M];

        queue.offer(new int[] {0, 0});
        visit[0][0] = true;

        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny]) continue;
                if(ar[nx][ny] == 1) {
                    ar[nx][ny] = 0;
                    visit[nx][ny] = true;
                    count--;
                    continue;
                }
                queue.offer(new int[] {nx, ny});
                visit[nx][ny] = true;
            }
        }

        resTime++;
    }

}