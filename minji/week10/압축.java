package PS.minji.week10;
import java.util.*;

public class 압축 {

    class Solution {
        public int[] solution(String msg) {

            List<Integer> answer = new ArrayList<>();

            Map<String,Integer> dict = new HashMap<>();
            for(int i=0; i<26; i++) {
                dict.put(String.valueOf((char)('A'+i)), i+1);
            }

            int index= 0;
            while(index < msg.length()) {
                String w = String.valueOf(msg.charAt(index));

                while(index+1 < msg.length()
                        && dict.containsKey(w + msg.charAt(index+1))) {
                    w = w + msg.charAt(index+1);
                    index++;
                }

                answer.add(dict.get(w));

                if(index+1<msg.length()) {
                    dict.put(w+msg.charAt(index+1), dict.size()+1);
                }
                index++;
            }


            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
