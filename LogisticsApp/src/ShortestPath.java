
import java.util.*;

    /* The authors of this work have released all rights to it and placed it
in the public domain under the Creative Commons CC0 1.0 waiver
(http://creativecommons.org/publicdomain/zero/1.0/).

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Retrieved from: http://en.literateprograms.org/Dijkstra's_algorithm_(Java)?oldid=15444
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
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(source);

            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();
                System.out.println(u);
                // Visit each edge exiting u
                for (Edge e : u.adjacencies)
                {

                    Vertex v = e.target;
                    double weight = e.weight;
                    System.out.println(weight);
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

        public static List<Vertex> getShortestPathTo(Vertex target)
        {
            List<Vertex> path = new ArrayList<Vertex>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                path.add(vertex);

            Collections.reverse(path);
            return path;
        }

        public static void main(String[] args){

            Vertex v1 = new Vertex("Baltimore");
            Vertex v2 = new Vertex("Washington");
            Vertex v3 = new Vertex("Philadelphia");
            Vertex v4 = new Vertex("Binghamton");
            Vertex v5 = new Vertex("Allentown");
            Vertex v6 = new Vertex("New York");

            v1.adjacencies = new ArrayList<Edge>();
            v1.adjacencies.add(new Edge(v1,  79));
            v1.adjacencies.add(new Edge(v2,  39));

            System.out.println(v1.adjacencies.get(1));


        }

        public static void returnSP(String start, String end, HashMap<String,Facility> facilities ){

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





            Vertex start1 = vertices.get("Santa Fe, NM");
            //System.out.println(start1.adjacencies.get(0).weight);
            computePaths(start1);

            Vertex end1 = vertices.get("Norfolk, VA");
            List<Vertex> temp = getShortestPathTo(end1);

           System.out.println(temp);



        }
    }



