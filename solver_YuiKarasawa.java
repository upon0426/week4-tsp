import java.util.*;
import java.io.*;

public class solver_YuiKarasawa {

	

	
	private static double calcDistance(double oldx, double oldy, double newx, double newy) {
		return Math.sqrt((newx-oldx)*(newx-oldx) + (newy-oldy)*(newy-oldy));
	}
	
    public static void main(String[] args) {
        double[][] nums;
        List<String> temp = new ArrayList<String>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;

            while((line = br.readLine()) != null) {
                temp.add(line);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        
        // delete the firstLine
        //temp.remove(0);
        
        nums = new double[2][temp.size()+1];
        nums[0][0] = 0;
        nums[1][0] = 0;
        
        for (int i=1;i<temp.size(); i++) {
            String[] s = temp.get(i).split(",");
            for (int j=0; j<2; j++) {
                nums[j][i] = Double.parseDouble(s[j]);
            }
        }
        
        nums[0][temp.size()] = 0;
        nums[1][temp.size()] = 0;

        ArrayList<Double> distances = new ArrayList<Double>();
        // for ‚Ì‚È‚©‚É‚¢‚ê‚é
        for (int i=0; i<temp.size(); i++) {
            distances.add(calcDistance(nums[0][i], nums[1][i], nums[0][i+1], nums[1][i+1]));
        }
        
        
        for (int i=0; i<distances.size();i++) {
        	System.out.println(distances.get(i));
        }
        
        
        
        
    }
}