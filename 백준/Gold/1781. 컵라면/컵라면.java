import java.io.*;
import java.util.*;

class Main {

    static class Node implements Comparable<Node> {
        int deadline, ramen;
        Node(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Node o) {
            if(this.deadline == o.deadline) return Integer.compare(o.ramen, this.ramen);
            return Integer.compare(this.deadline, o.deadline);
        }
    }

    static int N, answer;
    static ArrayList<Node> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list.add(new Node(d, r));
        }
        list.sort(null);

        find();
        System.out.println(answer);
    }

    static void find() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(Node node : list) {
            pq.offer(node.ramen);

            if(pq.size() > node.deadline) {
                pq.poll();
            }
        }

        while(!pq.isEmpty()) {
            answer += pq.poll();
        }
    }

}