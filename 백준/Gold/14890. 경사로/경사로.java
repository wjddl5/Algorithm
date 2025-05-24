import java.io.*;
import java.util.*;

public class Main {
    static int N, L, total;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
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
        System.out.println(total);
    }

    static void find() {
        for(int i=0; i<N; i++) {
            findRow(i);
            findCol(i);
        }
    }

    static void findRow(int r) {
        int now =  map[r][0];
        int cnt = 1;
        boolean chk = false;

        for(int c=1; c<N; c++) {
            if(map[r][c] == now) {
                cnt++;
                if(chk && cnt>=L) {
                    chk = false;
                    if(L == 1) cnt = 1;
                    else cnt = 0;
                }
                continue;
            } else if(map[r][c] == now+1) {
                if(chk || cnt < L) return;
                now++;
                cnt = 1;
                continue;
            } else if(map[r][c] == now-1){
                if(chk && cnt < L) return;
                chk = true;
                now--;
                cnt = 1;
                continue;
            } else {
                return;
            }
        }
        if(chk && cnt < L) return;
        total++;
    }

    static void findCol(int c) {
        int now =  map[0][c];
        int cnt = 1;
        boolean chk = false;

        for(int r=1; r<N; r++) {
            if(map[r][c] == now) {
                cnt++;
                if(chk && cnt>=L) {
                    chk = false;
                    if(L == 1) cnt = 1;
                    else cnt = 0;
                }
                continue;
            } else if(map[r][c] == now+1) {
                if(chk || cnt < L) return;
                now++;
                cnt = 1;
                continue;
            } else if(map[r][c] == now-1){
                if(chk && cnt < L) return;
                chk = true;
                now--;
                cnt = 1;
                continue;
            } else {
                return;
            }
        }
        if(chk && cnt < L) return;
        total++;
    }

}