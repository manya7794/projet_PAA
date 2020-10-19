public class Ville {
	//Attributs
	private String nom;
	private boolean ecole;
	
	//Constructeurs
	public Ville(String nom, boolean ecole) {
		this.nom=nom;
		this.ecole=ecole;
	}
	public Ville(String ecole2) {
		this.nom=ecole2;
		ecole=false;
	}
	
	//Setters
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public void setEcole (boolean ecole) {
		this.ecole=ecole;
	}
	
	//Getters
	
	public String getNom () {
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