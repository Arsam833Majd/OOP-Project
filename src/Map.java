import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
public class Map {
    int numberOfNodes;
    int numberOfEdges;
    int[] firstColumnOfNodes;
    int[] secondColumnOfNodes;
    int[] distances;
    Map(String address){
            File mapFile = new File(address);
            try
            {
                Scanner scanner = new Scanner(mapFile);
                numberOfNodes = scanner.nextInt();
                numberOfEdges = scanner.nextInt();
                firstColumnOfNodes = new int[numberOfEdges];
                secondColumnOfNodes = new int[numberOfEdges];
                distances = new int[numberOfEdges];
                for (int i = 0; i < numberOfEdges; i++)
                {
                    firstColumnOfNodes[i] = scanner.nextInt();
                    secondColumnOfNodes[i] = scanner.nextInt();
                    distances[i] = scanner.nextInt();
                }
            }
            catch (FileNotFoundException exception)
            {
                System.out.println(exception);
            }
    }
    Vector<Integer> findNearNodes(int Node, int NodesOrDistances) {
            Vector<Integer> nearNodes = new Vector<Integer>();
            Vector<Integer> distanceToNearNodes = new Vector<Integer>();
            for (int i = 0; i < numberOfEdges; i++)
            {
                if (firstColumnOfNodes[i] == Node)
                {
                    nearNodes.add(secondColumnOfNodes[i]);
                    distanceToNearNodes.add(distances[i]);
                }
                if (secondColumnOfNodes[i] == Node)
                {
                    nearNodes.add(firstColumnOfNodes[i]);
                    distanceToNearNodes.add(distances[i]);
                }
            }
            return (NodesOrDistances == 1) ? nearNodes : distanceToNearNodes;
    }
    int nextClosestNode(int[] distanceArray, int numberOfNodesInteger, Vector<Integer> foundedNodesVector, int recentClosestDistance) {
            int[][] sortedDistanceArray = new int[2][numberOfNodesInteger];
            for (int i = 0; i < numberOfNodesInteger; i++)
            {
                sortedDistanceArray[0][i] = distanceArray[i];
                sortedDistanceArray[1][i] = i;
            }
            for (int i = 0; i < numberOfNodesInteger - 1; i++)
            {
                for ( int j = i + 1; j < numberOfNodesInteger; j++)
                {
                    if (sortedDistanceArray[0][i] > sortedDistanceArray[0][j])
                    {
                        int swapInteger = sortedDistanceArray[0][i];
                        sortedDistanceArray[0][i] = sortedDistanceArray[0][j];
                        sortedDistanceArray[0][j] = swapInteger;
                        swapInteger = sortedDistanceArray[1][i];
                        sortedDistanceArray[1][i] = sortedDistanceArray[1][j];
                        sortedDistanceArray[1][j] = swapInteger;
                    }
                }
            }
            int index = numberOfNodesInteger - 1;
            while (sortedDistanceArray[0][index] > recentClosestDistance)
            {
                index --;
            }
            int finalIndex = index;
            int sameDistance = 1;
            while (sameDistance == 1)
            {
                if (sortedDistanceArray[0][index] == recentClosestDistance)
                {
                    if (foundedNodesVector.contains(sortedDistanceArray[1][index]+1))
                    {
                        index --;
                    }
                    else
                    {
                        finalIndex = index;
                        sameDistance = 0;
                    }
                }
                else
                {
                    finalIndex ++;
                    sameDistance = 0;
                }
            }
            return sortedDistanceArray[1][finalIndex] + 1;
    }
    int findPath(int Node, int[] distanceArray, Vector<Integer> foundedNodesVector) {
            int finalNearNode = 0;
            int finalNearNodeFound = 0;
            while (finalNearNodeFound == 0)
            {
                for (int i = 0; i < findNearNodes(Node, 1).size() && finalNearNodeFound == 0; i++)
                {
                    if (foundedNodesVector.contains(findNearNodes(Node,1).get(i)))
                    {
                        if (distanceArray[Node - 1] == distanceArray[findNearNodes(Node,1).get(i) - 1] + findNearNodes(Node, 0).get(i))
                        {
                            finalNearNode = findNearNodes(Node,1).get(i);
                            finalNearNodeFound = 1;
                        }
                    }
                }
            }
            return finalNearNode;
    }
    int shortestPath(int origin, int destination, int showPathOrTime) {
            int[][] paths;
            paths = new int[numberOfNodes][];
            paths[origin - 1] = new int[1];
            paths[origin - 1][0] = origin;
            int[] distanceFromOrigin = new int[numberOfNodes];
            for (int i = 0; i < numberOfNodes; i++)
            {
                distanceFromOrigin[i] = -1;
            }
            distanceFromOrigin[origin - 1] = 0;
            int recentlyFoundNode = origin;
            Vector<Integer> FoundedNodes = new Vector<Integer>();
            FoundedNodes.add(origin);
            while (recentlyFoundNode != destination)
            {
                for (int i = 0; i < findNearNodes(recentlyFoundNode, 1).size(); i++)
                {
                    if (distanceFromOrigin[findNearNodes(recentlyFoundNode, 1).get(i) - 1] == -1)
                    {
                        distanceFromOrigin[findNearNodes(recentlyFoundNode, 1).get(i) - 1] = distanceFromOrigin[recentlyFoundNode - 1] + findNearNodes(recentlyFoundNode, 0).get(i);
                    }
                    else
                    {
                        if (distanceFromOrigin[recentlyFoundNode - 1] + findNearNodes(recentlyFoundNode, 0).get(i) < distanceFromOrigin[findNearNodes(recentlyFoundNode, 1).get(i) - 1])
                        {
                            distanceFromOrigin[findNearNodes(recentlyFoundNode, 1).get(i) - 1] = distanceFromOrigin[recentlyFoundNode - 1] + findNearNodes(recentlyFoundNode, 0).get(i);
                        }
                    }
                }
                recentlyFoundNode = nextClosestNode(distanceFromOrigin, numberOfNodes, FoundedNodes, distanceFromOrigin[recentlyFoundNode - 1]);
                FoundedNodes.add(recentlyFoundNode);
                paths[recentlyFoundNode - 1] = new int[paths[findPath(recentlyFoundNode, distanceFromOrigin, FoundedNodes) - 1].length + 1];
                System.arraycopy(paths[findPath(recentlyFoundNode, distanceFromOrigin, FoundedNodes) - 1], 0, paths[recentlyFoundNode - 1], 0, paths[findPath(recentlyFoundNode, distanceFromOrigin, FoundedNodes) - 1].length);
                paths[recentlyFoundNode - 1][paths[recentlyFoundNode - 1].length - 1] = recentlyFoundNode;
            }
            if (showPathOrTime == 1)
            {
                String pathString = Arrays.toString(paths[destination - 1]);
                System.out.println(pathString);
            }
            return distanceFromOrigin[destination - 1];
        }
}


