import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        for(String gem : gems)
            set.add(gem);
        
        int left = 0;
        int min = Integer.MAX_VALUE;
        int totalGemCount = set.size();
        
        for(int right=0; right<gems.length; right++) {
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            
            while(map.size() == totalGemCount) {
                if(min > right - left + 1) {
                    min = right - left + 1;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
                
                String leftGem = gems[left];
                map.put(gems[left], map.get(gems[left]) -1);
                if(map.get(leftGem) == 0) {
                    map.remove(leftGem);
                }
                
                left++;
            }
        }
        
        return answer;
    }
}