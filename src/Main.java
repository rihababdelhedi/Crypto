import java.io.File;

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
	        System.out.println(key);
	        String s = m.encode("MONOALPHABITIQUE.", key);
	        System.out.println(s);
	        System.out.println(m.decode(s, key));

	}

}
