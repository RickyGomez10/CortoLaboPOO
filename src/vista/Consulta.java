/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.ProductoDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.producto;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {

    public JLabel lblnombre, lblcodigo, lblprecio, lblcantidad, lbldisponibilidad, lbltipo, lblexistencia, lblid;
    public JTextField nombre, codigo, precio, cantidad, id;
    public JComboBox tipo;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        //llenarTabla();

        Container container = getContentPane();
        container.add(lblnombre);
        container.add(lblcodigo);
        container.add(lblprecio);
        container.add(lblcantidad);
        container.add(lbldisponibilidad);
        container.add(lbltipo);
        container.add(lblid);

        container.add(codigo);
        container.add(nombre);
        container.add(precio);
        container.add(cantidad);
        container.add(id);
        container.add(tipo);

        container.add(buscar);
        container.add(eliminar);
        container.add(insertar);
        container.add(limpiar);
        container.add(table);
        container.add(actualizar);
        setSize(600, 600);
        eventos();

    }

    public final void agregarLabels() {
        lblid = new JLabel("ID");
        lblnombre = new JLabel("Nombre");
        lblcodigo = new JLabel("codigo");
        lblprecio = new JLabel("Precio");
        lbltipo = new JLabel("Tipo");
        lblcantidad = new JLabel("Cantidad");
        lbldisponibilidad = new JLabel("Disponibilidad");
        lblexistencia = new JLabel("Stock en tienda");

        lblid.setBounds(300, 10, ANCHOC, ALTOC);
        lblnombre.setBounds(10, 10, ANCHOC, ALTOC);
        lblcodigo.setBounds(10, 50, ANCHOC, ALTOC);
        lblprecio.setBounds(10, 90, ANCHOC, ALTOC);
        lbltipo.setBounds(10, 140, ANCHOC, ALTOC);
        lblcantidad.setBounds(10, 180, ANCHOC, ALTOC);
        lblexistencia.setBounds(10, 220, ANCHOC, ALTOC);

    }

    public final void formulario() {
        id = new JTextField();
        nombre = new JTextField();
        codigo = new JTextField();
        precio = new JTextField();
        cantidad = new JTextField();
        tipo = new JComboBox();
        si = new JRadioButton();
        no = new JRadioButton();
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        tipo.addItem("Fruta");
        tipo.addItem("Verdura");
        tipo.addItem("Dulce");
        tipo.addItem("Bebida");

        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        id.setBounds(250, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 10, ANCHOC, ALTOC);
        codigo.setBounds(140, 50, ANCHOC, ALTOC);
        precio.setBounds(140, 90, ANCHOC, ALTOC);
        tipo.setBounds(140, 140, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);
        cantidad.setBounds(140, 180, ANCHOC, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return int.class;
                    case 1:
                        return String.class;
                    case 2:
                        return int.class;
                    case 3:
                        return String.class;
                    case 4:
                        return int.class;
                    case 5:
                        return double.class;

                    default:
                        return int.class;

                }

            }

        };
        tm.addColumn("Id");
        tm.addColumn("Nombre");
        tm.addColumn("Codigo");
        tm.addColumn("tipo");
        tm.addColumn("cantidad");
        tm.addColumn("precio");
        tm.addColumn("Disponibilidad");

        ProductoDao fd = new ProductoDao();
        ArrayList<producto> productos = fd.readAll();

        for (producto fi : productos) {
            tm.addRow(new Object[]{fi.getId(), fi.getNombre(), fi.getCodigo(), fi.getTipo(), fi.getCantidad(), fi.getPrecio(), fi.getDisponibilidad()});

        }
        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                producto f = new producto(Integer.parseInt(id.getText()), nombre.getText(), Integer.parseInt(codigo.getText()), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Integer.parseInt(precio.getText()), 1);
                if (no.isSelected()) {
                    f.setDisponibilidad(0);
                }
                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Producto creado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el Producto");
                }
            }

        });
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                producto f = new producto(Integer.parseInt(id.getText()), nombre.getText(), Integer.parseInt(codigo.getText()), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Integer.parseInt(precio.getText()), 1);
                if (no.isSelected()) {
                    f.setDisponibilidad(0);
                }
                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Producto Modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el Producto");
                }
            }

        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                if (fd.delete(id.getText())) {
                    JOptionPane.showMessageDialog(null, "Producto Eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el Producto");
                }
            }

        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                producto f = fd.read(id.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado");

                } else {
                    id.setText(Integer.toString(f.getId()));
                    nombre.setText(f.getNombre());
                    codigo.setText(Integer.toString(f.getCodigo()));
                    tipo.setSelectedItem(f.getTipo());
                    cantidad.setText(Integer.toString(f.getCantidad()));

                    if (f.getDisponibilidad() == 1) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);

                    }
                }

            }

        });
        limpiar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        id.setText("");
        nombre.setText("");
        codigo.setText("");
        cantidad.setText("");
        precio.setText("");
        tipo.setSelectedItem("Fruta");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });

    }

}
