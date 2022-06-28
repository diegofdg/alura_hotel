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
		
		limpiarTabla(modeloH);
		limpiarTabla(modeloR);
		
		try {						
			listaReservas = miCoordinador.listarReservas();
			llenarTablaReservas(listaReservas);
						
			listaHuespedes = miCoordinador.listarHuespedes();
			llenarTablaHuespedes(listaHuespedes);
						
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
				null,
				"Ha ocurrido un error",
				"Error",
				JOptionPane.ERROR_MESSAGE
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
							null,
							"Ingresa un número para buscar por id",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
						limpiarTabla(modeloH);
						llenarTablaHuespedes(listaHuespedes);
						
					} else {
						try {
					        int id = (int)Double.parseDouble(texto);
					        listaFiltradaHuespedes = miCoordinador.buscarHuespedPorId(id);
					        
					        if(listaFiltradaHuespedes.size() > 0) {
								limpiarTabla(modeloH);
								llenarTablaHuespedes(listaFiltradaHuespedes);
							} else {
								JOptionPane.showMessageDialog(
									null,
									"No existen registros que coincidan con la busqueda ingresada",
									"Error",
									JOptionPane.ERROR_MESSAGE
								);
								limpiarTabla(modeloH);
								llenarTablaHuespedes(listaHuespedes);								
							}
					        
					    } catch(NumberFormatException e1) {
					    	JOptionPane.showMessageDialog(
								null,
								"Debes ingresar un número válido como id",
								"Error",
								JOptionPane.ERROR_MESSAGE
							);
					    	limpiarTabla(modeloH);
							llenarTablaHuespedes(listaHuespedes);
					    	
					    } catch(SQLException e2) {
					    	JOptionPane.showMessageDialog(
								null,
								"Ha ocurrido un error",
								"Error",
								JOptionPane.ERROR_MESSAGE
							);
					    	limpiarTabla(modeloH);
							llenarTablaHuespedes(listaHuespedes);
					    }
					}					
					
				} else if(rdbtnApellido.isSelected() ) {
					if(texto.isEmpty() || texto.length() < 3) {
						JOptionPane.showMessageDialog(
							null,
							"Ingresa al menos tres caracteres para poder buscar",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
						limpiarTabla(modeloH);
						llenarTablaHuespedes(listaHuespedes);
					}
					
					try {
						listaFiltradaHuespedes = miCoordinador.buscarHuespedPorApellido(texto);
				        
						if(listaFiltradaHuespedes.size() > 0) {
							limpiarTabla(modeloH);
							llenarTablaHuespedes(listaFiltradaHuespedes);
						} else {
							JOptionPane.showMessageDialog(
								null,
								"No existen registros que coincidan con la busqueda ingresada",
								"Error",
								JOptionPane.ERROR_MESSAGE
							);													
							limpiarTabla(modeloH);
							llenarTablaHuespedes(listaHuespedes);								
						}
				        
				    } catch(SQLException e1) {
				    	JOptionPane.showMessageDialog(
							null,
							"Ha ocurrido un error",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
				    	limpiarTabla(modeloH);
						llenarTablaHuespedes(listaHuespedes);
				    }
				}
			} else if(panelSeleccionado == 1) {
				if(rdbtnId.isSelected()) {
					
					if(texto.isEmpty()) {
						JOptionPane.showMessageDialog(
							null,
							"Ingresa un número para buscar por id",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
						limpiarTabla(modeloR);
						llenarTablaReservas(listaReservas);
						
					} else {
						try {
					        int id = (int)Double.parseDouble(texto);
					        listaFiltradaReservas = miCoordinador.buscarReservaPorId(id);
					        
					        if(listaFiltradaReservas.size() > 0) {
								limpiarTabla(modeloR);
								llenarTablaReservas(listaFiltradaReservas);								
							} else {
								JOptionPane.showMessageDialog(
									null,
									"No existen registros que coincidan con la busqueda ingresada",
									"Error",
									JOptionPane.ERROR_MESSAGE
								);
								limpiarTabla(modeloR);
								llenarTablaReservas(listaReservas);								
							}
					        
					    } catch(NumberFormatException e1) {
					    	JOptionPane.showMessageDialog(
								null,
								"Debes ingresar un número válido como id",
								"Error",
								JOptionPane.ERROR_MESSAGE
							);
					    	limpiarTabla(modeloR);
							llenarTablaReservas(listaReservas);
					    	
					    } catch(SQLException e2) {
					    	JOptionPane.showMessageDialog(
								null,
								"Ha ocurrido un error",
								"Error",
								JOptionPane.ERROR_MESSAGE
							);
						    limpiarTabla(modeloR);
							llenarTablaReservas(listaReservas);
					    }
					}
					
				} else if(rdbtnApellido.isSelected()) {
					JOptionPane.showMessageDialog(
						null,
						"No es posible buscar por Apellido en la pestaña de Reservas",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
					limpiarTabla(modeloR);
					llenarTablaReservas(listaReservas);
				}
			}	
		}
		
		if(e.getSource() == btnEditar) {
			int panelSeleccionado = panel.getSelectedIndex();
			if(panelSeleccionado == 0) {
				if (tieneFilaElegida(tbHuespedes)) {
					JOptionPane.showMessageDialog(
						null,
						"Por favor, elije un item",
						"Error",
						JOptionPane.ERROR_MESSAGE
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
						
						JOptionPane.showMessageDialog(
							null,
							"Se modificó con éxito",
							"Exito",
							JOptionPane.INFORMATION_MESSAGE
						);
						llenarTablas();
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(
							null,
							"Ha ocurrido un error",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					}											
				}	
				
			} else if(panelSeleccionado == 1) {
				if (tieneFilaElegida(tbReservas)) {
					JOptionPane.showMessageDialog(
						null,
						"Por favor, elije un item",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
					tbHuespedes.clearSelection();
					tbReservas.clearSelection();
					return;
					
				} else {
					try {
						int fila = tbReservas.getSelectedRow();
						Integer id = Integer.valueOf(tbReservas.getValueAt(fila, 0).toString());
						
						String fechaEntrada = tbReservas.getValueAt(fila, 1).toString();						
						java.sql.Date fechaEntradaSql = java.sql.Date.valueOf(fechaEntrada);
						
						String fechaSalida = tbReservas.getValueAt(fila, 2).toString();						
						java.sql.Date fechaSalidaSql = java.sql.Date.valueOf(fechaSalida);
						
						Integer valor = Integer.valueOf(tbReservas.getValueAt(fila, 3).toString());						
						String formaPago = tbReservas.getValueAt(fila, 4).toString();
						
						Reserva nuevaReserva = new Reserva();
						nuevaReserva.setId(id);
						nuevaReserva.setFecha_entrada(fechaEntradaSql);
		            	nuevaReserva.setFecha_salida(fechaSalidaSql);
		            	nuevaReserva.setValor(valor);;
		            	nuevaReserva.setForma_pago(formaPago);
		            	miCoordinador.editarReserva(nuevaReserva);
		            	
		            	JOptionPane.showMessageDialog(
							null,
							"Se modificó con éxito",
							"Exito",
							JOptionPane.INFORMATION_MESSAGE
						);
						llenarTablas();
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(
							null,
							"Ha ocurrido un error",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					}											
				}	
			}
		}
		
		if(e.getSource() == btnEliminar) {
			int panelSeleccionado = panel.getSelectedIndex();
			if(panelSeleccionado == 0) {
				if (tieneFilaElegida(tbHuespedes)) {
					JOptionPane.showMessageDialog(
						null,
						"Por favor, elije un item",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
					tbHuespedes.clearSelection();
					tbReservas.clearSelection();
					return;
				} else {
					try {
						int fila = tbHuespedes.getSelectedRow();
						Integer id = Integer.valueOf(tbHuespedes.getValueAt(fila, 0).toString());
						Object [] opciones = { "Aceptar", "Cancelar" };
						int eleccion = JOptionPane.showOptionDialog(
							rootPane, 
							"Realmente desea eliminar el registro con id: "+id,
							"Mensaje de Confirmacion",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							opciones,
							"Aceptar"
						);
						if (eleccion == JOptionPane.YES_OPTION) {
							String respuesta = miCoordinador.eliminarHuesped(id);
							if(respuesta.equals("ok")) {
								JOptionPane.showMessageDialog(
									null,
									"Se eliminó con éxito",
									"Exito",
									JOptionPane.INFORMATION_MESSAGE
								);
								llenarTablas();
							} else {
								JOptionPane.showMessageDialog(
									null,
									"Ha ocurrido un error",
									"Error",
									JOptionPane.ERROR_MESSAGE
								);								
							}
						}
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(
							null,
							"Ha ocurrido un error",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					}
				}
			} else if(panelSeleccionado == 1) {
				if (tieneFilaElegida(tbReservas)) {
					JOptionPane.showMessageDialog(
						null,
						"Por favor, elije un item",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
					tbHuespedes.clearSelection();
					tbReservas.clearSelection();
					return;
					
				} else {
					try {
						int fila = tbReservas.getSelectedRow();
						Integer id = Integer.valueOf(tbReservas.getValueAt(fila, 0).toString());
						Object [] opciones = { "Aceptar", "Cancelar" };
						int eleccion = JOptionPane.showOptionDialog(
							rootPane, 
							"Realmente desea eliminar el registro con id: "+id,
							"Mensaje de Confirmacion",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							opciones,
							"Aceptar"
						);
						if (eleccion == JOptionPane.YES_OPTION) {
							String respuesta = miCoordinador.eliminarReserva(id);
							if(respuesta.equals("ok")) {
								JOptionPane.showMessageDialog(
									null,
									"Se eliminó con éxito",
									"Exito",
									JOptionPane.INFORMATION_MESSAGE
								);
								llenarTablas();
							} else {
								JOptionPane.showMessageDialog(
									null,
									"Ha ocurrido un error",
									"Error",
									JOptionPane.ERROR_MESSAGE
								);								
							}
						}
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(
							null,
							"Ha ocurrido un error",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					}
				}
			}
		}
		
		if(e.getSource() == btnCancelar) {
			llenarTablas();			
		}
		
		if(e.getSource() == btnSalir) {
			miCoordinador.mostrarMenuUsuario();
			miCoordinador.ocultarBusqueda();			
		}
	}
	
	private boolean tieneFilaElegida(JTable tabla) {
		boolean resultado = tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
		return resultado;
	}

	private int getPanelSeleccionado() {
		return panel.getSelectedIndex();
	}
	
	private void limpiarTabla(DefaultTableModel modelo) {		
		modelo.setRowCount(0);
	}
}