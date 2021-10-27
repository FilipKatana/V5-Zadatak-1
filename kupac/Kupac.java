package kupac;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import ispis.Ispisivo;
import magacin.Artikal;

public class Kupac implements Ispisivo {
	private String naziv;
	private VrstaKupca vrsta;
	private ArrayList<ProizvodZaKupovinu> kupljeniProizvodi = new ArrayList<ProizvodZaKupovinu>();
	
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition slobodno = lock.newCondition();
	private boolean upisivanje = false;
	
	public Kupac(String naziv, VrstaKupca vrsta) {
		this.naziv = naziv;
		this.vrsta = vrsta;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public VrstaKupca getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaKupca vrsta) {
		this.vrsta = vrsta;
	}

	@Override
	public void ispis() {
		System.out.print(this.naziv + " - ");
		
		switch(this.vrsta) {
		case FIZICKO_LICE:
			System.out.print("fizičko lice");
			break;
		case PRAVNO_LICE:
			System.out.print("pravno lice");
		}
		System.out.println(" sa artiklima: ");
		
		for (ProizvodZaKupovinu pro : this.kupljeniProizvodi) {
			pro.ispis();
		}
	}
	
	
	public void addKupljenProizvod(Artikal a) {
		try {
			lock.lockInterruptibly();
			while (upisivanje) {
				slobodno.await();
			}
			upisivanje = true;
			this.kupljeniProizvodi.add(new ProizvodZaKupovinu(a));
			upisivanje = false;
			slobodno.signal();
			lock.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
}
