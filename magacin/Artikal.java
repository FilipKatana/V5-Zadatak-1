package magacin;

import ispis.Ispisivo;
import proizvod.Proizvod;

public class Artikal implements Ispisivo {
	private Proizvod proizvod;
	private int kolicina = 0;
	
	public Artikal(Proizvod proizvod, int kolicina) {
		this.proizvod = proizvod;
		this.setKolicina(kolicina);
	}
	
	
	public int getKolicina() {
		return kolicina;
	}
	
	public boolean setKolicina(int kolicina) {
		if (kolicina >= 0) {
			this.kolicina = kolicina;
			return true;
		}
		return false;
	}
	
	public Proizvod getProizvod() {
		return proizvod;
	}
	
	public void ispis() {
		System.out.println(this.getProizvod().getNaziv() + " x" + this.getKolicina());
	}
}
