
public class Ville {
	//Attributs
	private char nom;
	private boolean ecole;
	
	//Constructeurs
	public Ville(char nom) {
		this.nom=nom;
		ecole=false;
	}
	
	public Ville(char nom, boolean ecole) {
		this.nom=nom;
		this.ecole=ecole;
	}

	
	//Setters
	public void setNom(char nom) {
		this.nom=nom;
	}
	
	public void setEcole (boolean ecole) {
		this.ecole=ecole;
	}
	
	//Getters
	
	public char getNom () {
		return nom;
	}
	
	public boolean getEcole() {
		return ecole;
	}
	
	//Methodes
	
	public void ajoutEcole() {
		setEcole(true);
	}
	
	public void retireEcole() {
		setEcole(false);
	}
}
