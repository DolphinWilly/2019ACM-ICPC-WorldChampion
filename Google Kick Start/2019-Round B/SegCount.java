import java.util.*;
public class SegCount {
	static int N = 100010;
	int[] lazy = new int[4*N];
	int[] maxPrefix = new int[4*N];
	
	public void upd(int p, int l, int r, int x, int y, int v) {
		if(x>=r || y <=l)
			return;
		if(x<=l && r<=y) {
			lazy[p] += v;
			maxPrefix[p] += v;
			return;
		}
		// [x,y) didn't cover the entire [l,r), so we need to split [l,r)
		// before we move down we need to move the info in lazy down the tree
		if(lazy[p] != 0) { // note we cannot just if lazy[p] > 0  since lazy[p] < 0 is also information!!!!!
			lazy[2*p] += lazy[p];
			lazy[2*p+1] += lazy[p];
			maxPrefix[2*p] += lazy[p];
			maxPrefix[2*p+1] += lazy[p];
			lazy[p] = 0;
		}
		int mid = (l+r)/2;
		upd(2*p, l, mid, x, y, v);
		upd(2*p+1, mid, r, x, y, v);
		maxPrefix[p] = Math.max(maxPrefix[2*p], maxPrefix[2*p+1]);
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i=1; i<=t; i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			ArrayList<Integer>[] maps = new ArrayList[N];
			for(int j=0; j<N; j++) {
				maps[j] = new ArrayList<Integer>();
			}
			int[] arr = new int[n];
			int[] pre = new int[n]; // how many number of the same kind as the number at this index occurs before
			for(int j=0; j<n; j++) {
				arr[j] = sc.nextInt();
				pre[j] = maps[arr[j]].size();
				maps[arr[j]].add(j);
			}

			SegCount segT = new SegCount();
			int ans = 0;
			for(int j=n-1; j>=0; j--) {
				segT.upd(1, 0, n, j, n, 1); // normally one item will cause one increase
				if(pre[j] + m < maps[arr[j]].size()) {
					segT.upd(1, 0, n, maps[arr[j]].get(pre[j]+m), n, -m-1); // the first time we have m+1 of them we clear all the counts
					// the above will repeat in m+2, m+3, .. of them so we need to add some back
					// for m+2, m+3, .. we only need to minus 1 instead of (m+1) so we add m back
					if(m+1 < maps[arr[j]].size() - pre[j]) {
						segT.upd(1,0,n,maps[arr[j]].get(pre[j]+m+1),n, m);
					}
				}
				ans = Math.max(ans, segT.maxPrefix[1]); // 1 is the index of root node of the segTree
			}
			System.out.println("Case #"+i+": "+ans);
		}
	}
}
