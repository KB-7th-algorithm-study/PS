import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    Map<String, Integer> map;
    
    public List<String> solution(String[] orders, int[] course) {
        for(int k=0;k<course.length;k++){
            map = new HashMap<>();
            for(int i=0;i<orders.length;i++){
                char[] charArr = orders[i].toCharArray();
                Arrays.sort(charArr);
                String s = new String(charArr);
                dfs(s,"",0,course[k]);
            }
            
            if(map.isEmpty())
                continue;
            
            int max = Collections.max(map.values());
            for(String key : map.keySet()){
                if(map.get(key)==max && max>=2)
                    answer.add(key);
            }
        }
        Collections.sort(answer);
        return answer;
    }
    
    void dfs(String s, String cur, int start, int size){
        if(cur.length()==size){
            map.put(cur, map.getOrDefault(cur,0)+1);
            return;
        }
        
        for(int i=start;i<s.length();i++){
            if(cur.indexOf(s.substring(i,i+1))==-1){
                cur += s.substring(i,i+1);
                dfs(s, cur,i+1,size);
                cur = cur.substring(0,cur.length()-1);
            }
        }
    }
}
