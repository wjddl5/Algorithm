import java.io.*;
import java.util.*;

public class Main {

    static int[] res = new int[2];
    static int[] ar;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ar = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ar);

        find(0, N - 1);
        System.out.println(res[0] + " " + res[1]);
    }

    static void find(int l, int r) {
        int t = Integer.MAX_VALUE;
        while(l < r) {
            int m = ar[l] + ar[r];
            if(m < 0) {
                if(t > Math.abs(m)) {
                    t = Math.abs(m);
                    res[0] = ar[l];
                    res[1] = ar[r];
                }
                l++;
            } else if(m > 0) {
                if(t > m) {
                    t = m;
                    res[0] = ar[l];
                    res[1] = ar[r];
                }
                r--;
            } else {
                res[0] = ar[l];
                res[1] = ar[r];
                break;
            }
        }
    }
}