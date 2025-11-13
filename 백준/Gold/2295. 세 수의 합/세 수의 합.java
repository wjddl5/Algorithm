import java.io.*;
import java.util.*;

public class Main {

    static int N, maxSum;
    static int[] nums;
    static ArrayList<Integer> sums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        sums = new ArrayList<>();

        for(int i=0; i<N; i++) {
            int n =Integer.parseInt(br.readLine());
            nums[i] = n;
        }
        
        // left + right 모든 조합
        for(int i=0; i<N; i++) {
            for(int j=i; j<N; j++) {
                sums.add(nums[i] + nums[j]);
            }
        }
        
        Arrays.sort(nums);
        sums.sort(null);

        find();

        System.out.println(maxSum);
    }

    static void find() {
        for (int i=N-1; i>=0; i--) { // 큰 수 부터 탐색, nums[i]에서
            for (int m=0; m<i; m++) { // mid를
                int sum = nums[i] - nums[m]; // 뺐을 때
                if(Collections.binarySearch(sums, sum) >= 0) { // 그 수가 조합에 포함되면
                    maxSum = nums[i]; // left + mid + right ( nums의 조건 맞음
                    return; // 큰 수 부터 탐색했으니 바로 return
                }
            }
        }

    }

}