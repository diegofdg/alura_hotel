package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controllers.Coordinador;
import models.Huesped;
import models.Reserva;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class Busqueda extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private Coordinador miCoordinador;
	private JButton btnBuscar;
	private JButton btnSalir;
	private JTable tbReservas;
	private DefaultTableModel modeloR;
	private DefaultTableModel modeloH;
	private JScrollPane scrollTablaHuespedes;
	private JScrollPane scrollTablaReservas;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnCancelar;

	public Busqueda() {
		setBounds(100, 100, 910, 516);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Alura Hotel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		btnBuscar.addActionListener(this);
		contentPane.add(btnBuscar);
		
		btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		btnEditar.addActionListener(this);
		contentPane.add(btnEditar);
		
		JLabel lblTitulo = new JLabel("Sistema de Búsqueda");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitulo.setBounds(155, 42, 258, 42);
		contentPane.add(lblTitulo);
		
		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);
		
		scrollTablaHuespedes = new JScrollPane();
		tbHuespedes = new JTable();
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		tbHuespedes.setModel(new DefaultTableModel(
			new Object [][] { },
            new String [] { "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Teléfono", "ID Reserva" }
	    ) {
	        boolean[] canEdit = new boolean [] { true, true, true, true, true, false };
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
	    });
        scrollTablaHuespedes.setViewportView(tbHuespedes);
	    panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), scrollTablaHuespedes);
	    	    
	    scrollTablaReservas = new JScrollPane();
	    tbReservas = new JTable();
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		tbReservas.setModel(new DefaultTableModel(
            new Object [][] { },
            new String [] { "ID Reserva", "Fecha de Entrada", "Fecha de Salida", "Total", "Forma de Pago" }
	    ) {
	        boolean[] canEdit = new boolean [] { false, true, true, true, true };
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
	        }
	    });
		scrollTablaReservas.setViewportView(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), scrollTablaReservas);
				
		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);
		
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblLogo.setBounds(25, 10, 104, 107);
		contentPane.add(lblLogo);	
	}

	public void llenarTablas() {
		modeloR = (DefaultTableModel) tbReservas.getModel();
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		
		try {						
			ArrayList<Reserva> listaReservas = miCoordinador.listarReservas();
			listaReservas.forEach((reserva) -> {
	            modeloR.addRow(
                    new Object[]{	               
                    	reserva.getId(),
                		reserva.getFecha_entrada(),
        				reserva.getFecha_salida(),
        				reserva.getValor(),
        				reserva.getForma_pago(),
                    }
	            );
			 });
			
			
			ArrayList<Huesped> listaHuespedes = miCoordinador.listarHuespedes();
			listaHuespedes.forEach((huesped) -> {
	            modeloH.addRow(
                    new Object[]{	                        
                		huesped.getNombre(),
        				huesped.getApellido(),
        				huesped.getFecha_nacimiento(),
        				huesped.getNacionalidad(),
        				huesped.getTelefono(),
        				huesped.getId_reserva()
                    }
	            );
			 });			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
				this,
				"Ha ocurrido un error!!!"
			);		
		}
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBuscar) {
			System.out.println("Buscando...");			
		}
		
		if(e.getSource() == btnEditar) {
			System.out.println("Editando...");			
		}
		
		if(e.getSource() == btnEliminar) {
			System.out.println("Eliminando...");			
		}
		
		if(e.getSource() == btnCancelar) {
			System.out.println("Cancelando...");			
		}
		
		if(e.getSource() == btnSalir) {
			miCoordinador.mostrarMenuUsuario();
			miCoordinador.ocultarBusqueda();			
		}
	}
}