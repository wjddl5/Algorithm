import java.io.*;
import java.util.*;

public class Main {
    static int N, S, X, Y;
    static int[][] map;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        ArrayList<Virus> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0)
                    continue;
                map[i][j] = num;
                list.add(new Virus(num, i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        Queue<Virus> queue = new ArrayDeque<>();
        list.sort((o1, o2) -> o1.num - o2.num);
        for (Virus v : list) {
            queue.offer(v);
        }

        while (S-- > 0) {
            int size = queue.size();
            while (size-- > 0) {
                Virus virus = queue.poll();
                int num = virus.num;
                int x = virus.x;
                int y = virus.y;

                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] > 0)
                        continue;

                    map[nx][ny] = num;
                    queue.offer(new Virus(num, nx, ny));
                }
            }
        }

        System.out.println(map[X][Y]);
    }
}

class Virus {
    int num;
    int x;
    int y;

    Virus(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}
