package monoalphabitique;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MonoCipter implements ICipter{

	
	private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String orderFrequencyAlaphabet = "ESAITNRULODCPMéVQFBGHJàXYèêZWçùKûïë";
	//"EAISTNRULODMPCVQGBFJHZXYKW";

    public  HashMap<Character, Float> tabFrequence = new HashMap<Character, Float>();
    
	public void generateFreqence()
	{
		tabFrequence.put('e', (float) 14.715);
		tabFrequence.put('s', (float) 7.948);
		tabFrequence.put('a', (float) 7.636);
		tabFrequence.put('i', (float) 7.529);
		tabFrequence.put('t', (float) 7.244);
		tabFrequence.put('n', (float) 7.095);
		tabFrequence.put('r', (float) 6.553);
		tabFrequence.put('u', (float) 6.311);
		tabFrequence.put('l', (float) 5.456);
		tabFrequence.put('o', (float) 5.378);
		tabFrequence.put('d', (float) 3.669);
		tabFrequence.put('c', (float) 3.260);
		tabFrequence.put('p', (float) 3.021);
		tabFrequence.put('m', (float) 2.968);
		tabFrequence.put('é', (float) 1.904);
		tabFrequence.put('v', (float) 1.628);
		tabFrequence.put('q', (float) 1.362);
		tabFrequence.put('f', (float) 1.066);
		tabFrequence.put('b', (float) 0.901);
		tabFrequence.put('g', (float) 0.866);
		tabFrequence.put('h', (float) 0.737);
		tabFrequence.put('j', (float) 0.545);
		tabFrequence.put('à', (float) 0.486);
		tabFrequence.put('x', (float) 0.387);
		tabFrequence.put('y', (float) 0.308);
		tabFrequence.put('è', (float) 0.271);
		tabFrequence.put('ê', (float) 0.225);
		tabFrequence.put('z', (float) 0.136);
		tabFrequence.put('w', (float) 0.114);
		tabFrequence.put('ç', (float) 0.085);
		tabFrequence.put('ù', (float) 0.058);
		tabFrequence.put('k', (float) 0.049);
		tabFrequence.put('û', (float) 0.045);
		tabFrequence.put('ï', (float) 0.006);
		tabFrequence.put('ë', (float) 0);
		
		
	}
	
	
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
