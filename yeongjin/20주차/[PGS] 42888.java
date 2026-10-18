import java.util.*;

class Solution {
    public List<String> solution(String[] record) {
        List<String> answer = new ArrayList<String>();
        HashMap<String,String> map = new HashMap<String,String>();
        
        for(String str : record){
            String[] arr = str.split(" ");
            if(arr[0].equals("Leave")) continue;
            map.put(arr[1],arr[2]);
        }
        
        for(String str : record){
            String[] arr = str.split(" ");
            if(arr[0].equals("Enter")){
                answer.add(map.get(arr[1])+"님이 들어왔습니다.");
            }else if(arr[0].equals("Leave")){
                answer.add(map.get(arr[1])+"님이 나갔습니다.");
            }
        }
        return answer;
    }
}