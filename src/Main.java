import java.io.File;

import monoalphabitique.ICipter;
import monoalphabitique.MonoCipter;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MonoCipter icipter =new MonoCipter();
		icipter.generateKey(new File("key.txt"));
		icipter.encode(new File("message.txt"), new File("key.txt"), new File("encoded.txt"));
		icipter.decode(new File("message.txt"), new File("key.txt"), new File("encoded.txt"));
		

	}

}
