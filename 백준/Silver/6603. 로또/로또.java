import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] arr;
	public static boolean[] used;
	public static int[] nums;
	public static int k;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens()==1)
				break;
			
			k = Integer.parseInt(st.nextToken());
			arr = new int[k];
			used = new boolean[k];
			nums = new int[6];
			
			
			for(int i = 0; i<k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			autobahn(k,0);
			sb.append('\n');
		}
		bw.write(sb.toString());
		br.close();
		bw.close();	
		
	}
	
	public static void autobahn(int k, int depth) {
		if(depth==6) {
			for(int i = 0; i<6; i++) {
				sb.append(nums[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		for(int i = 0; i<k; i++) {
			if(depth==0 && !used[i]) {
				used[i]=true;
				nums[depth]=arr[i];
				autobahn(k,depth+1);
				used[i]=false;
			}
			if(depth>0 && nums[depth-1]<arr[i] && !used[i]) {
				used[i]=true;
				nums[depth]=arr[i];
				autobahn(k,depth+1);
				used[i]=false;
			}
		}
	}

}