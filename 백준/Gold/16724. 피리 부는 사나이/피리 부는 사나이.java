import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count;
    static int[][] map, state;
    static int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        state = new int[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                switch (str.charAt(j)) {
                    case 'U':
                        map[i][j] = 0;
                        break;
                    case 'R':
                        map[i][j] = 1;
                        break;
                    case 'D':
                        map[i][j] = 2;
                        break;
                    case 'L':
                        map[i][j] = 3;
                        break;
                }
            }
        }

        find();
        System.out.println(count);
    }

    static void find() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(state[i][j] == 0) dfs(i, j);
            }
        }
    }

    static void dfs(int x, int y) {
        if (state[x][y] == 1) {
            count++;
            return;
        }
        if (state[x][y] == 2) return;

        state[x][y] = 1; // visit

        int nx = x + dx[map[x][y]];
        int ny = y + dy[map[x][y]];

        dfs(nx, ny);

        state[x][y] = 2; // safe
    }

}