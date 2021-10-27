package proizvod;

import java.util.ArrayList;

public class SlozeniProizvod extends Proizvod {

	private ArrayList<Proizvod> sastavniDelovi = new ArrayList<Proizvod>();
	
	public SlozeniProizvod(String naziv, double cena, Proizvod ...delovi) {
		super(naziv, cena);
		for (Proizvod pro : delovi) {
			this.sastavniDelovi.add(pro);
		}
	}
	
	public double ukupnaCena() {
		double result = super.getCena();
		for (Proizvod pro : this.sastavniDelovi) {
			result += pro.getCena();
		}
		return result;
	}
	
	public void ispis() {
		super.ispis(' ');
		System.out.print("Delovi: ");
		for (Proizvod pro : this.sastavniDelovi) {
			System.out.print(pro.getNaziv() + "  ");
		}
	}

}
