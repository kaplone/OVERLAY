package fr.kaplone.overlay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiversUtils {

	public static void main(String[] args) {
		File dir = new File("/home/david/TESTS_racket/move/frames");
		
		listeTriee(dir);
	}
	
	public static List<String> listeTriee (File d){
		
		String[] listDir = d.list();
		List<String> ld = Arrays.asList(listDir);
		Collections.sort(ld);
		return ld;

	}
	
	
}
