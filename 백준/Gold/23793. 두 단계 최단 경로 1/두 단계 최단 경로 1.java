import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

public class Main {
    static int[] dist;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        dist = new int[N + 1];
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        int u, v, w;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            u = parseInt(st.nextToken());
            v = parseInt(st.nextToken());
            w = parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        int x, y, z;
        st = new StringTokenizer(br.readLine());
        x = parseInt(st.nextToken());
        y = parseInt(st.nextToken());
        z = parseInt(st.nextToken());

        int result1, result2;
        dijkstra(x, -1);
        result1 = dist[y] == MAX_VALUE ? -1 : dist[y];
        dijkstra(y, -1);
        if (result1 == -1 || dist[z] == MAX_VALUE)
            result1 = -1;
        else
            result1 += dist[z];

        dijkstra(x, y);
        result2 = dist[z] == MAX_VALUE ? -1 : dist[z];

        System.out.println(result1 + " " + result2);
        br.close();
    }

    static void dijkstra(int start, int y) {
        Arrays.fill(dist, MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        dist[start] = 0;
        pq.offer(new Node(start, dist[start]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.v] < cur.w)
                continue;

            for (Node next : graph.get(cur.v)) {
                if (next.v == y) continue;

                if (dist[next.v] < dist[cur.v] + next.w)
                    continue;

                dist[next.v] = dist[cur.v] + next.w;
                pq.offer(new Node(next.v, dist[next.v]));
            }
        }
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}