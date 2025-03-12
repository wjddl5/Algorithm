import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {

            int n = Integer.parseInt(br.readLine());
            if(n == 0) {
                System.out.println(sb);
                break;
            }
            
            int res = 0;
            int end = n * 2;
            boolean[] ar = new boolean[end+1];
            
            for(int i=2; i<=end; i++) {
                if(!ar[i] && i>n) {
                    res ++;
                    ar[i] = true;
                }
                for(int k=i; k<=end; k+=i) {
                    ar[k] = true;
                }
            }
            sb.append(res).append('\n');       
        } 
    }

}