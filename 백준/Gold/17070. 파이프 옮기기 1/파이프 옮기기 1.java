import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int N, res;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        go(0, 1, 1);
        System.out.println(res);
    }

    public static void go(int x, int y, int dir) {
        if(x==N-1 && y==N-1) {
            res++;
            return;
        }

        switch (dir) {
            case 1: // 가로
                if(canMove(x, y, 1)) go(x, y+1, 1);
                if(canMove(x, y, 3)) go(x+1, y+1, 3);
                break;
            case 2: // 세로
                if(canMove(x, y, 2)) go(x+1, y, 2);
                if(canMove(x, y, 3)) go(x+1, y+1, 3);
                break;
            case 3: // 대각선
                if(canMove(x, y, 1)) go(x, y+1, 1);
                if(canMove(x, y, 2)) go(x+1, y, 2);
                if(canMove(x, y, 3)) go(x+1, y+1, 3);
                break;
        }
        
    }

    public static boolean canMove(int x, int y, int dir) {
        switch (dir) {
            case 1:
                if(checkBound(x, y+1) && map[x][y+1] == 0) return true;
                break;
            case 2:
                if(checkBound(x+1, y) && map[x+1][y] == 0) return true;
                break;
            case 3:
                if(checkBound(x+1, y+1) && map[x+1][y]==0 && map[x][y+1]==0 && map[x+1][y+1]==0) return true;
                break;
        }
        return false;
    }

    public static boolean checkBound(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx>=N || ny >=N) return false;
        return true;
    }

}