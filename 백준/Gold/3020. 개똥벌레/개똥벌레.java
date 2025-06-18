import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] top = new int[H+1];
        int[] down = new int[H+1];
    
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            if(i % 2 == 0) { // 석순
                down[n]++;
            } else { // 종유석
                top[H-n+1]++;
            }
        }
       
        for(int i=1; i<H; i++) {
            top[i+1] += top[i];
        }
        for(int i=H-1; i>0; i--) {
            down[i-1] += down[i];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=1; i<=H; i++) {
            if(min > top[i] + down[i]) {
                min = top[i] + down[i];
                cnt = 1;
            } else if (min == top[i] + down[i]) {
                cnt ++;
            }
        }

        System.out.println(min+" "+cnt);
    }

}
