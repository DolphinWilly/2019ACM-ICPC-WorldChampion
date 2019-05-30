import java.util.*;
public class KSB1 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i=1; i<=t; i++) {
			int n = sc.nextInt();
			int q = sc.nextInt();
			String str =sc.next();
			int count = 0;
			int[][] dict = new int[n+1][26];
			for(int j=1; j<n+1; j++) {
					for(int k=0; k<26; k++) {
						dict[j][k] = dict[j-1][k];	
					}
				dict[j][str.charAt(j-1)-'A'] += 1;
			}
			for(int j=0; j<q; j++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				int c = 0;
				for(int k=0; k<26; k++) {
					if((dict[v][k]-dict[u-1][k]) % 2 == 1)
						c++;
				}
//				System.out.println(c);
				if(c<=1)
					count++;
			}
			System.out.println("Case #"+i+": "+count);
		}
	}
}
