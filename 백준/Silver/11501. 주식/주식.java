import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long answer;
    static int[] ar;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            ar = new int[N];
            answer = 0;
            StringTokenizer st = new StringTokenizer(br.readLine()); 
            for(int i=0; i<N; i++) {
                ar[i] = Integer.parseInt(st.nextToken());
            }
            greedy();
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    public static void greedy() {
        int max=0;
        for(int i=N-1; i>=0; i--) {
            if(max < ar[i]) max = ar[i];
            if(max > ar[i]) answer += max-ar[i];
        }
    }

}

