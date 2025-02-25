import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] ar;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ar = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			ar[i] = ar[i-1] + Integer.parseInt(st.nextToken());
		}

		for(int tc=0; tc<M; tc++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int res = ar[to] - ar[from-1];
			System.out.println(res);
		}
	}
}
