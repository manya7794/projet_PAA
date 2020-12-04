import java.util.ArrayList;
import java.util.Scanner;

public class Resolution {
	
	static Scanner scan = new Scanner(System.in);
	
	//Resolution manuelle
	public static class ResolutionManuelle {
		

		
		/*
		 * Cette methode doit afficher le menu sur les ecoles, nous devons saisir entre 1, 2 et 3 qui sont trois options differente:
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

			do {
				System.out.println("");
				System.out.println("Menu d'ecole :");
				System.out.println("1) Ajouter une ecole");
				System.out.println("2) Retirer une ecole");
				System.out.println("3) Fin");

				int option = scan.next().charAt(0);
				switch(option) {
				case '1' :
					ajoutManuel(tab_ville);
					break;
				case '2' : 
					suppressionManuelle(tab_ville, tab_voisin);
					break;
				case '3' : 
					sortie = false;
					break;
				default : System.out.println("Commande invalide, choissisez une option");
					break;
				}
			}while(sortie);
		}
		
		/*
		 *@param
		 * 
		 */
		public static void ajoutManuel(ArrayList<Ville> tab_ville) {
			
			boolean existe;
			String ville;
			System.out.println("Saisissez le nom de la ville ou vous voulez creer une ecole");
			ville= scan.next();
			existe = false;
			/*
			 * Rechercher un nom de ville dans le un tableau de ville
			 * s'il le trouve il retourne vrai sinon false
			 */
			for(int i = 0; i<tab_ville.size() && (!existe); i++) {
				if(tab_ville.get(i).getNom().equals(ville)) {
					existe=true;
					boolean ajout =true;
					tab_ville.get(i).gestionEcole(existe, ajout);
				}
			}
			System.out.print("Liste des villes avec une ecole : ");
			for(int i = 0; i<tab_ville.size(); i++) {
				if(tab_ville.get(i).getEcole())
					System.out.print(tab_ville.get(i).getNom()+" ");
			}
		}
		
		public static void suppressionManuelle(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
			
			boolean existe = false;
			String ville = scan.next();
			System.out.println("Saisissez la ville ou vous voulez retirer une ecole");
	
			/*
			 * Recherche un nom de ville dans le tableau de villes
			 * s'il le trouve il retourne vrai sinon false
			 */
			for(int i = 0; i<tab_ville.size() && (!existe); i++) {
				if(tab_ville.get(i).getNom().equals(ville)) {
					//Ville trouvee
					existe=tab_ville.get(i).rechercheEcole(tab_ville, tab_voisin, ville);
					
					if (existe) {
						boolean ajout = false;
						tab_ville.get(i).gestionEcole(existe, ajout);	
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
		}
	}
	
	//Resolution algorithmique
	public static class ResolutionAutomatique {
		
		/*
		 * Implementation d'algorithme peu optimise
		 */
		public static ArrayList<Ville> automatiqueApproximation(ArrayList<Ville> tab_ville) {
			int scoreCourant = 0;
			for(int i = 0; i<tab_ville.size();i++) {
				if(tab_ville.get(i).getEcole()) {
					scoreCourant++;
				}
			}
			for(int i =0; i<tab_ville.size();) {
				int score = scoreCourant;
				Ville v = tab_ville.get(i);
				if(tab_ville.get(i).getEcole()) {
					v.setEcole(false);
					score--;
				}
				else {
					v.setEcole(true);
					score++;
				}
				if(score<scoreCourant) {
					i=0;
					scoreCourant=score;
				}
				else {
					i++;
				}
			}
			return tab_ville;
		}
	}
}