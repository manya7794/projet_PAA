import java.util.ArrayList;
import java.util.Scanner;

public class Utilitaire {
	static Scanner scan = new Scanner(System.in);
	/*
	 * Cette methode remplit un tableau de villes en remplissant l'entierete du tableau avec des villes 
	 * 
	 * @param Ville [] tab_ville, Sert de point de stockage des villes crees dans la methode
	 */
	public static void nomVille(Ville []tab_ville) {
		char ascii = 65;
		for(int i = 0; i<tab_ville.length; i++) {
			char nom = (char) ascii;
			tab_ville[i]= new Ville(nom);
			//Affichage de toutes les villes creees
			System.out.println("Ville "+tab_ville[i].getNom());
			ascii++;
		}
		System.out.println();
	}
	/*
	 * Cette methode doit afficher un menu pour les routes
	 * relier deux villes par une route l'option 1 et quitter l'option 2
	 * 
	 * @param Ville[] tab_ville, lorsque nous saisissons deux noms de ville nous devons verifier 
	 * si cette ville existant le tableau de nom de ville, 
	 * s'il n'existe pas nous pouvons pas relier ses deux villes
	 */
		public static ArrayList <ArrayList <String>> menuRoute(Ville[]tab_ville) {
		//Creation de la liste des voisins avec le modele de liste d'adjacence
		ArrayList <ArrayList <String>> tab_voisin = new ArrayList <ArrayList <String>>(tab_ville.length);
		//Ajoute le nombre de "ligne" correspondant au nb de ville
		for(int i = 0 ; i < tab_ville.length ; i++) {
			ArrayList <String> addNbVille = new ArrayList <String>();
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
	/*
	 *Affichage des choix lie au menu des routes
	 */
	private static int choixMenuRoute() {
		int option;
		do {
			System.out.println("Menu route :");
			System.out.println("1) Ajouter une route");
			System.out.println("2) Fin");
			option = scan.nextInt();
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
			System.out.println("La ville " + ville_1 + " ne peut etre reliee a elle-meme");
		}
		else {
			/*
			 * Gerer les cas ou la ville est deja voisine et qu'elles sont deja reliees
			 */
			boolean create_route = true;
			for(int i = 0; i<tab_ville.length && create_route==true; i++) {
				if(tab_ville[i].equals(ville_1) || tab_ville[i].equals(ville_2)) {
					if(tab_voisin.get(i).contains(ville_2)|| tab_voisin.get(i).contains(ville_1)) {
						System.out.println("La ville "+ville_1+" et la ville "+ville_2+" sont deja reliees");
						create_route = false;
					}
				}
			}
			/*
			 * Lorsque nous avons verifier tout les cas, nous allons relies les deux ville et inserer dans la liste voisin qui lui correspond
			 */
			if(create_route) {
				for(int i = 0; i<tab_ville.length;i++) {
					if(tab_ville[i].equals(ville_1)) {
						tab_voisin.get(i).add(ville_1);
					}
					if(tab_ville[i].equals(ville_2)) {
						tab_voisin.get(i).add(ville_2);
					}
				}
			}
			//Affichage du tab_voisin 
			afficherVoisin(tab_ville, tab_voisin);
		}
	}
	
		/*
	 * Affichage de la liste des voisins de chaque ville
	 * 
	 * @param Ville[] tab_ville, liste de ville
	 * @param tab_voisin - ArrayList <ArrayList<String>> contenant la liste de tous les voisins de chaque ville
	 */
	private static void afficherVoisin(Ville[] tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		//ArrayList <ArrayList <String>>
		for(int i = 0 ; i < tab_voisin.size() ; i ++) {
			System.out.print("Voisins de la ville " +tab_ville[i].getNom()+" : ");
			
			//ArrayList <Character>
			for(int j = 0 ; j < tab_voisin.get(i).size(); j++) {
				System.out.print(tab_voisin.get(i).get(j) + " ");
			}
			//Saut entre chaque ligne (chaque ville)
			System.out.println();
		}
		System.out.println();
	}
	
	/*
	 * Cette methode doit afficher le menu sur les ecoles, nous devons saisir entre 1, 2 et 3 qui sont trois options differentes:
	 * l'option 1 permet de ajouter une ecole a une ville, elle devra changer la variable boolean ecole en true pour savoir 
	 * qu'il y a une ecole dans cette ville et nous ne pouvons pas continuer a recreer un ecole par dessu
	 * l'option 2 doit retirer une ecole, si nous retirons une ecole la variable boolean ecole redeviendra false et nous pourrons recreer une ecole par dessu
	 * l'option 3 met fin a cette application
	 * 
	 * @param Ville[]tab_ville, est un tableau de classe ville un tableau de ville oÃ¹ nous pouvons connaitre le nom de la ville et
	 * savoir si une ecole est construite dedans
	 */
	public static void menuEcole(Ville[]tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		boolean sortie = true;
		boolean existe;
		String ville;
		do {
			int option = choixMenuEcole();
			switch(option) {
			case 1 :
				System.out.println("Saisissez le nom de la ville ou vous voulez creer une ecole");
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
						tab_ville[i].gestionEcole(ajout);
					}
				}
				if(!existe)
					System.err.println("La ville n'existe pas");
				System.out.print("Liste des villes avec une ecole : ");
				for(int i = 0; i<tab_ville.length; i++) {
					if(tab_ville[i].getEcole())
						System.out.print(tab_ville[i].getNom()+" ");
				}
				System.out.print("\n");
				break;
				
			case 2 : 
				System.out.println("Saisissez la ville ou vous voulez retirer une ecole");
				ville = scan.next().charAt(0);
				existe = false;
				/*
				 * Recherche un nom de ville dans le tableau de villes
				 * s'il le trouve il retourne vrai sinon false
				 */
				for(int i = 0; i<tab_ville.length && (!existe); i++) {
					if(tab_ville[i].getNom()==ville) {
						//Ville trouvee
						existe=tab_ville[i].rechercheEcole(tab_ville, tab_voisin, ville);
						
						if (existe) {
							boolean ajout = false;
							tab_ville[i].gestionEcole(ajout);	
						}
						else {
							System.out.println("Impossible de supprimer l'ecole, il n'y aucune ecole dans une ou plusieurs villes voisines..");
							existe=true;
						}
					}
				}
				if(!existe)
					System.err.println("La ville n'existe pas");
				System.out.print("Liste des villes avec une ecole : ");
				for(int i = 0; i<tab_ville.length; i++) {
					if(tab_ville[i].getEcole())
						System.out.print(tab_ville[i].getNom()+" ");
				}
				System.out.print("\n");
				break;
				
			case 3 : 
				sortie = false;
				break;
			default : System.out.println("Commande invalide, choissisez une option\n");
				break;
			}
		}while(sortie);
		scan.close();
	}
	/*
	 *Affichage des choix du menu lie aux ecoles
	 */
	private static int choixMenuEcole() {
		int option;
		do {
			System.out.println("\nMenu d'ecole :");
			System.out.println("1) Ajouter une ecole");
			System.out.println("2) Retirer une ecole");
			System.out.println("3) Fin");

			option = scan.nextInt();
			
		} while ((option==1) || (option==2)||(option==3));
		return option;
	}
}
