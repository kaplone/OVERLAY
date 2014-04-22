package fr.kaplone.overlay;

import java.lang.Math;

public class Point {
	
	private double coordX;
	private double coordY;
	private Point relatifA;
	private int imageNumero;
	
	final static Point origine = new Point(0, 0);

	public Point(double coordX, double coordY, Point relatif, int num) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.relatifA = relatif;
		this.imageNumero = num;
	}
	public Point(double coordX, double coordY) {
		this(coordX, coordY, null, -1);
	}
	
	public double grandDeltaX(Point p){
		return p.getCoordX() - this.coordX;
	}
	public double grandDeltaY(Point p){
		return p.getCoordY() - this.coordY;
	}
	
	public double distanceAuPoint (Point p){
		double carreDeltaX = ( this.coordX - p.getCoordX())*  (this.coordX - p.getCoordX());
		double carreDeltaY = (this.coordY - p.getCoordY()) *  (this.coordY - p.getCoordY());
		return Math.sqrt(carreDeltaX + carreDeltaY);
	}
	
	public Point doigtRelatifToOrigine(int x, int y){
		return new Point(x - this.getCoordX( ), y - this.getCoordY(), origine, this.imageNumero);
	}
	
	public Point doigtRelatifToDevice(int x, int y){
		return doigtRelatifToOrigine(x + 788, y + 451);
	}

	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public Point getRelatifA() {
		return relatifA;
	}

	public void setRelatifA(Point relatif) {
		this.relatifA = relatif;
	}
	
	public int getImageNumero() {
		return imageNumero;
	}
	
	public void setImageNumero(int imageNumero) {
		this.imageNumero = imageNumero;
	}

}
