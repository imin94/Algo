import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Vertex2887 {
	
	int idx;
	int x;
	int y;
	int z;
	
	public Vertex2887(int idx, int x, int y, int z) {
		this.idx=idx;
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
}

class Edge2887{
	
	int idxS;
	int idxE;
	int len;
	
	public Edge2887(int idxS, int idxE, int len) {
		this.idxS=idxS;
		this.idxE=idxE;
		this.len=len;
	}
	
}

public class Main {
	
	public static int n;
	public static int sum;
	public static int[] p;
	public static Vertex2887[] vertex;
	public static ArrayList<Edge2887> edge;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		sum = 0;
		edge = new ArrayList<>();
		p = new int[n];
		vertex = new Vertex2887[n];
		
		for(int i = 0; i<n; i++) {
			p[i]=i;
		}
		
		//행성의 좌표를 입력, 거리를 나타내는 len 값은 default를 0으로 설정
		for(int i = 0; i<n; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			vertex[i] = new Vertex2887(i,a,b,c);
		}
		
		//행성들의 위치를 x좌표를 기준으로 오름차순
		Arrays.sort(vertex, new Comparator<Vertex2887>() {

			@Override
			public int compare(Vertex2887 o1, Vertex2887 o2) {
				// TODO Auto-generated method stub
				return o1.x-o2.x;
			}
			
		});
		//x좌표의 차이를 ArrayList edge에 입력
		for(int i = 1; i<n; i++) {
			edge.add(new Edge2887(vertex[i].idx, vertex[i-1].idx, Math.abs(vertex[i].x - vertex[i-1].x)));
		}
		//행성들의 위치를 y좌표를 기준으로 오름차순
		Arrays.sort(vertex, new Comparator<Vertex2887>() {
			
			@Override
			public int compare(Vertex2887 o1, Vertex2887 o2) {
				// TODO Auto-generated method stub
				return o1.y-o2.y;
			}
			
		});
		//y좌표의 차이를 ArrayList edge에 입력
		for(int i = 1; i<n; i++) {
			edge.add(new Edge2887(vertex[i].idx, vertex[i-1].idx, Math.abs(vertex[i].y - vertex[i-1].y)));
		}
		//행성들의 위치를 z좌표를 기준으로 오름차순
		Arrays.sort(vertex, new Comparator<Vertex2887>() {
			
			@Override
			public int compare(Vertex2887 o1, Vertex2887 o2) {
				// TODO Auto-generated method stub
				return o1.z-o2.z;
			}
			
		});
		//y좌표의 차이를 ArrayList edge에 입력
		for(int i = 1; i<n; i++) {
			edge.add(new Edge2887(vertex[i].idx, vertex[i-1].idx, Math.abs(vertex[i].z - vertex[i-1].z)));
		}
		
		Collections.sort(edge, new Comparator<Edge2887>() {
			@Override
			public int compare(Edge2887 o1, Edge2887 o2) {
				return o1.len-o2.len;
			}
		});
		
		for(Edge2887 i : edge) {
			if(top(i.idxS)!=top(i.idxE)) {
				union(i.idxS, i.idxE);
				sum += i.len;
			}
		}
		
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
	}
	
	public static int top(int i) {
		if(p[i] != i)
			p[i] = top(p[i]);
		return p[i];
	}
	
	public static void union(int a, int b) {
		int topA = top(a);
		int topB = top(b);
		
		if(topA<topB)
			p[topB]=topA;
		else
			p[topA]=topB;
	}
	
}