import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] Dp = new int[N+1];

        for(int i=1;i<=N;i++){
            if(i<3)
                Dp[i] = i;
            else
                Dp[i] = (Dp[i-1]+Dp[i-2])%10007;
        }

        System.out.println(Dp[N]%10007);
    }

    // private static int calc(int on, int j, int col){
    //     int n = 1;
    //     int big = j>= col ? j:col;
    //     int small = j < col ? j : col;

    //     if(on==big)
    //         return n;
    //     else{
    //         for(int i=on;i>big;i--){
    //             n *= i;
    //             if(n>=10007) n%=10007;
    //         }
    //         for(int i=2;i<=small;i++){
    //             n /= i;
    //         }
    //     }

    //     return n;
    // }    
}
