import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class KNN {
    private PointSet pointSet;
    private int k;

    public KNN(PointSet pointSet) {
        this.pointSet = pointSet;
        this.k = 1;
    }

    public void setK(int k) {
        this.k = k;
    }
    
    public static ArrayList<LabelledPoint> findNN(int k, int versionNumber, ArrayList<LabelledPoint> dataSet, LabelledPoint queryPoint) {
        
        // find the k nearest neighbors of queryPoint in pointSet
        ArrayList<LabelledPoint> neighbours = new ArrayList<LabelledPoint>(k);

        // create a priority queue
        PriorityQueueIF<LabelledPoint> priorityQueue;

        // use the specified version of priority queue
        switch (versionNumber) {
            case 1:
                priorityQueue = new PriorityQueue1<LabelledPoint>(k);
                for (LabelledPoint p : dataSet) {
                    double distance = p.distanceTo(queryPoint);
                    p.setKey(distance);
                    priorityQueue.offer(p);
                }
                while (!priorityQueue.isEmpty()) {
                    neighbours.add(priorityQueue.poll());
                }
                break;

            case 2:
                priorityQueue = new PriorityQueue1<LabelledPoint>(k);
                for (LabelledPoint p : dataSet) {
                    double distance = p.distanceTo(queryPoint);
                    p.setKey(distance);
                    priorityQueue.offer(p);
                }
                while (!priorityQueue.isEmpty()) {
                    neighbours.add(priorityQueue.poll());
                }
                break;

            case 3:
                priorityQueue = new PriorityQueue1<LabelledPoint>(k);
                for (LabelledPoint p : dataSet) {
                    double distance = p.distanceTo(queryPoint);
                    p.setKey(distance);
                    priorityQueue.offer(p);
                }
                while (!priorityQueue.isEmpty()) {
                    neighbours.add(priorityQueue.poll());
                }
                break;

            default:
                System.out.println("Invalid version number");
                break;
        }
        return neighbours;
    }

    public static void main(String[] args) {

        // read the data points from the files
        PointSet queryPoints = new PointSet(PointSet.read_ANN_SIFT("siftsmall_query.fvecs"));

        // read the query points from the files
        PointSet dataPoints = new PointSet(PointSet.read_ANN_SIFT("siftsmall_base.fvecs"));

        int k = 1; // change the number of neighbours here
        int versionNumber = 3; // change the version number here

        long startTime = System.currentTimeMillis();        
        
        try {
            // Write the neighbors to a file
            FileWriter fileWriter = new FileWriter("knn_" + versionNumber + "_" + k + "_1000_1000000.txt");
            StringBuilder str = new StringBuilder("");


            for (int i = 0; i < queryPoints.getPointsList().size(); i++) {
                LabelledPoint queryPoint = queryPoints.getPointsList().get(i);
                ArrayList<LabelledPoint> neighbors = findNN(k, versionNumber, dataPoints.getPointsList(), queryPoint);
                
                System.out.print((i) + " : ");
                str.append((i) + " : ");

                for (int j = 0; j < neighbors.size(); j++) {
                    System.out.print(neighbors.get(j).getLabel() + ", ");
                    str.append(neighbors.get(j).getLabel() + ", ");
                }
                str.append("\n");
                System.out.println();

                
            }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println();
        System.out.println("PriorityQueue" + versionNumber + ", k = " + k);
        System.out.println("Runtime: " + (totalTime) + "ms");

        str.append("\n");
        str.append("PriorityQueue" + versionNumber + ", k = " + k);
        str.append("\n");
        str.append("Runtime: " + (totalTime) + "ms");
        fileWriter.write(str.toString());

        fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
