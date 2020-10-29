import java.util.ArrayList;
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
		char ascii = 65;
		for(int i = 0; i<tab_ville.length; i++) {
			//Conversion char en code ASCII
			char nom = (char) ascii;
			tab_ville[i]= new Ville(nom);
			System.out.println("Ville "+tab_ville[i].getNom());
			ascii++;
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
	public static void menuRoute(Ville[]tab_ville) {
		//Creation de la liste des voisins avec le modele de liste d'adjacence
		ArrayList <ArrayList <Character>> tab_voisin = new ArrayList <ArrayList <Character>>(tab_ville.length);
		//Ajoute le nombre de "ligne" correspondant au nb de ville
		for(int i = 0 ; i < tab_ville.length ; i++) {
			ArrayList <Character> addNbVille = new ArrayList <Character>();
			tab_voisin.add(addNbVille);
		}
		
		boolean b = true;
		do {
			int option = choixMenuRoute();
			switch(option) {
			case 1 : 
				createRoute(tab_ville, tab_voisin);
				break;
			case 2 : 
				b = false;
				break;
			default : 
				System.out.println("Commande invalide, choissisez une option");
				break;
			}
		}while(b);
	}
	
	private static int choixMenuRoute() {
		int option = 0;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Menu route :");
			System.out.println("1) Ajouter une route");
			System.out.println("2) Fin");
			option = scan.nextInt();
		} while ((option < 1) || (option > 2));
		return option;
	}
	
	//Methode ajoutant les listes de voisins au tab_voisin (liste d'adjacence)
	private static void createRoute(Ville[] tab_ville, ArrayList <ArrayList<Character>> tab_voisin) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Saisissez une ville");
		char ville_1 = scan.next().charAt(0);
		System.out.println("Saisissez une autre ville");
		char ville_2 = scan.next().charAt(0);				
		
		boolean bool_1 = false;
		boolean bool_2 = false;
		
		//Regarde si ville_1 est dans tab_ville
		for(int i = 0 ; (i < tab_ville.length) && !bool_1 ; i++) {
			if(tab_ville[i].getNom() == ville_1) {
				bool_1 = true;
			}
		}
		
		//Regarde si ville_2 est dans tab_ville
		for(int i = 0 ; (i < tab_ville.length) && !bool_2 ; i++) {
			if(tab_ville[i].getNom() == ville_2) {
				bool_2 = true;
			}
		}
		
		if(!bool_1 || !bool_2) {
			System.err.println("L'une des deux villes est inexistante");
		}
		else {
			
			//Sert pour trouver l'emplacement de la Ville dans tab_voisin
			int placeVille1 = ((int) ville_1)-65;
			int placeVille2 = ((int) ville_2)-65;
			
			//Ajout du ville voisin sur la ligne (placeVille) correspondant a la ville avec laquel il est voisin
			tab_voisin.get(placeVille1).add(ville_2);
			tab_voisin.get(placeVille2).add(ville_1);
			
			//Affichage du tab_voisin (temporaire, a enlever)
			afficherVoisin(tab_voisin);
		}
	}
	
	//Affichage de la liste de voisin
	private static void afficherVoisin(ArrayList <ArrayList<Character>> tab_voisin) {
		//Pour recuperer le nom de la ville
		int ascii = 65;
		char ville = (char) ascii;
		
		//ArrayList <ArrayList <Character>>
		for(int i = 0 ; i < tab_voisin.size() ; i ++) {
			System.out.print("Voisin de la ville " +ville+" : ");
			
			//ArrayList <Character>
			for(int j = 0 ; j < tab_voisin.get(i).size(); j++) {
				System.out.print(tab_voisin.get(i).get(j) + " ");
			}
			ascii++;
			ville=(char)ascii;
			//Saut entre chaque ligne (chaque ville)
			System.out.println();
		}
		System.out.println();
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

	public static void menuEcole(Ville[]tab_ville) {
		boolean b = true;
		System.out.println("Menu d'école :");
		System.out.println("1) Ajouter une école");
		System.out.println("2) Retirer une école");
		System.out.println("3) Fin");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		//Problème de scanner
		do {
			switch(option) {
			case 1 : 
				System.out.println("Saisissez la ville où vous voulez creer l'ecole");
				char ecole_1 = scan.next().charAt(0);
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
				char ecole_2 = scan.next().charAt(0);
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