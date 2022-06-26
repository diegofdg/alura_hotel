package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controllers.Coordinador;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class MenuUsuario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JButton btnReserva;
	private JButton btnSalir;
	private JButton btnBusqueda;

	public MenuUsuario() {
		setBounds(100, 100, 906, 539);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Alura Hotel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/menu-img.png")));
		lblImagen.setBounds(-56, 0, 741, 471);
		contentPane.add(lblImagen);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/Ha-100px.png")));
		lblLogo.setBounds(724, 32, 104, 107);
		contentPane.add(lblLogo);
		
		btnReserva = new JButton("");
		btnReserva.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservas.png")));
		btnReserva.setForeground(Color.WHITE);
		btnReserva.setBackground(Color.WHITE);
		btnReserva.setBounds(741, 186, 71, 73);
		btnReserva.addActionListener(this);
		contentPane.add(btnReserva);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setBounds(0, 470, 894, 30);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Desarrollado por Diego Fabián Di Giorgio © 2022");
		lblNewLabel_3.setForeground(new Color(12, 138, 199));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		panel_1.add(lblNewLabel_3);
		
		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(823, 411, 50, 47);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		JLabel lblReservas = new JLabel("Reservas");
		lblReservas.setForeground(new Color(12, 138, 199));
		lblReservas.setFont(new Font("Arial", Font.BOLD, 16));
		lblReservas.setBounds(741, 163, 80, 17);
		contentPane.add(lblReservas);
		
		JLabel lblBusqueda = new JLabel("Búsqueda");
		lblBusqueda.setForeground(new Color(12, 138, 199));
		lblBusqueda.setFont(new Font("Arial", Font.BOLD, 16));
		lblBusqueda.setBounds(741, 274, 80, 17);
		contentPane.add(lblBusqueda);
		
		btnBusqueda = new JButton("");
		btnBusqueda.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/busqueda.png")));
		btnBusqueda.setForeground(Color.WHITE);
		btnBusqueda.setBackground(Color.WHITE);
		btnBusqueda.setBounds(741, 302, 71, 73);
		btnBusqueda.addActionListener(this);
		contentPane.add(btnBusqueda);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReserva) {
			miCoordinador.mostrarReservas();
			miCoordinador.ocultarMenuUsuario();
		}
		
		if(e.getSource() == btnSalir) {
			miCoordinador.mostrarLogin();
			miCoordinador.ocultarMenuUsuario();
		}
		
		if(e.getSource() == btnBusqueda) {
			miCoordinador.mostrarBusqueda();
			miCoordinador.ocultarMenuUsuario();
		}
	}
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;		
	}
}