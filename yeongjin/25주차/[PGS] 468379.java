/**
 * 유형 : 2차원 슬라이딩 윈도우
 * 풀이 :
 *      1. 세로 h 구간 최댓값을 먼저 구해 colMax에 저장한 뒤, 가로 w 구간 최댓값을 순회하며 최소 max 탐색
 * 시간 복잡도: O(m * n) 
 */

import java.util.*;
class Solution {
    
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        
        int[] answer = {0,0};
        
        int[][] arr = new int[m][n];
        
        for(int i =0; i<drops.length; i++){
            int y = drops[i][0];
            int x = drops[i][1];
            arr[y][x] = drops.length-i;
        }
        
        
        int[][] colMax = new int[m-h+1][n];
        
        for(int c = 0; c< n; c++){
            
            Deque<Integer> dq = new ArrayDeque<>();
            
            for(int r =0; r < m; r++){
                
                
                while(!dq.isEmpty() && dq.peekFirst() < r-h+1){
                    dq.pollFirst();
                }
                
                while(!dq.isEmpty() && arr[dq.peekLast()][c] < arr[r][c]){
                    dq.pollLast();
                }
                
                dq.offerLast(r);
                
                if(r >= h-1){
                    colMax[r-h+1][c] = arr[dq.peekFirst()][c];   
                }
                
            }
        }
        
        int min = drops.length+1;
        
        for(int r = 0; r< m-h+1; r++){
            Deque<Integer> dq = new ArrayDeque<>();
            for(int c = 0; c<n; c++){
                
                while(!dq.isEmpty() && dq.peekFirst() < c-w+1){
                    dq.pollFirst();
                }
                
                while(!dq.isEmpty() && colMax[r][dq.peekLast()] < colMax[r][c] ){
                    dq.pollLast();
                }
                
                dq.offerLast(c);
                
                if( c >= w-1){
                    int max = colMax[r][dq.peekFirst()];
                    if(max ==0){
                        return new int[]{r,c-w+1};  
                    }
                    if(max < min) {
                        min = max;
                        answer = new int[] {r,c-w+1};
                    }
                    
                }
            }
        }
        
        
        return answer;
    }
}