import java.util.ArrayList;

public class Ville {
	//Attributs
	private String nom;
	private boolean ecole;
	
	//Constructeurs
	public Ville(String nom) {
		this.nom=nom;
		ecole=true;
	}
	
	public Ville(String nom, boolean ecole) {
		this.nom=nom;
		this.ecole=ecole;
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
	
	/*
	 * Cette methode permet d'effectuer toutes les taches inherantes aux ecoles d'une ville
	 * (ajout, retrait) tout en verifiant l'existence de la ville
	 * @param ajout - boolean signifiant l'ajout ou le retrait d'une ecole
	 */
	public void gestionEcole(boolean ville, boolean ajout) {

		if(!ville) {
			//Verifie si la ville saisie est une ville dans qui existe dans le tableau de ville
			System.err.println("La ville n'existe pas");
		}
		else if (ajout && getEcole()) {
			//Si une ecole est deja presente alors qu'on veut en ajouter une
			System.err.println("Cette ville a deja une ecole");
		}
		else if (!ajout && !getEcole()) {
			//Si aucune ecole n'est presente alors qu'on veut en retirer une
			System.err.println("Cette ville n'a aucune ecole a retirer");
		}
		else if (ajout&& !getEcole()) {
			//Methode qui ajoute une ecole dans une ville, qui change la variable boolean ecole en true
			//Nous ne pourrons plus creer d'ecole dans cette ville 
			ajoutEcole();
			System.out.println("Une ecole a ete ajoute dans la ville "+getNom());
		}
		else {
			//Methode qui retire l'ecole d'une ville, qui change la variable boolean ecole en false
			//Nous pouvons a nouveau creer une ecole dans cette ville
			retireEcole();
			System.out.println("L'ecole a ete retire de la ville de "+getNom());
		}
	}
	
	/*
	 * @param tab_ville - Ville{] contenant toutes les villes creees au debut du programme
	 * @param tab_voisin - ArrayList <ArrayList<Character>> contenant la liste des voisins de chaque ville
	 * @param ville - char contenant le nom de la ville actuelle dont on recherche les voisins
	 * @return ecole - boolean signifiant l'existence ou non d'une ecole dans les villes voisines a la ville passee en entree
	 */
	public boolean rechercheEcole (ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin, String ville) {
		boolean ecole = false;
		boolean sortie = false;
		for (int i=0; i<tab_ville.size() && !sortie;i++) {
			//Accès à la position de la ville
			if (tab_ville.get(i).getNom()==ville) {
				sortie =true;
				//Balayage de la liste des voisins
				for(int j=0;j<tab_voisin.get(i).size();j++) {
					String villeTmp=tab_voisin.get(i).get(j); //Variable tampon contenant le nom du voisin actuel
					//Balayage du tableau de villes
					for(int k=0; k<tab_ville.size();k++) {
						if(tab_ville.get(k).getNom()==(villeTmp)) {
							ecole=tab_ville.get(k).getEcole();//Place la valeur du boolean Ecole
						}
					}
				}
			}
		}
		return ecole;
	}

	public String toString() {
		if (ecole)
			return "Nom de la ville : "+nom+" Ecole : presente";
		else
			return "Nom de la ville : "+nom+" Ecole : non presente";
	}
	
	public String toStringNom() {
		return nom;
	}
}
