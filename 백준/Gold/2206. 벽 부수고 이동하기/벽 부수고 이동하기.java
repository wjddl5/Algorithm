import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer = -1;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visit = new boolean[2][N][M];

        String str = null;
        for(int i=0; i<N; i++) {
            str = br.readLine();
            for(int j=0; j<M; j++) {
                if(str.charAt(j) == '1') map[i][j] = 1;
            }
        }
        
        bfs();

        System.out.println(answer);
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {0, 0, 0, 1});
        visit[1][0][0] = true;

        while(!queue.isEmpty()) {
            int[] ar = queue.poll();
            int x = ar[0];
            int y = ar[1];
            int cnt = ar[2];
            int isBreak = ar[3];

            if(x == N-1 && y == M-1) {
                answer = cnt + 1;
                break;
            }

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || visit[isBreak][nx][ny]) continue;

                if(map[nx][ny] == 0) {
                    queue.offer(new int[] {nx, ny, cnt+1, isBreak});
                    visit[isBreak][nx][ny] = true;
                } else {
                    if(isBreak > 0) {
                        queue.offer(new int[] {nx, ny, cnt+1, 0});
                        visit[0][nx][ny] = true;
                    }
                }
            }
        }
    }

}