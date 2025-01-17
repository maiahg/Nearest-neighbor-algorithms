# Nearest neighbour algorithms
Given a large number of vectors (the dataset) in a high-dimensional space and given a query vector, the objective is to find the vector that is the closest to the query vector, usually using an Euclidean distance. This vector is called the nearest neighbour, in the general formulation, the objective is to find the k nearest neighbours (kNN).

The kNN search could be very time consuming if the dataset is very large. In some applications, it's not necessary to obtain the exact nearest neighbours, an approximate solution could suffice. We can use an Approximate Nearest Neighbours (ANN) in this case.

This repo includes kNN, ANN algorithms implementation as well as a small dataset for testing since a big dataset is too large to be uploaded.

## k Nearest Neighbors
The kNN algorithm was implemented with three versions, each containing different implementations for the priority queue. The three versions are as follows:
- PriorityQueue1: implemented with a simple array, the max element being the last element in the array.
- PriorityQueue2: implemented with an array representing a heap, the max element is at the root of the heap tree. Each insertion will trigger an upheap operation. Each removal will trigger a downheap operation.
- PriorityQueue3: simply use the java.PriorityQueue standard class.

### Instructions on how to run the program
To run the program, the following arguments should be provided to the main program
- version number
- value of k
- dataset filename
- query point filename

For example, the command line would look like this: <br>


    java KNN 1 10 siftsmall_base.fvecs siftsmall_query.fvecs

## Approximate Nearest Neighbors
The ANN was implemented using graph traversal. Graph-based ANN (GANN) builds a graph from the vectors in the dataset in which connected nodes are vectors relatively close to each other. When a query is received, GANN will traverse the graph until it finds a good candidate nearest neighbour. There are two steps:
- Building the graph: pre-processing step that is executed only once using all the vectors in the dataset.
- Graph traversal: finds an approximate nearest neighbours from a query point
  
### Instructions on how to run the program
To run the program, the following arguments should be provided to the main program
- value of k
- value of s (capacity of the array A in which the vertices of the graph will be sorted by their distance to Q)
- dataset filename
- query point filename

For example, the command line would look like this: <br>


    java GraphA1NN 10 25 siftsmall_base.fvecs siftsmall_query.fvecs
