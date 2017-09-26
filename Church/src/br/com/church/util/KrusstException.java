package br.com.church.util;

public class KrusstException extends Exception{

	private static final long serialVersionUID = 2498672509971439344L;
	
	private String message;  
    private String field;  
  
      
    public KrusstException(){  
        super();  
    }  
      
    public KrusstException(String field, String message){  
        this.message = message;  
        this.field = field;  
    }  
    
    public KrusstException(String mensagem, Exception e) {  
        super(mensagem,e);
    }
    
    public KrusstException(String mensagem){  
        super(mensagem);  
    }
      
    public String getMessage() {  
        return message;  
    }  
  
    public String getField() {  
        return field;  
    }  
}
