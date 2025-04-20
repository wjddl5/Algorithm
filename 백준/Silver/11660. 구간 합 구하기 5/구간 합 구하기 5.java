import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N+1];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = map[i][j-1] + Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            find(Integer.parseInt(st.nextToken()) -1,
                 Integer.parseInt(st.nextToken()),
                 Integer.parseInt(st.nextToken()) -1,
                 Integer.parseInt(st.nextToken())
            );
        }
        System.out.println(sb);
    }

    static void find(int x1, int y1, int x2, int y2) {
        int sum = 0;
        for(int i=x1; i<=x2; i++) {
            sum += map[i][y2] - map[i][y1-1];
        }
        sb.append(sum).append("\n");
    }

}