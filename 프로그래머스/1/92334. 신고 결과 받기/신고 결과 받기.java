import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report_list, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, Integer> userInfo = new HashMap<>(); // 유저 정보
        Map<String, Set<String>> reportMap = new HashMap<>(); // key:피신고자 value:신고자목록
        Set<String> reports = new HashSet<>(Arrays.asList(report_list)); // 중복 신고 제거
        
        for(int i=0; i<id_list.length; i++) {
            userInfo.put(id_list[i], i);
        }
        
        for(String report : reports) {
            String[] user = report.split(" ");
            
            reportMap.putIfAbsent(user[1], new HashSet<>());
            reportMap.get(user[1]).add(user[0]);   
        }
        
        for(String key : reportMap.keySet()) {
            Set<String> set = reportMap.get(key); // key를 신고한 사람들
            if(set.size() >= k) {
                for(String name : set) {
                    answer[userInfo.get(name)]++;
                }
            }
        }
        
        return answer;
    }
}