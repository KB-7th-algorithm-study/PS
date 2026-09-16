// [프로그래머스] 마법의 엘리베이터: JAVA
class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey > 0){
            int n = storey % 10;
            storey /= 10;
            if(n < 5){
              answer += n;
            }else if(n > 5){
                storey++;
                answer += 10 - n;
            }else{
                int tmp = storey % 10;
                if(tmp >= 5){
                    storey++;
                }
                answer += n;
            }
        }
        return answer;
    }
}