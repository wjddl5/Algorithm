import java.io.*;
import java.util.*;

class Main {

    
    static int row = 3, col = 3, time = Integer.MAX_VALUE,
               r, c, k;
    static int[][] map = new int[100][100];

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) -1;
        c = Integer.parseInt(st.nextToken()) -1;
        k = Integer.parseInt(st.nextToken());

        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t=0; t<=100; t++) {
            if(map[r][c] == k) {
                time = Math.min(time, t);
                break;
            }

            if(row >= col) sortRow();
            else sortCol();
        }

        System.out.println(time < Integer.MAX_VALUE ? time : -1);
    }

    static void sortRow() {
        for(int i=0; i<row; i++) {
            int[] ar = new int[101];
            ArrayList<Node> list = new ArrayList<>();

            for(int j=0; j<col; j++) {
                ar[map[i][j]]++;  
                map[i][j] = 0; 
            }

            for(int k=1; k<=100; k++) {
                if(ar[k] > 0) {
                    list.add(new Node(k, ar[k]));
                }
            }

            list.sort(null);

            int idx = 0;
            for(Node node : list) {
                map[i][idx++] = node.idx;
                map[i][idx++] = node.cnt;
                if(idx > 99) break;
            }
            col = Math.max(col, list.size() * 2 > 100 ? 99 : list.size() * 2);
        }

    }

    static void sortCol() {
        for(int i=0; i<col; i++) {
            int[] ar = new int[101];
            ArrayList<Node> list = new ArrayList<>();

            for(int j=0; j<row; j++) {
                ar[map[j][i]]++;
                map[j][i] = 0;
            }
            for(int k=1; k<=100; k++) {
                if(ar[k] > 0) {
                    list.add(new Node(k, ar[k]));
                }
            }

            list.sort(null);

            int idx = 0;
            for(Node node : list) {
                map[idx++][i] = node.idx;
                map[idx++][i] = node.cnt;
                if(idx > 99) break;
            }
            row = Math.max(row, list.size() * 2> 100 ? 99 : list.size() * 2);
        }

    }

}

class Node implements Comparable<Node> {
    int idx;
    int cnt;
    Node(int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
    }
    @Override
    public int compareTo(Node o) {
        if(this.cnt == o.cnt) return Integer.compare(this.idx, o.idx);
        return Integer.compare(this.cnt, o.cnt);
    }
    
}