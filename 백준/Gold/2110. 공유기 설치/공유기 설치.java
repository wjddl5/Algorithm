import java.io.*;
import java.util.*;

public class Main {

    static int N, C, answer;
    static int[] house;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        house = new int[N];

        for(int i=0; i<N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        binarySearch(1, house[N-1]);

        System.out.println(answer);
    }

    static void binarySearch(int min, int max) {
        while(min <= max) {
            int mid = (min + max) / 2;
            int cnt = 1;
            int pre = 0;

            for(int i=1; i<N; i++) {
                if(house[i] - house[pre] >= mid) {
                    cnt++;
                    pre = i;
                }
            }

            if(cnt < C) max = mid - 1;
            else min = mid + 1;
        }
        answer = min - 1;
    }
    
}