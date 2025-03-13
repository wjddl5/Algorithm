import java.io.*;
import java.util.*;

public class Main {

    static int N, M, x, y;
    static int[][] map, dice = new int[3][2];
    static int[] dx={0, 0, -1, 1}, dy={1, -1, 0, 0}; // 동서북남
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            int num = Integer.parseInt(st.nextToken());
            game(num);
        }
        System.out.println(sb);
    }

    public static void game(int k) {
        int nx = x + dx[k-1];
        int ny = y + dy[k-1];

        if(nx<0 || ny<0 || nx>=N || ny>=M) return;
        degul(k);

        if(map[nx][ny] == 0) {
            map[nx][ny] = dice[0][1];
        }
        else {
            dice[0][1] = map[nx][ny];
            map[nx][ny] = 0;
        }

        sb.append(dice[0][0]).append("\n");

        x = nx;
        y = ny;
    }

    public static void degul(int k) {
        int[][] copy = new int[3][2];
        switch (k) {
            case 1:
                copy[0][0] = dice[2][0];
                copy[0][1] = dice[2][1];
                copy[1][0] = dice[1][0 ];
                copy[1][1] = dice[1][1];
                copy[2][0] = dice[0][1];
                copy[2][1] = dice[0][0];
                break;
            case 2:
                copy[0][0] = dice[2][1];
                copy[0][1] = dice[2][0];
                copy[1][0] = dice[1][0];
                copy[1][1] = dice[1][1];
                copy[2][0] = dice[0][0];
                copy[2][1] = dice[0][1];
                break;
            case 3:
                copy[0][0] = dice[1][0];
                copy[0][1] = dice[1][1];
                copy[1][0] = dice[0][1];
                copy[1][1] = dice[0][0];
                copy[2][0] = dice[2][0];
                copy[2][1] = dice[2][1];
                break;
            case 4:
                copy[0][0] = dice[1][1];
                copy[0][1] = dice[1][0];
                copy[1][0] = dice[0][0];
                copy[1][1] = dice[0][1];
                copy[2][0] = dice[2][0];
                copy[2][1] = dice[2][1];
                break;
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<2; j++) {
                dice[i][j] = copy[i][j];
            }
        }
    }

}