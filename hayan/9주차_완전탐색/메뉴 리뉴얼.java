import java.util.*;
import java.util.stream.Collectors;
class Solution {
    Map<String, Integer> combi_cnt;
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for(int i=0; i<course.length; i++){
            combi_cnt = new HashMap<>();
            int max =0;
            for(int j=0; j<orders.length; j++){
                char[] order = orders[j].toCharArray();
                Arrays.sort(order);
                dfs(0, course[i], new ArrayList<>(), order);
            }
            for(String k : combi_cnt.keySet()){
                if(combi_cnt.get(k)>1 && combi_cnt.get(k)>max){
                    max = combi_cnt.get(k);
                }
            }
            for(String k : combi_cnt.keySet()){
                if(combi_cnt.get(k)==max){
                    answer.add(k);
                }
            }
        }
        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }
    public void dfs(int start, int r, List<Character> curr, char[] order){
        if(curr.size()==r){
            String combi = curr.stream()
                   .map(String::valueOf)
                   .collect(Collectors.joining());
            combi_cnt.put(combi, combi_cnt.getOrDefault(combi, 0)+1);
            return;
        }
        for(int i=start; i<order.length; i++){
            curr.add(order[i]);
            dfs(i+1, r, curr, order);
            curr.remove(curr.size()-1);
        }
    }
}