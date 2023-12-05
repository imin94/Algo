import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str1 = br.readLine();
		int len1 = str1.length();
		String str2 = br.readLine();
		int len2 = str2.length();
		//1열과 1행은 0 으로 넣기 위해 len1 len2이 아닌 len1+1 len2+1
		int[][] dp = new int[len1+1][len2+1];
		int max = 0;
		for(int i = 1; i<=len1; i++) {
			for(int j = 1; j<=len2; j++) {
                //i와 j는 1행 1열 0을 위한 시작 점으로 str1, 2는 i-1, j-1부터 시작
				if(str1.charAt(i-1)==str2.charAt(j-1)) {
					dp[i][j]=dp[i-1][j-1]+1;
					if(max<dp[i][j])
						max=dp[i][j];
				}
			}
		}
		bw.write(""+max);
		br.close();
		bw.close();
		

	}
	
}