import java.io.*;
import java.util.*;

public class Main {
    static int N, count;
    static int[][] map;
    static int[] dx={0,1,0,-1}, dy={-1,0,1,0};
    
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
        start(N/2, N/2, 0);
        System.out.println(count);
    }

    static void start(int x, int y, int dis) {
        int length = 1;
        while(length <= N) {
            for(int i=0; i<2; i++) {
                for(int k=1; k<=length; k++) {
                    x = x + dx[dis];
                    y = y + dy[dis];
                    move(x, y, dis);
                    if(x==0 && y==0) return;
                }
                dis = (dis+1) % 4;
            }
            length ++;
        }
    }

    static int moveSand;
    static void move(int x, int y, int dis) {
        moveSand = 0;
        int sand = map[x][y];
        if(dis==0 || dis==2) {
            // 10%
            wind(x+1, y+dy[dis],       sand/10);
            wind(x-1, y+dy[dis],       sand/10);
            // 7%
            wind(x+1, y,               sand*7/100);
            wind(x-1, y,               sand*7/100);
            // 5%
            wind(x, y+dy[dis]*2,       sand/20);
            // 2%
            wind(x+2, y,               sand/50);
            wind(x-2, y,               sand/50);
            // 1%
            wind(x+1, y+dy[dis]*-1,    sand/100);
            wind(x-1, y+dy[dis]*-1,    sand/100);
            // a%
            wind(x, y+dy[dis],         sand-moveSand);
        } else {
            // 10%
            wind(x+dx[dis], y+1,       sand/10);
            wind(x+dx[dis], y-1,       sand/10);
            // 7%
            wind(x, y+1,               sand*7/100);
            wind(x, y-1,               sand*7/100);
            // 5%
            wind(x+dx[dis]*2, y,       sand/20);
            // 2%
            wind(x, y+2,               sand/50);
            wind(x, y-2,               sand/50);
            // 1%
            wind(x+dx[dis]*-1, y+1,    sand/100);
            wind(x+dx[dis]*-1, y-1,    sand/100);
            // a%
            wind(x+dx[dis], y,         sand-moveSand);
        }
        map[x][y] = 0;
    }

    static void wind(int x, int y, int sand) {
        if(isOutBound(x, y)) count += sand;
        else {
            map[x][y] += sand;
        }
        moveSand += sand;
    }

    static boolean isOutBound(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) return true;
        return false; 
    }

}
