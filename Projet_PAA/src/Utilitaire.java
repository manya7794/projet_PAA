import java.util.ArrayList;
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
		public static ArrayList <ArrayList <Character>> menuRoute(Ville[]tab_ville) {
		//Creation de la liste des voisins avec le modele de liste d'adjacence
		ArrayList <ArrayList <Character>> tab_voisin = new ArrayList <ArrayList <Character>>(tab_ville.length);
		//Ajoute le nombre de "ligne" correspondant au nb de ville
		for(int i = 0 ; i < tab_ville.length ; i++) {
			ArrayList <Character> addNbVille = new ArrayList <Character>();
			tab_voisin.add(addNbVille);
		}
		
		boolean b = true;
		do {
			char option = choixMenuRoute();
			switch(option) {
			case '1' : 
				createRoute(tab_ville, tab_voisin);
				break;
			case '2' : 
				b = false;
				break;
			default : 
				System.out.println("Commande invalide, choissisez une option");
				break;
			}
		}while(b);
		return tab_voisin;
	}
	
	private static char choixMenuRoute() {
		char option;
		do {
			System.out.println("Menu route :");
			System.out.println("1) Ajouter une route");
			System.out.println("2) Fin");
			option = scan.next().charAt(0);
		} while ((option==1) || (option==2));
		return option;
	}
	
	/*
	 * Ajout les listes de voisins au tab_voisin (liste d'adjacence)
	 * 
	 * @param tab_ville - Ville[] contenant la liste des villes creees au debut
	 * @param tab_voisin - ArrayList <ArrayList<Character>> contenant la liste de tous les voisins de chaque ville
	 */
	private static void createRoute(Ville[] tab_ville, ArrayList <ArrayList<Character>> tab_voisin) {
		
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
		//Verifie que les deux villes sont differentes
		else if(ville_1 == ville_2) {
			System.out.println("La ville " + ville_1 + " ne peut etre reliée à elle-même");
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
	
	/*
	 * Affichage de la liste des voisins de chaque ville
	 * 
	 * @param tab_voisin - ArrayList <ArrayList<Character>> contenant la liste de tous les voisins de chaque ville
	 */
	private static void afficherVoisin(ArrayList <ArrayList<Character>> tab_voisin) {
		//Pour recuperer le nom de la ville
		int ascii = 65;
		char ville = (char) ascii;
		
		//ArrayList <ArrayList <Character>>
		for(int i = 0 ; i < tab_voisin.size() ; i ++) {
			System.out.print("Voisins de la ville " +ville+" : ");
			
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
	public static void menuEcole(Ville[]tab_ville, ArrayList <ArrayList<Character>> tab_voisin) {
		boolean sortie = true;
		boolean existe;
		char ville;
		do {
			System.out.println("");
			System.out.println("Menu d'école :");
			System.out.println("1) Ajouter une école");
			System.out.println("2) Retirer une école");
			System.out.println("3) Fin");

			int option = scan.next().charAt(0);
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
				System.out.print("Liste des villes avec une ecole : ");
				for(int i = 0; i<tab_ville.length; i++) {
					if(tab_ville[i].getEcole())
						System.out.print(tab_ville[i].getNom()+" ");
				}
				break;
			case '2' : 
				System.out.println("Saisissez la ville où vous voulez retirer une ecole");
				ville = scan.next().charAt(0);
				existe = false;
				/*
				 * Recherche un nom de ville dans le tableau de villes
				 * s'il le trouve il retourne vrai sinon false
				 */
				for(int i = 0; i<tab_ville.length && (!existe); i++) {
					if(tab_ville[i].getNom()==ville) {
						//Ville trouvée
						existe=tab_ville[i].rechercheEcole(tab_ville, tab_voisin, ville);
						
						if (existe) {
							boolean ajout = false;
							tab_ville[i].gestionEcole(existe, ajout);	
						}
						else
							System.out.println("Impossible de supprimer l'ecole, il n'y aucune ecole dans les villes voisines.");
					}
				}
				System.out.print("Liste des villes avec une ecole : ");
				for(int i = 0; i<tab_ville.length; i++) {
					if(tab_ville[i].getEcole())
						System.out.print(tab_ville[i].getNom()+" ");
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
