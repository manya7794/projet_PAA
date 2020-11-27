import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String [] Args) {
		int nb_ville = 0;
		Scanner scan = new Scanner(System.in);
		/*
		 * Premiere etape : Lire le nombre de ville, dans le fichier
		 */
		System.out.println("Args[0] : "+Args[0]);
		try {
			FileReader fRead = new FileReader(Args[0]);
			BufferedReader bRead = new BufferedReader(fRead);
			String ligne = null;
			
			//Recupere le nombre de ville, donc lit chaque ligne si la lettre commence par 'v' alors incremente nb_ville
			while((ligne = bRead.readLine()) != null) {
				if(ligne.charAt(0) == 'v') {
					nb_ville += 1;
				}
			}
			bRead.close();
		}catch(IOException e){
			System.out.println("Probleme FileReader / BufferedReader dans main");
		}
		
		System.out.println("nb de ville : "+nb_ville);
		//Definir un tableau de ville de taille "nb_ville"
		Ville[] tab_ville = new Ville[nb_ville];
		//Definir les noms des villes
		Utilitaire.nomVille(tab_ville, Args[0]);

		/*
		 * Deuxieme etape : Affichage du menu pour les routes et pouvoir faire le choix par l'utilisateur
		 * 					L'utilisateur peut ajouter / relier deux ville avec un route 
		 */
		ArrayList <ArrayList <Character>>tab_voisin =Utilitaire.menuRoute(tab_ville);
		
		/*
		 * Troisieme etape : Affichage du menu pour les ecoles en attente d'un choix par l'utilisateur
		 * 					 L'utilisateur peut ajouter une ecole dans la ville si cette ville n'a pas d'ecole
		 * 					 L'utilisateur peut retirer une ecole d'une ville s'il ne veut plus de cette ecole
		 */
		Utilitaire.menuEcole(tab_ville,tab_voisin);
		
		scan.close();
	}
}
