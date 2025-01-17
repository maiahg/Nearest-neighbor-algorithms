import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Accuracy {
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

    private static boolean linearSearch(List<Integer> list, int target) {
        return list.contains(target);
    }

    public static void main(String[] args) throws IOException, Exception {
        ArrayList<List<Integer>> adjacency = readAdjacencyFile("knn_3_10_100_10000.txt", 100);
        ArrayList<List<Integer>> result = readAdjacencyFile("10_100_100_10000.txt", 100);
        System.out.println("Result: " + result);
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (linearSearch(adjacency.get(i), result.get(i).get(0))) {
                count++;
            }
        }
        System.out.println("Accuracy: " + count);
    }
}
