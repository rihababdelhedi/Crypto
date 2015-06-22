package monoalphabitique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.KeyGenerator;

public class MonoCipter implements ICipter{
	
	final char [] keyBase ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q'};

	@Override
	public void encode(File message, File key, File encoded) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decode(File message, File key, File encoded) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateKey(File key) {
		// TODO Auto-generated method stub
		char alphabet [] = keyBase;
		Random rand =new Random();
		for (int i=0;i<alphabet.length;i++)
		{
			int randomIndex=rand.nextInt(i+1);
			char c =alphabet[i];
			alphabet[i]=alphabet[randomIndex];
			alphabet[randomIndex]=c;
		}
		System.out.print(new String(alphabet));
		try {
			BufferedWriter writer =new BufferedWriter(new FileWriter(key));
			writer.write(new String(alphabet));
			writer.flush();
			writer.close();
			
		}
		catch(Exception e )
		{
		
		}
		}
	

	

}
