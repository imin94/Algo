import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int h;
	public static StringBuilder[] place;
	public static int[] nums;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		h = Integer.parseInt(br.readLine());
		nums = new int[(int) Math.pow(2, h)-1];
		
		place = new StringBuilder[h];
		for(int i = 0; i<h; i++) {
			place[i]=new StringBuilder();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<nums.length; i++) {
			nums[i]=Integer.parseInt(st.nextToken());
		}
		
		tree(0,nums.length-1,0);
		
		for(int i = 0; i<h; i++) {
			bw.write(place[i].toString()+'\n');
		}
		br.close();
		bw.close();
	}
	
	public static void tree(int s, int e, int depth) {
		if(depth==h)
			return;
		int mid = (s+e)/2;
		place[depth].append(nums[mid]+" ");
		
		tree(s,mid-1,depth+1);
		tree(mid+1,e,depth+1);
	}

}