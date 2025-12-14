import java.io.*;
import java.util.*;

class Main {

    static int count;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        find(N, 1, 2, 3);

        System.out.println(count);
        System.out.println(sb);
    }

    static void find(int N, int from, int sub, int to) {
        if(N == 1) {
            count++;
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }
        find(N-1, from, to, sub);
        count++;
        sb.append(from).append(" ").append(to).append("\n");
        find(N-1, sub, from, to);
    }

}