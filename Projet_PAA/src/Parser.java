package projetPartie2_DESPIERRES_WANG_ZHOU;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	
	public static class VilleParser {
		
		//Attribut
		private static boolean sortie=false;
		
		//Methodes
		/*
		 * Methode renvoyant une ville apres lecture du fichier
		 * 
		 * @param fichier le fichier dans lequel effectuer la lecture
		 * @param nbLigne le nombre de ligne a lire avant d'arriver a la ligne courante
		 * 
		 * @return la ville specifiee par le fichier
		 */
		public static Ville parser(String fichier, int nbLigne) {
			String nom=null;
			boolean ecole=false;
			try(BufferedReader br=new BufferedReader(new FileReader(fichier))){
				//Initialisation de la ligne servant a parcourir le fichier
				String ligne=null;
				//Sauts de lignes necessaires pour arriver a la prochaine ville
				for(int i=0; i<nbLigne; i++) {
					ligne=br.readLine();
				}
				while((ligne=br.readLine())!=null){
					if(ligne.startsWith("ville")){
						if(nom!=null){}
						else{
							//Recuperation du nom de la ville
							nom=ligne.substring(6, (ligne.length()-2));
							//Saut de ligne pour verifier si la recuperation des villes continue
							ligne=br.readLine();
							//Cas ou la recuperation des villes est finie
							if(!ligne.startsWith("ville")) {
								setSortie();
							}
						}
					}
				}
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
				if(nom==null){
					setSortie();
				}
			return new Ville(nom, ecole);
		}
		
		private static void setSortie() {
			sortie=true;
		}
		public static boolean getSortie() {
			return sortie;
		}
	}
	
	public static class RouteParser {
		
		//Attributs
		private static boolean sortie = false;
		
		/*
		 * Methode renvoyant un string contenant les deux villes reliees par une route
		 * 
		 * @param fichier fichier dans lequel chercher les voisins
		 * @param nbLignes nombres de lignes a lire avant d'arriver aux lignes contenant les informations sur les voisins
		 * 
		 * @return villesaCouper les deux voisins a couper
		 */
		public static String parser(String fichier, int nbLignes) {
			String villesaCouper=null;
			
			try (BufferedReader br = new BufferedReader(new FileReader(fichier))){
				//Initialisation de la ligne servant a parcourir le fichier
				String ligne=null;
				//Sauts de lignes necessaires pour arriver aux lignes de declaration des voisins
				for(int i=0; i<nbLignes;i++) {
					ligne=br.readLine();
				}
					while ((ligne=br.readLine())!=null) {
						if(ligne.startsWith("route")){
							if(villesaCouper!=null) {}
							else {
								//Recuperation des deux villes
								villesaCouper =ligne.substring(6, ligne.length()-2);
								//Cas ou la recuperation des routes du fichier est finie
								if(!ligne.startsWith("route")) {
									setSortie();
								}
							}
							
						}
					}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Cas ou aucune route n'a ete definie dans le fichier
			if(villesaCouper==null) {
				setSortie();
			}
			return villesaCouper;
		}
		
		/*
		 * Enclenche la sortie de la boucle
		 */
		private static void setSortie() {
			sortie =true;
		}
		
		/*
		 * Recupere la sortie pour arreter la boucle externe dans l'utilitaire
		 * 
		 * @return sortie true pour arreter la boucle, false pour continuer la boucle
		 */
		public static boolean getSortie() {
			return sortie;
		}
	}

	
	public static class EcoleParser {
		
		//Attributs
		private static String nomVille;
		
		//Methodes
		
		/*
		 * Methode ajoutant les ecoles dans les villes specifiees dans le fichier
		 * 
		 * @param fichier le fichier dans lequel chercher les ecoles
		 * @param tab_ville le tableau contenant la liste des villes presentes
		 */
		public static void parser(String fichier, ArrayList<Ville> tab_ville) {
			
			//Ouverture du fichier
			try(BufferedReader br=new BufferedReader(new FileReader(fichier))){
				//Placement de la ligne de lecture au debut du fichier
				String ligne=br.readLine();
				//Lecture du fichier
				while(ligne!=null) {
					if(ligne.startsWith("ecole")) {
						//Recuperation de la ville
						nomVille=ligne.substring(6, (ligne.length()-2));
						//Recherche de la ville dans le tableau pour l'ajout de l'ecole
						for(int i=0; i<tab_ville.size(); i++) {
							if(tab_ville.get(i).getNom().equals(nomVille)) {
								tab_ville.get(i).ajoutEcole();	
							}
						}
					}
					//Saut de ligne necessaire a la lecture de la ligne suivante
					ligne=br.readLine();
				}
				System.out.println();
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
		}
	}
}
