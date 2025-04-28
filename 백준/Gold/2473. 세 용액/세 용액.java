import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] ar, answer=new int[3];
    static long min=Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ar = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ar);
        find();
        System.out.println(answer[0]+" "+answer[1]+" "+answer[2]);
    }
    
    static void find() {
        for(int left=0; left<N-2; left++) {
            int mid = left + 1;
            int right = N - 1;

            while(mid < right) {
                long sum = (long) ar[left] + ar[mid] + ar[right];       
                minCheck(left, mid, right, sum);
                
                if(sum == 0) return;
                
                if(sum < 0) {
                    mid++;
                    continue;
                } else {
                    right--;
                    continue;
                }
            }
        }
    }

    static void minCheck(int l, int m, int r, long tmpsum) {
        long sum = Math.abs(tmpsum);    
        if(min > sum) {
            min = sum;
            answer[0] = ar[l];
            answer[1] = ar[m];
            answer[2] = ar[r];
        }  
    }

}