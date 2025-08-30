import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] cards;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) 
            cards[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(cards);
                
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(m-- > 0)
            binarySearch(Integer.parseInt(st.nextToken()));
         
        System.out.println(sb);
    }

    static void binarySearch(int num) {
        int left = 0;
        int right = N - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(cards[mid] < num) {
                left = mid + 1;
            } else if(cards[mid] > num) {
                right = mid - 1;
            } else {
                sb.append(1).append(" ");
                return;
            }
        }
        sb.append(0).append(" ");
    }
}