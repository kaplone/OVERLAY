package fr.kaplone.overlay;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	static final int posXDevice = 0;
	static final int posYDevice = 0;
	static final int posXContenu = 789;
	static final int posYContenu  = 452;
	
	public static BufferedImage scale(BufferedImage bi, double scaleValue) {
        AffineTransform tx = new AffineTransform();
        tx.scale(scaleValue, scaleValue);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        BufferedImage biNew = new BufferedImage( (int) (bi.getWidth() * scaleValue),
                (int) (bi.getHeight() * scaleValue),
                bi.getType());
        return op.filter(bi, biNew);
	}
	
	public static BufferedImage composition4Niveaux(BufferedImage fond,
			                                        BufferedImage device,
			                                        BufferedImage contenu,
			                                        BufferedImage main, double posXMain, double posYMain,
			                                        BufferedImage finale){
		Graphics2D g = finale.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(fond, 0, 0, null);
		g.drawImage(contenu, posXContenu, posYContenu, null);
		g.drawImage(device, posXDevice, posYDevice, null);
		g.drawImage(main, (int)posXMain, (int)posYMain, null);
		g.dispose();
		
		return finale;
	}
	
	public static void ecrireImage (BufferedImage image, File path, String nom) throws IOException{
		ImageIO.write(image, "PNG", new File(path, nom));
	}

}
