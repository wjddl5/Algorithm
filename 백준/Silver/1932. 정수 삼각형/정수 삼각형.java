import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<int[]> graph = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            int[] ar = new int[st.countTokens()];
            while(st.hasMoreTokens()) {
                ar[j++] = Integer.parseInt(st.nextToken());
            }
            graph.add(ar);
        }
        find();
        System.out.println(graph.get(0)[0]);
    }

    static void find() {
        int depth = N-2;
        while(depth >= 0) {
            int[] ar = graph.get(depth);
            int[] downAr = graph.get(depth+1);
            for(int i=0; i<ar.length; i++) {
                int next = Math.max(downAr[i], downAr[i+1]);
                graph.get(depth)[i] += next;
            }
            depth--;
        }      
        return;
    }
}