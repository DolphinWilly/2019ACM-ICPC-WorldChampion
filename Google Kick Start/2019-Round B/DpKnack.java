import java.util.*;
public class DpKnack {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i=1; i<=t; i++) {
			ArrayList<Stone> arr = new ArrayList();
			int time = 0;
			int n = sc.nextInt();
			for(int j=0; j<n; j++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int l = sc.nextInt();
				time += s;
				arr.add(new Stone(s,e,l));
			}
			Collections.sort(arr);
			int dp[][] = new int[n+1][time+1];
			for(int j=1; j<=n; j++) {
				for(int p=0; p<=time; p++) {
					dp[j][p] = dp[j-1][p];
					if(p>=arr.get(j-1).s) {
						dp[j][p] = Math.max(dp[j][p], dp[j-1][p-arr.get(j-1).s] + arr.get(j-1).e - (p-arr.get(j-1).s) * arr.get(j-1).l);
					}
				}
			}
			int ans = 0;
			for(int temp: dp[n]) {
				ans = Math.max(ans, temp);
			}
			System.out.println("Case #"+i+": "+ans);
		}
	}
}

class Stone implements Comparable<Stone> {
	int s;
	int e;
	int l;
	
	public Stone(int s, int e, int l) {
		this.s = s;
		this.e = e;
		this.l = l;
	}
	public int compareTo(Stone stone) {
		double me = 1.*this.l/this.s;
		double his = 1.*stone.l/stone.s;
		if(me > his)
			return -1;
		else if(me < his)
			return 1;
		return 0;
	}
}
