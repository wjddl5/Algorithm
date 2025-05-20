import java.io.*;
import java.util.*;

public class Main {
    static int[] gate;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        gate = new int[g+1];

        for(int i=0; i<=g; i++) {
            gate[i] = i;
        }

        for(int i=0; i<p; i++) {
            int a = Integer.parseInt(br.readLine());
            int d = find(a);
            if(d == 0) break;
            union(d, d-1);
            count++;
        }
        System.out.println(count);
    }

    static int find(int i) {
        if(gate[i] == i) return gate[i];
        return gate[i] = find(gate[i]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        gate[a] = b;
    }

}