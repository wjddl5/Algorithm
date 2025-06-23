import java.io.*;
import java.util.*;

public class Main {
    static int N, M, min= -1;
    static char[][] map;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        String str;
        int x = 0;
        int y = 0;
        for(int i=0; i<N; i++) {
            str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                map[i][j] = c; 
                if(c == '0') {
                    x = i;
                    y = j;
                }
            }
        }

        bfs(x ,y);
        System.out.println(min);
    }

    static void bfs(int startX, int startY) {     
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visit = new boolean[N][M][64];

        queue.offer(new int[] {startX, startY, 0, 0});
        visit[startX][startY][0] = true;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int count = tmp[2];
            int key = tmp[3];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny][key] || map[nx][ny] == '#') continue;
                
                char point = map[nx][ny];
                int nKey = key;
                if(point >= 'a' && point <= 'f') {
                    nKey |= 1 << (point - 'a');
                }
                else if(point >= 'A' && point <= 'F') {
                    if((key & 1 << (point - 'A')) == 0) continue;
                }
                else if(point == '1') {
                    min = count + 1;
                    return;
                }  

                queue.offer(new int[] {nx, ny, count+1, nKey});
                visit[nx][ny][nKey] = true;
            }
        }
    }

}