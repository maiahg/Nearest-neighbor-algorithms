import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


class GraphA1NN {
	private int S; // capacity of array A

	UndirectedGraph<LabelledPoint> annGraph;
    private PointSet dataset;
	
	// construct a graph from a file
    public GraphA1NN(String fvecs_filename) {

	    annGraph= new UndirectedGraph<>();
		dataset= new PointSet(PointSet.read_ANN_SIFT(fvecs_filename));
    }

	// construct a graph from a dataset
    public GraphA1NN(PointSet set){
		
	   annGraph= new UndirectedGraph<>();
       this.dataset = set;
    }

	public void setS(int S) { this.S= S; }

    // build the graph
    public void constructKNNGraph(int K) throws IOException, Exception {
		ArrayList<List<Integer> > adjacency= GraphA1NN.readAdjacencyFile("knn.txt", dataset.getPointsList().size());
		for (int i=0; i <dataset.getPointsList().size(); i++) {
			for (int j=0; j < Math.min(K, adjacency.get(i).size()); j++) {
				annGraph.addEdge(dataset.getPointsList().get(i),
				                 dataset.getPointsList().get(adjacency.get(i).get(j)));
			}
		}
	}

	public LabelledPoint find1NN(LabelledPoint pt) {
		ArrayList<LabelledPoint> arrayA = new ArrayList<>();

		// Randomly select a vertex W from the graph
		LabelledPoint W = annGraph.getRandomVertex();

		// Compute the distance from W to the query point and set it as the key of W
		W.setKey(W.distanceTo(pt));

		// Add W to array A
		arrayA.add(W);

		// While array A is not full
		for (int i = 0; i < arrayA.size(); i++) {
			// Select a vertex C from array A that has not been checked and has the smallest key
			LabelledPoint C = arrayA.get(i);
			if (!C.isChecked()) {
				C.checked();
				// For each neighbor V of C
				for (LabelledPoint V : annGraph.getNeighbors(C)) {
					// If the distance from V to the query point has not been computed then compute it and add it to array A
					if (V.getIKey() != pt.getLabel()) {
						V.setKey(V.distanceTo(pt));
						V.setIKey(pt.getLabel());
						if (arrayA.size() < S) {
							arrayA.add(V);
							Collections.sort(arrayA, new DistanceComparator());
						} else {
							if (V.getKey() < arrayA.get(arrayA.size() - 1).getKey()) {
								arrayA.remove(arrayA.size() - 1);
								arrayA.add(V);
								Collections.sort(arrayA, new DistanceComparator());
								}
							}
						
					}
				}
			}
		}

	return arrayA.get(0);
}
	
	public static ArrayList<List<Integer> > readAdjacencyFile(String fileName, int numberOfVertices) 
	                                                                 throws Exception, IOException
	{	
		ArrayList<List<Integer> > adjacency= new ArrayList<List<Integer> >(numberOfVertices);
		for (int i=0; i<numberOfVertices; i++) 
			adjacency.add(new LinkedList<>());
		
		// read the file line by line
	    String line;
        BufferedReader flightFile = 
        	      new BufferedReader( new FileReader(fileName));
        
		// each line contains the vertex number followed 
		// by the adjacency list
        while( ( line = flightFile.readLine( ) ) != null ) {
			StringTokenizer st = new StringTokenizer( line, ":,");
			int vertex= Integer.parseInt(st.nextToken().trim());
			while (st.hasMoreTokens()) { 
			    adjacency.get(vertex).add(Integer.parseInt(st.nextToken().trim()));
			}
        } 
	
	    return adjacency;
	}
	
	public int size() { return annGraph.size(); }

    public static void main(String[] args) throws IOException, Exception {
		PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT(args[3]));
		PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT(args[2]));
		GraphA1NN graph = new GraphA1NN(pointSet);

		graph.constructKNNGraph(Integer.parseInt(args[0]));
		graph.setS(Integer.parseInt(args[1]));

		double avgtime = 0.0;

		for (LabelledPoint q : queryPts.getPointsList()) {
			long startTime = System.currentTimeMillis();

			LabelledPoint nn = graph.find1NN(q);

			long endTime = System.currentTimeMillis();
			avgtime += (double) (endTime - startTime);

			System.out.println(q.getLabel() + " : " + nn.getLabel());
		}

		avgtime /= (double) queryPts.getPointsList().size();
		System.out.println("Average time: " + avgtime + " ms");
	}
}

