import java.io.*;
import java.util.*;

public class Main {
    static int N, cnt, min=Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setIslandNum();
        bfs();

        System.out.println(min);
    }

    static void setIslandNum() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        int num = 2;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] > 1 || map[i][j] == 0) continue;

                q.offer(new int[] {i, j});
                visit[i][j] = true;
                map[i][j] = num;

                while(!q.isEmpty()) {
                    int[] tmp = q.poll();
                    int x = tmp[0];
                    int y = tmp[1];

                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(isOutOfBound(nx, ny) || visit[nx][ny] || map[nx][ny] == 0) continue;

                        q.offer(new int[] {nx, ny}); 
                        visit[nx][ny] = true;
                        map[nx][ny] = num;
                    }
                }  
                num++;
            }
        }
        cnt = num;
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[N][N][cnt+1];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] < 2) continue;

                int num = map[i][j];
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(isOutOfBound(nx, ny) || map[nx][ny] != 0) continue;

                    q.offer(new int[] {i, j, 0, num});
                    visit[i][j][num] = true;
                    break;
                }

            }
        }

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int length = tmp[2];
            int num = tmp[3];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(isOutOfBound(nx, ny) || visit[nx][ny][num]) continue;

                if(map[nx][ny] == 0) {
                    q.offer(new int[] {nx, ny, length + 1, num});
                    visit[nx][ny][num] = true;
                } else if(map[nx][ny] > 0 && map[nx][ny] != num) {
                    min = Math.min(min, length);
                }             
            }
        }
    }

    static boolean isOutOfBound(int x, int y) {
        return !(x>=0 && y>=0 && x<N && y<N); 
    }

}
