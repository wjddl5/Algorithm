import java.io.*;
import java.util.*;

public class Main {
    static long A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(pow(A, B));
    }

    static long pow(long a, long b) {
        if(b == 1) return a % C;

        long t = pow(a, b/2);
        
        if(b % 2 == 1) return (t * t) % C * a % C;
        return t * t % C;
    }


}