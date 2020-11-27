import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class VilleParser {
	public static Ville parser(String fichier, int nbVille) {
		String nom=null;
		boolean ecole=false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(fichier))){
			String ligne =null;
			for (int i=0; i<nbVille; i++) {
				while ((ligne=br.readLine())!=null) {
					if(ligne.startsWith("ville")){
						if(nom!=null) {
							System.err.println("Une ville ne peut pas avoir deux noms");
							System.exit(0);
						}
						else {
							nom= ligne.split("(")[1];
							nom.substring(0, nom.length()-2);
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (nom ==null) {
			System.err.println("Le nom de la ville n'a pas été indique");
			System.exit(0);
		}
		return new Ville(nom, ecole);
	}
}
