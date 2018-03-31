import java.util.Stack;

public class EdgeWeightedDirectedCycle {
	private boolean[] marked;
	private DirectedEdge[] edgeTo;
	private boolean[] onStack;
	private Stack<DirectedEdge> cycle;

	public EdgeWeightedDirectedCycle(EdgeWeightedDirectedGraph G){
		this.marked = new boolean[G.V()];
		this.onStack = new boolean[G.V()];
		this.edgeTo = new DirectedEdge[G.V()];
		for(int i = 0; i < G.V(); i++)
			if(!marked[i]) dfs(G, i);
	}

	private void dfs(EdgeWeightedDirectedGraph G, int v){
		this.onStack[v] = true;
		this.marked[v] = true;

		for(DirectedEdge e : G.adj(v)){
			int w = e.to();
			if(this.cycle != null)
				return;
			else if(!marked[w]){
				edgeTo[w] = e;
				dfs(G, w);
			}
			else if(onStack[w]){
				this.cycle = new Stack<DirectedEdge>();
				DirectedEdge f = e;
				while(f.from() != w){
					cycle.push(f);
					f = edgeTo[f.from()];
				}
				this.cycle.push(f);
				return;
			}
		}
		onStack[v] = false;
	}

	public boolean hasCycle(){
		return cycle != null;
	}

	public Iterable<DirectedEdge> cycle() {
		return cycle;
	}
}
