
import java.text.DecimalFormat;
import java.util.*;

    /* The authors of this work have released all rights to it and placed it
in the public domain under the Creative Commons CC0 1.0 waiver
(http://creativecommons.org/publicdomain/zero/1.0/).

Retrieved from: http://en.literateprograms.org/Dijkstra's_algorithm_(Java)?oldid=15444



-NOTE: A few changes have been made to the existing code to work for us.
*/


    class Vertex implements Comparable<Vertex>
    {
        public final String name;
        public ArrayList<Edge> adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;
        public Vertex(String argName) { name = argName; }
        public String toString() { return name; }
        public int compareTo(Vertex other)
        {
            return Double.compare(minDistance, other.minDistance);
        }

    }


    class Edge
    {
        public final Vertex target;
        public final double weight;
        public Edge(Vertex argTarget, double argWeight)
        { target = argTarget; weight = argWeight; }
    }

    public class ShortestPath
    {
        public static void computePaths(Vertex source)
        {
            source.minDistance = 0.0;
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
            vertexQueue.add(source);

            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();

                // Visit each edge exiting u
                for (Edge e : u.adjacencies)
                {

                    Vertex v = e.target;
                    double weight = e.weight;

                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {
                        vertexQueue.remove(v);

                        v.minDistance = distanceThroughU ;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }

        public static ArrayList<Vertex> getShortestPathTo(Vertex target)
        {
            ArrayList<Vertex> path = new ArrayList<>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
                path.add(vertex);
            }
            Collections.reverse(path);
            return path;
        }


        public static ArrayList<Vertex> returnSP(String start, String end, HashMap<String,Facility> facilities ){

            HashMap<String,Vertex> vertices = new HashMap<>();


           //Adding Vertices
            for (String key : facilities.keySet()) {
                Vertex temp = new Vertex(key);
                vertices.put(key,temp);
            }

            ArrayList<LinkImpl> links;

            //Adding edges.
            for(String key : facilities.keySet()){

                Vertex currentVtex = vertices.get(key);


                links = facilities.get(key).getConnections();
                currentVtex.adjacencies = new ArrayList<>();
                for(Link l: links){

                    currentVtex.adjacencies.add(new Edge(vertices.get(l.getCity()),l.getDistance()));
                }



            }

            Vertex startCity = vertices.get(start);
            Vertex endCity = vertices.get(end);
            computePaths(startCity);


            ArrayList<Vertex> path = getShortestPathTo(endCity);
            return path;
            //System.out.println(finalPrint);





        }



    }



