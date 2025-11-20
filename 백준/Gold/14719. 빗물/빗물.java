import java.io.*;
import java.util.*;

public class Main {

    static int N, M, count;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        count = N * M;
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        for(int j=0; j<M; j++) {
            int h = Integer.parseInt(st.nextToken());
            for(int i=0; i<h; i++) {
                map[i][j] = 1;
                count--;
            }
        }

        find();

        System.out.println(count);
    }

    static void find() {
        for(int i=N-1; i>=0; i--) {
            if(map[i][0] == 0) {
                for(int j=0; j<M; j++) {
                    if(map[i][j] == 1) break;
                    map[i][j] = 1;
                    count--;
                }
                
            }
        }

        for(int i=N-1; i>=0; i--) {
            if(map[i][M-1] == 0) {
                for(int j=M-1; j>=0; j--) {
                    if(map[i][j] == 1) break;
                    count--;
                }
            }
        }
    }

}