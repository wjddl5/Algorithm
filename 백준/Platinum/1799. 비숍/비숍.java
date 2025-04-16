import java.io.*;
import java.util.*;

public class Main {
    static int N, white, black;
    static boolean[][] map, colorMap;
    static int[][] check;
    static int[] dx={-1,-1,1,1}, dy={-1,1,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        colorMap = new boolean[N][N];
        check = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                String s = st.nextToken();
                if(s.equals("0")) {
                    map[i][j] = true;
                    check[i][j] ++;
                }
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                colorMap[i][j] = (i + j) % 2 == 0;
            }
        }
        find(0, 0, true, 0);
        find(0, 1, false, 0);
        System.out.println(white + black);
    }

    static void find(int x, int y, boolean isWhite, int cnt) {
        if(x >= N) {
            if(isWhite) white = Math.max(white, cnt);
            else black = Math.max(black, cnt);
            return;
        }

        int nx = x;
        int ny = y + 2;

        if(ny >= N) {
            nx++;
            if(nx < N) {
                if(colorMap[nx][0] == isWhite) {
                    ny = 0;
                }
                else {
                    ny = 1;
                }
            }
        }

        if(map[x][y]) {
            find(nx, ny, isWhite, cnt);
            return;
        }
        
        if(!map[x][y] && check[x][y] == 0) {
            on(x, y);
            find(nx, ny, isWhite, cnt + 1);
            off(x, y);
        }
        find(nx, ny, isWhite, cnt);
    }

    static void on(int x, int y) {
        check[x][y] += 1;
        for(int k=0; k<4; k++) {
            int n = 1;
            while(true) {
                int nx = x + dx[k] * n;
                int ny = y + dy[k] * n++;
                if(nx<0 || ny<0 || nx>=N || ny>=N) break;
                check[nx][ny] += 1;
            }
        }
    }

    static void off(int x, int y) {
        check[x][y] -= 1;
        for(int k=0; k<4; k++) {
            int n = 1;
            while(true) {
                int nx = x + dx[k] * n;
                int ny = y + dy[k] * n++;
                if(nx<0 || ny<0 || nx>=N || ny>=N) break;
                check[nx][ny] -= 1;
            }
        }
    }

}
