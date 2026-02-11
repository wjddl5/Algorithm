import java.io.*;
import java.util.*;

class Main {

    static long answer;
    static int[] ar1, ar2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        ar1 = new int[n*n];
        ar2 = new int[n*n];
        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                ar1[idx] = a[i] + b[j];
                ar2[idx++] = c[i] + d[j];
            }
        }

        Arrays.sort(ar1);
        Arrays.sort(ar2);

        find(n);
        System.out.println(answer);
    }

    static void find(int n) {
        int l = 0;
        int r = n * n - 1;

        while (l < n*n && r >= 0) {
            long sum = (long) ar1[l] + ar2[r];

            if (sum == 0) {
                long target1 = ar1[l];
                long count1 = 0;
                while (l < n*n && ar1[l] == target1) {
                    count1++;
                    l++;
                }

                long target2 = ar2[r];
                long count2 = 0;
                while (r >= 0 && ar2[r] == target2) {
                    count2++;
                    r--;
                }
                
                answer += count1 * count2;
            } else if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }
    } 

}