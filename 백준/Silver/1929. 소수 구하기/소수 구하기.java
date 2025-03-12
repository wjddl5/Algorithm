import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] ar = new boolean[end+1];

        for(int i=2; i<=end; i++) {

            if(!ar[i] && i>=start) {
                sb.append(i).append('\n');
            }

            for(int k=i; k<=end; k+=i) {
                ar[k] = true;
            } 
        }
        System.out.println(sb);
    }

}