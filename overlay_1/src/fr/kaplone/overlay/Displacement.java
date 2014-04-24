package fr.kaplone.overlay;
import java.util.ArrayList;

public class Displacement {

	public static void main(String[] args) {
		
		Point p1 = new Point(0, 0);
		Point p2 = new Point(400, 500, null, 500);
		Point p3 = new Point(100, 800);
		
		
		Deltas D0 = acceleration(p1, p2, 4);
		Deltas D1 = acceleration(p2, p3, 2);
		ArrayList<Point> PP = deplacement(D0, p1, p2, p3, D1);
		for (Point p : PP){
			System.out.println(p.getImageNumber() + " " + p.getCoordX() + " " + p.getCoordY());
		}

	}
	
	public static Deltas acceleration (Point starting, Point ending, double coef){
		double distance = starting.distanceToPoint(ending);
		double max = distance /2;
		double done = 0;
		int mult = 1;
		ArrayList<Double> deltas = new ArrayList<Double>(); 
		while (done < max){
			deltas.add(coef * mult);
			done += coef * mult;
			mult ++;
		}
		return new Deltas(deltas, done - coef);
	}
	
	public static ArrayList<Point> deplacement (Deltas deltasBefore, Point starting, Point touch, Point ending, Deltas deltasAfter){
		ArrayList<Point> intervals = new ArrayList<Point>();
		
		int numberOfIntervalsBefore = deltasBefore.getListOfDeltas().size() * 2;
		int numberOfIntervalsAfter = deltasAfter.getListOfDeltas().size() * 2;
		int numberOfStartingFrame = touch.getImageNumber() - numberOfIntervalsBefore;
		int numberRefFrame = touch.getImageNumber();
		int numberOfEndingFrame = touch.getImageNumber() + numberOfIntervalsAfter;
		int frameNumber = numberOfStartingFrame;
		double xTemporary = starting.getCoordX();
		double yTemporary = starting.getCoordY();
		
		double stepXBefore = starting.fullDeltaX(touch) / deltasBefore.getStepValue() / 2;
		double stepYBefore = starting.fullDeltaY(touch) / deltasBefore.getStepValue() / 2;
		
		double stepXAfter = touch.fullDeltaX(ending) / deltasAfter.getStepValue() / 2;
		double stepYAfter = touch.fullDeltaY(ending) / deltasAfter.getStepValue() / 2;
		
		double progres;
		
		for (int i =0; i< deltasBefore.getListOfDeltas().size(); i++){
			
			intervals.add(new Point(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasBefore.getListOfDeltas().get(i);
			xTemporary += stepXBefore * progres;
			yTemporary += stepYBefore * progres;
		}
		
        for (int i = deltasBefore.getListOfDeltas().size() -1; i >= 0 ; i--){
			
			intervals.add(new Point(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasBefore.getListOfDeltas().get(i);
			xTemporary += stepXBefore * progres;
			yTemporary += stepYBefore * progres;
		}
        
      intervals.add(touch);
      xTemporary = touch.getCoordX();
	  yTemporary = touch.getCoordY();
	  frameNumber = touch.getImageNumber() + 1;
      
      for (int i =0; i< deltasAfter.getListOfDeltas().size(); i++){
			
			intervals.add(new Point(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasAfter.getListOfDeltas().get(i);
			xTemporary += stepXAfter * progres;
			yTemporary += stepYAfter * progres;
		}
		
      for (int i = deltasAfter.getListOfDeltas().size() -1; i >= 0 ; i--){
			
			intervals.add(new Point(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasAfter.getListOfDeltas().get(i);
			xTemporary += stepXAfter * progres;
			yTemporary += stepYAfter * progres;
		}
      intervals.add(new Point(ending.getCoordX(), ending.getCoordY(), null, frameNumber )); 
	  return intervals;
	}

}
