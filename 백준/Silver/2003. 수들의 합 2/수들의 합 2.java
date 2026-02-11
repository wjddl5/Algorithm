import java.io.*;
import java.util.*;

class Main {

    static int N, M, answer;
    static int[] ar;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ar = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }

        find();
        System.out.println(answer);
    }

    static void find() {
        int l = 0, r = 0, sum = 0;
        while (true) {
            if (sum == M) answer++;
            
            if (sum >= M) {
                sum -= ar[l++];
            } else if (r == N) {
                break;
            } else {
                sum += ar[r++];
            }
        }
    }
}