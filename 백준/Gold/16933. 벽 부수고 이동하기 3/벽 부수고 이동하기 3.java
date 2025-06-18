import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, min=-1;
    static boolean[][] map;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                if(str.charAt(j) == '1') map[i][j] = true;
            }
        }

        find();
        System.out.println(min);
    }

    static void find() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][][] visit = new boolean[N][M][2][K+1];
        queue.offer(new int[] {0, 0, 1, K, 1}); // x y day broke count 
        visit[0][0][1][K] = true; // x y day broke

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int day = now[2];
            int broke = now[3];
            int cnt = now[4];

            if(x==N-1 && y==M-1) {
                min = cnt;
                return; 
            }

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                int nday = 1 - day;

                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

                if(day == 1) { // 낮
                    if(map[nx][ny]) { // 벽 o
                        if(broke > 0) { // 부술 수 있으면
                            if(!visit[nx][ny][nday][broke-1]) {
                                queue.offer(new int[] {nx, ny, nday, broke-1, cnt+1});
                                visit[nx][ny][nday][broke-1] = true;             
                            }
                        } else { // 부술 수 없으면
                            if(!visit[x][y][nday][0]) {
                                queue.offer(new int[] {x, y, nday, 0, cnt+1});
                                visit[x][y][nday][0] = true;
                            }
                        }
                    } else { // 벽 x
                        if(!visit[nx][ny][nday][broke]) {
                            queue.offer(new int[] {nx, ny, nday, broke, cnt+1});
                            visit[nx][ny][nday][broke] = true;
                        }
                    }
                } else { // 밤
                    if(map[nx][ny]) { // 벽 o
                        if(!visit[x][y][nday][broke]) {
                            queue.offer(new int[] {x, y, nday, broke, cnt+1});
                            visit[x][y][nday][broke] = true;
                        }
                    } else { // 벽 x
                        if(!visit[nx][ny][nday][broke]) {
                            queue.offer(new int[] {nx, ny, nday, broke, cnt+1});
                            visit[nx][ny][nday][broke] = true;
                        }
                    } 
                }
            }
        }
    }

}
