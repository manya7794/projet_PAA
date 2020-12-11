package projetPartie2_DESPIERRES_WANG_ZHOU;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilitaire {
	static Scanner scan = new Scanner(System.in);
	
	/*
	 * Cette methode cree un tableau de nom  ville a toutes les villes en definissant le nombre total de ville 
	 * grace a la fonction precedente "nombreVille()"
	 * 
	 * @param String [] tab_ville, il faut stocker les noms des villesdans un tableau de String
	 * car les noms de ville peuvent être en lettre alphabetique ou des chaines de caractere
	 */
	public static void nomVille(ArrayList <Ville>tab_ville, String nomFichier) {		
		Ville v;
		boolean sortie = false;
		int nb_ville = 0;
		
		while(!sortie) {
			v = Parser.VilleParser.parser(nomFichier, nb_ville);
			tab_ville.add(nb_ville, v); 
			nb_ville +=1;
			sortie = Parser.VilleParser.getSortie();
		}
		//Affichage de la liste des villes
		System.out.println("Liste des villes : ");
		for(int i=0; i<tab_ville.size(); i++) 
			System.out.println(tab_ville.get(i).toStringNom());
		System.out.println();
	}
	
	public static ArrayList<ArrayList<String>> createListeVoisin(int tailleListe){
		ArrayList<ArrayList<String>> tab_voisin = new ArrayList<ArrayList<String>>();
		//Ajoute le nombre de "ligne" correspondant au nb de ville
		for(int i = 0 ; i < tailleListe; i++) {
			ArrayList<String> addNbVille = new ArrayList <String>();
			tab_voisin.add(addNbVille);
		}
		return tab_voisin;
	}
	
	public static void lireRoute(String nomFichier, ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		boolean sortie =false;
		String route;
		int taille = tab_ville.size();
		while(!sortie) {
			route = Parser.RouteParser.parser(nomFichier, taille);
			taille+=1;
			
			sortie=Parser.RouteParser.getSortie();
			if(!sortie)
			createRoute(route.split(","), tab_ville, tab_voisin);
		}
	}
	
	/*
	 * Ajout les listes de voisins au tab_voisin (liste d'adjacence)
	 * 
	 * @param tab_ville - Ville[] contenant la liste des villes creees au debut
	 * @param tab_voisin - ArrayList <ArrayList<Character>> contenant la liste de tous les voisins de chaque ville
	 */
	public static void createRoute(String []route, ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
					
		String ville_1 = route[0];
		String ville_2 = route[1];
		
		boolean bool_1 = false;
		boolean bool_2 = false;
		
		//Regarde si ville_1 est dans tab_ville
		for(int i = 0 ; (i < tab_ville.size()) && !bool_1 ; i++) {
			if(tab_ville.get(i).getNom().equals(ville_1)) 
				bool_1 = true;
		}
		
		//Regarde si ville_2 est dans tab_ville
		for(int i = 0 ; (i < tab_ville.size()) && !bool_2 ; i++) {
			if(tab_ville.get(i).getNom().equals(ville_2))
				bool_2 = true;
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
			
			
			for(int j = 0 ; j < tab_voisin.get(i).size(); j++) {
				System.out.print(tab_voisin.get(i).get(j) + " ");
			}
			//Saut entre chaque ligne (chaque ville)
			System.out.println();
		}
		System.out.println();
	}
	
	public static void lireEcole(ArrayList<Ville> tab_ville, String nomFichier) {
		
			Parser.EcoleParser.parser(nomFichier, tab_ville);
			
			for(int i=0;i<tab_ville.size();i++){
				System.out.println(tab_ville.get(i).toString());
			}
			//System.out.println("Fin de la lecture des ecoles");
		
	}
	
	/*
	 * Menu principal
	 * 
	 * @param tab_ville la liste des villes
	 * @param tab_voisin la liste des voisins de chaque ville
	 */
	public static void menuPrincipal(ArrayList<Ville> tab_ville, ArrayList <ArrayList<String>> tab_voisin) {
		//Variable permettant la sortie du programme
		boolean sortie = false;
		do {
			//Recuperation des choix
			int option = choixMenuPrincipal();
			
			switch(option) {
			//Resolution manuelle
			case 1 : 
				System.out.println("\nResolution manuelle");
				Resolution.ResolutionManuelle.menuEcole(tab_ville, tab_voisin);
			break;
			//Resolution automatique
			case 2 :
				System.out.println("\nResolution automatique");
				Resolution.ResolutionAutomatique.automatiqueApproximation(tab_ville, tab_voisin);
			break;
			//Sauvegarde	
			case 3 : 
				System.out.println("\nSauvegarde");
				String nomFichier;
				int sauvegarde=choixSauvegarde();
				
				switch (sauvegarde) {
					//Sauvegarde automatique
					case 1:
						System.out.println("\nSauvegarde automatique");
						//Sauvegarde automatique activee
						if(Sauvegarde.getSauvegardeAuto()) {
							Sauvegarde.setSauvegardeAuto();
							System.out.println("Les resultats ne seront plus sauvegardes automatiquement a l'adresse "+Sauvegarde.getfichierSauvegardeAuto());
						}
						//Sauvegarde automatique non activee
						else {
							System.out.println("Ou voulez-vous sauvegarder les resultats ?");
							nomFichier =scan.next();
							Sauvegarde.setfichierSauvegardeAuto(nomFichier);
							Sauvegarde.setSauvegardeAuto();
							System.out.println("Les resultats seront sauvegardes automatiquement a l'adresse "+nomFichier);
						}
					break;
					//Sauvegarde manuelle
					case 2:
						System.out.println("\nSauvegarde manuelle");
						System.out.println("\nOu voulez-vous sauvegarder les resultats ?");
						nomFichier =scan.next();
						sauvegardeFichier(nomFichier, tab_ville, tab_voisin);
						System.out.println("Les resultars sont sauvegardes a l'adresse "+nomFichier);
					break;
				
					default:
						System.out.println("Les resultats n'ont pas ete sauvegarde");
					break;
				}
				break;
				
			//Fin du programme	
			case 4 :
				System.out.println("Fin du programme");
				sortie = true;
				scan.close();
				break;
			default :
				System.out.println("Option invalide, choissisez une autre option");
				break;
			}
		}while(!sortie);
	}
	
	/*
	 * Methode affichant les options disponibles pour le menu principal
	 * et recuperant un entier correspondant au choix
	 * 
	 * @return option le choix effectue par l'utilisateur
	 */
	private static int choixMenuPrincipal() {
		int option;
		do {
			System.out.println("*************Menu principal*************");
			System.out.println("1)Resoudre manuellement");
			System.out.println("2)Resoudre automatiquement");
			System.out.println("3)Sauvegarder");
			System.out.println("4)Quitter");
			System.out.println("*****************************************");
			option = scan.nextInt();
		}while(option>=4 && option<=1);
		return option;
	}
	
	/*
	 * Methode affichant les options disponibles pour la sauvegarde
	 * et recuperant un entier correspondant au choix
	 * 
	 * @return option le choix effectue par l'utilisateur
	 */
	private static int choixSauvegarde() {
		int option;
		do {
			System.out.println("1)Sauvegarder automatiquement");
			System.out.println("2)Sauvegarder manuellement");
			option = scan.nextInt();
		}while(option>=2&&option<=1);
		return option;
	}
	
	/*
	 * Methode effectuant la sauvegarde de la derniere solution dans un fichier
	 * 
	 * @param fichier l'adresse du fichier dans lequel sauvegarder les resultats
	 * @param tab_ville la liste des villes
	 * @param tab_voisin la liste des voisins pour chaque ville
	 */
	public static void sauvegardeFichier(String fichier, ArrayList<Ville> tab_ville,  ArrayList <ArrayList<String>> tab_voisin) {
		try {
			Sauvegarde.SauvegardeVille.sauvegardeVersFichier(fichier, tab_ville);
			Sauvegarde.SauvegardeRoute.sauvegardeVersFichier(fichier, tab_voisin, tab_ville);
			Sauvegarde.SauvegardeEcole.sauvegardeVersFichier(fichier, tab_ville);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}