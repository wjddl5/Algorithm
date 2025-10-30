import java.io.*;
import java.util.*;

public class Main {

    static int N, L, answer;
    static int[][] map;
    static int[] dx = {1, 0}, dy = {0, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find();

        System.out.println(answer);
    }

    static void find() {
        for(int i=0; i<N; i++) {
            move(0, i, 0);
            move(i, 0, 1);
        }
    }

    static void move(int x, int y, int d) {
        int now = map[x][y];
        boolean isDown = false;
        int floor = 1;

        while(true) {
            x += dx[d];
            y += dy[d];

            if(x >= N || y >= N) {
                if(isDown && floor < L) break;
                answer++;
                break;
            }

            int next = map[x][y];
            int gap = now - next;
            now = next;

            // 내려갈 공간 충분함
            if(isDown && floor >= L) {
                isDown = false;
                floor = 0;
            }

            // 2칸 이상 차이남
            if(Math.abs(gap) >= 2) break;

            // 내려가는 중에 높이 다른 칸 나옴
            if(isDown && gap > 0) break; 

            // 내려감
            if(gap == 1) {
                isDown = true;
                floor = 1;
                continue;
            }

            // 올라감
            if(gap == -1) {
                // 올라갈 공간 부족
                if(floor < L) break;
                floor = 1;
                continue;
            }

            // 진행
            floor++;
        }
    }

}