import java.util.*;

class Solution {
    
    LinkedList<String> caches = new LinkedList<>();
    
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        
        int answer = 0;
        
        for(String cityName : cities) {
            String city = cityName.toLowerCase();
            
            if(caches.contains(city)) {
                answer++;
                caches.remove(city);
                caches.add(city);
            } else {
                answer += 5;
                if(caches.size() >= cacheSize) caches.remove(0);
                caches.add(city);
            }    
        }
        
        return answer;
    }
}