import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        
        Set<Integer> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        set.add(d);
        
        int[] ar = new int[7];
        ar[a]++;
        ar[b]++;
        ar[c]++;
        ar[d]++;      
        
        if(set.size() == 1) { // 4
            answer = 1111 * a;    
        }
        
        if(set.size() == 2) {
            int n1 = 0;
            int n2 = 0;
            boolean chk = false;
            
            for(int i=1; i<7; i++) {
                // 3:1
                if(ar[i] == 3) {
                    n1 = i;
                    continue;
                }
                if(ar[i] == 1) {
                    n2 = i;
                    continue;
                }
                
                // 2:2
                if(ar[i] == 2) {
                    if(chk) {
                        n2 = i;
                        continue;
                    }
                    n1 = i;
                    chk = true;
                    continue;
                }
            }
            if(chk) answer = (n1 + n2) * Math.abs(n1 - n2);
            else {
                int tmp = 10 * n1 + n2;
                answer = (int)Math.pow(tmp, 2);
            }
        }
        
        if(set.size() == 3) { // 2:1:1
            int n1 = 0;
            int n2 = 0;
            boolean chk = false;
            for(int i=1; i<7; i++) {
                if(ar[i] == 1) {
                    if(chk) {
                        n2 = i;
                        continue;
                    }
                    n1 = i;
                    chk = true;
                    continue;
                }
            }
            answer = n1 * n2;
        }
        
        if(set.size() == 4) { // 1:1:1:1
            int tmp = Math.min(a, b);
            tmp = Math.min(tmp, c);
            tmp = Math.min(tmp, d);
            answer = tmp;
        }
         
        return answer;
    }
}