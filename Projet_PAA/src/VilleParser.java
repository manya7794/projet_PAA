import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class VilleParser {
	private static boolean sortie=false;
	public static Ville parser(String fichier, int nbLigne) {
		String nom=null;
		boolean ecole=false;
		try(BufferedReader br=new BufferedReader(
			new FileReader(fichier))){
	String ligne=null;
	for(int i=0; i<nbLigne; i++) {
		ligne=br.readLine();
	}
	while((ligne=br.readLine())!=null){
		if(ligne.startsWith("ville")){
		if(nom!=null){
			
		}else{
		nom=ligne.substring(6, (ligne.length()-2));
		}
		}
			}}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
			e.printStackTrace();
			}
			if(nom==null){
			//System.err.println("Le nom n’a pas ete indique");
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
