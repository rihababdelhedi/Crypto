package monoalphabitique;

import java.io.File;

public interface  ICipter {

	  String encode(String message, String key);
	  String decode(String crypted, String key);
	
}
