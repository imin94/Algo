import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static List<String> list = new ArrayList<String>();
	public static boolean[] flag;
	public static int L;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		flag = new boolean[C];
		for(int i = 0; i<C; i++) {
			list.add(st.nextToken());
		}
		java.util.Collections.sort(list);
		
		dfs(0,0);
		
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	public static void dfs(int s, int depth) {
		int len = flag.length;
		if(depth==L) {
			String str="";
			for(int i = 0; i<len; i++) {
				if(flag[i])
					str+=list.get(i);
			}
			int cnt = 0;
			for(int i = 0; i<str.length(); i++) {
				if(str.charAt(i)=='a' || str.charAt(i)=='e' || str.charAt(i)=='i' || str.charAt(i)=='o' || str.charAt(i)=='u')
					cnt++;
			}
			if(cnt>0 && str.length()-cnt>1) {
				sb.append(str);
				sb.append('\n');
			}
			return;
		}
		for(int i = s; i<len; i++) {
			flag[i]=true;
			dfs(i+1, depth+1);
			flag[i]=false;
		}
	}

}