import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<Integer>();
		
		int n = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");
		for(int i = 0; i<n; i++) {
			list.add(Integer.parseInt(arr[i]));
		}
		Collections.sort(list);
		
		int s = 0;
		int e = n-1;
		int min = Integer.MAX_VALUE;
		int mins = 0;
		int mine = 0;		
		while(s<e) {
			int sum = list.get(s)+list.get(e);
			if(Math.abs(sum)<min) {
				min = Math.abs(sum);
				mins = s;
				mine = e;
				if(sum==0)
					break;
			}
			if(sum>0)
				e--;
			else
				s++;
		}
		
		System.out.print(list.get(mins) + " " + list.get(mine));
	}

}