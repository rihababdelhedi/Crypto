package monoalphabitique;

import java.util.HashMap;

public interface  ICipter {

	  String encode(String message, String key);
	  String decode(String crypted, String key);
	  String findKey(String messageEncoded);
	  HashMap<Character, Integer> findFrequency(String text);
}
