package fr.kaplone.overlay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Point p1 = new Point(300, 200);
		Point p2 = new Point(600, 400, null, 400);
		Point p3 = new Point(500, 900);
		
		
		Deltas D0 = Mouvement.acceleration(p1, p2, 10);
		Deltas D1 = Mouvement.acceleration(p2, p3, 12);
		ArrayList<Point> PP = Mouvement.deplacement(D0, p1, p2, p3, D1);
		
		
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
		
		ArrayList<Integer> tousLesTemps = ParseFileUtils.fichierVersTemps("/home/david/TESTS_racket/move/ref.txt");
		System.out.println(tousLesTemps.get(8));
		
		ArrayList<Integer> tousLesPosX = ParseFileUtils.fichierVersPosX("/home/david/TESTS_racket/move/ref.txt");
		System.out.println(tousLesPosX.get(8));
		
		List<String> frames = DiversUtils.listeTriee(new File("/home/david/TESTS_racket/move/frames_WakeApp"));
		
		Point temp;
		
		for (Point p : PP){
			ImageUtils.ecrireImage(ImageUtils.composition4Niveaux(fond, device, contenu(p.getImageNumero()), main, p.getCoordX(), p.getCoordY(), composition), pathOut, String.format("test_mvt_%05d.png", p.getImageNumero()));
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
