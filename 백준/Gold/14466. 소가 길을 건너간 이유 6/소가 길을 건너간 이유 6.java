import java.io.*;
import java.util.*;

class Main {
    static int N, K, total;
    static int[][] cowMap;
    static boolean[][][][] isRoad;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        cowMap = new int[N][N];
        isRoad = new boolean[N][N][N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;
            isRoad[r1][c1][r2][c2] = true;
            isRoad[r2][c2][r1][c1] = true;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            cowMap[r][c]++;
        }

        // 가능한 모든 조합 수
        int total = K * (K - 1) / 2; 
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && cowMap[i][j] > 0) {
                    int groupCnt = bfs(i, j);

                    // 길 없이 만날 수 있는 조합 수
                    total -= groupCnt * (groupCnt - 1) / 2;
                }
            }
        }

        System.out.println(total);
    }

    static int bfs(int startX, int startY) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        visit[startX][startY] = true;

        int count = 0;
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];

            count += cowMap[x][y];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny]) continue;
                if (isRoad[x][y][nx][ny]) continue;

                visit[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        return count;
    }
}