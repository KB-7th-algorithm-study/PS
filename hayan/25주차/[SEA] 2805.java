import java.io.*;
import java.util.*;

//1. 마름모
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for(int k=1; k<=t; k++) {
			int n = sc.nextInt();
			int[][] arr = new int[n][n];
			int answer = 0;
			
			for(int i=0; i<n; i++) {
				String str = sc.next();
				
				for(int j=0; j<n; j++) {
					arr[i][j] = str.charAt(j)-'0';
				}
			}
			for(int i=0; i<n/2; i++) {
				for(int j=n/2-i; j<=n/2+i; j++) {
					answer += arr[i][j];
				}
			}
			for(int i=0; i<=n/2; i++) {
				for(int j=i; j<n-i; j++) {
					answer += arr[n/2+i][j];
				}
			}
			System.out.println("#"+t+" "+answer);
		}

	}

}

//2. 맨해튼거리 방식
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for(int k=1; k<=t; k++) {
			int n = sc.nextInt();
			int[][] arr = new int[n][n];
			int answer = 0;
            int mid = n/2;
			
			for(int i=0; i<n; i++) {
				String str = sc.next();
				
				for(int j=0; j<n; j++) {
					arr[i][j] = str.charAt(j)-'0';
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					int distance = Math.abs(i-mid) + Math.abs(j-mid);
					
					if(distance <= mid) {
						answer += arr[i][j];
					}
				}
			}
			System.out.println("#"+t+" "+answer);
		}

	}

}