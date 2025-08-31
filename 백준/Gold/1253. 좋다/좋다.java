import java.io.*;
import java.util.*;

public class Main {

    static int N, res;
    static int[] ar;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ar = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) 
            ar[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(ar);

        for(int i=0; i<N; i++) 
            find(i, ar[i]);

        System.out.println(res);
    }

    static void find(int i, int n) {
        int l = 0;
        int r = N - 1;
        while(l < r) {
            if(l == i) {l++; continue;}
            if(r == i) {r--; continue;}
                
            int m = ar[l] + ar[r];
            if(m == n) {
                res++;
                return;
            }
            if(m < n) {
                l++;
            }
            else if(m > n) {
                r--;
            }
        }
    }

}