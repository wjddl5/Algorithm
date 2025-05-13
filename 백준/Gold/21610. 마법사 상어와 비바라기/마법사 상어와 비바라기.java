import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, cloudAr;
    static boolean[][] c_map;
    static Queue<int[]> cloudQ = new ArrayDeque<>();
    static int[] dx={0,-1,-1,-1,0,1,1,1}, dy={-1,-1,0,1,1,1,0,-1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));                          
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        c_map = new boolean[N][N];
        cloudAr = new int[m][2];
        cloudQ.offer(new int[] {N-2, 0});
        cloudQ.offer(new int[] {N-2, 1});
        cloudQ.offer(new int[] {N-1, 0});
        cloudQ.offer(new int[] {N-1, 1});

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            cloudAr[i][0] = Integer.parseInt(st.nextToken());
            cloudAr[i][1] = Integer.parseInt(st.nextToken());
        }

        start();
        System.out.println(getTotal());
    }

    static void start() {
        for(int[] cloud : cloudAr) {
            int d = cloud[0]; // 방향
            int s = cloud[1]; // 거리
            
            move(d-1, s);
            rain();
            WaterCopyBug();
            makeCloud();
        }
    }

    static void move(int d, int s) {
        int size = cloudQ.size();
        for(int i=0; i<size; i++) {
            int[] cloud = cloudQ.poll();
            int x = cloud[0];
            int y = cloud[1];

            for(int j=0; j<s; j++) {
                x += dx[d];
                y += dy[d];

                if(x < 0) x = N-1;
                if(x == N) x = 0;
                if(y < 0) y = N-1;
                if(y == N) y = 0;
            }
            cloudQ.offer(new int[] {x, y});
        }
    }

    static void rain() {
        while(!cloudQ.isEmpty()) {
            int[] c = cloudQ.poll();
            int x = c[0];
            int y = c[1];
            map[x][y] ++;
            c_map[x][y] = true;
        }
    }

    static void WaterCopyBug() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(c_map[i][j]) {
                    int cnt = 0;
                    int x, y;
                    for(int k=1; k<8; k+=2) {
                        x = i + dx[k];
                        y = j + dy[k];
                        if(x<0 || y<0 || x>=N || y>=N || map[x][y] < 1) continue;
                        cnt ++;
                    }
                    map[i][j] += cnt;
                }
            }
        }
    }

    static void makeCloud() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] < 2) continue;
                if(c_map[i][j]) {
                    c_map[i][j] = false;
                    continue;
                }
                cloudQ.offer(new int[] {i, j});
                map[i][j] -= 2;
            }
        }
    }


    static int getTotal() {
        int sum = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }

}