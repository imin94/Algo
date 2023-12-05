import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	int idx;
	char value;
	Node p;
	int chL;
	int chR;
	
	public Node() {
	}
	
	public Node(int idx, char value) {
		this.idx = idx;
		this.value=value;
	}
	
}

public class Main {
	
	public static Node[] tree;
	public static StringBuilder sb = new StringBuilder();
	public static int n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		tree = new Node[n];
		
		for(int i = 0; i<n; i++) {
			tree[i]=new Node(i,(char)('A'+i));
		}
		for(int i = 0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int c = 0; c<3; c++) {
				char value = str[0].charAt(0);
				char l = str[1].charAt(0);
				char r = str[2].charAt(0);
				if(l != '.')
					tree[value-'A'].chL=l-'A';
				if(r != '.')
					tree[value-'A'].chR=r-'A';
			}
		}
		
		front(0);
		sb.append('\n');
		mid(0);
		sb.append('\n');
		back(0);
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	public static void front(int i) {
		sb.append(tree[i].value);
		if(tree[i].chL != 0)
			front(tree[i].chL);
		if(tree[i].chR != 0)
			front(tree[i].chR);
	}
	
	public static void mid(int i) {
		if(tree[i].chL != 0)
			mid(tree[i].chL);
		sb.append(tree[i].value);
		if(tree[i].chR != 0)
			mid(tree[i].chR);
	}
	
	public static void back(int i) {
		if(tree[i].chL != 0)
			back(tree[i].chL);
		if(tree[i].chR != 0)
			back(tree[i].chR);
		sb.append(tree[i].value);
	}

}