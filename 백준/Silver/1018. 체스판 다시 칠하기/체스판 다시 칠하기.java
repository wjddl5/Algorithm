import java.io.*;
import java.util.*;

public class Main {
    static int n, m, min=Integer.MAX_VALUE;
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<m; j++) {
                if(str.charAt(j)=='W') map[i][j] = true;
            }
        }
        for(int i=0; i<n-7; i++) {
            for(int j=0; j<m-7; j++) {
                getCount(i, j);
            }
        }
        System.out.println(min);
    }

    static void find() {
        for(int i=0; i<n-7; i++) {
            for(int j=0; j<m-7; j++) {
                getCount(i, j);
            }
        }
    }

    static void getCount(int x, int y) {
        int count = 0;
        boolean color = map[x][y];
        for (int i=x; i<x+8; i++) {
            for (int j=y; j<y+8; j++) {
                if (map[i][j] != color) count++;              
                color = !color;
            }
            color = !color;
        }
        count = Math.min(count, 64-count);
        min = Math.min(min, count);
    }

}