import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int[] dx={-1,0,1,0}, dy={0,1,0,-1};
        
        int cnt = 0;
        boolean[][] visit = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visit[i][j]) continue;
                cnt++;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {i, j});
                visit[i][j] = true;
                while (!queue.isEmpty()) {  
                    int[] xy = queue.poll();
                    int x = xy[0];
                    int y = xy[1];
                    char color = map[x][y];
                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny] || color != map[nx][ny]) continue;

                        queue.offer(new int[] {nx, ny});
                        visit[nx][ny] = true;
                    }
                }
            }
        }

        int cnt2 = 0;
        visit = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visit[i][j]) continue;
                cnt2++;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {i, j});
                visit[i][j] = true;
                while (!queue.isEmpty()) {  
                    int[] xy = queue.poll();
                    int x = xy[0];
                    int y = xy[1];
                    char color = map[x][y];
                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny]) continue;

                        if(color=='B') {
                            if(map[nx][ny]=='B') {
                                queue.offer(new int[] {nx, ny});
                                visit[nx][ny] = true;
                            }
                        } else {
                            if(map[nx][ny] != 'B') {
                                queue.offer(new int[] {nx, ny});
                                visit[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(cnt+" "+cnt2);   
    }

}