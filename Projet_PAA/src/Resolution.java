package projetPartie2_DESPIERRES_WANG_ZHOU;
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
				System.out.println("\nMenu d'ecole :");
				System.out.println("1)Ajouter une ecole");
				System.out.println("2)Retirer une ecole");
				System.out.println("3)Fin");

				int option = scan.next().charAt(0);
				switch(option) {
				case '1' :
					ajoutManuel(tab_ville);
					//Verification pour voir si la sauvegarde auto est activee
					if(Sauvegarde.getSauvegardeAuto())
						Utilitaire.sauvegardeFichier(Sauvegarde.getfichierSauvegardeAuto(), tab_ville, tab_voisin);
					break;
				case '2' : 
					suppressionManuelle(tab_ville, tab_voisin);
					//Verification pour voir si la sauvegarde auto est activee
					if(Sauvegarde.getSauvegardeAuto())
						Utilitaire.sauvegardeFichier(Sauvegarde.getfichierSauvegardeAuto(), tab_ville, tab_voisin);
					break;
				case '3' : 
					sortie = false;
					//Verification pour voir si la sauvegarde auto est activee
					if(Sauvegarde.getSauvegardeAuto())
						Utilitaire.sauvegardeFichier(Sauvegarde.getfichierSauvegardeAuto(), tab_ville, tab_voisin);
					break;
				default : System.out.println("Commande invalide, choissisez une option");
					break;
				}
			}while(sortie);
		}
		
		/*
		 * Methode ajoutant une ecole dans une ville
		 * 
		 *@param tab_ville la liste des villes
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
		
		/*
		 * Methode supprimant une ecole dans une ville
		 * 
		 * @param tab_ville la liste des villes
		 * @param tab_voisin la liste des voisins
		 */
		public static void suppressionManuelle(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
			
			boolean existe = false;
			System.out.println("Saisissez la ville ou vous voulez retirer une ecole");
			String ville = scan.next();

	
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
		 * Les etapes de la methode pour resoudre automatiquement :
		 * G : Un graphe
		 * Un sommet : la ville courante
		 * Une arete : une ville voisine de la ville courante
		 * 
		 * Etape 1 :
		 * 		Un ensemble listNoeud(G) de sommet avec un nombre d'aretes strictement superieur a 1, 
		 * 		et les sommets seront tries dans l'ordre decroissant du nombre d'aretes.
		 * Etape 2 :
		 * 		Un ensemble listFeuille(G) de sommets possedant 1 seule arete.
		 * Etape 3 :
		 * 		Un ensemble listNoeudsRetires(G), initialement vide et qui contiendra les sommets supprimes dans listNoeud(G)
		 * Etape 4 :
		 * 		Pour tous les sommets de listNoeud(G) et jusqu'a ce que listNoeud(G) soit vide, regarde (a partir de i = 0 jusqu'a nb_sommet(listNoeud(G)))
		 * 			Si le sommet courant possede un voisin dans listNoeudsRetires(G) 
		 * 				Alors regarder les sommets suivants
		 * 			Sinon 	ajoute une ecole au sommet courant
		 * 					puis retire le sommet courant ainsi que tous ses voisins des listes listNoeud(G) et listFeuille(G) 
		 * 					Et pour tous les sommets listNoeud(G) retirer, les ajouter dans listNoeudsRetires(G)
		 * Etape 5 : 
		 * 		Pour tous les sommets de listFeuille(G) et jusqu'a ce que listFeuille(G) soit vide,
		 * 			Si le sommet v (ville voisine) qui est adjacent au sommet u (ville courante) possede une ecole
		 * 				Alors retirer le sommet u de listFeuille(G) (ainsi que tous les voisins de v qui sont dans listFeuille(G))
		 * 			Sinon si le sommet v ne possede pas d'ecole, alors ajouter une ecole au sommet v 
		 * 				et retirer le sommet u de listFeuille(G) (ainsi que tous les voisins de v qui sont dans listFeuille(G))
		 * 
		 * @param tab_ville ArrayList contenant les villes
		 * @param tab_voisin ArrayList d'arrayList contenant les voisins de chaque ville
		 */
		public static void automatiqueApproximation(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
			//Retire l'ensemble des ecoles avant de commencer
			suppressionEcole(tab_ville);
			
			//Liste des noeuds (sommet (ville) avec un nombre d'aretes (nombre de voisins) > 1) de tab_ville
			ArrayList<String> listNoeud = new ArrayList<String>();
			
			//Liste des feuilles (sommet (ville) possedant une unique arete (un seul voisin)) de tab_ville
			ArrayList<String> listFeuille = new ArrayList<String>();
			
			//Liste des noeuds qui ont ete retire de listNoeud
			ArrayList<String> listNoeudsRetires = new ArrayList<String>();
			
			//Remplissage de listNoeud et listFeuille
			nbVoisin(tab_ville, tab_voisin, listNoeud, listFeuille);
		
			//Ajout des ecoles
			ajoutEcole(tab_ville, tab_voisin, listNoeud, listFeuille, listNoeudsRetires);
			
			//Verification pour voir si la sauvegarde auto est activee
			if(Sauvegarde.getSauvegardeAuto()) {
				Utilitaire.sauvegardeFichier(Sauvegarde.getfichierSauvegardeAuto(), tab_ville, tab_voisin);
			}
			
			//Affichage des villes possedant une ecole
			System.out.print("Liste des villes avec une ecole : ");
			for(int i = 0; i<tab_ville.size(); i++) {
				if(tab_ville.get(i).getEcole())
					System.out.print(tab_ville.get(i).getNom()+" ");
			}
			System.out.println();
		}
		
		/*
		 * Methode supprimant toutes les ecoles deja existantes
		 * 
		 * @param tab_ville ArrayList contenant les villes
		 */
		private static void suppressionEcole(ArrayList<Ville> tab_ville) {
			//Place l'attribut ecole de chaque ville sur la valeur "false"
			for(int i = 0 ; i < tab_ville.size() ; i++) {
				tab_ville.get(i).retireEcole();
			}
		}
		
		/*
		 * Methode qui remplit les listes listNoeud et listFeuille 
		 * et qui les trie dans l'ordre decroissant de nb d'arete (pour listNoeud)
		 * 
		 * @param tab_ville ArrayList contenant les villes
		 * @param tab_voisin ArrayList d'arrayList contenant les voisins de chaque ville
		 * @param ListNoeud ArrayList contenant les noeuds avec un nombre de voisins superieur a 1
		 * @param ListFeuille ArrayList contenant les noeuds avec les noeuds ne possdant qu'un voisin
		 */
		private static void nbVoisin(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin, ArrayList<String> listNoeud, ArrayList<String> listFeuille) {
			
			//Liste temporaire afin de pouvoir trier listNoeud, tmp contient le nombre d'aretes de chaque ville de listNoeud
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			
			//Parcourt toutes les villes existantes
			for(int i = 0 ; i < tab_voisin.size() ; i++) {
				//La ville n'a qu'un seul voisin => listFeuille
				if(tab_voisin.get(i).size()==1) {
					listFeuille.add(tab_ville.get(i).getNom());
				}
				//La ville a au minimum 2 voisins ou plus => listNoeud
				else {
					tmp.add(tab_voisin.get(i).size());
					listNoeud.add(tab_ville.get(i).getNom());
				}
			}
			//Tri de listNoeud par rapport a tmp
			tri(tmp, listNoeud);
		}
		
		/*
		 * Methode triant dans l'ordre decroissant selon la liste tmp = listNoeud.size() (int) 
		 * afin de mettre dans l'ordre decroissant la listNoeud
		 * listNoeud sera triee selon tmp (donc au meme moment et de la meme maniere)
		 * 
		 * @param tmpListes ArrayList d'entier contenant le nombre d'arete de chaque ville de tmpVille
		 * @param tmpVille ArrayList contenant les noeuds avec un nombre de voisins superieur a 1
		 */
		private static void tri(ArrayList<Integer> tmpListes, ArrayList<String> tmpVille) {
			//Verifie que la liste n'est pas vide
			if(tmpListes.isEmpty()) {
				//Liste vide
				System.out.println("La liste est vide");
			}
			else {
				//Applique l'algorithme de triFusion sur la liste si cette derniere n'est pas vide
				triFusion(tmpListes, tmpVille, 0, tmpListes.size()-1);
			}
		}
		
		/*
		 * Methode appliquant l'algorithme de triFusion
		 * 
		 * @param tmpListes ArrayList d'entier contenant le nombre d'arete de chaque ville de tmpVille
		 * @param tmpVille ArrayList contenant les noeuds avec un nombre de voisins superieur a 1
		 * @param iMin Position de l'element minimal
		 * @param iMax Position de l'element maximal
		 */
		private static void triFusion(ArrayList <Integer> tmpListes, ArrayList<String> tmpVille, int iMin, int iMax) {
			//Verification que la position minimale est bien inferieure a la position maximale
			if(iMin < iMax) {
				//Recuperation de la position servant de milieu entre les elements minimaux et maximaux
				int iMilieu = (iMin+iMax)/2;
				//Application du triFusion sur la partie inferieure
				triFusion(tmpListes, tmpVille, iMin, iMilieu);
				//Application du triFusion sur la partie superieure
				triFusion(tmpListes, tmpVille, iMilieu+1, iMax);
				//Reunion des parties inferieures et superieures en une seule liste
				fusion(tmpListes, tmpVille, iMin, iMilieu, iMax);
			}
		}
		
		/*
		 *Methode reunissant les deux parties d'une liste en une seule 
		 * 
		 * @param tmpListes ArrayList d'entier contenant le nombre d'arete de chaque ville de tmpVille
		 * @param tmpVille ArrayList contenant les noeuds avec un nombre de voisins superieur a 1
		 * @param iMin  Position de l'element minimal
		 * @param iMilieu Position de l'element du milieu
		 * @param iMax Position de l'element maximal
		 */
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
			 * On regarde les tetes de chaque liste entre iMin et iMilieu 
			 * Le plus petit est recopie dans tmpListes (donc tmpVille egalement)
			 * puis on incremente celui qui est recopie
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
			 * Cela voudrait dire qu'il reste des elements dans tmp qui n'ont pas encore ete traites et donc mis dans tmpListes
			 */
			if(k <= iMax) {
				//S'il reste des elements dans la premiere partie du tableau tmp alors ils sont recopies dans tmpListes
				while(i <= iMilieu) {
					tmpListes.set(k, tmp.get(i));
					tmpVille.set(k, tmp2.get(i));
					k++;
					i++;
				}
				//S'il reste des elements dans la seconde partie du tableau tmp alors ils sont recopies dans tmpListes
				while(j <= iMax) {
					tmpListes.set(k, tmp.get(j));
					tmpVille.set(k, tmp2.get(j));
					k++;
					j++;
				}
			}
		}
		
		/*
		 * Methode Parcourant la liste des voisins du sommet courant et les retirant de listNoeud et listFeuille
		 * tout en ajoutant les villes retirees dans listNoeudsRetires 
		 * 
		 * @param indiceDeVille Indice du sommet courant
		 * @param tab_voisin ArrayList d'arrayList contenant les voisins de chaque ville
		 * @param listNoeud ArrayList contenant les noeuds avec un nombre de voisins superieur a 1
		 * @param listFeuille ArrayList contenant les noeuds avec les noeuds ne possdant qu'un voisin
		 * @param listNoeudsRetires ArrayList des noeuds qui ont ete retire de listNoeud
		 */
		private static void parcoursVoisin(int indiceDeVille, ArrayList <ArrayList<String>> tab_voisin, ArrayList<String> listNoeud, ArrayList<String> listFeuille, ArrayList<String> listNoeudsRetires) {
			//Retrait des voisins de la ville courante dans listNoeud et listFeuille
			for(int p = 0 ; p < tab_voisin.get(indiceDeVille).size() ; p++) {
				//Premiere boucle for pour comparer la liste des voisins  a la liste des noeuds
				for(int k = 0 ; k < listNoeud.size() ; k++) {
					//Si les voisins de la ville dans laquelle l'ecole vient d'etre rajoutee se trouve dans listNoeud alors on les retire 
					if(tab_voisin.get(indiceDeVille).get(p).equals(listNoeud.get(k))) {
						//Ajoute la ville qui est a retirer de listNoeud dans listNoeudsRetires 
						listNoeudsRetires.add(listNoeud.get(k));
						//Retrait du noeud
						listNoeud.remove(k);
					}
				}
				//Seconde boucle for pour comparer la liste des voisins a la liste des feuilles
				for(int k = 1 ; k < listFeuille.size() ; k++) {
					//Si les voisins de la ville dans laquelle l'ecole vient d'etre rajoutee se trouve dans listFeuille alors on les retire
					if(tab_voisin.get(indiceDeVille).get(p).equals(listFeuille.get(k))) {
						//Retrait du noeud
						listFeuille.remove(k);
					}
				}
			}
		}
		
		/*
		 * Etape 4 et 5 de la resolution automatique
		 * Resume : recherche d'ecole, ajout, d'ecole 
		 */
		/*
		 * Methode ajoutant les ecoles aux villes
		 * 
		 * @param tab_ville ArrayList contenant les villes
		 * @param tab_voisin ArrayList d'arrayList contenant les voisins de chaque ville
		 * @param listNoeud ArrayList contenant les noeuds avec un nombre de voisins superieur a 1
		 * @param listFeuille ArrayList contenant les noeuds avec les noeuds ne possdant qu'un voisin
		 * @param listNoeudsRetires ArrayList des noeuds qui ont ete retire de listNoeud
		 */
		private static void ajoutEcole (ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin, ArrayList<String> listNoeud, ArrayList<String> listFeuille, ArrayList<String> listNoeudsRetires) {
			
			while(!listNoeud.isEmpty()) {

				//Boolean permettant de verifier les changements ayant lieu parmis les ecoles des villes du voisinage present dans listNoeudsRetires
				boolean changement = false;
				for(int i = 0 ; i < listNoeud.size() ; i++) {
					
					//Noeud courant = la ville courante
					String noeudCourant = listNoeud.get(i);
					
					//Indice de la ville courante dans tab_ville et tab_voisin
					int indiceDeVille;
					
					//True si la ville est trouvee, false sinon
					boolean villeTrouvee = false;
					
					//Recherche la ville dans tab_ville de indiceDeVille correspondante a la ville de listNoeud de i
					for(indiceDeVille = 0 ; (indiceDeVille < tab_ville.size()) && !villeTrouvee ; indiceDeVille++) {
						if(tab_ville.get(indiceDeVille).getNom().equals(noeudCourant)) {
							villeTrouvee = true;
							//On retire 1 a indiceDeVille car : indiceDeVille++ donc en sortant de la boucle indiceDeVille aura +1
							indiceDeVille -= 1;
						}
					}
					
					/*
					 * Si la liste de noeuds retires (listNoeudsRetires) est vide alors on ajoute une ecole
					 * a la ville (dans tab_ville) qui est la meme que celle presente dans listNoeud
					 */
					if(listNoeudsRetires.isEmpty()) {
						//Ajout de l'ecole a la ville tab_ville de indiceDeVille 
						tab_ville.get(indiceDeVille).ajoutEcole();

						//Ajout de la ville dans listNoeudsRetires, car on a deja ajoute une ecole a cette ville donc on va la retirer de listNoeud
						listNoeudsRetires.add(noeudCourant);
						
						//Retire les voisins de la ville courante de listNoeud et listFeuille et les ajoute a listNoeudsRetires
						parcoursVoisin(indiceDeVille, tab_voisin, listNoeud, listFeuille, listNoeudsRetires);

						//Et enfin on retire la ville courante de listNoeud
						listNoeud.remove(noeudCourant);
						
						changement = true;			
					}
					
					//Cas ou la liste de noeuds retires n'est pas vide
					else {
						//True si un voisin est trouve, false sinon
						boolean trouverVoisin = false;
						
						//Double for afin de voir si les voisins de listNoeud de i se trouvent dans listNoeudsRetires
						for(int p = 0 ; p < tab_voisin.get(indiceDeVille).size() && !trouverVoisin ; p++) {
							for(int k = 0 ; k < listNoeudsRetires.size() && !trouverVoisin; k++) {
								//Un de ses voisins se trouve dans listNoeudsRetires
								if(tab_voisin.get(indiceDeVille).get(p).equals(listNoeudsRetires.get(k))) {
									trouverVoisin = true;
									//Sortie de boucle car un voisin est trouve
								}
							}
						}
						 
						//Cas ou la ville courante n'a pas de voisin
						if(!trouverVoisin) {
							
							//Ajout d'une ecole a la ville situee a la position j de tab_ville
							tab_ville.get(indiceDeVille).ajoutEcole();
							//Ajout de la ville dans listNoeudsRetires, car une ecole vient d'etre ajoutee
							listNoeudsRetires.add(noeudCourant);
							
							//Retire les voisins de la ville courante de listNoeud et listFeuille et les ajoute a la liste de noeuds retires
							parcoursVoisin(indiceDeVille, tab_voisin, listNoeud, listFeuille, listNoeudsRetires);
							
							//Retrait de la ville courante de listNoeud
							listNoeud.remove(noeudCourant);
							//Passage de changement a true car ajout d'une ville
							changement = true;
						}
						
					} 
					//Cas ou les noeuds n'ont pas ete traite
					if(!changement) {
						//Ajout de l'ecole a la ville tab_ville de j 
						tab_ville.get(indiceDeVille).ajoutEcole();
						//Ajout de la ville dans listNoeudsRetires, car une ecole vient d'etre ajoutee
						listNoeudsRetires.add(noeudCourant);
						
						//Retrait des voisins de la ville courante de listNoeud et listFeuille et les ajoute a la liste de noeuds retires
						parcoursVoisin(indiceDeVille, tab_voisin, listNoeud, listFeuille, listNoeudsRetires);
						
						//Retrait de  la ville courante de listNoeud
						listNoeud.remove(noeudCourant);
					}
				}
			}
			
			//Cas ou il reste des villes sans ecole
			while(!listFeuille.isEmpty()) {
				//True si la ville est trouvee, false sinon
				boolean villeTrouvee = false;
				
				//Parcourir toute la liste de listFeuille
				for(int i = 0 ; i < listFeuille.size() ; i++) {
					String noeudCourant = listFeuille.get(i);
					//Pour trouver l'indiceDeVille,
					for(int indiceDeVille = 0; (indiceDeVille < tab_ville.size()) && !villeTrouvee ; indiceDeVille++) {
						//Cas ou la ville est trouvee
						if(tab_ville.get(indiceDeVille).getNom().equals(noeudCourant)) {
							
							//On recupere le seul voisin de la ville courante donc get(0)
							String nomVilleVoisin = tab_voisin.get(indiceDeVille).get(0);
							
							//True si la ville voisine est trouve dans tab_ville
							boolean voisinTrouve = false;
							
							//Boucle pour trouver le voisin de la ville courante (nomVilleVoisin) dans tab_ville
							for(int j = 0 ; j < tab_ville.size() && !voisinTrouve ; j++) {
								//nomVilleVoisin trouve dans tab_ville
								if(tab_ville.get(j).getNom().equals(nomVilleVoisin)) {
									
									//Si la ville voisine possede une ecole
									if(tab_ville.get(j).getEcole()) {
										//Retrait de la ville courante de listNoeud 
										listFeuille.remove(noeudCourant);
									}
				
									//Cas ou la ville voisine ne possede pas d'ecole
									else {
										//Ajout d'une ecole a la ville voisine
										tab_ville.get(j).ajoutEcole();
										//Cas ou la ville voisine est aussi une feuille
										if(listFeuille.contains(nomVilleVoisin)) {
											//Retrait du voisin
                                            						listFeuille.remove(nomVilleVoisin);
                                        }
										//Retrait de la ville courante de la liste des feuilles
										listFeuille.remove(noeudCourant);
									}
									voisinTrouve = true;
								}
							}
							villeTrouvee = true;
						}
					}
				}
			}
		}
	}
}
