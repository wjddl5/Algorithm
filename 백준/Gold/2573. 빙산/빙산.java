import java.io.*;
import java.util.*;

class Main {

    static int N, M, ice, year;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
    
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int k = Integer.parseInt(st.nextToken());;
                map[i][j] = k;
                if(k > 0) ice++;
            }
        }

        while(true) {
            if(isComeoff() || ice == 0) break;
            bfs();
            year++;
        }

        System.out.println(ice == 0 ? 0 : year);
    }

    static boolean isComeoff() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] > 0) {
                    int cnt = 1;
                    q.offer(new int[] {i, j});
                    v[i][j] = true;

                    while(!q.isEmpty()) {
                        int[] tmp = q.poll();
                        int x = tmp[0];
                        int y = tmp[1];
                        for(int k=0; k<4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny] || map[nx][ny] == 0) continue;
                            q.offer(new int[] {nx, ny});
                            v[nx][ny] = true;
                            cnt++;
                        }
                    }
                    return cnt < ice;
                }
            }
        }

        return false;
    }

    static void bfs() {
        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) continue;

                int melt = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] > 0) continue;
                    melt++;
                }

                if(map[i][j] > melt) map[i][j] -= melt;
                else {
                    list.add(new int[] {i, j});
                }
            }
        }

        for(int[] xy : list) {
            map[xy[0]][xy[1]] = 0;
            ice--;
        }
    }
    
}