package proizvod;

import ispis.Ispisivo;

public abstract class Proizvod implements Ispisivo {
	private String naziv;
	private double cena;
	
	
	public Proizvod(String naziv, double cena) {
		this.naziv = naziv;
		this.cena = cena;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public double ukupnaCena() {
		return this.cena;
	}
	
	public void ispis(char end) {
		System.out.print("Naziv: " + this.naziv + " Cena: " + this.ukupnaCena() + end);
	}
	
	public void ispis() {
		this.ispis('\n');
	}
	
}
