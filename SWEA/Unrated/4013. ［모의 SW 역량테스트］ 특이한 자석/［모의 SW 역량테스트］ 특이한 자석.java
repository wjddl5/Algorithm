import java.io.*;
import java.util.*;

public class Solution {

    static int[][] mag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            mag = new int[6][8];
            int K = Integer.parseInt(br.readLine());
            for(int i=1; i<5; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++) {
                    mag[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                check(num, dir);
            }
            sb.append("#").append(tc).append(" ").append(getAnswer()).append("\n");
        }
        System.out.println(sb);
    }

    public static void check(int num, int dir) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(num);

        int[] chk = new int[6];
        chk[num] = dir;

        while(!q.isEmpty()) {
            int n = q.poll();
            if(n>1 && mag[n][6] != mag[n-1][2] && chk[n-1] == 0) {
                q.offer(n-1);
                chk[n-1] = chk[n] * -1;
            }
            if(n<4 && mag[n][2] != mag[n+1][6] && chk[n+1] == 0) {
                q.offer(n+1);
                chk[n+1] = chk[n] * -1;
            }
        }
        go(chk);
    }

    public static void go(int[] chk) {
        for(int i=1; i<5; i++) {
            if(chk[i] == 0) continue;
            int dir = chk[i];

            if(dir == 1) {
                int tmp = mag[i][7];
                for(int j=6; j>=0; j--) {
                    mag[i][j+1] = mag[i][j];
                }
                mag[i][0] = tmp;
            }
            else {
                int tmp = mag[i][0];
                for(int j=1; j<8; j++) {
                    mag[i][j-1] = mag[i][j];
                }
                mag[i][7] = tmp;
            }
        }
    }

    public static int getAnswer() {
        int res = 0;
        for(int i=1; i<5; i++) {
            if(mag[i][0] == 1) res += Math.pow(2, i-1);
        }
        return res;
    }

}