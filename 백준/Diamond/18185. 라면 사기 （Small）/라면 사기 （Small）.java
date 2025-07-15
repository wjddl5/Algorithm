import java.io.*;
import java.util.*;

public class Main {

    static int N, answer;
    static int[] ar;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        N = Integer.parseInt(br.readLine());
        ar = new int[N+2]; // +2 여유 범위 추가

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            ar[i] = n;
        }

        for(int i=0; i<N; i++) {
            if(ar[i] == 0) continue;

            if(ar[i+1] > ar[i+2]) { // 3개중 가운데가 큼
                // 2개 구매
                int cnt = Math.min(ar[i], ar[i+1] - ar[i+2]); 
                answer += cnt * 5;
                ar[i] -= cnt;
                ar[i+1] -= cnt;

                // 3개 구매
                cnt = Math.min(ar[i], Math.min(ar[i+1], ar[i+2])); 
                answer += cnt * 7;
                ar[i] -= cnt;
                ar[i+1] -= cnt;
                ar[i+2] -= cnt;
            } else {
                int cnt = Math.min(ar[i], Math.min(ar[i+1], ar[i+2])); 
                answer += cnt * 7;
                ar[i] -= cnt;
                ar[i+1] -= cnt;
                ar[i+2] -= cnt;

                cnt = Math.min(ar[i], ar[i+1]); 
                answer += cnt * 5;
                ar[i] -= cnt;
                ar[i+1] -= cnt;
            }
            answer += ar[i] * 3; // 남은 라면 전부 구매
            ar[i] = 0;
        }

        System.out.println(answer);
    }
}