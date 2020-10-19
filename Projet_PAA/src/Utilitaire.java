import java.util.Arrays;
import java.util.Scanner;

public class Utilitaire {
	
	/*
	 * Cette methode creer un tableau de nom  ville a toutes les villes en définissant le nombre total de ville 
	 * grace a la fonction precedente "nombreVille()"
	 * 
	 * @param String [] tab_ville, il faut stocker les noms des villes dans un tableau de String
	 * car les noms de ville peuvent etre en lettre alphabetique ou des chaines de caractere
	 */
	public static void nomVille(Ville[]tab_ville) {
		char nom = 'A';
		for(int i = 0; i<tab_ville.length; i++) {
			System.out.println("Nom de la ville n°"+(i+1));
			//Conversion char en code ASCII
			tab_ville[i]= null;
		}
	}
	/*
	 * Cette methode doit afficher un menu pour les routes
	 * relier deux ville par une route l'option 1 et quitter l'option 2
	 * 
	 * @param ville[] tab_ville, est un tableau de la classe, lorsque nous saisissons deux ville, nous devons rechercher dans le tableau de ville
	 * si cette ville est existante sinon il nous sera demander de resaisir deux nom de ville
	 * Si les deux villes sont tout les deux existante dans le tableau de ville, cette methode doit créer une route entre ses deux villes
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static void menuRoute(Ville[]tab_ville) {
		boolean b = true;
		System.out.println("Menu route :");
		System.out.println("1) Ajouter une route");
		System.out.println("2) Fin");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		do {
			switch(option) {
			case 1 : 
				System.out.println("Saisissez une ville");
				String ville_1 = scan.next();
				System.out.println("Saisissez une autre ville");
				String ville_2 = scan.next();				
				//Creer un classe ensemble de ville
				if(!Arrays.asList(tab_ville).contains(ville_1) || !Arrays.asList(tab_ville).contains(ville_2)) {
					System.err.println("L'une des deux villes est inexistante");
				}
				else {
					//Methode qui permet de créer une ville dans la classe communauté qui permet de regrouper tout objets de la ville
					System.err.println("Les deux villes sont existante dans le tableau ");
				}
				break;
			case 2 : 
				b = false;
				break;
			default : System.out.println("Commande invalide, choissisez une option");
				break;
			}
		}while(b);
		scan.close();
	}
	
	/*
	 * Cette methode doit afficher le menu sur les ecoles, nous devons saisir entre 1, 2 et 3 qui sont trois option differente:
	 * l'option 1 permet de ajouter une ecole a une ville, elle devra changer la variable boolean ecole en true pour savoir 
	 * qu'il y a une ecole dans cette ville et nous ne pouvons pas continuer a recreer un ecole par dessu
	 * l'option 2 doit retirer une ecole, si nous retirons une ecole la variable boolean ecole redeviendra false et nous pourrons recreer une ecole par dessu
	 * l'option 3 met fin a cette application
	 * 
	 * @param Ville[]tab_ville, est un tableau de classe ville un tableau de ville où nous pouvons connaitre le nom de la ville et
	 * savoir si une ecole est construite dedans
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static void menuEcole(Ville[]tab_ville) {
		boolean b = true;
		System.out.println("Menu d'école :");
		System.out.println("1) Ajouter une école");
		System.out.println("2) Retirer une école");
		System.out.println("3) Fin");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		do {
			switch(option) {
			case 1 : 
				System.out.println("Saisissez la ville où vous voulez creer l'ecole");
				String ecole_1 = scan.next();
				Ville ville_ecole_1 = new Ville(ecole_1) ;
				if(!Arrays.asList(tab_ville).contains(ville_ecole_1)) {
					//Verifie si la ville saisie est une ville dans qui existe dans le tableau de ville
					System.err.println("La ville n'existe pas");
				}
				else if(Arrays.asList(tab_ville).contains(ville_ecole_1) && ville_ecole_1.getEcole()){
					//Verifie que l'ecole exite, et verifie s'il y a deja une ecole ou non
					System.err.println("Cette ville a deja une ecole");
				}
				else {
					//Methode qui ajoute une ecole dans une ville, qui change la variable boolean ecole en true
					//Nous ne pourrons plus creer d'ecole dans cette ville 
					ville_ecole_1.ajoutEcole();
					System.out.println("Une ecole a ete ajouter dans la ville de "+ville_ecole_1.getNom());
				}
				break;
			case 2 : 
				System.out.println("Saisissez la ville où vous voulez creer l'ecole");
				String ecole_2 = scan.next();
				Ville ville_ecole_2 = new Ville(ecole_2) ;
				if(!Arrays.asList(tab_ville).contains(ville_ecole_2)) {
					//Verifie si la ville saisie est une ville dans qui existe dans le tableau de ville
					System.err.println("La ville n'existe pas");
				}
				else if(Arrays.asList(tab_ville).contains(ville_ecole_2) && !ville_ecole_2.getEcole()){
					//Verifie que l'ecole exite, et verifie s'il y a deja une ecole ou non
					System.err.println("Cette ville n'a pas d'ecole");
				}	
				else {
					//Methode qui retire l'ecole d'une ville, qui change la variable boolean ecole en false
					//Nous pouvons a nouveau creer une ecole dans cette ville
					ville_ecole_2.retireEcole();
					System.out.println("L'ecole a ete retirer de la ville de"+ ville_ecole_2.getNom());
				}
				break;
			case 3 : 
				b = false;
				break;
			default : System.out.println("Commande invalide, choissisez une option");
				break;
			}
		}while(b);
		scan.close();
		
	}

}
