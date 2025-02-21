import java.io.*;
import java.util.*;

public class Main {
	static int M, N , low=1, high=0;
	static int[] ar;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		ar = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			ar[i] = temp;
			high = Math.max(high, temp);
		}

		find();
	}

	public static void find() {
		int res = 0;
		while(low <= high) {
			int cnt = 0;
			int mid = (high + low) /2;
			for(int snack : ar) {
				if(mid > snack) continue;
				cnt += snack / mid;
			}
			if(M > cnt){
				high = mid-1;
			}
			else if(M <= cnt){
				res = mid;
				low = mid+1;
			}
		}
		System.out.println(res);
	}
}