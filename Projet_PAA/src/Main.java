import java.util.ArrayList;


public class Main {
	public static void main(String [] Args) {
		
		ArrayList<Ville> tab_ville = new ArrayList<Ville>();
		
		String nomFichier=Args[0];
		/*
		 * Premiere etape : Lire le nombre de ville, dans le fichier
		 */
		Utilitaire.nomVille(tab_ville, nomFichier);
		
		//Creation de la liste des voisins avec le modele de liste d'adjacence
		ArrayList<ArrayList<String>> tab_voisin = Utilitaire.createListeVoisin(tab_ville.size());
		
		/*
		 * Deuxieme etape : Liaison des villes avec des routes
		 * 					 
		 */
		Utilitaire.lireRoute(nomFichier, tab_ville, tab_voisin);
		
		//Affichage des voisins finaux
		Utilitaire.afficherVoisin(tab_ville, tab_voisin);
		
		/*
		 * Troisieme etape: Ajout des ecoles
		 */
		Utilitaire.lireEcole(tab_ville, nomFichier);
		/*
		 * Quatrieme etape : Affichage du menu pour les ecoles en attente d'un choix par l'utilisateur
		 * 					 L'utilisateur peut ajouter une ecole dans la ville si cette ville n'a pas d'ecole
		 * 					 L'utilisateur peut retirer une ecole d'une ville s'il ne veut plus de cette ecole
		 */
		Utilitaire.menuPrincipal(tab_ville, tab_voisin);
	}
}