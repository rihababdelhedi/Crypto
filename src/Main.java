import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import monoalphabitique.MonoCipter;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
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
	        System.out.println(tableencode); */

	        MonoCipter m = new MonoCipter();
	        String key = m.generateKey();

	        System.out.println("Official key =" + key);
	        
	        System.out.println(getArticle_1());
	        
	        String encoded = m.encode(getArticle_1(), key);
	        
	        System.out.println(encoded);
	        		
	        String keyFounded = m.findKey(encoded);
	        
	        System.out.println("Key founded =" + keyFounded);
	}


	public static String getArticle(String name) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(name));
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static String getArticle_1() {
		return getArticle("txt/article_1");
	}
	
	public static String getArticle_2() {
		return getArticle("txt/article_2");
	}


}
