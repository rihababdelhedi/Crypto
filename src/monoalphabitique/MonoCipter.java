package monoalphabitique;

<<<<<<< HEAD
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
=======
import java.awt.image.ConvolveOp;
import java.util.ArrayList;
>>>>>>> 87dd8d9bb5ef0ca2b792e124fe102bba905424bb
import java.util.HashMap;
import java.util.List;

public class MonoCipter implements ICipter{

	
	private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + " .,;:\"'";

	    public String generateKey(){

	        char[] charTable = alphabet.toCharArray();
	        int currentIndex = charTable.length, randomIndex;
	        char temporaryValue;


	        while (0 != currentIndex) {

	            randomIndex = (int) Math.floor(Math.random() * currentIndex);
	            currentIndex -= 1;

	            // And swap it with the current element.
	            temporaryValue = charTable[currentIndex];
	            charTable[currentIndex] = charTable[randomIndex];
	            charTable[randomIndex] = temporaryValue;
	        }

	        return new String(charTable);
	    }

	    private HashMap<Character, Character> buildConversionTable(String key, boolean reverse){
	    	// HaspMap valeur et leur key 
	        HashMap<Character,Character> table = new HashMap<Character, Character>();
	        for(int i=0 ; i < alphabet.length() ; i++){
	        	// pour de décodage 
	            if(reverse){
	                table.put(key.charAt(i), alphabet.charAt(i));
	            }else{
	            	// encodage 
	                table.put(alphabet.charAt(i), key.charAt(i));
	            }
	        }
	        return table;
	    }

	    @Override
	    public String encode(String message, String key) {
	    	
	        HashMap<Character,Character> table = buildConversionTable(key, false);
	        StringBuilder stringBuilder = new StringBuilder();

	        for (int i = 0 ; i < message.length(); i++){
	            stringBuilder.append(table.get(message.charAt(i)));
	        }

	        return stringBuilder.toString();
	    }

	    @Override
	    public String decode(String crypted, String key) {
	        HashMap<Character,Character> table = buildConversionTable(key, true);
	        StringBuilder stringBuilder = new StringBuilder();

	        for (int i = 0 ; i < crypted.length(); i++){
	            stringBuilder.append(table.get(crypted.charAt(i)));
	        }

	        return stringBuilder.toString();
	    }
	    
	    public int nbreFrequence(char caractere,String message)
	    {
	    	int nbre=0;
	    	for(int i=0;i<message.length();i++)
	    	{
	    		if (message.charAt(i)==caractere)
	    	     nbre++;
	    	}
	    	return nbre ;
	    }
	    
	    public HashMap<Character,Integer> FindFrequences (String message)
	    {
	    	 HashMap<Character,Integer> table = new HashMap<Character, Integer>();
	    	for(int i=0;i<message.length();i++)
	    	{
	    	      table.put(message.charAt(i),nbreFrequence(message.charAt(i),message));
	    	 
	    	}
	    	return table;
	    }
	 

		@Override
		public String findKey(String message, String crtypted) {
			// TODO Auto-generated method stub
			
			
			return null;
		}
	    
>>>>>>> 87dd8d9bb5ef0ca2b792e124fe102bba905424bb
}
