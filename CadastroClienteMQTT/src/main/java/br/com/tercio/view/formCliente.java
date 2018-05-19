package br.com.tercio.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.tercio.DAO.DAOCliente;
import br.com.tercio.model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;


	


public class formCliente extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtId;
	private JTextField txtRg;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JFormattedTextField formatedTel;
	private JFormattedTextField formatedCpf;


	public formCliente() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 54, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(66, 51, 325, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 101, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(10, 146, 46, 14);
		contentPane.add(lblRg);
		
		txtRg = new JTextField();
		txtRg.setBounds(66, 143, 325, 20);
		contentPane.add(txtRg);
		txtRg.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 193, 46, 14);
		contentPane.add(lblTelefone);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnSalvar.setBounds(149, 271, 89, 23);
		contentPane.add(btnSalvar);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(this);
		
		txtId = new JTextField();
		txtId.setText("NOVO");
		txtId.setEnabled(false);
		txtId.setBounds(66, 8, 325, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		btnNovo.setBounds(10, 271, 89, 23);
		contentPane.add(btnNovo);
		
		formatedCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		formatedCpf.setBounds(66, 98, 325, 20);
		contentPane.add(formatedCpf);
		
		formatedTel = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
		formatedTel.setBounds(66, 190, 325, 20);
		contentPane.add(formatedTel);
	}
	public void actionPerformed(ActionEvent e) {
		
		
		try {
			
		if(e.getSource()==btnNovo)				
		{
			txtId.setEnabled(true);
			txtNome.setEnabled(true);
			formatedCpf.setEnabled(true);
			txtRg.setEnabled(true);
			formatedTel.setEnabled(true);
			
			txtId.setText(" ");
			txtNome.setText(" ");
			formatedCpf.setText(" ");
			txtRg.setText(" ");
			formatedTel.setText(" ");
			
			
			
						
		}
		else if(e.getSource()== btnSalvar)
		{
			btnSalvar_click();
			
			}
		}catch(Exception ex){
			
			
		}
	
		

}





private void btnSalvar_click() throws SQLException, ParseException {
		
		
		
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
				Cliente cli = new Cliente();
				cli.setCpf(formatedCpf.getText().replace(".", " "));
				cli.setNome(txtNome.getText());
				cli.setRg(txtRg.getText());
				cli.setTel(formatedTel.getText().replace("(", " ").replace(")", " ").replace("-", " "));
				
				
				
				
				
				
				txtId.setText(" ");
				txtNome.setText(" ");
				formatedCpf.setText(" ");
				txtRg.setText(" ");
				formatedTel.setText(" ");

				
				
				DAOCliente daoCliente = new DAOCliente();

				/*if (txtId.getText().equals("NOVO")) {
					
				}else {
					cli.setId(Integer.parseInt(txtId.getText()));
					daoCliente.atualizaCliente(cli);*/
				                                         

				if (txtId.getText().equals("NOVO")) {
					
					daoCliente.novoCliente(cli);
					
					JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "ERRO AO SALVAR NO BANCO DE DADOS!", "Sistema",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
}

		
	
	
	public void preencheCadastro(Cliente cli) throws ParseException {
		

		

		txtId.setText(Integer.toString(cli.getId()));
		
		MaskFormatter mask = new MaskFormatter("###.###.###.##");
		mask.setValueContainsLiteralCharacters(false);
		
		formatedCpf.setValue(mask.valueToString(cli.getCpf()));
		txtNome.setText(cli.getNome());
		txtRg.setText(cli.getRg());
		formatedTel.setValue(mask.valueToString(cli.getTel()));
		
	}
	private void btnNovo_click() {
		
		
	}
}



