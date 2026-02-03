import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        br.readLine();
        int d = 0;
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int h = N - Integer.parseInt(st.nextToken());
            throwStick(h, d, d == 0 ? 1 : -1);
            d = d == 0 ? M-1 : 0; 
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) sb.append(map[i]).append("\n");
        System.out.println(sb);
    }

    static void throwStick(int h, int d, int k) {
        while(d >=0 && d < M) {
            if(map[h][d] == 'x') {
                map[h][d] = '.';
                getBlock();
                break;
            }
            d += k;
        }
    }

    static void getBlock() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        boolean[][] isStop = new boolean[N][M];

        // 바닥에 붙어있는 블럭
        for(int i=0; i<M; i++) {
            if(map[N-1][i] == '.') continue;
            q.offer(new int[] {N-1, i});
            v[N-1][i] = true;
            isStop[N-1][i] = true; 
        }

        while(!q.isEmpty()) {
            int[] ar = q.poll();
            int x = ar[0];
            int y = ar[1];
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny] || map[nx][ny]== '.') continue;
                q.offer(new int[] {nx, ny});
                v[nx][ny] = true;
                isStop[nx][ny] = true; 
            }
        }

        // 내려갈 블럭
        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i<N-1; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 'x' && !isStop[i][j]) list.add(new int[] {i, j});
            }
        }
        
        down(list, isStop);
    }

    static void down(ArrayList<int[]> list, boolean[][] isStop) {
        // 몇 칸 내려가는지 파악
        int count = 999;
        for(int[] ar : list) {
            int x = ar[0];
            int y = ar[1];
            int cnt = 0;
            while(true) {
                if(x == N-1 || isStop[x+1][y]) {
                    count = Math.min(count, cnt);
                    break;
                }
                cnt++;
                x++;
            }
        }

        if(count == 999) return;

        for(int[] ar : list) {
            map[ar[0]][ar[1]] = '.';
        }
        for(int[] ar : list) {
            map[ar[0] + count][ar[1]] = 'x';
        }
    }

}