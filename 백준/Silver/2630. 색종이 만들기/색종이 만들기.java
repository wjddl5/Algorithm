import java.io.*;
import java.util.*;

public class Main {
    static int white, blue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                if(st.nextToken().equals("1")) map[i][j] = true;
            }
        }
        find(map);
        System.out.println(white + "\n" + blue);
    }

    static void find(boolean[][] map) {
        if(isSame(map)) {
            if(map[0][0]) blue++;
            else white++;
            return;
        }
        for(int k=0; k<4; k++) {
            boolean[][] d_map = getD_map(map, k);
            find(d_map);
        }
    }

    static boolean[][] getD_map(boolean[][] map, int k) {
        int leng = map.length / 2;
        boolean[][] d_map = new boolean[leng][leng];
        int ii = 0;
        switch (k) {
            case 0:
                for(int i=0; i<leng; i++) {
                    for(int j=0; j<leng; j++) {
                        d_map[i][j] = map[i][j];
                    }
                }
            break;
            case 1:
                for(int i=0; i<leng; i++) {
                    int jj = 0;
                    for(int j=leng; j<map.length; j++) {
                        d_map[ii][jj++] = map[i][j];
                    }
                    ii++;
                }
            break;
            case 2:
                for(int i=leng; i<map.length; i++) {
                    int jj = 0;
                    for(int j=0; j<leng; j++) {
                        d_map[ii][jj++] = map[i][j];
                    }
                    ii++;
                }
            break;
            case 3:
                for(int i=leng; i<map.length; i++) {
                    int jj = 0;
                    for(int j=leng; j<map.length; j++) {
                        d_map[ii][jj++] = map[i][j];
                    }
                    ii++;
                }
            break;
        }
        return d_map;
    }

    static boolean isSame(boolean[][] map) {
        boolean chk = map[0][0];
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map.length; j++) {
                if(map[i][j] != chk) return false;
            }
        }
        return true;
    }

}