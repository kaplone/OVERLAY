package fr.kaplone.overlay;

import java.util.ArrayList;

public class Deltas {
	ArrayList<Double> deltas = new ArrayList<Double>();
	double graduations;
	public Deltas(ArrayList<Double> deltas, double d) {
		super();
		this.deltas = deltas;
		this.graduations = d;
	}
	public ArrayList<Double> getDeltas() {
		return deltas;
	}
	public void setDeltas(ArrayList<Double> deltas) {
		this.deltas = deltas;
	}
	public double getGraduations() {
		return graduations;
	}
}
