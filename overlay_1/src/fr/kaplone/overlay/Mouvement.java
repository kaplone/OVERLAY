package fr.kaplone.overlay;
import java.util.ArrayList;

public class Mouvement {

	public static void main(String[] args) {
		
		Point p1 = new Point(0, 0);
		Point p2 = new Point(400, 500, null, 500);
		Point p3 = new Point(100, 800);
		
		
		Deltas D0 = acceleration(p1, p2, 4);
		Deltas D1 = acceleration(p2, p3, 2);
		ArrayList<Point> PP = deplacement(D0, p1, p2, p3, D1);
		for (Point p : PP){
			System.out.println(p.getImageNumero() + " " + p.getCoordX() + " " + p.getCoordY());
		}

	}
	
	public static Deltas acceleration (Point depart, Point arrivee, double coef){
		double distance = depart.distanceAuPoint(arrivee);
		double max = distance /2;
		double fait = 0;
		int mult = 1;
		ArrayList<Double> deltas = new ArrayList<Double>(); 
		while (fait < max){
			deltas.add(coef * mult);
			fait += coef * mult;
			mult ++;
		}
		return new Deltas(deltas, fait - coef);
	}
	
	public static ArrayList<Point> deplacement (Deltas deltasAvant, Point depart, Point touche, Point arrivee, Deltas deltasApres){
		ArrayList<Point> intervales = new ArrayList<Point>();
		
		int nombreIntervalesAvant = deltasAvant.getDeltas().size() * 2;
		int nombreIntervalesApres = deltasApres.getDeltas().size() * 2;
		int numeroImageDepart = touche.getImageNumero() - nombreIntervalesAvant;
		int numeroImageRef = touche.getImageNumero();
		int numeroImageArrivee = touche.getImageNumero() + nombreIntervalesApres;
		int numeroImage = numeroImageDepart;
		double xTemporaire = depart.getCoordX();
		double yTemporaire = depart.getCoordY();
		
		double stepXavant = depart.grandDeltaX(touche) / deltasAvant.getGraduations() / 2;
		double stepYavant = depart.grandDeltaY(touche) / deltasAvant.getGraduations() / 2;
		
		double stepXapres = touche.grandDeltaX(arrivee) / deltasApres.getGraduations() / 2;
		double stepYapres = touche.grandDeltaY(arrivee) / deltasApres.getGraduations() / 2;
		
		double progres;
		
		for (int i =0; i< deltasAvant.getDeltas().size(); i++){
			
			intervales.add(new Point(xTemporaire, yTemporaire, depart.getRelatifA(),numeroImage));
			numeroImage++;
			progres = deltasAvant.getDeltas().get(i);
			xTemporaire += stepXavant * progres;
			yTemporaire += stepYavant * progres;
		}
		
        for (int i = deltasAvant.getDeltas().size() -1; i >= 0 ; i--){
			
			intervales.add(new Point(xTemporaire, yTemporaire, depart.getRelatifA(),numeroImage));
			numeroImage++;
			progres = deltasAvant.getDeltas().get(i);
			xTemporaire += stepXavant * progres;
			yTemporaire += stepYavant * progres;
		}
        
      intervales.add(touche);
      xTemporaire = touche.getCoordX();
	  yTemporaire = touche.getCoordY();
	  numeroImage = touche.getImageNumero() + 1;
      
      for (int i =0; i< deltasApres.getDeltas().size(); i++){
			
			intervales.add(new Point(xTemporaire, yTemporaire, depart.getRelatifA(),numeroImage));
			numeroImage++;
			progres = deltasApres.getDeltas().get(i);
			xTemporaire += stepXapres * progres;
			yTemporaire += stepYapres * progres;
		}
		
      for (int i = deltasApres.getDeltas().size() -1; i >= 0 ; i--){
			
			intervales.add(new Point(xTemporaire, yTemporaire, depart.getRelatifA(),numeroImage));
			numeroImage++;
			progres = deltasApres.getDeltas().get(i);
			xTemporaire += stepXapres * progres;
			yTemporaire += stepYapres * progres;
		}
      intervales.add(new Point(arrivee.getCoordX(), arrivee.getCoordY(), null, numeroImage )); 
	  return intervales;
	}

}
