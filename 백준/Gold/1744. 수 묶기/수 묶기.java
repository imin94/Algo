import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> positive = new ArrayList<>();
		List<Integer> negative = new ArrayList<>();
		for(int i = 0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num>0)
				positive.add(num);
			else
				negative.add(num);
		}
		
		Collections.sort(positive,Collections.reverseOrder());
		Collections.sort(negative);
		
		int sum = 0;
		int pIdx = 0;
		int pLen = positive.size();
		while(pIdx<pLen) {
			if(positive.get(pIdx)==1 || pIdx+1==pLen || ((pIdx+1<pLen) && positive.get(pIdx+1)==1))
				sum+=positive.get(pIdx++);
			else {
				sum+=positive.get(pIdx)*positive.get(pIdx+1);
				pIdx+=2;
			}
		}
		int nIdx = 0;
		int nLen = negative.size();
		while(nIdx<nLen) {
			if(negative.get(nIdx)==0 || nIdx+1==nLen)
				sum+=negative.get(nIdx++);
			else {
				sum+=negative.get(nIdx)*negative.get(nIdx+1);
				nIdx+=2;
			}
		}
		
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
		
	}

}