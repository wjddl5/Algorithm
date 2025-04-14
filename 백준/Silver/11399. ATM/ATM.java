import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ar = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ar);
        
        int[] arr = new int[N];
        arr[0] = ar[0];
        for(int i=1; i<N; i++) {
            arr[i] = arr[i-1] + ar[i];
        }
        
        int sum = 0;
        for(int n : arr) {
            sum += n;
        }
        System.out.println(sum);
    }
}