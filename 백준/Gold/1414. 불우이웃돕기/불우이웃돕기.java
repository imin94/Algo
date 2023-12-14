import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

class Node1414 implements Comparable<Node1414>{
	
	int from;
	int to;
	int w;
	
	public Node1414(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node1414 o) {
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n, sum;
	public static int[] parents;
	public static Queue<Node1414> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		int len = 0;
		parents = new int[n+1];
		for(int r = 1; r <= n; r++) {
			//각 노드의 default root는 자기자신
			parents[r] = r;
			String str = br.readLine();
			for(int c = 1; c <= n; c++) {
				char chr = str.charAt(c-1);
				if(chr=='0')
					continue;
				else if(chr >=97)
					len = chr-96;
				else
					len = chr-38;
				sum+=len;
				pq.add(new Node1414(r,c,len));
			}
		}
		//컴퓨터가 한 대이고 랜선이 연결되어있는 경우(랜선이 연결 안된 경우는 chr=='0'에서 continue 동작함)
		if(n==1 && pq.isEmpty())
			bw.write('0');
		else if(n==1 && pq.size()==1)
			bw.write(String.valueOf(pq.poll().w));
		else
			bw.write(String.valueOf(kruskal()));
		br.close();
		bw.close();
	}
	
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	public static int kruskal() {
		//이어진 랜선의 수
		int cnt = 0;
		//이어진 랜선의 길이의 합
		int len = 0;
		
		while(!pq.isEmpty()) {
			Node1414 now = pq.poll();
			int from = now.from;
			int to = now.to;
			int w = now.w;
			
			//root가 동일하다면 연결된 것으로 continue
			if(find(from)==find(to))
				continue;
			//root를 연결
			parents[find(from)] = find(to);
			//연결된 랜선의 길이 누적
			len+=w;
			//모두 이어졌다면 모든 랜선의 길이의 합에서 연결된 랜선의 길이의 합을 차감한 후 출력
			if(++cnt==n-1)
				return sum-len;
		}
		
		//모든 컴퓨터가 이어지지 못하면 -1 출력
		return -1;
	}
}