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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.JRadioButton;

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
	private ArrayList<Reserva> listaReservas;
	private ArrayList<Reserva> listaFiltradaReservas;
	private ArrayList<Huesped> listaHuespedes;
	private ArrayList<Huesped> listaFiltradaHuespedes;
	private JTabbedPane panel;
	private ButtonGroup grupoDeRadios;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnApellido;

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
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);
		
		scrollTablaHuespedes = new JScrollPane();
		tbHuespedes = new JTable();
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		tbHuespedes.setModel(new DefaultTableModel(
			new Object [][] { },
            new String [] { "Id", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Teléfono", "ID Reserva" }
	    ) {
	        boolean[] canEdit = new boolean [] { false, true, true, true, true, true, false };
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
		
		grupoDeRadios = new ButtonGroup();
		
		rdbtnId = new JRadioButton("ID Reserva");
		rdbtnId.setBounds(414, 89, 109, 23);
		rdbtnId.setSelected(true);
		grupoDeRadios.add(rdbtnId);
		contentPane.add(rdbtnId);
		
		rdbtnApellido = new JRadioButton("Apellido");
		rdbtnApellido.setBounds(532, 89, 109, 23);
		grupoDeRadios.add(rdbtnApellido);
		contentPane.add(rdbtnApellido);
	}

	public void llenarTablas() {
		modeloR = (DefaultTableModel) tbReservas.getModel();
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		
		try {						
			listaReservas = miCoordinador.listarReservas();
			llenarTablaReservas(listaReservas);
						
			listaHuespedes = miCoordinador.listarHuespedes();
			llenarTablaHuespedes(listaHuespedes);
						
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
				this,
				"Ha ocurrido un error!!!"
			);		
		}
	}

	private void llenarTablaHuespedes(ArrayList<Huesped> listaHuespedes) {
		listaHuespedes.forEach((huesped) -> {
            modeloH.addRow(
                new Object[]{	                        
            		huesped.getId(),
            		huesped.getNombre(),
    				huesped.getApellido(),
    				huesped.getFecha_nacimiento(),
    				huesped.getNacionalidad(),
    				huesped.getTelefono(),
    				huesped.getId_reserva()
                }
            );
		 });
		
	}

	private void llenarTablaReservas(ArrayList<Reserva> listaReservas) {
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
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnBuscar) {
			String texto = txtBuscar.getText();
			int panelSeleccionado = getPanelSeleccionado();
			
			if(panelSeleccionado == 0) {
				
				if(rdbtnId.isSelected()) {
					
					if(texto.isEmpty()) {
						JOptionPane.showMessageDialog(
							this,
							"Ingresa un número para buscar por id"
						);
						limpiarTabla("tabla huespedes");
						llenarTablaHuespedes(listaHuespedes);
						
					} else {
						try {
					        int id = (int)Double.parseDouble(texto);
					        listaFiltradaHuespedes = miCoordinador.buscarHuespedPorId(id);
					        
					        if(listaFiltradaHuespedes.size() > 0) {
								limpiarTabla("tabla huespedes");
								llenarTablaHuespedes(listaFiltradaHuespedes);
							} else {
								JOptionPane.showMessageDialog(
										this,
										"No existen registros que coincidan con la busqueda ingresada"
										);						
								limpiarTabla("tabla huespedes");
								llenarTablaHuespedes(listaHuespedes);								
							}
					        
					    } catch(NumberFormatException e1) {
					    	JOptionPane.showMessageDialog(
								this,
								"Debes ingresar un número válido como id"
					    	);
					    	limpiarTabla("tabla huespedes");
							llenarTablaHuespedes(listaHuespedes);
					    	
					    } catch(SQLException e2) {
					    	JOptionPane.showMessageDialog(
								this,
								"Ha ocurrido un error"
							);
					    	limpiarTabla("tabla huespedes");
							llenarTablaHuespedes(listaHuespedes);
					    }
					}					
					
				} else if(rdbtnApellido.isSelected() ) {
					if(texto.isEmpty() || texto.length() < 3) {
						JOptionPane.showMessageDialog(
							this,
							"Ingresa al menos tres caracteres para poder buscar"
						);
						limpiarTabla("tabla huespedes");
						llenarTablaHuespedes(listaHuespedes);
					}
					
					try {
						listaFiltradaHuespedes = miCoordinador.buscarHuespedPorApellido(texto);
				        
						if(listaFiltradaHuespedes.size() > 0) {
							limpiarTabla("tabla huespedes");
							llenarTablaHuespedes(listaFiltradaHuespedes);
						} else {
							JOptionPane.showMessageDialog(
								this,
								"No existen registros que coincidan con la busqueda ingresada"
							);						
							limpiarTabla("tabla huespedes");
							llenarTablaHuespedes(listaHuespedes);								
						}
				        
				    } catch(SQLException e1) {
				    	JOptionPane.showMessageDialog(
				    			this,
				    			"Ha ocurrido un error"
				    			);
				    	limpiarTabla("tabla huespedes");
						llenarTablaHuespedes(listaHuespedes);
				    }					
				}				
			} else if(panelSeleccionado == 1) {
				if(rdbtnId.isSelected()) {
					
					if(texto.isEmpty()) {
						JOptionPane.showMessageDialog(
							this,
							"Ingresa un número para buscar por id"
						);
						limpiarTabla("tabla reservas");
						llenarTablaReservas(listaReservas);
						
					} else {
						try {
					        int id = (int)Double.parseDouble(texto);
					        listaFiltradaReservas = miCoordinador.buscarReservaPorId(id);
					        
					        if(listaFiltradaReservas.size() > 0) {
								limpiarTabla("tabla reservas");
								llenarTablaReservas(listaFiltradaReservas);								
							} else {
								JOptionPane.showMessageDialog(
										this,
										"No existen registros que coincidan con la busqueda ingresada"
										);						
								limpiarTabla("tabla reservas");
								llenarTablaReservas(listaReservas);								
							}
					        
					    } catch(NumberFormatException e1) {
					    	JOptionPane.showMessageDialog(
					    			this,
					    			"Debes ingresar un número válido como id"
					    			);
					    	limpiarTabla("tabla reservas");
							llenarTablaReservas(listaReservas);
					    	
					    } catch(SQLException e2) {
					    	JOptionPane.showMessageDialog(
					    			this,
					    			"Ha ocurrido un error"
					    			);
					    	limpiarTabla("tabla reservas");
							llenarTablaReservas(listaReservas);
					    }
					}
					
				} else if(rdbtnApellido.isSelected()) {
					JOptionPane.showMessageDialog(
							this,
							"No es posible buscar por Apellido en la pestaña de Reservas"
							);
					limpiarTabla("tabla reservas");
					llenarTablaReservas(listaReservas);
				}
			}	
		}
		
		if(e.getSource() == btnEditar) {
			int panelSeleccionado = panel.getSelectedIndex();
			if(panelSeleccionado == 0) {
				if (tieneFilaElegida(tbHuespedes)) {
					JOptionPane.showMessageDialog(
						this,
						"Por favor, elije un item"
					);
					tbHuespedes.clearSelection();
					tbReservas.clearSelection();
					return;
					
				} else {
					try {
						int fila = tbHuespedes.getSelectedRow();
						Integer id = Integer.valueOf(tbHuespedes.getValueAt(fila, 0).toString());
						String nombre = tbHuespedes.getValueAt(fila, 1).toString();
						String apellido = tbHuespedes.getValueAt(fila, 2).toString();
						
						String nacimiento = tbHuespedes.getValueAt(fila, 3).toString();
						java.sql.Date fechaSql = java.sql.Date.valueOf(nacimiento);				        
						
						String nacionalidad = tbHuespedes.getValueAt(fila, 4).toString();
						String telefono = tbHuespedes.getValueAt(fila, 5).toString();
						Integer id_reserva = Integer.valueOf(tbHuespedes.getValueAt(fila, 6).toString());
						Huesped nuevoHuesped = new Huesped();
						nuevoHuesped.setId(id);
						nuevoHuesped.setNombre(nombre);
		            	nuevoHuesped.setApellido(apellido);
		            	nuevoHuesped.setFecha_nacimiento(fechaSql);
		            	nuevoHuesped.setNacionalidad(nacionalidad);
		            	nuevoHuesped.setTelefono(telefono);
		            	nuevoHuesped.setId_reserva(id_reserva);
						miCoordinador.editarHuesped(nuevoHuesped);
						JOptionPane.showMessageDialog(this, "Se modificó con éxito");
						
					} catch (SQLException e1) {						
						e1.printStackTrace();
					}											
				}	
				
			} else if(panelSeleccionado == 1) {
				System.out.println("Editando Reservas");
			}
	        
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

	private java.sql.Date convertirDateASqlDate(java.util.Date fecha) {
		// Convertir Date a String
		SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");		
        String fechaFormateada = formatoSalida.format(fecha);
        
        // Convertir String a Sql Date
        java.sql.Date fechaSql = java.sql.Date.valueOf(fechaFormateada);
        return fechaSql;
	}
	
	private java.util.Date convertirStringADate(String fecha) {
		java.util.Date fechaDate = null;		
		
		try {
			// Convertir String a java.util.Date
			SimpleDateFormat formatoDate = new SimpleDateFormat("yyyy-MM-dd");
			fechaDate = formatoDate.parse(fecha);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fechaDate;		
	}

	private boolean tieneFilaElegida(JTable tabla) {
		boolean resultado = tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
		return resultado;
	}

	private int getPanelSeleccionado() {
		return panel.getSelectedIndex();
	}
	
	private void limpiarTabla(String tabla) {
		if(tabla.equals("tabla huespedes")) {
			modeloH.setRowCount(0);			
		} else if(tabla.equals("tabla reservas")) {		
			modeloR.setRowCount(0);
		}
	}
}