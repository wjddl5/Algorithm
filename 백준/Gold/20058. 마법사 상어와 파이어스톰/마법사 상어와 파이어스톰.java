import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));                          
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        int Q = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] fireStorm = new int[Q];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            fireStorm[i] = Integer.parseInt(st.nextToken());
        }

        sharkMagic(fireStorm);

        System.out.println(getTotal());
        System.out.println(getMax());
    }

    static void sharkMagic(int[] fireStorm) {
        for(int L : fireStorm) {
            setPoint((int) Math.pow(2, L));
            meltIce();
        }

    }

    static void setPoint(int size) {
        if(size == 1) return;
        for(int i=0; i<N; i+=size) {
            for(int j=0; j<N; j+=size) {
                int[][] copyMap = copyMap(i, j, size);
                rotationMap(i, j, copyMap);
            }
        }
    }

    static int[][] copyMap(int x, int y, int size) {
        int[][] ar = new int[size][size];

        int ti = 0;
        int tj = 0;
        for(int i=x; i<x+size; i++) {
            tj = 0;
            for(int j=y; j<y+size; j++) {
                ar[ti][tj] = map[i][j];
                tj++;
            }
            ti++;
        }
        return ar;
    }

    static void rotationMap(int x, int y, int[][] copyMap) {
        int size = copyMap.length;
        int[][] rotaMap = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                rotaMap[j][size - (i+1)] = copyMap[i][j];
            }
        }

        int tx = x;
        int ty = y;
        for(int i=0; i<size; i++) {
            ty = y;
            for(int j=0; j<size; j++) {
                map[tx][ty] = rotaMap[i][j];
                ty++;
            }
            tx++;
        }
    }

    static void meltIce() {
        int[][] copyMap = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] < 1) continue;

                int cnt = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny] < 1) continue;
                    
                    cnt++;
                }
                if(cnt<3) copyMap[i][j] --;
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = copyMap[i][j];
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

    static int getMax() {
        Queue<int[]> queue = new ArrayDeque<>();
        int count = 0;
        boolean[][] visit = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] < 1 || visit[i][j]) continue;

                queue.offer(new int[] {i, j});
                visit[i][j] = true;
                int tmpCount = 1;

                while(!queue.isEmpty()) {
                    int[] tmp = queue.poll();
                    int x = tmp[0];
                    int y = tmp[1];
                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny] || map[nx][ny] < 1) continue;
                        
                        queue.offer(new int[] {nx, ny});
                        visit[nx][ny] = true;
                        tmpCount ++;
                    }
                }
                if(count < tmpCount) count = tmpCount;
            }
        }
        return count;
    }

}