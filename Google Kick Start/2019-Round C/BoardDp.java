import java.util.*;
public class BoardDp {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i=1; i<=t; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int k = sc.nextInt();
			int ans = 0;
			int[][] matrix = new int[r][c];
			for(int x=0; x<r; x++) {
				for(int y=0; y<c; y++)
					matrix[x][y] = sc.nextInt();
			}
			int[][] dp = new int[c][c];
			for(int j=0; j<r; j++) {
				for(int x=0; x<c; x++) {
					int max = matrix[j][x];
					int min = matrix[j][x];
					for(int y=x; y<c; y++) {
						max = Math.max(matrix[j][y], max);
						min = Math.min(matrix[j][y], min);
						if(max-min<=k) {
							dp[x][y] += (y-x+1); 
							ans = Math.max(ans, dp[x][y]);
						} else {
							dp[x][y] = 0;
						}
					}
				}
			}
			System.out.println("Case #"+i+": "+ans);
		}
	}
}
