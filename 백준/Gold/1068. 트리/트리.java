import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int parent;
		List<Integer> child;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] tree = new Node[N];
		for(int i = 0 ; i < N ; i++) {
			tree[i] = new Node();
			tree[i].child = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p;
		int root = -1;
		for(int i = 0 ; i < N ; i++) {
			p = Integer.parseInt(st.nextToken());
			if(p == -1) {
				root = i;
			}else {
				tree[i].parent = p;
				tree[p].child.add(i);
			}
		}
		
		int remove = Integer.parseInt(br.readLine());
		
		if(remove == root) {
			System.out.println(0);
			return;
		}
		
		tree[tree[remove].parent].child.remove(Integer.valueOf(remove));
		
		int ans = 0;
		Node node;
		Queue<Node> que = new LinkedList<>();
		que.add(tree[root]);
		while(!que.isEmpty()) {
			node = que.poll();
			if(node.child.isEmpty()) {
				ans++;
			}
			for(int n : node.child) {
				que.add(tree[n]);
			}
		}
		System.out.println(ans);
	}
}
