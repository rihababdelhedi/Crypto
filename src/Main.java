import java.io.File;
import java.util.HashMap;

import monoalphabitique.ICipter;
import monoalphabitique.MonoCipter;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  MonoCipter m = new MonoCipter();
	        String key = m.generateKey();
	        m.writeFile("macle.txt", key);
	        System.out.println(key);
	        String s = m.encode("MONOALPHABITIQUE.", key);
	        m.writeFile("encoded.txt", s);
	        System.out.println(s);
	        System.out.println(m.decode(s, key));
	        HashMap<Character, Integer> table =m.FindFrequences("ARRRRAJJJEE");
	        HashMap<Character, Integer> tableencode =m.FindFrequences("EFFFFECCCTT");
	        System.out.println(table);
	        System.out.println(tableencode);
	}

}
