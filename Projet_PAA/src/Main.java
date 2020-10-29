import java.util.Scanner;

public class Main {
	public static void main(String [] Args) {
		int nb_ville;
		Scanner scan = new Scanner(System.in);
		/*
		 * Premiere etape : Saissez le nombre de ville que nous souhaitons
		 */
		do {
			System.out.println("Choissisez le nombre de ville (<=26) ");
			nb_ville = scan.nextInt();
			System.out.println("Nombre de ville : "+nb_ville);
			if(nb_ville>26) {
				System.out.println("Le nombre de ville est superieur a 26");
			}
		}while(nb_ville>26);
		//Definir un tableau de ville de taille "nb_ville"
		Ville[] tab_ville = new Ville[nb_ville];
		//Definir les noms des villes
		Utilitaire.nomVille(tab_ville);

		/*
		 * Deuxieme etape : Afficher le menu de la route et pouvoir faire le choix par l'utilisateur 
		 */
		Utilitaire.menuRoute(tab_ville);
		
		/*
		 * Troiseme etape : Afficher le menu d'ecole et pouvoir faire le choix par l'utilisateur
		 */
		Utilitaire.menuEcole(tab_ville);
		
		scan.close();
	}
}