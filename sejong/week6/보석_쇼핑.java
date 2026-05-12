import java.util.*;

class Solution {
    // 객체 비교 + 상태가 세가지이므로 enum 타입으로 정의
    enum EqualResult{
        LESS,
        EQUAL,
        MORE,
        ERROR
    }
    static HashSet<String> set; // 보석 카테고리
    static HashSet<String> tempSet; // 비교를 위한 임시 바구니
    static int setLen;
    public int[] solution(String[] gems) {
        int[] answer = new int[]{0, 100001};
        
        set = new HashSet<>();
        for(String gem : gems) set.add(gem);
        setLen = set.size();
        
        int len = gems.length;
        int s = 0;
        int e = 0;
        tempSet = new HashSet<>();
        while(s <= e && e < len){
            for(int i = s; i <= e; i++) tempSet.add(gems[i]);
            
            EqualResult equal = equal();
            if(equal == EqualResult.EQUAL){
                if(e - s == answer[1] - answer[0]){
                    if(s < answer[0]){
                        answer[0] = s;
                        answer[1] = e;
                    }
                }else if(e - s < answer[1] - answer[0]){
                    answer[0] = s;
                    answer[1] = e;
                }
                s++;
            }else if(equal == EqualResult.LESS) e++;
            else if(equal == EqualResult.MORE) s++;
            tempSet.clear();
        }
        
        answer[0]++;
        answer[1]++;
        
        return answer;
    }
    public EqualResult equal(){
        int same = 0;
        for(String jewel : set) if(tempSet.contains(jewel)) same++;
        if(same == setLen) return EqualResult.EQUAL;
        else if(same < setLen) return EqualResult.LESS;
        else if(same > setLen) return EqualResult.MORE;
        return EqualResult.ERROR;
    }
}