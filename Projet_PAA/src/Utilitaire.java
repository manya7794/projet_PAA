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
	public static void nomVille(ArrayList <Ville>tab_ville, String nomFichier) {		
		Ville v;
		boolean sortie = false;
		int nb_ville = 0;
		
		while(!sortie) {
			v = VilleParser.parser(nomFichier, nb_ville);
			tab_ville.add(nb_ville, v);
			nb_ville +=1;
			System.out.println(v);
			sortie = VilleParser.getSortie();
		}
		System.out.println("Nombre de villes :"+nb_ville);
	}

	public static void lireRoute(String nomFichier, int nb_ville, ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		boolean sortie =false;
		String route;
		while(!sortie) {
			route = RouteParser.parser(nomFichier, nb_ville);
			
			nb_ville+=1;
			createRoute(route.split(","), tab_ville, tab_voisin);
			//Utilitaire.createRoute(route.split(","), tab_ville, tab_voisin);
			sortie=RouteParser.getSortie();
		}
	}
	
	/*
	 * Ajout les listes de voisins au tab_voisin (liste d'adjacence)
	 * 
	 * @param tab_ville - Ville[] contenant la liste des villes creees au debut
	 * @param tab_voisin - ArrayList <ArrayList<Character>> contenant la liste de tous les voisins de chaque ville
	 */
	private static void createRoute(String[] route, ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		String ville_1=route[0];
		String ville_2=route[1];				
		
		boolean bool_1 = false;
		boolean bool_2 = false;
		
		//Regarde si ville_1 est dans tab_ville
		for(int i = 0 ; (i < tab_ville.size()) && !bool_1 ; i++) {
			if(tab_ville.get(i).getNom().equals(ville_1)) {
				bool_1 = true;
			}
		}
		
		//Regarde si ville_2 est dans tab_ville
		for(int i = 0 ; (i < tab_ville.size()) && !bool_2 ; i++) {
			if(tab_ville.get(i).getNom().equals(ville_2)) {
				bool_2 = true;
			}
		}
		
		if(!bool_1 || !bool_2) {
			System.err.println("L'une des deux villes est inexistante");
		}
		//Verifie que les deux villes sont differentes
		else if(ville_1.equals(ville_2)) {
			System.out.println("La ville " + ville_1 + " ne peut etre reliee a elle-meme");
		}
		else {
			
			/*
			 * Gerer les cas ou la ville est deja voisine et qu'elles sont deja reliees
			 */
			boolean create_route = true;
			for(int i = 0; i<tab_ville.size() && create_route==true; i++) {
				if(tab_ville.get(i).getNom().equals(ville_1) || tab_ville.get(i).getNom().equals(ville_2)) {
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
				for(int i = 0; i<tab_ville.size();i++) {
					if(tab_ville.get(i).getNom().equals(ville_1)) {
						tab_voisin.get(i).add(ville_2);
					}
					if(tab_ville.get(i).getNom().equals(ville_2)) {
						tab_voisin.get(i).add(ville_1);
					}
				}
			}
		
			//Affichage du tab_voisin 
			afficherVoisin(tab_ville, tab_voisin);
		}
	}
	
	/* Affichage de la liste des voisins de chaque ville
	 * 
	 * @param Ville[] tab_ville, liste de ville
	 * @param tab_voisin - ArrayList <ArrayList<String>> contenant la liste de tous les voisins de chaque ville
	 */
	public static void afficherVoisin(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		//ArrayList <ArrayList <String>>
		for(int i = 0 ; i < tab_voisin.size() ; i ++) {
			System.out.print("Voisins de la ville " +tab_ville.get(i).getNom()+" : ");
			
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
	 * Cette methode doit afficher le menu sur les ecoles, nous devons saisir entre 1, 2 et 3 qui sont trois option differente:
	 * l'option 1 permet de ajouter une ecole a une ville, elle devra changer la variable boolean ecole en true pour savoir 
	 * qu'il y a une ecole dans cette ville et nous ne pouvons pas continuer a recreer un ecole par dessu
	 * l'option 2 doit retirer une ecole, si nous retirons une ecole la variable boolean ecole redeviendra false et nous pourrons recreer une ecole par dessu
	 * l'option 3 met fin a cette application
	 * 
	 * @param Ville[]tab_ville, est un tableau de classe ville un tableau de ville où nous pouvons connaitre le nom de la ville et
	 * savoir si une ecole est construite dedans
	 */
	public static void menuEcole(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		boolean sortie = true;
		boolean existe;
		String ville;
		do {
			System.out.println("");
			System.out.println("Menu d'ecole :");
			System.out.println("1) Ajouter une ecole");
			System.out.println("2) Retirer une ecole");
			System.out.println("3) Fin");

			int option = scan.next().charAt(0);
			switch(option) {
			case '1' :
				System.out.println("Saisissez le nom de la ville ou vous voulez creer une ecole");
				ville= scan.next();
				existe = false;
				/*
				 * Rechercher un nom de ville dans le un tableau de ville
				 * s'il le trouve il retourne vrai sinon false
				 */
				for(int i = 0; i<tab_ville.size() && (!existe); i++) {
					if(tab_ville.get(i).getNom()==ville) {
						existe=true;
						boolean ajout =true;
						tab_ville.get(i).gestionEcole(ajout);
					}
				}
				System.out.print("Liste des villes avec une ecole : ");
				for(int i = 0; i<tab_ville.size(); i++) {
					if(tab_ville.get(i).getEcole())
						System.out.print(tab_ville.get(i).getNom()+" ");
				}
				break;
			case '2' : 
				System.out.println("Saisissez la ville ou vous voulez retirer une ecole");
				ville = scan.next();
				existe = false;
				/*
				 * Recherche un nom de ville dans le tableau de villes
				 * s'il le trouve il retourne vrai sinon false
				 */
				for(int i = 0; i<tab_ville.size() && (!existe); i++) {
					if(tab_ville.get(i).getNom()==ville) {
						//Ville trouvée
						existe=tab_ville.get(i).rechercheEcole(tab_ville, tab_voisin, ville);
						
						if (existe) {
							boolean ajout = false;
							tab_ville.get(i).gestionEcole(ajout);	
						}
						else
							System.out.println("Impossible de supprimer l'ecole, il n'y aucune ecole dans les villes voisines.");
					}
				}
				System.out.print("Liste des villes avec une ecole : ");
				for(int i = 0; i<tab_ville.size(); i++) {
					if(tab_ville.get(i).getEcole())
						System.out.print(tab_ville.get(i).getNom()+" ");
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
