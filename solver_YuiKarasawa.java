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
         
        // define start point
        SolverPoint startPoint = new SolverPoint();
        startPoint.index = 0;
        startPoint.x = 0;
        startPoint.y = 0;
        
        // add startPoint to beginning of List
        spList.add(startPoint);
        
        for (int i=1;i<temp.size(); i++) {
            SolverPoint sp = new SolverPoint();
        	String[] s = temp.get(i).split(",");
            
            sp.index = i;
            sp.x = Double.parseDouble(s[0]);
            sp.y = Double.parseDouble(s[1]);
            spList.add(sp);
        }
        // add startPoint to end of List
        spList.add(startPoint);
		
		return spList;
	}
	
	
	private static double calcDistances(List<SolverPoint> spList) {
        double sum = 0; 
    	
        // for ‚Ì‚È‚©‚É‚¢‚ê‚é
        for (int i=0; i<spList.size()-1; i++) {
            sum += calcDistance(spList.get(i).x, spList.get(i).y, spList.get(i+1).x, spList.get(i+1).y);
        }
		return sum;
	}
	
	
    public static void main(String[] args) {
    	
    	// make list of points by input
    	List<SolverPoint> spList = makeSpList(args[0]);
    	
    	// ---somehow change the order---
    	// 1. find the closest
//    	List<SolverPoint> spList = new ArrayList<SolverPoint>();
//        // define start point
//        SolverPoint startPoint = new SolverPoint();
//        startPoint.index = 0;
//        startPoint.x = 0;
//        startPoint.y = 0;
//        
//        // add startPoint to beginning of List
//        spList.add(startPoint);
//    	double minDistance = calcDistance(oldSpList.get(0).x, oldSpList.get(0).y, oldSpList.get(1).x, oldSpList.get(1).y);
//    	int minIndex;
//    	double distance;
//    	
//    	for (int i=0; i<oldSpList.size()-2; i++) {
//    		minIndex = 0;
//    		distance = 0;
//    		for (int j=1;j<oldSpList.size()-2; j++) {
//    			distance = calcDistance(oldSpList.get(i).x, oldSpList.get(i).y, oldSpList.get(i+1).x, oldSpList.get(i+1).y);
//    			if (distance < minDistance) {
//    				minIndex = j;
//    			}
//    		}
//    		SolverPoint p = new SolverPoint();
//    		p.index = minIndex;
//    		p.x = oldSpList.get(minIndex).x;
//    		p.y = oldSpList.get(minIndex).y;
//    		spList.add(p);
//    	}
//    	
//    	spList.add(startPoint);
    	
    	
    	// Calcurate all of distances
    	//double totalDistances = calcDistances(spList);
    	
    	System.out.println("Index");
        for (int i=1; i<spList.size()-1; i++) { // exclude start point & final destination
        	System.out.println(spList.get(i-1).index);
        }
        
        
        
        
    }
}