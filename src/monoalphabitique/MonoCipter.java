package monoalphabitique;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class MonoCipter implements ICipter{

	
	private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
	        	/*if(table.get(Character.toUpperCase(message.charAt(i))) == null)
	        		System.out.println("=" + message.charAt(i)); */
	            stringBuilder.append(table.get(Character.toUpperCase(message.charAt(i))));
	        }

	        return stringBuilder.toString();
	    }


	    @Override
	    public String decode(String crypted, String key) {
	        HashMap<Character,Character> table = buildConversionTable(key, true);
	        StringBuilder stringBuilder = new StringBuilder();

	        for (int i = 0 ; i < crypted.length(); i++){
	            stringBuilder.append(table.get(Character.toUpperCase(crypted.charAt(i))));
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
	    /*
	    public HashMap<Character,Integer> FindFrequences (String message)
	    {
	    	 HashMap<Character,Integer> table = new HashMap<Character, Integer>();
	    	for(int i=0;i<message.length();i++)
	    	{
	    	      table.put(message.charAt(i),nbreFrequence(message.charAt(i),message));
	    	 
	    	}
	    	return table;
	    } */
	 

		@Override 
    public HashMap<Character, Integer> findFrequency(String text) {
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	for(int i=0 ; i < alphabet.length() ; i++){
    		map.put(alphabet.charAt(i), new Integer(0));
    	}
    	
    	for(int i=0 ; i < text.length() ; i++){
    		Integer newValue = map.get(Character.toUpperCase(text.charAt(i))) + 1;
    		map.put(Character.toUpperCase(text.charAt(i)), newValue);
    	}
    	
    	return map;
    }
    
    @Override
    public String findKey(String messageEncoded) {
    	
    	if(messageEncoded == null) {
    		return null;
    	}
    		
		HashMap<Character, Integer> fqrInitialText = findFrequency(messageEncoded); // Frequency initial text
		ValueComparator bvc =  new ValueComparator(fqrInitialText);
        TreeMap<Character,Integer> sorted_map = new TreeMap<Character,Integer>(bvc);
        sorted_map.putAll(fqrInitialText);
        
        int i = 0;
        StringBuilder keyBuilder = new StringBuilder();
        for(Map.Entry<Character,Integer> entry : sorted_map.entrySet()) {
        	Character keyEncoded = entry.getKey();
        	
        	if(i >= orderFrequencyAlaphabet.length())
        		break;
        	
        	Character key = orderFrequencyAlaphabet.charAt(i);
        	
        	keyBuilder.append(keyEncoded);
        	System.out.println(key + " => " + keyEncoded);
        	
        	i++;
        }
		
    	return keyBuilder.toString();
    }
    
    class ValueComparator implements Comparator<Character> {

        Map<Character, Integer> base;
        public ValueComparator(Map<Character, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        public int compare(Character a, Character b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
		
    public void writeFile(String filename, String message){
    	try{
    		File f = new File(filename);
    		//f.createNewFile();
    		FileWriter writer = new FileWriter(f);
    		writer.write(message);
    		writer.close();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
	
}
