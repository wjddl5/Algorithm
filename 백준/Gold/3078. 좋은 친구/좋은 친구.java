import java.io.*;
import java.util.*;

public class Main {

  static int N, K;
  static long goodFriend;
  static int[] names, cnts;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    names = new int[N];
    cnts = new int[21];

    for(int i=0; i<N; i++) {
      names[i] = br.readLine().length();
    }

    find();

    System.out.println(goodFriend);
  }

  static void find() {
    for(int i=0; i<N; i++) {
      goodFriend += cnts[names[i]];
      cnts[names[i]]++;

      if(i >= K) {
        cnts[names[i - K]]--;
      }
    }

  }

}