import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RouteParser {
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
						if(villesaCouper!=null) {
						
						}
						else {
							villesaCouper =ligne.substring(6, ligne.length()-2);
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
			System.exit(0);
		}
		return villesaCouper;
	}
	private static void setSortie() {
		sortie =true;
	}
	public boolean getSortie() {
		return sortie;
	}
}
