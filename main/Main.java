package main;

import kupac.Kupac;
import kupac.VrstaKupca;
import magacin.Artikal;
import magacin.Magacin;
import magacin.ProizvodUMagacinu;
import procesi.Kupovina;
import proizvod.Proizvod;
import proizvod.SlozeniProizvod;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Magacin magacin = new Magacin(new ProizvodUMagacinu(new SlozeniProizvod("Šraf", 40.5), 300),
				new ProizvodUMagacinu(new SlozeniProizvod("Kutija", 30.4), 100));
		
		Kupovina kupovina = new Kupovina(new Kupac("Marko Milić", VrstaKupca.FIZICKO_LICE), magacin, new Artikal(new SlozeniProizvod("Šraf", 40.5), 40));
		Kupovina k2 = new Kupovina(new Kupac("Mirka Milić", VrstaKupca.FIZICKO_LICE), magacin, new Artikal(new SlozeniProizvod("Šraf", 40.5), 259));
		Thread t1 = new Thread(kupovina);
		Thread t2 = new Thread(k2);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		magacin.ispis();
		System.out.println("------------------------");
		k2.getKupac().ispis();
		System.out.println("------------------------");
		kupovina.getKupac().ispis();

	}

}
