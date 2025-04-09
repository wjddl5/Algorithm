import java.io.*;
import java.util.*;

public class Main {

    static int N, R, C, num=0;
    static int[] dx={0,0,1,1}, dy={0,1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        find(N, R + C, 0, 0);
    }

    public static void find(int size, int length, int x, int y) {
        if(size == 1) {
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx==R && ny==C) {
                    System.out.println(num);
                    System.exit(0);
                }
                num++;
            }
            return;
        }

        int p = (int)Math.pow(2, size-1);
        int nLength = length;
        int nx = x;
        int ny = y;
        int nk = 0;
        for(int k=0; k<4; k++) {
            int tmpx = x + dx[k] * p;
            int tmpy = y + dy[k] * p;
            int tmplnR = R - tmpx; 
            int tmplnC = C - tmpy;
            if(tmplnR<0 || tmplnC<0) continue;
            if(nLength > tmplnR + tmplnC) {
                nx = tmpx;
                ny = tmpy;
                nLength = tmplnR + tmplnC;
                nk = k;
            }
        }
        num += ((int)Math.pow(4, size-1)) * nk;
        find(size-1, nLength, nx, ny);
    }
}