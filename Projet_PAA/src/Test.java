import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList <ArrayList<String>> voisin = new ArrayList <ArrayList<String>>(4);
		
		ArrayList<String> villeA = new ArrayList<String>();
		villeA.add("B");
		villeA.add("C");
		villeA.add("D");
		
		ArrayList<String> villeB = new ArrayList<String>();
		villeB.add("A");
		
		ArrayList<String> villeC = new ArrayList<String>();
		villeC.add("A");
		villeC.add("D");
		
		ArrayList<String> villeD = new ArrayList<String>();
		villeD.add("A");
		villeD.add("C");
		
		voisin.add(villeA);
		voisin.add(villeB);
		voisin.add(villeC);
		voisin.add(villeD);
		
		for(int i = 0 ; i < voisin.size(); i++) {
			for(int j = 0 ; j < voisin.get(i).size(); j++) {
				System.out.print(voisin.get(i).get(j) + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		voisin.get(3).remove(1);
		ArrayList<String> villeAdd = new ArrayList<String>();
		villeAdd.add("F");
		voisin.get(3).addAll(villeAdd);
		voisin.get(0).remove(0);
		voisin.get(0).remove(0);
		voisin.get(0).remove(0);
		
		voisin.get(0).add("F");
	
		
		for(int i = 0 ; i < voisin.size(); i++) {
			for(int j = 0 ; j < voisin.get(i).size(); j++) {
				System.out.print(voisin.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

}
