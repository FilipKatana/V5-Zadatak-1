package kupac;

import java.time.LocalDateTime;

import magacin.Artikal;
import proizvod.Proizvod;

public class ProizvodZaKupovinu extends Artikal {
	private LocalDateTime vreme;

	
	public ProizvodZaKupovinu(Proizvod proizvod, int kolicina) {
		super(proizvod, kolicina);
		this.vreme = LocalDateTime.now();
	}
	
	public ProizvodZaKupovinu(Artikal a) {
		this(a.getProizvod(), a.getKolicina());
	}

	public LocalDateTime getVreme() {
		return vreme;
	}

	
	
	
}
