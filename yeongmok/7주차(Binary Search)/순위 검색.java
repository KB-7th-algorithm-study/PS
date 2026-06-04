// GPT풀이
import java.util.*;

class Solution {
    Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for(String i : info){
            String[] conditions = i.split(" ");
            int score = Integer.parseInt(conditions[4]);

            makeKeys(conditions,0,new String[4],score);
        }

        for(List<Integer> i : map.values()){
            Collections.sort(i);    
        }

        for(int i=0;i<query.length;i++){
            String[] q = query[i].replaceAll("and ","").split(" ");
            String key = String.join(" ", new String[]{q[0],q[1],q[2],q[3]});
            int targetScore = Integer.parseInt(q[4]);

            List<Integer> list = map.getOrDefault(key, new ArrayList<Integer>());
            int low = binary(list, targetScore);
            answer[i]= list.size() - low;
        }

        return answer;
    }

    void makeKeys(String[] conditions, int depth, String[] selected, int score){
        if(depth==4){
            String key = String.join(" ", selected);
            map.computeIfAbsent(key,k->new ArrayList<>()).add(score);

            return;
        }

        selected[depth] = conditions[depth];
        makeKeys(conditions, depth+1,selected,score);

        selected[depth] = "-";
        makeKeys(conditions, depth+1,selected,score);
    }

    int binary(List<Integer> list, int target){
        int low=0;
        int high = list.size();

        while(low<high){
            int mid = (low+high)/2;
            if(list.get(mid)>=target){
                high = mid;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }
}
