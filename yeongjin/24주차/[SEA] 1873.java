import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution
{
    
    static int[] y = {-1,0,1,0};
    static int[] x = {0,1,0,-1};
    
    public static void shot(char[][] m, int[] p, int d, int h, int w){
            int ny = p[0]+y[d];
            int nx = p[1]+x[d];
        	while(true){
                if(ny < 0 || nx <0 || ny >=h || nx >=w) break;
                char c = m[ny][nx];
                if(c == '#') break;
                if(c == '*'){
                	m[ny][nx] = '.';
                    break;
                }
                if(c == '.' || c == '-'){
                	ny +=y[d];
                    nx +=x[d];
                }
                
            }
        }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
      

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
            int h = sc.nextInt();
            int w = sc.nextInt();
            
            char[][] m = new char[h][w]; 
            int[] p = {0,0};
            
            for(int i = 0; i<h; i++){
                String s = sc.next();
                for(int j =0; j<w; j++){
                    char c = s.charAt(j);
                	m[i][j] = c;
                    if(c== '<' || c== '>' || c== 'v' || c == '^'){ 
                        p = new int[] {i,j};
                    }
                }
			}
            
            int n = sc.nextInt();
            String commands = sc.next();
            
            for(int i =0; i<n; i++){
            		char command =commands.charAt(i);
                
                    switch (command){
                        case 'U':
                            if(!(p[0] -1 < 0 || m[p[0]-1][p[1]] != '.')){
                               	m[p[0]][p[1]] = '.';
                                p[0]--;
                            }
                            m[p[0]][p[1]] = '^';
                            break;
                        case 'D':
                            if(!(p[0]+1 >=h || m[p[0]+1][p[1]] != '.')){
                                m[p[0]][p[1]] = '.';
                                p[0]++;
                            }
                            m[p[0]][p[1]] = 'v';
                            break;
                        case 'L':
                            if(!(p[1] -1 < 0 || m[p[0]][p[1]-1] != '.')){
                                m[p[0]][p[1]] = '.';
                                p[1]--;
                            }
                            m[p[0]][p[1]] = '<';
                            break;
                        case 'R':
                            if(!(p[1] +1 >= w || m[p[0]][p[1]+1] != '.')){
                                m[p[0]][p[1]] = '.';
                                p[1]++;
                            }
                            m[p[0]][p[1]] = '>';
                            break;
                        case 'S':
                            char c = m[p[0]][p[1]];
                            int d = 0;
                            if(c == '^'){
								d = 0;
                            }else if (c=='>'){
                                d = 1;
                            }else if(c == 'v'){
                                d = 2;
                            }else if(c == '<'){
                                d = 3;
                            }
                            shot(m, p, d, h, w);
                            break;
                    }
            }
            System.out.printf("#" +test_case+" ");
            for(int i =0; i<h; i++){
                System.out.println(new String(m[i]));
            }
            
		}
}
}