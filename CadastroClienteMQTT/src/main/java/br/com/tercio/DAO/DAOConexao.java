package br.com.tercio.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConexao implements Serializable{
	private static DAOConexao conexao = null;
	private static Connection connection;
	private String usuario;
	private String senha;
	private String url;
	
	public DAOConexao() {
		usuario = "root";
		senha = "tercio1996";
		url = "jdbc:mysql://localhost:3306/colegio";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, usuario, senha);
			
			System.out.println("Conexão realizada com sucesso!");
		}catch (ClassNotFoundException | SQLException e) {
            System.err.print(e.getMessage());
		}
	}
	
	public static Connection getInstance() {
		if (connection == null) {
			synchronized (DAOConexao.class) {
				conexao = new DAOConexao();
			}
		}
		return connection;
	}
	
	public static void closeInstance() throws SQLException{
		if (connection != null) {
			connection.close();
			System.out.println("Conexão finalizada com sucesso!");
		}
	}
}


