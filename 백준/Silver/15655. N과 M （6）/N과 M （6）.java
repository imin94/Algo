import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int M;
	public static boolean[] flag;
	public static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		flag = new boolean[N];
		list = new ArrayList<Integer>();
		for(int i = 0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		dfs(0,0);
	}
	
	public static void dfs(int idx, int depth) {
		if(depth==M) {
			for(int i = 0; i<N; i++) {
				if(flag[i])
					System.out.print(list.get(i)+" ");
			}
			System.out.println();
			return;
		}
		for(int i = idx; i<N; i++) {
			flag[i]=true;
			dfs(i+1,depth+1);
			flag[i]=false;
		}
	}

}