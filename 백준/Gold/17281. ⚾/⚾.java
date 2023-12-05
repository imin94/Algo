import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int ans;
	public static int[][] arr;
	public static int[] expected = new int[10];
	public static boolean[] flag = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][10];
		
		for(int r = 0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c<=9; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		expected[4] = 1;
		flag[4] = true;
		
		dfs(2);
		System.out.println(ans);
		
	}
	
	public static void dfs(int depth) {
		if(depth==10) {
			int score = play();
			ans = Math.max(score, ans);
			return;
		}
		for(int i = 1; i<=9; i++) {
			
			if(flag[i])
				continue;
			flag[i] = true;
			
			expected[i] = depth;
			dfs(depth+1);
			
			flag[i] = false;
		}
	}
	
	public static int play() {
		int sum = 0;
		int idx = 1;
		boolean[] dp = new boolean[4];
		for(int inning = 0; inning<n; inning++) {
			for(int i = 1; i<4; i++) {
				dp[i]=false;
			}
			int out = 0;
			while(true) {
				int hit = arr[inning][expected[idx]];
				if(out==3) {
					break;
				}
				else if(hit==4) {
					sum++;
					if(dp[1])
						sum++;
					if(dp[2])
						sum++;
					if(dp[3])
						sum++;
					for(int i = 1; i<4; i++)
						dp[i]=false;
				}
				else if(hit==3) {
					if(dp[1])
						sum++;
					if(dp[2])
						sum++;
					if(dp[3])
						sum++;
					for(int i = 1; i<4; i++)
						dp[i]=false;
					dp[3]=true;
				}
				else if(hit==2) {
					if(dp[2])
						sum++;
					if(dp[3])
						sum++;
					dp[3]=dp[1];
					dp[2]=true;
					dp[1]=false;
				}
				else if(hit==1) {
					if(dp[3])
						sum++;
					dp[3]=dp[2];
					dp[2]=dp[1];
					dp[1]=true;
				}
				else if(hit==0)
					out++;
				if(++idx==10)
					idx=1;
			}
		}
		return sum;
		
	}

}