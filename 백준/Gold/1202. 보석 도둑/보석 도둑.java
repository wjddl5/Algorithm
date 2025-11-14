import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static long answer;
    static int[] bags;
    static Jam[] jams;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        jams = new Jam[N];
        bags = new int[K];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jams[i] = new Jam(w, v);
        }

        for(int i=0; i<K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jams, (o1, o2) -> o1.w - o2.w);
        Arrays.sort(bags);

        find();

        System.out.println(answer);
    }

    static void find() {
        PriorityQueue<Jam> pq = new PriorityQueue<>();
        int idx = 0;

        for(int i=0; i<K; i++) {
            int bag = bags[i];

            while(idx < N && jams[idx].w <= bag) {
                pq.offer(jams[idx]);
                idx++;
            }

            if(!pq.isEmpty()) {
                answer += pq.poll().v;
            }
        }
    }

}

class Jam implements Comparable<Jam>{
    int w, v;
    Jam(int w, int v) {
        this.w = w;
        this.v = v;
    }
    @Override
    public int compareTo(Jam o) {
        if(this.v == o.v) return Integer.compare(this.w, o.w);
        return Integer.compare(o.v, this.v);
    }
}