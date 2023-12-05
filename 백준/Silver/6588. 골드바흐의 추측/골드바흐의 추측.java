import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashSet<Integer> prime = new HashSet<Integer>();
		HashSet<Integer> unPrime = new HashSet<Integer>();
		for(int i=2; i<=1000000; i++) {
			prime.add(i);
		}
		for(int i = 2; i<=1000; i++) {
			if(unPrime.contains(i))
				continue;
			else {
				for(int j=i*i; j<=1000000; j=j+i) {
					unPrime.add(j);
					}
				}
			}
		prime.removeAll(unPrime);
		
		List<Integer> sortPrime = new ArrayList<Integer>(prime);
		Collections.sort(sortPrime);
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			
			for(int i = 0; i<sortPrime.size(); i++) {
				if(prime.contains((n-sortPrime.get(i)))) {
					bw.write(n+" = "+sortPrime.get(i)+" + "+(n-sortPrime.get(i))+'\n');
					break;
				}
				if(i==sortPrime.size())
					bw.write("Goldbach's conjecture is wrong.");
			}
		}
		br.close();
		bw.close();
	}
}