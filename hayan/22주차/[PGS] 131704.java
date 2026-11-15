//내가 먼저 푼 코드
import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> list = new ArrayDeque<>();
        Deque<Integer> res = new ArrayDeque<>();
        
        for(int i=0; i<order.length; i++){
            list.addLast(i+1);
        }
        
        for(int i=0; i<order.length; i++){
            if(!list.isEmpty() && order[i] == list.peekFirst()){
                res.addLast(order[i]);
                list.pollFirst();
                continue;
            }
            
            if(!stack.isEmpty() && order[i]==stack.peekFirst()){
                res.addLast(order[i]);
                stack.pollFirst();
                continue;
            }
            
            //둘 다 맞는 게 없는 경우
            while(!list.isEmpty() && order[i] != list.peekFirst()){
                int t = list.pollFirst();
                stack.push(t);
            }
            
            //다시 확인
            if(!list.isEmpty() && order[i] == list.peekFirst()){
                res.addLast(order[i]);
                list.pollFirst();
                continue;
            }
            
            if(!stack.isEmpty() && order[i]==stack.peekFirst()){
                res.addLast(order[i]);
                stack.pollFirst();
                continue;
            }
            //더이상 실을 수 없을떄
            break;
            
        }
        
        answer = res.size();
        
        return answer;
    }
}

//리팩토링
import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int box = 1;
        
        for(int i=0; i<order.length; i++){
            //원하는 상자 나올때까지 보조 컨베이어에 넣기
            while(box <= order.length && box < order[i]){
                stack.push(box);
                box++;
            }
            
            //메인 컨베이어에서 바로 실을 수 있는 경우
            if(order[i] == box){
                answer++;
                box++;
            }
            //보조 컨베이어에서 
            else if(!stack.isEmpty() && order[i] == stack.peek()){
                answer++;
                stack.pop();
            }
            else{
                break;
            }
        }

        return answer;
    }
}