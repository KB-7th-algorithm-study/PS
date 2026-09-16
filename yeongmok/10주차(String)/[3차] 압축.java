import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        List<String> dictionary = new ArrayList<>();
        
        String cur = "";
        for(int i=0;i<msg.length();i++){
            String s = cur+msg.charAt(i);
            System.out.println("cur: "+cur+", s: "+s);
            if(s.matches("[A-Z]")){
                if(i!=msg.length()-1){
                    cur = s;
                    continue;   
                }
                answer.add(msg.charAt(i)-64);
            }
            else if(dictionary.contains(s)){
                if(i!=msg.length()-1){
                    cur = s;
                    continue;
                }
                answer.add(dictionary.indexOf(s)+27);
            }
            else{
                //이전 문자열의 idx를 answer에 add
                if(cur.matches("[A-Z]")){
                    answer.add(cur.charAt(0)-64);
                }
                else if(dictionary.contains(cur)){
                    answer.add(dictionary.indexOf(cur)+27);
                }
                //지금 문자열을 추가
                dictionary.add(s);
                
                if(i==msg.length()-1){
                    if((msg.charAt(i)+"").matches("[A-Z]")){
                        answer.add(msg.charAt(i)-64);
                    }
                }
            }
            cur = (msg.charAt(i)+"");
        }
        
        int[] ans = answer.stream().mapToInt(Integer::intValue).toArray();
        
        return ans;
    }
}