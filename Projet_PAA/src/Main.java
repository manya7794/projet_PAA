import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String [] Args) {
		int nb_ville;
		Scanner scan = new Scanner(System.in);
		/*
		 * Premiere etape : Saisissez le nombre de ville que nous souhaitons
		 */
		do {
			System.out.println("Choissisez le nombre de ville (<=26)");
			nb_ville = scan.nextInt();
			if(nb_ville>26) {
				System.out.println("Le nombre de ville est superieur a 26");
			}
			else if(nb_ville<0)
				System.err.println("Saisissez un nombre positif");
		}while(nb_ville>26);
		System.out.println("Nombre de villes : "+nb_ville);
		
		//Definir un tableau de ville de taille "nb_ville"
		Ville[] tab_ville = new Ville[nb_ville];
		//Definir les noms des villes
		Utilitaire.nomVille(tab_ville);

		/*
		 * Deuxieme etape : Afficher le menu pour les routes et pouvoir faire le choix par l'utilisateur
		 * 					L'utilisateur peut ajouter / relier deux ville avec un route 
		 */
		ArrayList <ArrayList <Character>>tab_voisin =Utilitaire.menuRoute(tab_ville);
		
		/*
		 * Troisieme etape : Affcihe le menu pour les ecoles et pouvoir faire le choix par l'utilisateur
		 * 					 L'utilisateur peut ajouter une ecole dans la ville si cette ville n'a pas d'ecole
		 * 					 L'utilisateur peut retirer une ecole d'une ville s'il ne veut plus de cette ecole
		 */
		Utilitaire.menuEcole(tab_ville,tab_voisin);
		
		scan.close();
	}
}