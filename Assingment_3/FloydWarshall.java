import java.util.Stack;

public class FloydWarshall{
	private boolean hasNegativeCycle;
	private double[][] distTo;
	private DirectedEdge[][] edgeTo;

	public FloydWarshall(AdjMatrixEdgeWeightedDirectedGraph G){
		int V = G.V();
		distTo = new double[V][V];
		edgeTo = new DirectedEdge[V][V];
		for(int v = 0; v < V; v++){
			for(int w = 0; w < V; w++){
				distTo[v][w] = Double.POSITIVE_INFINITY;
			}
		}

		for(int v = 0; v < G.V(); v++){
			for(DirectedEdge e: G.edgeFromTo(v)){
				distTo[e.from()][e.to()] = e.weight();
				edgeTo[e.from()][e.to()] = e;
			}

			if(distTo[v][v] >= 0.0){
				distTo[v][v] = 0.0;
				edgeTo[v][v] = null;
			}
		}

		for(int i = 0; i < V; i++){
			for(int v = 0; v < V; v++){
				if(edgeTo[v][i] == null) continue;
				for(int w = 0; w < V; w++){
					if(distTo[v][w] > distTo[v][i] + distTo[i][w]){
						distTo[v][w] = distTo[v][i] + distTo[i][w];
						edgeTo[v][w] = edgeTo[i][w];
					}
          if(distTo[v][w] < 0.0){
            this.hasNegativeCycle = true;
            return;
          }
        }
      }
    }
  }

  public boolean hasNegativeCycle(){
		return hasNegativeCycle;
	}

	public Iterable<DirectedEdge> negativeCycle(){
		for(int v = 0; v < distTo.length; v++){
			if(distTo[v][v] < 0.0){
				int V = edgeTo.length;
				EdgeWeightedDirectedGraph spt = new EdgeWeightedDirectedGraph(V);

				for(int w = 0; w < V; w++){
					if(edgeTo[v][w] != null)
						spt.addEdge(edgeTo[v][w]);
				}

				EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
				return finder.cycle();
			}
		}
		return null;
	}

	public boolean hasPath(int f, int t){
		return distTo[f][t] < Double.POSITIVE_INFINITY;
	}

	public double dist(int f, int t){
		if(hasNegativeCycle())
			throw new UnsupportedOperationException("Negative cost cycle exists");
		return distTo[f][t];
	}

	public Iterable<DirectedEdge> path(int f, int t){
		if(hasNegativeCycle())
			throw new UnsupportedOperationException("Negative cost cycle exists");
		if(!hasPath(f, t))
			return null;

		Stack<DirectedEdge> path = new Stack<DirectedEdge>();

		for(DirectedEdge e = edgeTo[f][t]; e != null; e = edgeTo[f][e.from()]){
			path.push(e);
		}
		return path;
	}
}
