import java.util.Arrays;
import java.util.Scanner;

public class Utilitaire {
	static Scanner scan = new Scanner(System.in);
	/*
	 * Cette méthode créer un tableau de nom  ville a toutes les villes en définissant le nombre total de ville 
	 * grâce à la fonction précédente "nombreVille()"
	 * 
	 * @param String [] tab_ville, il faut stocker les noms des villesdans un tableau de String
	 * car les noms de ville peuvent être en lettre alphabétique ou des chaines de caracère
	 */
	public static void nomVille(Ville []tab_ville) {
		char ascii = 65;
		for(int i = 0; i<tab_ville.length; i++) {
			char nom = (char) ascii;
			tab_ville[i]= new Ville(nom);
			//affichage temporaire le nom de ville (à retirer plus tard)
			System.out.println("Ville "+tab_ville[i].getNom());
			ascii++;
		}

	}
	/*
	 * Cette méthode doit afficher un menu pour les routes
	 * relier deux ville par une route l'option 1 et quitter l'option 2
	 * 
	 * @param Ville[] tab_ville, lorsque nous saisissons deux noms de ville nous devons vérifier 
	 * si cette ville existant le tableau de nom de ville, 
	 * s'il n'existe pas nous pouvons pas relier ses deux villes
	 */
	public static void menuRoute(Ville[] tab_ville) {
		boolean b = true; 	

		do {
			System.out.println("Menu route :");
			System.out.println("1) Ajouter une route");
			System.out.println("2) Fin");
			int option = scan.nextInt();
			/*
			 * Fonctionnement apres avoir fait le choix du menu*/
			switch(option) {
			case 1 : 
				/*
				 * Saisir deux nom de ville ou nous voulions ajouter une route 
				 * */
				System.out.println("Saisissez une ville");
				char ville_1 = scan.next().charAt(0);
				System.out.println("Saisissez une autre ville");
				char ville_2 = scan.next().charAt(0);
				/*
				 * Rechercher un nom de ville dans le un tableau de ville
				 * s'il le trouve il retourne vrai sinon false
				 * */
				boolean bool_1 = false;
				boolean bool_2 = false;
				for(int i = 0; i<tab_ville.length && (!bool_1); i++) {
					if(tab_ville[i].getNom()==ville_1) {
						bool_1=true;
					}
				}
				for(int i = 0; i<tab_ville.length && (!bool_2); i++) {
					if(tab_ville[i].getNom()==ville_2) {
						bool_2=true;
					}
				}

				if(!bool_1 && !bool_2) {
					System.err.println("L'un des deux villes est inexistante");
				}
				else {
					System.out.println("Les deux villes sont existante dans le tableau ");
				}
				break;
			case 2 : 
				b = false;
				break;
			default : System.err.println("Commande invalide, choissisez une option");
				break;
			}
		}while(b);
	}
	
	public static void menuEcole(Ville[] tab_ville) {
		boolean b = true;
		do {
			System.out.println("Menu d'école :");
			System.out.println("1) Ajouter une école");
			System.out.println("2) Retirer une école");
			System.out.println("3) Fin");

			int option = scan.nextInt();

			switch(option) {
			case 1 :
				System.out.println("Saisissez la ville où vous voulez créer l'école");
				char ecole_1= scan.next().charAt(0);
				Ville ville_ecole_1 = new Ville(ecole_1);
				/*
				 * Rechercher un nom de ville dans le un tableau de ville
				 * s'il le trouve il retourne vrai sinon false
				 * */
				boolean bool_1 = false;
				for(int i = 0; i<tab_ville.length && (!bool_1); i++) {
					if(tab_ville[i].getNom()==ecole_1) {
						bool_1=true;
					}
				}
				if(!bool_1) {
					//Verifie si la ville saisie est une ville dans qui existe dans le tableau de ville
					System.err.println("La ville n'existe pas");
				}
				else if (bool_1 && ville_ecole_1.getEcole()) {
					//Verifie que l'ecole exite, et verifie s'il y a deja une ecole ou non
					System.err.println("Cette ville a deja une ecole");
				}
				else {
					//Methode qui ajoute une ecole dans une ville, qui change la variable boolean ecole en true
					//Nous ne pourrons plus creer d'ecole dans cette ville 
					ville_ecole_1.ajoutEcole();
					System.out.println("Une ecole a ete ajouter dans la ville de "+ville_ecole_1.getNom());
				}
				break;
			case 2 : 
				System.out.println("Saisissez la ville où vous voulez creer l'ecole");
				char ecole_2 = scan.next().charAt(0);
				Ville ville_ecole_2 = new Ville(ecole_2);
				/*
				 * Rechercher un nom de ville dans le un tableau de ville
				 * s'il le trouve il retourne vrai sinon false
				 * */
				boolean bool_2 = false;
				for(int i = 0; i<tab_ville.length && (!bool_2); i++) {
					if(tab_ville[i].getNom()==ecole_2) {
						bool_2=true;
					}
				}
				if(!bool_2){
					//Verifie si la ville saisie est une ville dans qui existe dans le tableau de ville
					System.err.println("La ville n'existe pas");
				}
				else if(Arrays.asList(tab_ville).contains(ville_ecole_2) && !ville_ecole_2.getEcole()){
					//Verifie que l'ecole exite, et verifie s'il y a deja une ecole ou non
					System.err.println("Cette ville n'a pas d'ecole");
				}	
				else {
					//Methode qui retire l'ecole d'une ville, qui change la variable boolean ecole en false
					//Nous pouvons a nouveau creer une ecole dans cette ville
					ville_ecole_2.retireEcole();
					System.out.println("L'ecole a ete retirer de la ville de"+ ville_ecole_2.getNom());
				}
				break;
			case 3 : 
				b = false;
				break;
			default : System.out.println("Commande invalide, choissisez une option");
				break;
			}
		}while(b);
		scan.close();
	}

}
