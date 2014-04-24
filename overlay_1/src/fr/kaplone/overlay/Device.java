package fr.kaplone.overlay;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Device {
	
	String cheminImage;
	File imageFile;
	double valeurEchelle;
	
	double[] tailleImage = new double[2];
	double[] tailleDevice = new double[2]; 
	double[] tailleEcran = new double[2];
	
	public Device(String cheminImage, double valeurEchelle, double[] tailleDevice, double[] tailleEcran) {
		this.cheminImage = cheminImage;
		this.imageFile = new File(cheminImage);
		BufferedImage image = ImageIO.read(this.imageFile);
		this.tailleImage = {i
		this.valeurEchelle = valeurEchelle;
		this.tailleImage = tailleImage;
		this.tailleDevice = tailleDevice;
		this.tailleEcran = tailleEcran;
	}
	
	public double getLargeurImage(){
		return this.tailleImage[0];
	}
	public double getHauteurImage(){
		return this.tailleImage[1];
	}
	
	public String getCheminImage() {
		return cheminImage;
	}

	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}
	
	public double[] getTailleImage() {
		return tailleImage;
	}

	public void setTailleImage(double[] tailleImage) {
		this.tailleImage = tailleImage;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public double[] getTailleDevice() {
		return tailleDevice;
	}

	public void setTailleDevice(double[] tailleDevice) {
		this.tailleDevice = tailleDevice;
	}

	public double getLargeurDevice(){
		return this.tailleDevice[0];
	}
	public double getHauteurDevice(){
		return this.tailleDevice[1];
	}
	
	public double[] getTailleEcran() {
		return tailleEcran;
	}

	public void setTailleEcran(double[] tailleEcran) {
		this.tailleEcran = tailleEcran;
	}
	
	public double getLargeurEcran(){
		return this.tailleEcran[0];
	}
	public double getHauteurEcran (){
		return this.tailleEcran[1];
	}
	
	public Device miseALEchelle(double standard){
		double[] imageEchelle = {this.getLargeurImage() * standard, this.getHauteurImage() * standard};
		double[] deviceEchelle = {this.getLargeurDevice() * standard, this.getHauteurDevice() * standard};
		double[] ecranEchelle = {this.getLargeurEcran() * standard, this.getHauteurEcran() * standard};
		return new Device(this.getCheminImage(), 0, deviceEchelle, ecranEchelle, imageEchelle);
	}
	

}
