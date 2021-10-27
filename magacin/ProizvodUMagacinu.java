package magacin;

import ispis.Ispisivo;
import proizvod.Proizvod;

public class ProizvodUMagacinu extends Artikal implements Ispisivo {
	
	
	
	public ProizvodUMagacinu(Proizvod proizvod, int kolicina) {
		super(proizvod, kolicina);
	}
	
}
