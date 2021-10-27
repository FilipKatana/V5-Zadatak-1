package procesi;

import kupac.Kupac;
import magacin.Artikal;
import magacin.Magacin;
import magacin.Porudzbenica;
import magacin.StanjeNarudzbine;

public class Kupovina implements Runnable {

	private Kupac kupac;
	private Magacin magacin;
	private Porudzbenica porudzbenica;
	
	public Kupovina(Kupac kupac, Magacin magacin, Artikal ...artikli) {
		this.porudzbenica = new Porudzbenica(kupac, artikli);
		this.kupac = kupac;
		this.magacin = magacin;
	}
	
	
	
	public Kupac getKupac() {
		return kupac;
	}



	public Magacin getMagacin() {
		return magacin;
	}



	public Porudzbenica getPorudzbenica() {
		return porudzbenica;
	}



	@Override
	public void run() {
		this.magacin.dostavi(porudzbenica);
		
		if (porudzbenica.getStanje() == StanjeNarudzbine.REALIZOVANO) {
			for (int i = 0; i < porudzbenica.duzina(); ++i) {
				this.kupac.addKupljenProizvod(porudzbenica.getArtikal(i));
			}
		}
		
	}

}
