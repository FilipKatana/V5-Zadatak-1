package magacin;

import java.util.ArrayList;

import kupac.Kupac;

public class Porudzbenica {
	private Kupac kupac;
	private ArrayList<Artikal> artikli = new ArrayList<Artikal>();
	private StanjeNarudzbine stanje = StanjeNarudzbine.U_PRIPREMI;
	
	public Porudzbenica(Kupac kupac, Artikal ...artikli) {
		this.kupac = kupac;
		
		for (Artikal art : artikli) {
			this.artikli.add(art);
		}
	}

	public Kupac getKupac() {
		return kupac;
	}
	
	public int duzina() {
		return this.artikli.size();
	}
	
	public Artikal getArtikal(int index) {
		return this.artikli.get(index);
	}

	public StanjeNarudzbine getStanje() {
		return stanje;
	}
	
	
	public void setStanje(StanjeNarudzbine stanje) {
		if (this.stanje == StanjeNarudzbine.U_PRIPREMI) {
			this.stanje = stanje;
		}
	}
	
}
