import java.util.*;
/*
 *  A trivial problem where I learned/remembered:
 *  bracket parsing's correctness is equivalent to Catalan Numbers Construction, i.e. starting from 0 and always floating above 0 and eventually
 *  come back to 0;
 *  where sum is certain and you want to max lowest item (avoid negative is just a special case) you aim for smallest Variation
 *  for {an} and {bn} we construct a {ci = aj + bk} then to have min Variation we sum {an} and {bn] in reverse sorted order
 *  (this can be proved by removing the unchanged whole parts like [Sum ai^2] and using reverse <= random <= same order through the 
 *  definition of Variation is Mean of Square - Square of Mean (the later part is invariant))
 *  
 *  Online Judge: https://prognova18.kattis.com/problems/bracketmatrix
 * 
 */
public class PB {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] pos = new int[n];
		int[] neg = new int[n];
		
		for(int i=0; i<n; i++) {
			String s = sc.next();
			for(int j=0; j< s.length(); j++) {
				if(s.charAt(j) == '(')
					pos[j]++;
				if(s.charAt(j) == ')') 
					neg[j]++;
			}
		}
		int sum = 0;
		if(pos[0] != n) {
			System.out.println("NO");
			return;
		}
		sum = n;
		int[] judge = new int[n];
		Arrays.fill(judge, 1);
		for(int i=1; i<n; i++) {
			sum = sum + pos[i] -neg[i];
			for(int j=0; j<n; j++) {
				if(j<pos[i]) 
					judge[j]+=1;
				else 
					judge[j]+=-1;
			}
			Arrays.sort(judge);
			if(judge[0] <0) {
				System.out.println("NO");
				return;
			}
		}
		if(sum == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
