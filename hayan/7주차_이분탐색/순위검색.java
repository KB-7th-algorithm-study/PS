import java.util.*;
class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    int score;
    String[] str;
    public int[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();
        
        //java backend junior pizza -> 각 위치 빠진거 안빠진거 모두 가능 
        //map<javabackendjuniorpizza, 점수 리스트>
        
        for(int i=0; i<info.length; i++){
            str = info[i].split(" ");
            score = Integer.parseInt(str[4]);
            dfs(0, "");
        }
        //리스트 정렬 
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        for(int i=0; i<query.length; i++){
            query[i] = query[i].replaceAll(" and ", " ");
            String[] q_arr = query[i].split(" ");
            String q_key = q_arr[0]+q_arr[1]+q_arr[2]+q_arr[3];
            int q_score = Integer.parseInt(q_arr[4]);
            
            List<Integer> list = map.getOrDefault(q_key, new ArrayList<>()); //해당하는 지원자가 없을 수도 있음
            int idx = lower_bound(list, q_score);
            answer.add(list.size()-idx);
        }
        
        
        return answer.stream().mapToInt(i->i).toArray();
    }
    public void dfs(int depth, String key){
        if(depth==4){
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(score);
            return;
        }
        dfs(depth+1, key+str[depth]);
        dfs(depth+1, key+"-");
    }
    //정렬된 배열에서 x 이상이 처음 나오는 위치
    public int lower_bound(List<Integer> list, int x){
        int s = 0;
        int e = list.size(); 
        while(s<e){
            int mid = (s+e)/2;
            if(list.get(mid)>=x){
                e = mid;
            }else{
                s = mid+1;
            }
        }
        return s;
    }
}