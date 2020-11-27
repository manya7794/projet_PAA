import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
public class Main {
	public static void main(String [] Args) {
		int nb_ville = 0;
		ArrayList<Ville> tab_ville = new ArrayList<Ville>();
		
		Scanner scan = new Scanner(System.in);
		String nomFichier="ville.txt";
		/*
		 * Premiere etape : Lire le nombre de ville, dans le fichier
		 */
		Utilitaire.nomVille(tab_ville, nomFichier);
		ArrayList<ArrayList<String>> tab_voisin = new ArrayList <ArrayList <String>>(tab_ville.size());
		Utilitaire.lireRoute(nomFichier, nb_ville, tab_ville, tab_voisin);
		
		scan.close();
	}
}