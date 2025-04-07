import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, answer = Integer.MAX_VALUE;
    static int[][] orimap, map, rota;
    static boolean[] v;
    static int[] a, b;

    public static void main(String[] args) throws Exception{        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        orimap = new int[N][M];
        map = new int[N][M];
        rota = new int[K][3];
        v = new boolean[K];
        a = new int[K];
        b = new int[K];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                orimap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            rota[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rota[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rota[i][2] = Integer.parseInt(st.nextToken());
            a[i] = i;
        }
        perm(0);
        System.out.println(answer);
    }

    public static void perm(int cnt) {
        if(cnt == K) {
            set();
            return;
        }
        for(int i=0; i<K; i++) {
            if(v[i]) continue;
            b[cnt] = a[i];
            v[i] = true;
            perm(cnt + 1);
            v[i] = false;
        }
    }

    public static void set() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = orimap[i][j];
            }
        }
        for(int n : b) {
            int r = rota[n][0];
            int c = rota[n][1];
            int s = rota[n][2];
            rotation(r, c, s);
        }
        getAnswer();
    }

    public static void rotation(int r, int c, int size) {
        for(int s=1; s<=size; s++) {
            int temp = map[r-s][c-s];
            for(int i=r-s; i<r+s; i++) {
                map[i][c-s] = map[i+1][c-s];
            }
            for(int j=c-s; j<c+s; j++) {
                map[r+s][j] = map[r+s][j+1];
            }
            for(int i=r+s; i>r-s; i--) {
                map[i][c+s] = map[i-1][c+s];
            }
            for(int j=c+s; j>c-s; j--) {
                map[r-s][j] = map[r-s][j-1];
            }
            map[r-s][c-s+1] = temp;
        }
    }

    public static int getAnswer() {
        for(int i=0; i<N; i++) {
            int cnt = 0;
            for(int j=0; j<M; j++) {
                cnt += map[i][j];
            }
            answer = Math.min(answer, cnt);
        }
        return answer;
    }
    
}