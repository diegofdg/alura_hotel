package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controllers.Coordinador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private Coordinador miCoordinador;
	private JButton btnLogin;
	private JButton btnCancelar;
	
	public Login() {
		setBounds(100, 100, 700, 538);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Alura Hotel");		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/perfil-del-usuario.png")));
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/hotel.png")));
		lblNewLabel.setBounds(-53, 0, 422, 499);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(409, 181, 234, 33);
		contentPane.add(txtUsuario);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Usuario");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(409, 156, 57, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(409, 261, 234, 33);
		contentPane.add(txtContrasena);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(409, 236, 133, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/imagenes/perfil-del-usuario.png")));
		btnLogin.addActionListener(this);
		btnLogin.setBounds(409, 322, 103, 33);
		contentPane.add(btnLogin);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/imagenes/cerrar-24px.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(540, 322, 103, 33);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\Ha-100px.png"));
		lblNewLabel_1.setBounds(470, 30, 103, 94);
		contentPane.add(lblNewLabel_1);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			String usuario = txtUsuario.getText();
			String password = String.valueOf(txtContrasena.getPassword());
			boolean loginExitoso;
			try {
				loginExitoso = miCoordinador.verificarLogin(usuario, password);
				if(loginExitoso) {
					miCoordinador.mostrarMenuUsuario();
					miCoordinador.ocultarLogin();
									
				} else {
					JOptionPane.showMessageDialog(
						null,
						"El usuario o la contraseña son incorrectos",
						"Advertencia",
						JOptionPane.WARNING_MESSAGE
					);				
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(
						null,
						"Ha ocurrido un error, intente nuevamente",
						"Advertencia",
						JOptionPane.WARNING_MESSAGE
					);
				e1.printStackTrace();
			}			
		}
		
		if(e.getSource() == btnCancelar) {
			miCoordinador.mostrarMenuPrincipal();
			miCoordinador.ocultarLogin();
		}
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;		
	}	
}