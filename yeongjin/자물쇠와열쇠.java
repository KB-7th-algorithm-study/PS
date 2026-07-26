import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {    
        int m = lock.length;
        int n = key.length;
    
        List<int[]> k_list = new ArrayList<>();
        List<int[]> l_list = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j =0; j<n; j++){
                if(key[i][j]==1) k_list.add(new int[] {i,j});
            }
        }
        int lock_count =0;
        for(int i =0; i<m; i++){
            for(int j=0; j<m; j++){
                if(lock[i][j] == 0) lock_count++;
            }
        }
        
        
        for(int r = 0; r<4; r++){
                for(int i = -n; i<m; i++){
                    for(int j = -n; j<m; j++){
                        int key_count = 0;
                        boolean b = false;
                        for(int[] p : k_list){
                            int y = p[0]+i;
                            int x = p[1]+j;
                            if(y<0 || y>=m || x<0 || x>=m) continue;
                            if(lock[y][x] == 1) {
                                b = true;
                                break;
                            }
                            key_count++;
                        }   
                        if(!b && key_count == lock_count) return true;
                    }
                } 
            
            for (int[] p : k_list) {
                int y = p[0];
                int x = p[1];
                p[0] = x;
                p[1] = n - 1 - y;
            }           
        }
        return false;
    }
}