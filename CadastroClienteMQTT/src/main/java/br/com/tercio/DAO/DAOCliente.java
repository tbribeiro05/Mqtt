package br.com.tercio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.tercio.model.Cliente;

public class DAOCliente {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoCliente(Cliente cli) throws SQLException {
		SQL = "INSERT INTO Cliente (Nome, Rg, Cpf, Telefone"
				+ ") VALUES (?, ?, ?, ?)";
		System.out.println(SQL);
		System.out.println(cli.getNome() + "," + cli.getCpf() + "," + cli.getRg()+ "," +
		cli.getTel());
		
		preparedStatement =  DAOConexao.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, cli.getNome());
		preparedStatement.setString(2, cli.getRg());
		preparedStatement.setString(3, cli.getCpf());
		preparedStatement.setString(4, cli.getTel());
		
		
		preparedStatement.execute();
		System.out.println("Cadastro de aluno realizado com sucesso!");
	}
}



