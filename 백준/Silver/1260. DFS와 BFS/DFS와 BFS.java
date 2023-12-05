import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static StringBuilder sb = new StringBuilder();
    public static List<Integer>[] list;
    public static boolean[] flagDfs;
    public static boolean[] flagBfs;

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[n+1];
        for(int i = 0; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        flagDfs = new boolean[n+1];
        flagBfs = new boolean[n+1];
        
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        for(int i = 1; i<=n; i++) {
        	Collections.sort(list[i]);
        }
        flagDfs[v]=true;
        dfs(v);
        sb.append('\n');
        bfs(v);
        System.out.println(sb.toString());
        
    }
    
    public static void dfs(int v) {
        sb.append(v).append(" ");
        for(int i : list[v]) {
            if(flagDfs[i])
                continue;
            flagDfs[i]=true;
            dfs(i);
        }
    }
    public static void bfs(int v) {
    	Queue<Integer> que = new LinkedList<Integer>();
    	que.add(v);
    	sb.append(v).append(' ');
    	flagBfs[v]=true;
    	
    	while(!que.isEmpty()) {
    		int idx = que.poll();
    		for(int i : list[idx]) {
    			if(flagBfs[i])
    				continue;
    			flagBfs[i]=true;
    			que.add(i);
    			sb.append(i).append(' ');
    		}
    	}
    }

}