import java.util.ArrayList;
import java.util.LinkedList;
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
					scan.close();
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
		
		
		public static void automatiqueApproximation(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
			//Retire l'ensemble des ecoles avant de commencer
			suppressionEcole(tab_ville);
			
			//listNoeud : une liste des noeuds (sommet avec un nombre d'arete (nombre de voisin) > 1) de tab_ville
			ArrayList<String> listNoeud = new ArrayList<String>();
			
			//listFeuille : une liste de feuille (sommet a une arete (un seul voisin)) de tab_ville
			ArrayList<String> listFeuille = new ArrayList<String>();
			
			//listNoeudRetirer : une liste de noeud qui a ete retirer de listNoeud
			ArrayList<String> listNoeudRetirer = new ArrayList<String>();
			
			//Remplie les listes listNoeud et listFeuille
			nbVoisin(tab_ville, tab_voisin, listNoeud, listFeuille);
			
			for(String i : listNoeud) {
				System.out.print(i+" ");
			}
			System.out.println();
			for(String i : listFeuille) {
				System.out.print(i+" ");
			}
			System.out.println();
			for(String i : listNoeudRetirer) {
				System.out.print(i+" ");
			}
		
			//Ajoute les ecoles
			ajoutEcole(tab_ville, tab_voisin, listNoeud, listFeuille, listNoeudRetirer);
			
			System.out.println("Après");
			for(String i : listNoeud) {
				System.out.print(i+" ");
			}
			System.out.println();
			for(String i : listFeuille) {
				System.out.print(i+" ");
			}
			System.out.println();
			for(String i : listNoeudRetirer) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
		//Methode supprimant toutes les ecoles deja existantes
		private static void suppressionEcole(ArrayList<Ville> tab_ville) {
			for(int i = 0 ; i < tab_ville.size() ; i++) {
				tab_ville.get(i).retireEcole();
			}
		}
		
		private static void nbVoisin(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin, 
				ArrayList<String> listNoeud, ArrayList<String> listFeuille) {
			
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			
			for(int i = 0 ; i < tab_voisin.size() ; i++) {
				if(tab_voisin.get(i).size()==1) {
					listFeuille.add(tab_ville.get(i).getNom());
				}
				else {
					tmp.add(tab_voisin.get(i).size());
					listNoeud.add(tab_ville.get(i).getNom());
				}
			}
			//Trie la liste listNoeud par rapport a tmp
			trie(tmp, listNoeud);
		}
		
		/*
		 * Trie dans l'ordre decroissant SELON la liste tmp = listNoeud.size() (int) 
		 * afin de mettre dans l'ordre decroissant la listNoeud
		 * listNoeud sera trier selon tmp (donc au meme moment et de la meme maniere)
		 */
		private static void trie(ArrayList<Integer> tmpListes, ArrayList<String> tmpVille) {
			//Verifie que la liste n'est pas vide
			if(tmpListes.isEmpty()) {
				System.out.println("La liste est vide");
			}
			else {
				triFusion(tmpListes, tmpVille, 0, tmpListes.size()-1);
			}
		}
		
		private static void triFusion(ArrayList <Integer> tmpListes, ArrayList<String> tmpVille, int iMin, int iMax) {
			if(iMin < iMax) {
				int iMilieu = (iMin+iMax)/2;
				triFusion(tmpListes, tmpVille, iMin, iMilieu);
				triFusion(tmpListes, tmpVille, iMilieu+1, iMax);
				fusion(tmpListes, tmpVille, iMin, iMilieu, iMax);
			}
		}
		
		private static void fusion(ArrayList <Integer> tmpListes, ArrayList<String> tmpVille, int iMin, int iMilieu, int iMax) {
			//Indice de debut de tmp
			int i = iMin;
			//Indice de fin de tmp
			int j = iMilieu+1;
			//Indice pour tmpListes (et donc aussi de tmpVille)
			int k = iMin;
			
			//Creation des LinkedList
			LinkedList<Integer> tmp = new LinkedList<Integer>(tmpListes);
			LinkedList<String> tmp2 = new LinkedList<String>(tmpVille);
			
			
			/*
			 * On regarde les tetes de liste entre iMin et iMilieu 
			 * Le plus petit est recopier dans tmpListes (donc tmpVille egalement)
			 * puis on incremente celui qui est recopier
			 */
			while( (i <= iMilieu) && (j <= iMax) ) {
				if(tmp.get(i) > tmp.get(j)) {
					tmpListes.set(k, tmp.get(i));
					tmpVille.set(k, tmp2.get(i));
					k++;
					i++;
				}
				else {
					tmpListes.set(k, tmp.get(j));
					tmpVille.set(k, tmp2.get(j));
					k++;
					j++;
				}
			}
			
			/*
			 * Si l'indice k n'est pas egale a iMax 
			 * Cela voudrait dire qu'il reste des elements dans tmp qui n'ont pas encore ete traiter et donc mise dans tmpListes
			 */
			if(k <= iMax) {
				//S'il reste des elements dans la premiere partie du tableau tmp alors ils sont recopier dans tmpListes
				while(i <= iMilieu) {
					tmpListes.set(k, tmp.get(i));
					tmpVille.set(k, tmp2.get(i));
					k++;
					i++;
				}
				//S'il reste des elements dans la seconde partie du tableau tmp alors ils sont recopier dans tmpListes
				while(j <= iMax) {
					tmpListes.set(k, tmp.get(j));
					tmpVille.set(k, tmp2.get(j));
					k++;
					j++;
				}
			}
		}
		
		/*
		 * Parcours la liste des voisins du sommet courrant et les retires de listNoeud et listFeuille
		 * et ajoute les villes retirer dans listNoeudRetirer 
		 */
		private static void parcoursVoisin(int indiceDeVille, ArrayList <ArrayList<String>> tab_voisin,
				ArrayList<String> listNoeud, ArrayList<String> listFeuille, ArrayList<String> listNoeudRetirer) {
			/*
			 * On va egalement retirer ses voisins de la ville courrante dans listNoeud et listFeuille
			 * On va utiliser un double for pour comparer la liste des voisins (tab_voisin) à la listNoeud et listFeuille
			 */
			for(int p = 0 ; p < tab_voisin.get(indiceDeVille).size() ; p++) {
				// k = 1 car la premiere valeur de listNoeud c'est la ville courrante, donc elle ne peut pas etre voisin d'elle meme
				for(int k = 1 ; k < listNoeud.size() ; k++) {
					//Si les voisin de la ville ou on vient d'ajouter l'ecole se trouve dans listNoeud alors on le retire 
					if(tab_voisin.get(indiceDeVille).get(p).equals(listNoeud.get(k))) {
						//Ajoute la ville dans listNoeudRetirer qui est a retirer de listNoeud
						listNoeudRetirer.add(listNoeud.get(k));
						listNoeud.remove(k);
					}
				}
				for(int k = 1 ; k < listFeuille.size() ; k++) {
					//Si les voisins de la ville ou on vient d'ajouter l'ecole se trouve dans listFeuille alors on le retire
					if(tab_voisin.get(indiceDeVille).get(p).equals(listFeuille.get(k))) {
						//Ajoute la ville dans listNoeudRetirer qui est a retirer de listFeuille
						//listNoeudRetirer.add(listFeuille.get(k));
						listFeuille.remove(k);
					}
				}
			}
		}
		
		private static void ajoutEcole (ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin,
				ArrayList<String> listNoeud, ArrayList<String> listFeuille, ArrayList<String> listNoeudRetirer) {
			
			while(!listNoeud.isEmpty()) {
				/*
				 * Boolean changement pour verifier s'il y a eu une affectation d'ecole ou non afin d'ajouter une ecole
				 * a une ville qui a son/ses voisins qui sont deja dans la listNoeudRetirer
				 */
				boolean changement = false;
				for(int i = 0 ; i < listNoeud.size() ; i++) {
					int indiceDeVille;
					//Un boolean trouver = vrai si on trouve le voisin, afin de sortir de la boucle for ci-dessous
					boolean trouver = false;
					//Recherche la ville dans tab_ville de indiceDeVille correspondante a la ville de listNoeud de i
					for(indiceDeVille = 0 ; (indiceDeVille < tab_ville.size()) && !trouver ; indiceDeVille++) {
						if(listNoeud.get(i).equals(tab_ville.get(indiceDeVille).getNom())) {
							trouver = true;
						}
					}
					/*
					 * Si la liste de noeud retirer (listNoeudRetirer) est vide alors on ajoute une ecole
					 * a la ville (dans tab_ville) qui est la meme que celui dans listNoeud
					 */
					if(listNoeudRetirer.isEmpty()) {
						//Ajout de l'ecole a la ville tab_ville de indiceDeVille 
						tab_ville.get(indiceDeVille).ajoutEcole();
						//Ajout de la ville dans listNoeudRetirer, car on a deja ajouter une ecole a cette ville donc on va le retirer de listNoeud
						listNoeudRetirer.add(listNoeud.get(i));
						
						//Retire les voisins de la ville courrante de listNoeud et listFeuille et les ajoutes a listNoeudRetirer
						parcoursVoisin(indiceDeVille, tab_voisin, listNoeud, listFeuille, listNoeudRetirer);

						//Et enfin on retire la ville courrant de listNoeud
						listNoeud.remove(i);
						changement = true;			
					}
					/*
					 * Si la liste (listNoeudRetirer) n'est pas vide alors verifier si les voisins du noeud courrant dans listNoeud
					 * se trouve dans la liste listNoeudRetirer,
					 * si Oui alors on regarde le noeud suivant dans listNoeud
					 * sinon si aucun de ses voisins ne s'y trouve alors on ajoute une ecole a la ville (dans tab_ville) 
					 * la meme ville que celui de la ville courrante dans listNoeud
					 */
					else {
						/*
						 * boolean trouverVoisin est vrai si on trouve un voisin dans listNoeudRetirer
						 * si on n'en trouve pas alors on ajoute une ecole
						 */
						boolean trouverVoisin = false;
						//Un boolean trouver = vrai si on trouve le voisin, afin de sortir de la boucle for ci-dessous
						
						//Double for afin de voir si les voisins de listNoeud de i se trouvent dans listNoeudRetirer
						for(int p = 0 ; p < tab_voisin.get(indiceDeVille).size() && !trouverVoisin ; p++) {
							for(int k = 0 ; k < listNoeudRetirer.size() && !trouverVoisin; k++) {
								//Si ses voisins se trouvent dans listNoeudRetirer alors trouverVoisin = true
								if(tab_voisin.get(indiceDeVille).get(p).equals(listNoeudRetirer.get(k))) {
									trouverVoisin = true;
								}
							}
						}
						
						/*
						 * Si la ville courrante listNoeud de i n'as pas de voisin qui se trouve dans listNoeudRetirer
						 * Alors on va ajouter une ville, et le retirer de listNoeud/listFeuille ainsi que son/ses voisin
						 */
						if(!trouverVoisin) {
							//Ajout de l'ecole a la ville tab_ville de j 
							tab_ville.get(indiceDeVille).ajoutEcole();
							//Ajout de la ville dans listNoeudRetirer, car on a deja ajouter une ecole a cette ville donc on va le retirer de listNoeud
							listNoeudRetirer.add(listNoeud.get(i));
							
							//Retire les voisins de la ville courrante de listNoeud et listFeuille et les ajoutes a listNoeudRetirer
							parcoursVoisin(indiceDeVille, tab_voisin, listNoeud, listFeuille, listNoeudRetirer);
							
							//Et enfin on retire la ville courrant de listNoeud
							listNoeud.remove(i);
							changement = true;
						}
						
					} 
					if( (i==listNoeud.size()-1) && !changement) {
						//Ajout de l'ecole a la ville tab_ville de j 
						tab_ville.get(indiceDeVille).ajoutEcole();
						//Ajout de la ville dans listNoeudRetirer, car on a deja ajouter une ecole a cette ville donc on va le retirer de listNoeud
						listNoeudRetirer.add(listNoeud.get(i));
						
						//Retire les voisins de la ville courrante de listNoeud et listFeuille et les ajoutes a listNoeudRetirer
						parcoursVoisin(indiceDeVille, tab_voisin, listNoeud, listFeuille, listNoeudRetirer);
						
						//Et enfin on retire la ville courrant de listNoeud
						listNoeud.remove(i-1);
					}
				}
			}
			
			while(!listFeuille.isEmpty()) {
				boolean trouver = false;
				for(int i = 0 ; i < listFeuille.size() ; i++) {
					for(int indiceDeVille = 0; (indiceDeVille < tab_ville.size()) && !trouver ; indiceDeVille++) {
						if(listFeuille.get(i).equals(tab_ville.get(indiceDeVille).getNom())) {
							String nomVoisin = tab_voisin.get(indiceDeVille).get(0);
							boolean trouverVille = false;
							for(int j = 0 ; j < tab_ville.size() && !trouverVille ; j++) {
								if(tab_ville.get(j).getNom().equals(nomVoisin)) {
									if(tab_ville.get(j).getEcole()) {
										listFeuille.remove(i);
									}
									else {
										tab_ville.get(j).ajoutEcole();
										listFeuille.remove(i);
									}
									trouverVille = true;
								}
							}
							trouver = true;
						}
					}
				}
			}
		}
	}
}
