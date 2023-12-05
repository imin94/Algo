import java.io.*;
import java.util.StringTokenizer;


public class Main {
	
	public static int row;
	public static int col;
	public static int ans = 1;
	public static char[][] arr;
	public static boolean[] flag;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		arr = new char[row][col];
		//알파벳을 지나왔는지 방문처리
		flag = new boolean[26];
		
		for(int i = 0; i<row; i++) {
			String str = br.readLine();
			for(int j = 0; j<col; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		dfs(0,0,0);
		bw.write(String.valueOf(ans));
		br.close();
		bw.close();
	}
	
	public static void dfs(int r, int c, int cnt) {
		//기저부분: 방문한 알파벳인 경우 cnt 비교
		if(flag[arr[r][c]-'A']) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		//방문한적 없는 알파벳인 경우 방문 처리
		flag[arr[r][c]-'A'] = true;
		//사방탐색
		for(int i = 0; i<4; i++) {
			int dr = r + delta[i][0];
			int dc = c+ delta[i][1];
			
			if(dr<0 || dr>=row || dc<0 || dc>=col || (cnt==0 && arr[dr][dc]==arr[r][c]))
				continue;
			dfs(dr,dc,cnt+1);
		}
		//사방 탐색을 끝맞추었다면 다른길로 탐색하기 위해 방문처리 해제
		flag[arr[r][c]-'A'] = false;
	}
}