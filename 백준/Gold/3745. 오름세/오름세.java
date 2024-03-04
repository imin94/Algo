import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String str = "";
		int[] arr;
		List<Integer> list = new ArrayList<>();
		while((str=br.readLine()) != null) {
			int n = Integer.parseInt(str.trim());
			st = new StringTokenizer(br.readLine());
			
			arr = new int[n];
			for(int i = 0; i<n; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			list.clear();
			list.add(arr[0]);
			int max;
			int size;
			for(int i = 1; i<n; i++) {
				size = list.size()-1;
				max = list.get(size);
				if(max<arr[i])
					list.add(arr[i]);
				
				else {
					int l = 0;
					int r = size;
					int num = 0;
					while(l<=r) {
						int mid = l+r >>> 1;
						num = list.get(mid);
						if(num<arr[i])
							l = mid+1;
						else if(num>arr[i])
							r = mid-1;
						else {
							l = mid;
							break;
						}
					}
					list.set(l, arr[i]);
				}
			}
			bw.write(String.valueOf(list.size())+"\n");
		}
		br.close();
		bw.close();
	}
}