import java.util.*;
import java.io.*;


class SolverPoint {
	int index;
	double x;
	double y;
}


public class solver_YuiKarasawa {

	
	private static double calcDistance(double oldx, double oldy, double newx, double newy) {
		return Math.sqrt((newx-oldx)*(newx-oldx) + (newy-oldy)*(newy-oldy));
	}
	
	private static double calcDistanceByPoint(SolverPoint p1, SolverPoint p2) {
		return calcDistance(p1.x, p1.y, p2.x, p2.y);
	}
	
	
	private static List<SolverPoint> makeSpList(String fileName) {

        List<String> temp = new ArrayList<String>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            

            while((line = br.readLine()) != null) {
                temp.add(line);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        
        
        // define list of points
        List<SolverPoint> spList = new ArrayList<SolverPoint>();
         
        
        for (int i=1;i<temp.size(); i++) {
            SolverPoint sp = new SolverPoint();
        	String[] s = temp.get(i).split(",");
            
            sp.index = i-1;
            sp.x = Double.parseDouble(s[0]);
            sp.y = Double.parseDouble(s[1]);
            spList.add(sp);
        }
		
		return spList;
	}
	
	
	private static double calcDistances(List<SolverPoint> spList) {
        double sum = 0; 
    	
        // for のなかにいれる
        for (int i=0; i<spList.size()-1; i++) {
            sum += calcDistance(spList.get(i).x, spList.get(i).y, spList.get(i+1).x, spList.get(i+1).y);
        }
		return sum;
	}
	
	private static ArrayList<Integer> tsp(double[][] graph, boolean[] v, 
			int currPos, int n, 
			int count, double cost, double ans, ArrayList<Integer> route, ArrayList<Integer> routeTmp) 
{ 
		
	// log

	// If last node is reached and it has a link 
	// to the starting node i.e the source then 
	// keep the minimum value out of the total cost 
	// of traversal and "ans" 
	// Finally return to check for more possible values 
	if (count == n && graph[currPos][0] > 0) 
	{ 
		if (ans > cost + graph[currPos][0]) {
			routeTmp = route;
		}
		System.out.println(ans);
		System.out.println(route.toString());
		System.out.println(routeTmp.toString());
		return routeTmp; 
	} 

	// BACKTRACKING STEP 
	// Loop to traverse the adjacency list 
	// of currPos node and increasing the count 
	// by 1 and cost by graph[currPos,i] value 
	for (int i = 0; i < n; i++) 
	{ 
		
		if (v[i] == false && graph[currPos][i] > 0) 
		{ 

			// Mark as visited 
			v[i] = true; 
			routeTmp.add(i);
			System.out.println(routeTmp.toString());
			route = tsp(graph, v, i, n, count + 1, 
					cost + graph[currPos][i], ans, route, routeTmp); 

			// Mark ith node as unvisited 
			v[i] = false;
			routeTmp.remove(routeTmp.size()-1);

		}
	}
	return route;
} 


	
	private static double[][] makeGraph(List<SolverPoint> spList) {
		double graph[][] = new double[spList.size()][spList.size()];
		for (int i = 0; i<spList.size(); i++) {
			for (int j=0; j<spList.size(); j++) {
				graph[j][i] = calcDistanceByPoint(spList.get(i), spList.get(j));
			}
		}
		
		
		
		
		return graph;
	}
	
	
    public static void main(String[] args) {
    	
    	// make list of points by input
    	List<SolverPoint> spList = makeSpList(args[0]);
    	
    	// ---somehow change the order---

    	int listSize = spList.size();
    	
    	
    	
    	boolean[] v = new boolean[listSize];
    	v[0] = true;
    	double ans = Double.MAX_VALUE;
    	
    	double graph[][] = makeGraph(spList);
    	
    	ArrayList<Integer> route = new ArrayList<Integer>();
    	ArrayList<Integer> routeTmp = new ArrayList<Integer>();
    	route = tsp(graph, v, 0, listSize, 1, 0, ans, route, routeTmp);
    	
    	// Calcurate all of distances
    	//double totalDistances = calcDistances(spList);
    	
    	System.out.println(route.toString());
    	
    	System.out.println("Index");
        for (int i=0; i<spList.size(); i++) { // exclude start point & final destination
        	System.out.println(spList.get(i).index);
        }
        
        
        
        
    }
}