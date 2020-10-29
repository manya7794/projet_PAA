import java.util.Scanner;

public class Ville {
	//Attributs
	private char nom;
	private boolean ecole;
	
	//Constructeurs
	public Ville(char nom) {
		this.nom=nom;
		ecole=true;
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
	
	public void gestionEcole(boolean ville, boolean ajout) {

		if(!ville) {
			//Verifie si la ville saisie est une ville dans qui existe dans le tableau de ville
			System.err.println("La ville n'existe pas");
		}
		else if (ajout && getEcole()) {
			//Verifie que l'ecole existe, et verifie s'il y a deja une ecole ou non
			System.err.println("Cette ville a deja une ecole");
		}
		else if (ajout&& !getEcole()) {
			//Methode qui ajoute une ecole dans une ville, qui change la variable boolean ecole en true
			//Nous ne pourrons plus creer d'ecole dans cette ville 
			ajoutEcole();
			System.out.println("Une ecole a ete ajoute dans la ville de "+getNom());
		}
		else {
			//Methode qui retire l'ecole d'une ville, qui change la variable boolean ecole en false
			//Nous pouvons a nouveau creer une ecole dans cette ville
			retireEcole();
			System.out.println("L'ecole a ete retire de la ville de "+getNom());
		}
	}
}