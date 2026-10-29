import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String,List<Integer>> map = new HashMap<>();
        
        List<String> numbers = new ArrayList<>();
        for(String s : records){
            String[] arr = s.split(" ");
            if(!map.containsKey(arr[1])){
                numbers.add(arr[1]);
                map.put(arr[1],new ArrayList<Integer>());
            }
            String[] t = arr[0].split(":");
            int m = Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]);
            map.get(arr[1]).add(m);
        }
        
        numbers.sort(null);
        
        int[] answer = new int[numbers.size()];
        for(int i =0; i<numbers.size(); i++){
            
            List<Integer> t = map.get(numbers.get(i));
            int m = 0;
            int temp = 0;
            for(int j =0; j<t.size(); j++){
                if(j%2==1){
                    m += t.get(j)-temp;
                }else{
                    temp = t.get(j);
                }
            }
            if(t.size()%2==1){
                m += 23*60+59-temp;
            }
            if(m<=fees[0]){
                answer[i] = fees[1];   
            }
            else{
                answer[i] = fees[1] + (m-fees[0] +fees[2]-1)/fees[2]*fees[3];
            }
        }
        
        return answer;
    }
}