import java.io.*;
import java.util.*;

public class Main {
    static int max=0;
    static Integer[] rope;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        rope = new Integer[n];
        for(int i=0; i<n; i++) {
            rope[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(rope, Collections.reverseOrder());

        for(int i=0; i<n; i++) {
            max = Math.max(max, rope[i] * (i+1));
        }
        System.out.println(max);
    }

}