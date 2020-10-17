
public class Ville {
	//Attributs
	private String nom;
	private int ecole;
	
	//Constructeurs
	public Ville(String nom, int ecole) {
		this.nom=nom;
		this.ecole=ecole;
	}
	public Ville(String nom) {
		this.nom=nom;
		ecole=0;
	}
	
	//Setters
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public void setEcole (int ecole) {
		this.ecole=ecole;
	}
	
	//Getters
	
	public String getNom () {
		return nom;
	}
	
	public int getEcole() {
		return ecole;
	}
	
	//Methodes
	
	public void ajoutEcole() {
		setEcole(1);
	}
	
	public void retireEcole() {
		setEcole(0);
	}
}
