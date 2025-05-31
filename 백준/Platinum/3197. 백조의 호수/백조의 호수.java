import java.io.*;
import java.util.*;

public class Main {
    static int N, M, day;
    static char[][] map;
    static boolean[][] swan_visit;
    static boolean[][] water_visit;
    static Queue<int[]> swan_queue;
    static Queue<int[]> water_queue;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        swan_visit = new boolean[N][M];
        water_visit = new boolean[N][M];
        swan_queue = new ArrayDeque<>();
        water_queue = new ArrayDeque<>();

        String str;
        boolean chk = false;
        int sx=0, sy=0;
        for(int i=0; i<N; i++) {
            str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                map[i][j] = c;
                if(c == '.') water_queue.offer(new int[] {i, j});
                if(c == 'L') {
                    sx = i;
                    sy = j;
                    water_queue.offer(new int[] {i, j});
                }
            }
        }
        swan_queue.offer(new int[] {sx, sy});
        swan_visit[sx][sy] = true;
        // -----
        while(!moveSwan()) {
            meltIce();
            day++;
        }
        System.out.println(day);
    }

    static boolean moveSwan() {
        ArrayList<int[]> list = new ArrayList<>();
        while(!swan_queue.isEmpty()) {
            int[] xy = swan_queue.poll();
            int x = xy[0];
            int y = xy[1];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || swan_visit[nx][ny]) continue;
                
                swan_visit[nx][ny] = true;

                if(map[nx][ny] == '.') swan_queue.offer(new int[] {nx, ny});
                else if (map[nx][ny] == 'X') list.add(new int[] {nx, ny});
                else return true;
            }
        }
        for(int[] ar : list)  {
            swan_queue.offer(ar);
        }
        return false;
    }
    
    static void meltIce() {
        ArrayList<int[]> list = new ArrayList<>();
        while(!water_queue.isEmpty()) {
            int[] xy = water_queue.poll();
            int x = xy[0];
            int y = xy[1];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || water_visit[nx][ny]) continue;
                
                water_visit[nx][ny] = true;
                
                if(map[nx][ny] == 'X')  {
                    map[nx][ny] = '.';
                    list.add(new int[] {nx, ny});
                }
            }
        }

        for(int[] ar : list) {
            water_queue.offer(ar);
        }
    }

}
