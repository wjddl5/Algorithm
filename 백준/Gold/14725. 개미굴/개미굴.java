import java.io.*;
import java.util.*;

class Main {

    static class Node {
        Map<String, Node> root = new TreeMap<>();
        
        public void insert(String[] foods, int index) {
            if(index == foods.length) return;

            String food = foods[index];
            if(!root.containsKey(food)) {
                root.put(food, new Node());
            }
            root.get(food).insert(foods, index + 1);
        }

        public void print(int depth) {
            for(String food : root.keySet()) {
                for(int i=0; i<depth; i++) {
                    sb.append("--");
                }
                sb.append(food).append("\n");
                root.get(food).print(depth + 1);
            }
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
    
        Node root = new Node();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String[] foods = new String[k];
            for(int i=0; i<k; i++) {
                foods[i] = st.nextToken();
            }
            root.insert(foods, 0);
        }
       
        root.print(0);
        System.out.println(sb);
    }
    
}