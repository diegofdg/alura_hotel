package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import controllers.Coordinador;
import models.Reserva;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;

public class Reservas extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtValor;
	private Coordinador miCoordinador;
	private JComboBox<String> txtFormaPago;
	private JButton btnContinuar;
	private JButton btnSalir;
	private JDateChooser txtFechaS;
	private JDateChooser txtFechaE;

	public Reservas() {
		setBounds(100, 100, 910, 540);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Alura Hotel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/calendario.png")));
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245,245,245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtFechaE = new JDateChooser();
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setBounds(88, 166, 235, 33);
		panel.add(txtFechaE);
		
		JLabel lblFechaE = new JLabel("Fecha de Check In");
		lblFechaE.setBounds(88, 142, 133, 14);
		lblFechaE.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblFechaE);
		
		JLabel lblFechaS = new JLabel("Fecha de Check Out");
		lblFechaS.setBounds(88, 210, 133, 14);
		lblFechaS.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblFechaS);
		
		txtFechaS = new JDateChooser();
		txtFechaS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularValor(txtFechaE, txtFechaS);
			}
		});		
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.setBounds(88, 234, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaS);
		
		txtValor = new JTextField();
		txtValor.setBounds(88, 303, 235, 33);
		txtValor.setEnabled(false);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor de la Reserva");
		lblValor.setBounds(88, 278, 133, 14);
		lblValor.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblValor);
		
		txtFormaPago = new JComboBox<String>();
		txtFormaPago.setBounds(88, 373, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);
		
		JLabel lblFormaPago = new JLabel("Forma de pago");
		lblFormaPago.setBounds(88, 347, 133, 24);
		lblFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblFormaPago);
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);
		
		btnContinuar = new JButton("Continuar");		
		btnContinuar.setForeground(Color.WHITE);
		btnContinuar.setBounds(88, 436, 140, 33);
		btnContinuar.addActionListener(this);
		btnContinuar.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/calendario.png")));
		btnContinuar.setBackground(new Color(65,105,225));
		btnContinuar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnContinuar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/reservas-img-2.png")));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/Ha-100px.png")));
		lblLogo.setBounds(15, 6, 104, 107);
		panel.add(lblLogo);
		
		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(this);
		btnSalir.setBounds(279, 436, 44, 33);
		panel.add(btnSalir);		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnContinuar) {
			int id_reserva = guardarReserva();
			
			if(id_reserva != 0) {
				miCoordinador.mostrarRegistroHuesped(id_reserva);				
			} else {
				JOptionPane.showMessageDialog(
					this,
					"Ha ocurrido un error!!!"
				);
			}
		}
		
		if(e.getSource() == btnSalir) {
			miCoordinador.mostrarMenuUsuario();
			miCoordinador.ocultarReservas();			
		}
	}

	private int guardarReserva() {
		int resultado = 0;
		
		if(txtFechaE.getDate() != null && txtFechaS.getDate() != null) {
			String fechaE = ((JTextField)txtFechaE.getDateEditor().getUiComponent()).getText();
			String fechaS = ((JTextField)txtFechaS.getDateEditor().getUiComponent()).getText();
			Reserva reserva = new Reserva(
				java.sql.Date.valueOf(fechaE),
				java.sql.Date.valueOf(fechaS),
				Integer.parseInt(txtValor.getText()),
				(String) txtFormaPago.getSelectedItem());
			
			try {
				resultado = miCoordinador.guardarReserva(reserva);
				if(resultado != 0) {
					JOptionPane.showMessageDialog(
						this,
						"Registro guardado correctamente"
					);
					limpiarCampos();					
				}				
				return resultado;
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(
					this,
					"Ha ocurrido un error!!!"
				);
				return resultado;
			}
		}
		return resultado;		
	}

	private void limpiarCampos() {
		this.txtFormaPago.setSelectedIndex(0);
		this.txtValor.setText("");
		this.txtFechaE.setCalendar(null);
		this.txtFechaS.setCalendar(null);		
	}

	private void calcularValor(JDateChooser fechaE, JDateChooser fechaS) {
		if(fechaE.getDate() != null && fechaS.getDate() != null) {
			Calendar inicio = fechaE.getCalendar();
			Calendar fin = fechaS.getCalendar();
			int dias = -1;
			int diaria = 180;
			int valor;
			
			while(inicio.before(fin) || inicio.equals(fin)) {
				dias ++;
				inicio.add(Calendar.DATE,1);
			}
			valor = dias * diaria;
			txtValor.setText(""+valor);
		}
		
	}
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;	
	}
}