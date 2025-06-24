import java.io.*;
import java.util.*;

public class Main {
    static int N, M, high, total;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M][10];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                int c = str.charAt(j) - 48;
                map[i][j] = c;
                high = Math.max(high, c);
            }
        }
        find();
        System.out.println(total);
    }

    static void find() {
        for(int h=2; h<=high; h++) {
            for(int i=1; i<N-1; i++) {
                for(int j=1; j<M-1; j++) {
                    if(visit[i][j][h] || map[i][j]>=h) continue;             
                    total += bfs(i, j, h);
                }
            }
        }
    }

    static int bfs(int i, int j, int h) {
        int count = 1;
        boolean chk = true;
        Queue<int[]> queue = new ArrayDeque<>(); 
        queue.offer(new int[] {i, j});
        visit[i][j][h] = true;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx>=0 && ny>=0 && nx<N && ny<M) {
                    if(visit[nx][ny][h] || map[nx][ny]>=h) continue;
                    queue.offer(new int[] {nx, ny});
                    visit[nx][ny][h] = true;
                    count++;
                } else {
                    chk = false;
                }
            
            }
        }
        return chk ? count : 0;
    }

}