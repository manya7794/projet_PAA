import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String [] Args) {
		
		Scanner scan = new Scanner(System.in);
		/*
		 * Premiere etape : Lire le nombre de ville, dans le fichier
		 */
		System.out.println("Args[0] : "+Args[0]);
		
		
		ArrayList<Ville> tab_ville = new ArrayList<Ville>();
		//Definir les noms des villes
		Utilitaire.nomVille(tab_ville, Args[0]);
		
		//Creation de la liste des voisins avec le modele de liste d'adjacence
		ArrayList<ArrayList<String>> tab_voisin = new ArrayList <ArrayList <String>>(tab_ville.size());
		//Ajoute le nombre de "ligne" correspondant au nb de ville
		for(int i = 0 ; i < tab_ville.size() ; i++) {
			ArrayList<String> addNbVille = new ArrayList <String>();
			tab_voisin.add(addNbVille);
		}
		
		/*
		 * Deuxieme etape : Affichage du menu pour les routes et pouvoir faire le choix par l'utilisateur
		 * 					L'utilisateur peut ajouter / relier deux ville avec un route 
		 */
		//ArrayList <ArrayList <String>>tab_voisin =Utilitaire.menuRoute(tab_ville);
		Utilitaire.createRoute(tab_ville, tab_voisin);
		
		/*
		 * Troisieme etape : Affichage du menu pour les ecoles en attente d'un choix par l'utilisateur
		 * 					 L'utilisateur peut ajouter une ecole dans la ville si cette ville n'a pas d'ecole
		 * 					 L'utilisateur peut retirer une ecole d'une ville s'il ne veut plus de cette ecole
		 */
		Utilitaire.menuEcole(tab_ville,tab_voisin);

		scan.close();
	}
}
