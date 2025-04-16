import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] ar = new int[11];
        ar[1] = 1;
        ar[2] = 2;
        ar[3] = 4;
        for(int i=4; i<11; i++) {
            ar[i] = ar[i-1] + ar[i-2] + ar[i-3];
        }
        for(int tc=0; tc<T; tc++) {
            sb.append(ar[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

}