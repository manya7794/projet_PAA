import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String [] Args) {
		
		ArrayList<Ville> tab_ville = new ArrayList<Ville>();
		
		Scanner scan = new Scanner(System.in);
		String nomFichier=Args[0];
		/*
		 * Premiere etape : Lire le nombre de ville, dans le fichier
		 */
		Utilitaire.nomVille(tab_ville, nomFichier);
		//Creation de la liste des voisins avec le modele de liste d'adjacence
		ArrayList<ArrayList<String>> tab_voisin = Utilitaire.createListeVoisin(tab_ville.size());
		
		/*
		 * Deuxieme etape : Affichage du menu pour les routes et pouvoir faire le choix par l'utilisateur
		 * 					L'utilisateur peut ajouter / relier deux ville avec un route 
		 */
		Utilitaire.lireRoute(nomFichier, tab_ville, tab_voisin);
		//Affichage des voisins finaux
		Utilitaire.afficherVoisin(tab_ville, tab_voisin);
		
		Utilitaire.menuPrincipal(tab_ville, tab_voisin);
		/*
		 * Troisieme etape : Affichage du menu pour les ecoles en attente d'un choix par l'utilisateur
		 * 					 L'utilisateur peut ajouter une ecole dans la ville si cette ville n'a pas d'ecole
		 * 					 L'utilisateur peut retirer une ecole d'une ville s'il ne veut plus de cette ecole
		 */
		Utilitaire.menuEcole(tab_ville,tab_voisin);
		
		scan.close();
	}
}