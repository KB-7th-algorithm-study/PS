import java.util.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        int[][] arr = new int[n][n];
        int[][] Dp = new int[n][n];

        for(int i=0;i<n;i++){
            String s = sc.nextLine();
            String[] strArr = s.split(" ");

            for(int j=0;j<strArr.length;j++){
                arr[i][j] = Integer.parseInt(strArr[j]);
            }
        }
        Dp[0][0] = arr[0][0];
        
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                if(j==0)
                    Dp[i][j] = Dp[i-1][j] + arr[i][j];
                else if(j==i)
                    Dp[i][j] = Dp[i-1][j-1]+ arr[i][j];
                else
                    Dp[i][j] = arr[i][j] + Math.max(Dp[i-1][j],Dp[i-1][j-1]);
            }
        }

        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                max = Math.max(max, Dp[i][j]);
            }
        }
        System.out.println(max);
    }
}
