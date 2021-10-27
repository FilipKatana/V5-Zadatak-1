package magacin;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import ispis.Ispisivo;

public class Magacin implements Ispisivo {
	private ArrayList<ProizvodUMagacinu> inventar = new ArrayList<ProizvodUMagacinu>();
	private Porudzbenica uObradi;
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition slobodno = lock.newCondition();
	
	public Magacin(ProizvodUMagacinu ...proizvodi) {
		for (ProizvodUMagacinu pro : proizvodi) {
			inventar.add(pro);
		}
	}

	@Override
	public void ispis() {
		System.out.println("Proizvodi u magacinu: ");
		for (ProizvodUMagacinu pro : this.inventar) {
			pro.ispis();
		}
		
	}
	
	
	public void dostavi(Porudzbenica porudzbenica) {
		try {
			lock.lockInterruptibly();

			while (this.uObradi != null) {
				slobodno.await();
			}

			
			this.uObradi = porudzbenica;
			
			ArrayList<ProizvodUMagacinu> lista = new ArrayList<ProizvodUMagacinu>();
			

			for (int i = 0; i < porudzbenica.duzina(); ++i) {
				Artikal pro0 = porudzbenica.getArtikal(i);
				for (ProizvodUMagacinu pro : this.inventar) {
					if (pro0.getProizvod().getNaziv() == pro.getProizvod().getNaziv()) {
						if (pro0.getKolicina() > pro.getKolicina() || pro.getKolicina() <= 0) {
							porudzbenica.setStanje(StanjeNarudzbine.NEUSPELO);
							System.out.println(porudzbenica.getStanje());
							this.uObradi = null;
							slobodno.signal();
							lock.unlock();
							return;
						} else {
							lista.add(pro);
						}
					}
				}
			}
			
			
			
			
			for (int i = 0; i < porudzbenica.duzina(); ++i) {
				lista.get(i).setKolicina(lista.get(i).getKolicina() - porudzbenica.getArtikal(i).getKolicina());
			}
			
			porudzbenica.setStanje(StanjeNarudzbine.REALIZOVANO);
			
			this.uObradi = null;
			slobodno.signal();
			lock.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
