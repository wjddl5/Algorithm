import java.io.*;
import java.util.*;

public class Main {
    static int N, max;
    static int[] ar, lis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ar = new int[N];
        lis = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        lisSearch();
        System.out.println(max);
    }

    static void lisSearch() {
        lis[0] = ar[0];
        int len = 1;
        for(int i=0; i<N; i++) {
            if(lis[len-1] < ar[i]) lis[len++] = ar[i];
            else lis[binarySearch(ar[i], 0, len-1)] = ar[i]; 
        }
        max = len;
    }

    static int binarySearch(int num, int left, int right) {
        int mid;
        while(left < right) {
            mid = (left + right) / 2;
            if(num <= lis[mid]) right = mid;
            else left = mid + 1;
        }
        return right;
    }

}