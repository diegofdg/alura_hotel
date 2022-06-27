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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/perfil-del-usuario.png")));
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JLabel lblImagen = new JLabel("New label");
		lblImagen.setIcon(new ImageIcon(Login.class.getResource("/imagenes/hotel.png")));
		lblImagen.setBounds(-53, 0, 422, 499);
		contentPane.add(lblImagen);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(409, 181, 234, 33);
		contentPane.add(txtUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsuario.setBounds(409, 156, 57, 14);
		contentPane.add(lblUsuario);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(409, 261, 234, 33);
		contentPane.add(txtContrasena);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
		lblContrasena.setBounds(409, 236, 133, 14);
		contentPane.add(lblContrasena);
		
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
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/Ha-100px.png")));
		lblLogo.setBounds(470, 30, 103, 94);
		contentPane.add(lblLogo);		
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
					limpiarCampos();
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
			}			
		}
		
		if(e.getSource() == btnCancelar) {
			miCoordinador.mostrarMenuPrincipal();
			miCoordinador.ocultarLogin();
		}
	}

	private void limpiarCampos() {
		this.txtUsuario.setText("");
		this.txtContrasena.setText("");		
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;		
	}	
}