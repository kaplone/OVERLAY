package fr.kaplone.overlay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws IOException {
		
		File path = new File("/home/david/TESTS_racket/move/images_source/");
		File pathOut = new File("/home/david/TESTS_racket/move/images_out/");
		
		BufferedImage device = ImageIO.read(new File(path, "Main_F_iPhones_1.png"));
		BufferedImage main = ImageIO.read(new File(path, "Main_F_Clic_1_.png"));
		BufferedImage fond = ImageIO.read(new File(path, "vert.png"));
		main = ImageUtils.scale(main, 0.4);
		
		int w = device.getWidth();
		int h = device.getHeight();
		BufferedImage composition = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		ArrayList<Integer> tousLesTemps = ParseFileUtils.fichierVersTemps("/home/david/TESTS_racket/move/ref.txt");
		System.out.println(tousLesTemps.get(8));
		
		ArrayList<Integer> tousLesPosX = ParseFileUtils.fichierVersPosX("/home/david/TESTS_racket/move/ref.txt");
		System.out.println(tousLesPosX.get(8));
		
		List<String> frames = DiversUtils.listeTriee(new File("/home/david/TESTS_racket/move/frames"));
		
		for (int i = 1; i < frames.size(); i++){
		    ImageUtils.ecrireImage(ImageUtils.composition4Niveaux(fond, device, contenu(i), main, 500, 400, composition), pathOut, String.format("test_%05d.png", i));
		}
		
	}
	
	public static BufferedImage contenu(int i) throws IOException {
		File pathFrames = new File("/home/david/TESTS_racket/move/frames_WakeApp/");
		BufferedImage contenu = ImageIO.read(new File(pathFrames, String.format("image2_%05d.png", i)));
		contenu = ImageUtils.scale(contenu, 0.7);
		
		return contenu;
	}

}
