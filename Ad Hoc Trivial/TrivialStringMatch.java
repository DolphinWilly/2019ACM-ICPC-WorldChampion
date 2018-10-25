import java.util.*;
/*
 *  an Example where dp save time and space for recursion in string pattern matching
 *  a classic though trivial dp problem
 *  Online Judge: https://leetcode.com/problems/regular-expression-matching/description/
 */
public class TrivialStringMatch {
	public static void main(String args[]) {
		System.out.println(isMatch("","a*b*c*"));
	}
	
	public static boolean isMatch(String s, String p) {
		int ls = s.length(), lp = p.length();
        boolean[][] dp = new boolean[ls+1][lp+1]; // construct dp table
        dp[ls][lp] = true; //empty pattern matches empty string
        for(int i=ls; i>=0; i--) { // we let i starts from ls because we need to see what pattern will match empty string
        	for(int j=lp-1; j>=0; j--) { // empty pattern only match empty string which we have already recorded so no need to start from empty pattern
        		// don't need to make * valid when we see *, we can process * when we see something before it
        		boolean first_match = (i < ls) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        		// if we have a * after us process that, otherwise just consider first match
        		if(j+1 < lp && p.charAt(j+1) == '*') {
        			dp[i][j] = (first_match && dp[i+1][j]) || dp[i][j+2];
//        			System.out.println("previous dp i j+2 "+i+" "+ (j+2) +" "+ dp[i][j+2]);
//        			System.out.println("dp i j "+i+" " +j +" "+dp[i][j]);
        			continue; //we can use else below instead of continue; the main idea is we already processed * and don't want fall through
        		}
        		// so we don't have * immediately behind us and we just need to get rid of the first ones
        		dp[i][j] = first_match && dp[i+1][j+1]; // no need to worry index out of bound, since this is taken care of in first_match
        	}
        }
        
		return dp[0][0];
    }
}
