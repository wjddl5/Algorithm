import java.io.*;
import java.util.*;

public class Main {

    static int N, M, time = Integer.MAX_VALUE, pointCnt, totalCnt;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    static Point[] points;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        ArrayList<Point> list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;

                if(n == 2) {
                    list.add(new Point(i, j));
                    pointCnt++;
                }
                if(n != 1) totalCnt++; 
            }
        }

        v = new boolean[pointCnt];
        points = new Point[pointCnt];
        list.toArray(points);

        setPoint(0);



        System.out.println(time < Integer.MAX_VALUE ? time : -1);
    }

    static void setPoint(int depth) {
        if(depth == M) {
            getTime();
            return;
        }
        for(int i=depth; i<pointCnt; i++) {
            if(v[i]) continue;
            v[i] = true;
            setPoint(depth + 1);
            v[i] = false;
        }
    }

    static void getTime() {
        int max = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];

        for(int i=0; i<pointCnt; i++) {
            if(!v[i]) continue;

            Point point = points[i];
            queue.offer(new int[] {point.x, point.y, 0});
            visit[point.x][point.y] = true;
        }

        int vCnt = 0;
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            int cnt = p[2];

            if(cnt > time) return;

            max = Math.max(max, cnt);
            vCnt++;

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny] || map[nx][ny] == 1) continue;

                queue.offer(new int[] {nx, ny, cnt + 1});
                visit[nx][ny] = true;
            }
        }

        if(vCnt == totalCnt) time = Math.min(time, max);
    }

}

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}