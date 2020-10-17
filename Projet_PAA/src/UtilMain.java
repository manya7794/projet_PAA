import java.util.Scanner;

public class UtilMain {
	public static void main(String [] Args) {
		Scanner sc = new Scanner(System.in);
		int nbVille=27;
		while((nbVille>26)) {
			System.out.println("Entrez le nombre de villes (<=26)");
			nbVille=sc.nextInt();	
		}		
		System.out.println("Nombre de villes = "+nbVille);
		//Implémenter le for incrementant les villes lettre par lettre
		menuRoute();
		menuEcole();
	}
	
	public static void menuRoute(){
		Scanner sc= new Scanner(System.in);
		boolean sortie= false;
		int choix;
		while(!sortie) {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1) Ajouter une route");
			System.out.println("2) Fin");
			choix=sc.nextInt();
			switch(choix) {
			case 1:
				System.out.println("Quelle est la première ville que la route doit relier ?");
				Ville v1 = new Ville (sc.next());
				//Vérifier que la ville existe
				System.out.println("Quelle est la seconde ville que la route doit relier ?");
				Ville v2 = new Ville(sc.next());
				//Vérifier que la ville existe
				//Faut-il les intégrer dans un tableau ?
				break;
			case 2:
				sortie = true;
				break;
			default:
				System.out.println("Rentrez une valeur acceptée svp.");
			}
		}
	}
	
	public static void menuEcole() {
		Scanner sc = new Scanner (System.in);
		int choix;
		boolean sortie = false;
		String nomVille;
		while(!sortie) {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1) Ajouter une école");
			System.out.println("2) Retirer une école");
			System.out.println("3) Fin");
			choix= sc.nextInt();
			switch(choix) {
			case 1 :
				System.out.println("A quelle ville souhaitez-vous rajouter une école ?");
				nomVille=sc.nextLine();
				//Chercher dans le tableau la ville dont le nom correspond à nomVille
				/*Vérifier s'il n'y a pas déjà une école dans la ville 
				 *s'il y en a une et afficher un message si besoin
				 */
				//nomVille.ajoutEcole(); A decommenter quand le tableau de recherche est enlevée
				//Afficher la liste des villes possédant une école
				break;
			case 2:
				System.out.println("A quelle ville souhaitez-vous retirer une école ?");
				nomVille=sc.nextLine();
				//Chercher dans le tableau la ville dont le nom correspond à nomVille
				/*Faire une recherche pour l'accessibilité -> Vérifier les routes pour voir si les voisins
				 *ont une école chez eux ou dans leur voisinages directs donc première vérif chez chaque voisin
				 *puis if dans les routes du voisin s'il n'a pas d'école chez lui 
				 */
				//nomVille.retireEcole(); A decommenter quand le tableau de recherche est enlevée
				//Afficher la liste des villes possédant une école
				break;
			case 3:
				sortie = true;
				break;
			default:
				System.out.println("Rentrez une valeur acceptée svp.");
			}
		}
	}
}

