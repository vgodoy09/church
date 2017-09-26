package br.com.church.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;

public class Util {
	
	public static EntityManager criaConexao()
            throws ClassNotFoundException{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("church");
			EntityManager em = null;
            if(em == null){
            	em = factory.createEntityManager();
            }
            return em;
    }
	
	public static Connection criaConexaoPostgresql()
            throws ClassNotFoundException, SQLException {
            // Definir o driver de conexão.
            Class.forName("org.postgresql.Driver");
            // Cria uma conexão.
            String url = "jdbc:postgresql://127.0.0.1:5432/church";
            Connection conexao = null;
            if(conexao == null){
                conexao = DriverManager.getConnection(url, "postgres", "x8pdkv19");
            }
            return conexao;
    }
	
	public static Connection criaConexaoMySql()
            throws ClassNotFoundException, SQLException {
            // Definir o driver de conexão.
            Class.forName("com.mysql.jdbc.Driver");
            // Cria uma conexão.
            String url = "jdbc:mysql://127.0.0.1:3306/church";
//            String url = "jdbc:mysql://mysql117150-chuchmissionary.jelasticlw.com.br/church";
            Connection conexao = null;
            if(conexao == null){
                conexao = DriverManager.getConnection(url, "root", "x8pdkv19");
//                conexao = DriverManager.getConnection(url, "root", "lolbQ5FAdm");
            }
            return conexao;
    }
}
			