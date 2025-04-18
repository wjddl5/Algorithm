import java.io.*;
import java.util.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ar = new int[N];
        int[] leng = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(leng, 1);
        int max = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(ar[j] < ar[i]) {
                    leng[i] = Math.max(leng[j]+1, leng[i]);
                    max = Math.max(max, leng[i]);
                }
            }
        }
        System.out.println(max);
    }

}