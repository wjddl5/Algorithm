import java.io.*;
import java.util.*;

public class Main {

  static Gear[] gears = new Gear[4];
  static int[] visit;

    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null;

      for(int i=0; i<4; i++) {
        int[] ar = new int[8];
        String str = br.readLine();
        for(int j=0; j<8; j++) {
          ar[j] = str.charAt(j) == '0' ? 0 : 1;
        }
        Gear gear = new Gear(i, ar);
        gears[i] = gear;
      }

      int k = Integer.parseInt(br.readLine());
      while(k-- > 0) {
        st = new StringTokenizer(br.readLine());
        int id = Integer.parseInt(st.nextToken()) - 1;
        int d = Integer.parseInt(st.nextToken());
        visit = new int[4];

        find(id, d);
        
        for(int i=0; i<4; i++) {
          if(visit[i] != 0) rotation(i, visit[i]);
        }
      }

      int n1 = gears[0].node[0] == 0 ? 0 : 1;
      int n2 = gears[1].node[0] == 0 ? 0 : 2;
      int n3 = gears[2].node[0] == 0 ? 0 : 4;
      int n4 = gears[3].node[0] == 0 ? 0 : 8;
      System.out.println(n1 + n2 + n3 + n4);
    }

    static void find(int id, int d) {
      if(visit[id] != 0) return;

      visit[id] = d;
      int pre = id - 1;
      int post = id + 1;
      
      if(pre >= 0 && gears[pre].node[2] != gears[id].node[6]) {
        find(pre, -d);
      }

      if(post <= 3 && gears[id].node[2] != gears[post].node[6]) {
        find(post, -d);
      }

    }

    static void rotation(int id, int d) {
      if(d == -1) {
        int temp = gears[id].node[0];
        for(int i=1; i<8; i++) {
          gears[id].node[i-1] = gears[id].node[i];
        }
        gears[id].node[7] = temp;
      } else {
        int temp = gears[id].node[7];
        for(int i=6; i>=0; i--) {
          gears[id].node[i+1] = gears[id].node[i];
        }
        gears[id].node[0] = temp;
      }
    }
}

class Gear {
  int id;
  int[] node;
  Gear(int id, int[] node) {
    this.id = id;
    this.node = node;
  }
}