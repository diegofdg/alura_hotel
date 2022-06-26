package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controllers.Coordinador;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class MenuPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JButton btnLogin;
	private JButton btnSalir;
	
	public MenuPrincipal() {
		setBounds(100, 100, 910, 537);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Alura Hotel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
		iniciarComponentes();
	}

	private void iniciarComponentes() {		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(245,245,245));
		panel.setBounds(0, 0, 894, 501);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(-49, 0, 746, 471);
		lblImagen.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/menu-img.png")));
		panel.add(lblImagen);
		
		btnLogin = new JButton("");
		btnLogin.setForeground(SystemColor.text);
		btnLogin.setBackground(Color.white);
		btnLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/login.png")));
		btnLogin.addActionListener(this);
		btnLogin.setBounds(763, 241, 71, 73);
		panel.add(btnLogin);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(12, 138, 199));
		lblLogin.setFont(new Font("Arial", Font.BOLD, 16));
		lblLogin.setBounds(777, 213, 44, 24);
		panel.add(lblLogin);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/aH-150px.png")));
		lblLogo.setBounds(723, 33, 150, 156);
		panel.add(lblLogo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setBounds(0, 471, 894, 30);
		panel.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Desarrollado por Diego Fabián Di Giorgio © 2022");
		lblNewLabel_3.setForeground(new Color(240, 248, 255));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		panel_1.add(lblNewLabel_3);
		
		btnSalir = new JButton("");				
		btnSalir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(this);
		btnSalir.setBounds(832, 420, 44, 39);
		panel.add(btnSalir);		
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			miCoordinador.mostrarLogin();
			miCoordinador.ocultarMenuPrincipal();
		}
		
		if(e.getSource() == btnSalir) {
			Object [] opciones = { "Aceptar", "Cancelar" };
			int eleccion = JOptionPane.showOptionDialog(
				rootPane, 
				"Realmente desea realizar cerrar la aplicacion?",
				"Mensaje de Confirmacion",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opciones,
				"Aceptar"
			);
			if (eleccion == JOptionPane.YES_OPTION) {
				System.exit(0);
			}		
		}
	}
}