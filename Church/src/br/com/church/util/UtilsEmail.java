package br.com.church.util;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class UtilsEmail {

	public static void sendEmail() throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		// Quando a porta utilizada não é a padrão (gmail = 465)smtp
		// pop 995
		email.setSmtpPort(465);
		email.addTo("victorpradodegodoy09@gmail.com", "Victor Prado"); // destinatário
		email.setFrom("me@teste.org", "Me"); // remetente quem esta enviando
		email.setSubject("Mensagem de Teste"); // assunto do e-mail
		email.setMsg("Teste de Email utilizando commons-email"); // conteudo do
																	// e-mail
		email.setSSLOnConnect(true);
		email.setAuthentication("fatectggfc@gmail.com", "x8pdkv19");
		email.send(); // envia o e-mail
	}

	public static void sendEmail(String destinatario, String nomeDestinatario,
			String remetente, String nomeRemetente, String assunto,
			String mensagem) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.addTo(destinatario, nomeDestinatario); // destinatário
		if(remetente != null){
			email.setFrom(remetente, nomeRemetente); // remetente quem esta enviando
		}else{
			email.setFrom("me@cfg.org", "Me"); // remetente quem esta enviando
		}
		email.setSubject(assunto); // assunto do e-mail
		email.setMsg(mensagem); // conteudo do email
		email.setSSLOnConnect(true);
		email.setAuthentication("fatectggfc@gmail.com", "x8pdkv19");
		email.send(); // envia o e-mail
	}

	public static void sendEmailConfirmation(String destinatario,
			String nomeDestinatario, String remetente, String nomeRemetente,
			String assunto, String mensagem) throws EmailException,
			MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.addTo(destinatario, nomeDestinatario); // destinatário
		if(remetente != null){
			email.setFrom(remetente, nomeRemetente); // remetente quem esta enviando
		}else{
			email.setFrom("me@cfg.org", "Me"); // remetente quem esta enviando
		}
		
		email.setSubject(assunto); // assunto do e-mail
		// adiciona uma imagem ao corpo da mensagem e retorna seu id
//		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//		String cid = email.embed(url, "Apache logo");
		// configura a mensagem para o formato HTML
		email.setHtmlMsg("<html> " +
						" <head> " +
						" <title>Confirmacao Conta</title> " +
						" </head> " +
						" <body> " 
						+ " <p> "+mensagem+"  </p>"
						+ " <a href=\"http://localhost:8080/ControleFinanceiro/index.jsf\">Confirmacao da Sua Conta</a> " +
						" </body> " +
						" </html> ");
		
		// envia o e-mail
		email.setSSLOnConnect(true);
		email.setAuthentication("fatectggfc@gmail.com", "x8pdkv19");
		email.send();
	}

}
