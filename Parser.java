import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	
	public static class VilleParser {
		private static boolean sortie=false;
		public static Ville parser(String fichier, int nbLigne) {
			String nom=null;
			boolean ecole=false;
			try(BufferedReader br=new BufferedReader(new FileReader(fichier))){
				String ligne=null;
				for(int i=0; i<nbLigne; i++) {
					ligne=br.readLine();
				}
				while((ligne=br.readLine())!=null){
					if(ligne.startsWith("ville")){
						if(nom!=null){}
						else{
							nom=ligne.substring(6, (ligne.length()-2));
							ligne=br.readLine();
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
		private static boolean sortie = false;
		public static String parser(String fichier, int nbLignes) {
			String villesaCouper=null;
			
			try (BufferedReader br = new BufferedReader(new FileReader(fichier))){
				String ligne=null;
				for(int i=0; i<nbLignes;i++) {
					ligne=br.readLine();
				}
					while ((ligne=br.readLine())!=null) {
						if(ligne.startsWith("route")){
							if(villesaCouper!=null) {}
							else {
								villesaCouper =ligne.substring(6, ligne.length()-2);
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
			if(villesaCouper==null) {
				//System.err.println("Aucune route n'a ete indique");
				setSortie();
			}
			return villesaCouper;
		}
		private static void setSortie() {
			sortie =true;
		}
		public static boolean getSortie() {
			return sortie;
		}
	}

	public static class EcoleParser {
		private static boolean sortie=false;
		private static String nomVille;
		public static void parser(String fichier, ArrayList<Ville> tab_ville) {
			boolean ecole=false;
			try(BufferedReader br=new BufferedReader(new FileReader(fichier))){
				String ligne=null;
				
				while((ligne=br.readLine())!=null){
					if(ligne.startsWith("ecole")){
						if(ecole!=false){}
						else{
							nomVille=ligne.substring(6, (ligne.length()-2));
							//Parcourir le tableau de villes
							for(int i=0;i<tab_ville.size();i++) {
								if(nomVille.equals(tab_ville.get(i).getNom())) {
									ecole=true;
									tab_ville.get(i).setEcole(ecole);
									System.out.println("Ecole entrée"+tab_ville.get(i));
									ecole=false;
								}
							}
							ligne=br.readLine();
							if(ligne==null) {
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
				if(ecole==true){
					System.out.println("Sortie du parser");
					setSortie();
				}
		}
		
		private static void setSortie() {
			sortie=true;
		}
		public static boolean getSortie() {
			return sortie;
		}
	}
}
