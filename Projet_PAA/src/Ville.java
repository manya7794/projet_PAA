import java.util.ArrayList;

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

	public boolean rechercheEcole (Ville[] tab_ville, ArrayList <ArrayList<Character>> tab_voisin, char ville) {
		boolean ecole = false;
		for(int i = 0 ; i < tab_voisin.size() && !ecole ; i ++) {
			//Accès à la ligne correspondant à la ville
			if(tab_voisin.get(i).equals(ville)) {
				//Accès aux villes voisines
				for(int j = 0 ; j < tab_voisin.get(i).size() && !ecole; j++) {
					//Récupération du nom du voisin
					char voisin =tab_voisin.get(i).get(j);
					for(int k =0; k<tab_ville.length; k++) {
						//Affectation temporaire de la ville
						if(tab_ville[k].getNom()==voisin) {
							ecole=tab_ville[k].getEcole();
						}
					}
				}	
			}
		}
		return ecole;
	}
}