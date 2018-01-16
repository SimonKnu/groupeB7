package be.helha.groupeB7.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Tools {
	
public static byte[] readFile(String file) {
		
		ByteArrayOutputStream bos = null;
		
		File f = new File(file);
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for(int i; (i = fis.read(buffer)) != -1;)
				bos.write(buffer, 0, i);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos != null ? bos.toByteArray() : null;
		
	}

}
