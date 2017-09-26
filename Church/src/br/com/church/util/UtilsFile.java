package br.com.church.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class UtilsFile {
	public static InputStream byteToInputStream(byte[] bytes) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);  
        return bais;  
    }  
  
    @SuppressWarnings("resource")
	public static byte[] fileToByte(File imagem) throws Exception {  
        FileInputStream fis = new FileInputStream(imagem);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        byte[] buffer = new byte[8192];  
        int bytesRead = 0;  
        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {  
            baos.write(buffer, 0, bytesRead);  
        }  
        return baos.toByteArray();  
    }  

}
