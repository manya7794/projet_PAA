package projetPartie2_DESPIERRES_WANG_ZHOU;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Sauvegarde {
	//Attributs
	private static boolean sauvegardeAuto= false;
	private static String fichierSauvegardeAuto;

	//Methodes
	
	public static boolean getSauvegardeAuto() {
		return sauvegardeAuto;
	}
	
	public static void setSauvegardeAuto() {
		if(sauvegardeAuto)
			sauvegardeAuto=false;
		else
			sauvegardeAuto=true;
	}
	
	public static String getfichierSauvegardeAuto() {
		return fichierSauvegardeAuto;
	}
	
	public static void setfichierSauvegardeAuto(String nomFichier) {
		fichierSauvegardeAuto=nomFichier;
	}
	
	//Sous-classes
	public static class SauvegardeVille  {
		/*
		 * Sauvegarde de l'ensemble des villes dans un fichier
		 * 
		 * @param fichier le fichier dans lequel l'utilisateur va sauvegarder les villes
		 * @param tab_ville tableau contenant les villes
		 */
		public static void sauvegardeVersFichier(String fichier, ArrayList<Ville> tab_ville) throws FileNotFoundException, IOException{
			
			File fichier1 = new File(fichier);
			//Ouverture de l'acces en ecriture
			try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichier1))){
				//Creation de la ligne de lecture
				String ligneFichier=null;
				PrintWriter pW = new PrintWriter(bw);
					for(int i=0; i<tab_ville.size();i++) {
						//Ecriture de la ligne dans le fichier
						ligneFichier=tab_ville.get(i).toStringSauvegardeVille();
						pW.write(ligneFichier+"\n");	
					}
			}
		}
	}
	
	public static class SauvegardeRoute {
		/*
		 * Sauvegarde l'ensemble des routes dans un fichier(en evitant les doublons)
		 * 
		 * @param fichier le fichier dans lequel l'utilisateur va sauvegarder les villes
		 * @param tab_ville tableau contenant les villes 
		 * @param tab_voisin tableau contenant les voisins de toutes les villes
		 */
		public static void sauvegardeVersFichier(String fichier,  ArrayList <ArrayList<String>> tab_voisin, ArrayList<Ville> tab_ville) throws FileNotFoundException, IOException {
			//Ouverture de l'acces en ecriture
			File fichier1 = new File(fichier);
			try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichier1, true))){
				//Ouverture de l'acces en lecture
				PrintWriter pW = new PrintWriter(bw);
				try(BufferedReader br=new BufferedReader(new FileReader(fichier1))){
					//Creation de la ligne de lecture
					String ligneFichier=null;
					//Creation du tableau permettant d'eviter les doublons lors de la creation des routes
					ArrayList<Ville> villesVisitees= new ArrayList<Ville>();
					
					//Contenu du tableau principal
					for(int i=0; i<tab_voisin.size();i++) {
						//Creation de la ligne
						StringBuilder ligneTmp= new StringBuilder("route(");
						//Ajout dans la ligne de la premiere ville depuis le tableau principal
						ligneTmp.append(tab_ville.get(i).toStringNom());
						//Ajout de la ville dans le tableau des villes deja visitees
						villesVisitees.add(tab_ville.get(i));
						
						//Contenu du sous-tableau
						for(int j=0;j<tab_voisin.get(i).size();j++) {
							//Ajout dans la ligne de la seconde ville depuis le sous-tableau
							ligneTmp.append(","+tab_voisin.get(i).get(j).toString()+").");
							//Variable permettant de savoir si la seconde ville doit etre prise en compte
							boolean skip=false;
							for(int k=0;k<villesVisitees.size();k++) {
								//Ville deja visitee dans le tableau principal
								if(villesVisitees.get(k).getNom().equals(tab_voisin.get(i).get(j)))
									skip=true;
							}
							if(skip==false){
								
								while((ligneFichier=br.readLine())!=null) {
									//Visite des lignes pour arriver a la fin du fichier
									ligneFichier=br.readLine();
								}
								//Ecriture de la ligne dans le fichier
								ligneFichier=ligneTmp.toString();
								pW.write(ligneFichier+"\n");
							}
							//Suppression de la seconde ville 
							ligneTmp.delete(ligneTmp.lastIndexOf(tab_voisin.get(i).get(j).toString())-1, ligneTmp.length());
							}
						}
					System.out.println();
				}
			}
		}
	}
	
	public static class SauvegardeEcole {
		/*
		 * Sauvegarde l'ensemble des ecoles dans un fichier
		 * 
		 * @param fichier le fichier dans lequel l'utilisateur va sauvegarder les villes
		 * @param tab_ville tableau contenant les villes 
		 */
		public static void sauvegardeVersFichier(String fichier, ArrayList<Ville> tab_ville)throws FileNotFoundException, IOException {
			File fichier1 = new File(fichier);
			//Ouverture de l'acces en ecriture
			try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichier1, true))){
				//Ouverture de l'acces en lecture
				try(BufferedReader br=new BufferedReader(new FileReader(fichier1))){
					//Creation de la ligne de lecture
					String ligneFichier=null;
					PrintWriter pW = new PrintWriter(bw);
					for(int i=0;i<tab_ville.size();i++) {
						if(tab_ville.get(i).getEcole()) {
							while((ligneFichier=br.readLine())!=null) {
								//Visite des lignes pour arriver a la fin du fichier
								ligneFichier=br.readLine();
							}
							ligneFichier="ecole("+tab_ville.get(i).getNom()+").";
							pW.write(ligneFichier+"\n");
						}
							
					}
				}
			}
		}
	}
}
