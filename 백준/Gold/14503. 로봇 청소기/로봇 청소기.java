import java.io.*;
import java.util.*;

public class Main {

    static int N, M, cleanCount;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine()); 
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // input

        find(sx, sy, sd);

        System.out.println(cleanCount);
    }

    static void find(int x, int y, int d) {
        while(true) {

            if(map[x][y] == 0) {
                map[x][y] = 2;
                cleanCount++;
                continue;
            }

            if(!isClean(x, y)) {
                if(isBack(x, y, d)) {
                    x += dx[(d + 2) % 4];
                    y += dy[(d + 2) % 4];
                    continue;
                } else break;
            }

            int nd = d;
            for(int i=0; i<4; i++) {
                nd -= 1;
                if(nd < 0) nd = 3;

                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if(map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    d = nd;
                    break;
                }
            }
        }
    }

    static boolean isClean(int x, int y) {
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(map[nx][ny] == 0) return true;
        }
        return false;
    }

    static boolean isBack(int x, int y, int d) {
        int nd = (d + 2) % 4;
        int nx = x + dx[nd];
        int ny = y + dy[nd];
        if(map[nx][ny] == 1) return false;
        return true;
    }

}
