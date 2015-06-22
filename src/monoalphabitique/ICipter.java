package monoalphabitique;

import java.io.File;

public interface  ICipter {

	void encode(File message ,File key,File encoded);
	void decode(File message ,File key ,File encoded);
	void generateKey(File key);
	
}
