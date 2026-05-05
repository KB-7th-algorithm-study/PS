import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        int len = 0;
        
        // 1
        new_id = new_id.toLowerCase();
        
        // 2
        new_id = new_id.replaceAll("[^a-z0-9_.-]", "");
        
        // 3
        new_id = new_id.replaceAll("\\.{2,}", ".");
        
        // 4
        new_id = new_id.replaceAll("^[.]|[.]$", "");
        
        // 5
        if(new_id.isEmpty()) new_id += "a";
        
        // 6
        len = new_id.length();
        if(len >= 16){
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("[.]$", "");
        }
        
        // 7
        len = new_id.length();
        if(len <= 2){
            while(len < 3){
                new_id += new_id.substring(len - 1);
                len = new_id.length();
            }
        }
        
        answer = new_id;
        
        return answer;
    }
    
}