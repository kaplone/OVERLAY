package fr.kaplone.overlay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		Point p1 = new Point(500, 900);              // c'est le point de départ du mouvement, (x = 500, y=900) pas de temporalité.
		                                             // il s'agit des coordonnées sur l'écran du device
		
		Point p2 = new Point(1220, 2400, null, 500); // c'est le point du touch (x= 1220, y=2400)
		                                             // null représente un argument que je ne prends pas en compte
		                                             // 500 représente la temporalité.
		                                             // l'image du contact sera celle numérotée 500
		                                             // (image2_00500.png dans le répertoire frames_WakeApp)
		
		Point p3 = new Point(000, 0);                // c'est le point d'arrivée du mouvement, (x = 0, y=0) pas de temporalité.


		Deltas D0 = Displacement.acceleration(p1, p2, 20); // 20 est le "coefficient d'accélération"  
		Deltas D1 = Displacement.acceleration(p2, p3, 32); // 32 est le "coefficient d'accélération"  (plus rapide que 20)

		ArrayList<Point> PP = Displacement.deplacement(D0, p1, p2, p3, D1);
		
		
		File path = new File("/home/david/TESTS_racket/move/images_source/");
		File pathOut = new File("/home/david/TESTS_racket/move/images_out/");
		
		BufferedImage device = ImageIO.read(new File(path, "Main_F_iPhones_1.png"));
		int w = device.getWidth();
		int h = device.getHeight();
		Point pointDevice = new Point(w/2, h/2);
		
		BufferedImage main = ImageIO.read(new File(path, "Main_F_Clic_1_.png"));
		main = ImageUtils.scale(main, 0.4);
		Point pointDoigt = new Point(650, 120);
		
		BufferedImage fond = ImageIO.read(new File(path, "vert.png"));
		
		BufferedImage composition = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		ArrayList<Integer> tousLesTemps = ParseFileUtils.fileToFrameNumber("refs/ref.txt");
		System.out.println(tousLesTemps.get(8));
		
		ArrayList<Integer> tousLesPosX = ParseFileUtils.fileToPosX("refs/ref.txt");
		System.out.println(tousLesPosX.get(8));
		
		List<String> frames = OtherUtils.sortedList(new File("/home/david/TESTS_racket/move/frames_WakeApp"));
		
		Point temp;
		
		for (Point p : PP){
			ImageUtils.writeImage(ImageUtils.compose4Layers(fond, device, contenu(p.getImageNumber()), main, p.getCoordX(), p.getCoordY(), composition), pathOut, String.format("test_mvt_%05d.png", p.getImageNumber()));
		}
		
//		for (int i = 1; i < frames.size(); i++){
//		    temp = pointDoigt.doigtRelatifToDevice(0, 0);// remplacer  (0, 0) par les accesseurs à la liste des coordonnees
//		    ImageUtils.ecrireImage(ImageUtils.composition4Niveaux(fond, device, contenu(i), main, temp.getCoordX(), temp.getCoordY(), composition), pathOut, String.format("test_%05d.png", i));
//		}
		
	}
	
	public static BufferedImage contenu(int i) throws IOException {
		File pathFrames = new File("/home/david/TESTS_racket/move/frames_WakeApp/");
		BufferedImage contenu = ImageIO.read(new File(pathFrames, String.format("image2_%05d.png", i)));
		contenu = ImageUtils.scale(contenu, 0.7);
		
		return contenu;
	}

}
