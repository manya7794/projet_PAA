import java.util.Arrays;
import java.util.Scanner;

public class Utilitaire {
	static Scanner scan = new Scanner(System.in);
	/*
	 * Cette methode creer un tableau de nom  ville a toutes les villes en définissant le nombre total de ville 
	 * grace a la fonction precedente "nombreVille()"
	 * 
	 * @param String [] tab_ville, il faut stocker les noms des villesdans un tableau de String
	 * car les noms de ville peuvent être en lettre alphabétique ou des chaines de caracère
	 */
	public static void nomVille(Ville []tab_ville) {
		char ascii = 65;
		for(int i = 0; i<tab_ville.length; i++) {
			char nom = (char) ascii;
			tab_ville[i]= new Ville(nom);
			//affichage temporaire le nom de ville (à retirer plus tard)
			System.out.println("Ville "+tab_ville[i].getNom());
			ascii++;
		}

	}
	/*
	 * Cette méthode doit afficher un menu pour les routes
	 * relier deux ville par une route l'option 1 et quitter l'option 2
	 * 
	 * @param Ville[] tab_ville, lorsque nous saisissons deux noms de ville nous devons vérifier 
	 * si cette ville existant le tableau de nom de ville, 
	 * s'il n'existe pas nous pouvons pas relier ses deux villes
	 */
	public static void menuRoute(Ville[] tab_ville) {
		boolean b = true; 	
		do {
			System.out.println("Menu route :");
			System.out.println("1) Ajouter une route");
			System.out.println("2) Fin");
			int option = scan.next().charAt(0);
			/*
			 * Fonctionnement apres avoir fait le choix du menu
			 */
			switch(option) {
			case '1' : 
				/*
				 * Saisir deux nom de ville ou nous voulions ajouter une route 
				 * */
				System.out.println("Saisissez une ville");
				char ville_1 = scan.next().charAt(0);
				System.out.println("Saisissez une autre ville");
				char ville_2 = scan.next().charAt(0);
				/*
				 * Rechercher un nom de ville dans le un tableau de ville
				 * s'il le trouve il retourne vrai sinon false
				 */
				boolean bool_1 = false;
				boolean bool_2 = false;
				for(int i = 0; i<tab_ville.length && (!bool_1); i++) {
					if(tab_ville[i].getNom()==ville_1) {
						bool_1=true;
					}
				}
				for(int i = 0; i<tab_ville.length && (!bool_2); i++) {
					if(tab_ville[i].getNom()==ville_2) {
						bool_2=true;
					}
				}
				if(!bool_1 && !bool_2) {
					System.err.println("L'un des deux villes est inexistante");
				}
				else {
					System.err.println("Les deux villes sont existante dans le tableau ");
				}
				break;
			case '2' : 
				b = false;
				break;
			default : System.err.println("Commande invalide, choissisez une option");
				break;
			}
		}while(b);
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
		boolean sortie = true;
		boolean existe;
		char ville;
		do {
			System.out.println("Menu d'école :");
			System.out.println("1) Ajouter une école");
			System.out.println("2) Retirer une école");
			System.out.println("3) Fin");

			int option = scan.next().charAt(0);
			Scanner scan = new Scanner(System.in);
			switch(option) {
			case '1' :
				System.out.println("Saisissez le nom de la ville ou vous voulez créer une ecole");
				ville= scan.next().charAt(0);
				existe = false;
				/*
				 * Rechercher un nom de ville dans le un tableau de ville
				 * s'il le trouve il retourne vrai sinon false
				 */
				for(int i = 0; i<tab_ville.length && (!existe); i++) {
					if(tab_ville[i].getNom()==ville) {
						existe=true;
						boolean ajout =true;
						tab_ville[i].gestionEcole(existe, ajout);
					}
				}
				//scan.close();
				break;
			case '2' : 
				System.out.println("Saisissez la ville où vous voulez retirer une ecole");
				ville = scan.next().charAt(0);
				existe = false;
				/*
				 * Recherche un nom de ville dans le tableau de villes
				 * s'il le trouve il retourne vrai sinon false
				 * */
				for(int i = 0; i<tab_ville.length && (!existe); i++) {
					if(tab_ville[i].getNom()==ville) {
						existe=true;
						boolean ajout = false;
						tab_ville[i].gestionEcole(existe, ajout);
					}
				}

				break;
			case '3' : 
				sortie = false;
				break;
			default : System.out.println("Commande invalide, choissisez une option");
				break;
			}
		}while(sortie);
		scan.close();
	}

}
