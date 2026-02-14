import java.io.*;
import java.util.*;

class Main {

    static int N, M, score;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    static int X, Y, D = 1;
    static int[] dice = {1, 2, 3, 4, 5, 6}; // 위, 뒤, 오, 왼, 앞, 밑
    static int[][] rollDice = {
        {0, 4, 5, 1}, // Up    (위<-앞, 앞<-밑, 밑<-뒤, 뒤<-위)
        {0, 3, 5, 2}, // Right (위<-왼, 왼<-밑, 밑<-오, 오<-위)
        {0, 1, 5, 4}, // Down  (위<-뒤, 뒤<-밑, 밑<-앞, 앞<-위)
        {0, 2, 5, 3}  // Left  (위<-오, 오<-밑, 밑<-왼, 왼<-위)
    };
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(K-- > 0) {
            move();
            getScore();
        }

        System.out.println(score);
    }

    static void move() {
        if(X + dx[D] < 0 
            || Y + dy[D] < 0
            || X + dx[D] >= N
            || Y + dy[D] >= M
        ) {
                D = (D + 2) % 4;
        }

        roll();

        X += dx[D];
        Y += dy[D];

        if(dice[5] > map[X][Y]) {
            D = (D + 1) % 4;
        } else if(dice[5] < map[X][Y]) {
            D = (D + 3) % 4;
        }
    }

    static void roll() {
        int[] ar = rollDice[D];
        int tmp = dice[ar[0]];
        dice[ar[0]] = dice[ar[1]];
        dice[ar[1]] = dice[ar[2]];
        dice[ar[2]] = dice[ar[3]];
        dice[ar[3]] = tmp;
    }

    static void getScore() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];

        q.offer(new int[] {X, Y});
        v[X][Y] = true;
        
        int p = map[X][Y];
        int cnt = 1;

        while(!q.isEmpty()) {
            int[] ar = q.poll();
            int x = ar[0];
            int y = ar[1];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny] || map[nx][ny] != p) continue;

                q.offer(new int[] {nx, ny});
                v[nx][ny] = true;
                cnt++;
            }
        }

        score += p * cnt;
    }

}