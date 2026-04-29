//GPT 풀이, dfs 활용
import java.lang.Math;

class Solution {
    public int solution(String name) {
        int answer = 0;
        //알파벳 맞추기
        for(int i=0;i<name.length();i++){
            answer +=  Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1); 
        }
        //거리계산(dfs)
        char[] charArr = name.toCharArray();
        charArr[0] = 'A';
        answer += dfs(0, charArr);
        
        return answer;
    }
    
    int dfs(int cur, char[] charArr){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<charArr.length;i++){
            if(charArr[i]!='A'){
                int move = getDistance(cur,i,charArr.length);
                
                char[] copy = charArr.clone();
                copy[i] = 'A';
                //재귀
                int total = move + dfs(i, copy);
                min = Math.min(total,min);
            }
        }
        //더이상 A 아닌 값이 없을 때 처리
        if(min==Integer.MAX_VALUE){
            min = 0;
        }
        
        return min;
    }
    //원형일때 최소 거리 구하는 함수
    int getDistance(int cur, int next, int len){
        int right = Math.abs(cur-next);
        int left = len - right;
        
        return right<=left ? right : left;
    }
}