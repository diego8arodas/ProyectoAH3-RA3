/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class MenuPrincipal extends JFrame {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion acceso = new Conexion();
    Usuarios pro = new Usuarios();
    JFrame inicio = new JFrame();
    JTabbedPane menu = new JTabbedPane();
    ClienteDao dao = new ClienteDao();
    Conexion ingresar = new Conexion();
    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JLabel t1 = new JLabel();
    JLabel t2 = new JLabel();
    JTable tabla;
    JScrollPane sp = new JScrollPane(tabla);
    int estado = 1, rol;
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    private String nombre;
    Login login = new Login();
    Object[] Usuarios = new Object[6];
    DefaultTableModel modelo = new DefaultTableModel();
    Usuarios p = new Usuarios();
    JPanel p1 = new JPanel();

    public void Frame() {

        inicio.setLocationRelativeTo(null);
        inicio.setTitle("Hoteles GT- Usuarios");

        inicio.setBounds(500, 200, 1000, 700);
        inicio.setVisible(true);
        inicio.setResizable(false);

        inicio.add(p1);
        p1.setSize(1000, 700);
        p1.setLayout(null);
        p1.setVisible(true);
        p1.setBackground(Color.white);

        JLabel l5 = new JLabel("Usuario: " + nombre);
        l5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l5.setBounds(750, 75, 250, 25);
        p1.add(l5);

    }

    public void ejecutar() {
        boton();
        Frame();
        tabla();

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void boton() {

        JButton cerrar = new JButton("Salir");
        cerrar.setBounds(250, 0, 100, 25);
        cerrar.setForeground(Color.BLACK);
        cerrar.setBackground(Color.WHITE);
        cerrar.setFont(new Font("Roboto black", Font.PLAIN, 22));
        p1.add(cerrar);

        ActionListener funcionCerrar;
        funcionCerrar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                inicio.setVisible(false);

                inicio login = new inicio();

                login.ejecutar();

            }
        };

        // Acción del evento
        cerrar.addActionListener(funcionCerrar);

        JButton Modificar = new JButton("Modificar Usuario");
        Modificar.setBounds(200, 600, 250, 25);
        Modificar.setForeground(Color.WHITE);
        Modificar.setFont(new Font("Roboto black", Font.PLAIN, 22));
        p1.add(Modificar);

        ActionListener modificar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                modificar();

            }
        };

        // Acción del evento
        Modificar.addActionListener((ActionListener) Modificar);

        JButton eliminar = new JButton("Eliminar Usuario");
        eliminar.setBounds(500, 600, 250, 25);
        eliminar.setForeground(Color.WHITE);
        eliminar.setFont(new Font("Roboto black", Font.PLAIN, 22));
        p1.add(eliminar);

        ActionListener funcioneliminar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                eliminar();

            }
        };

        // Acción del evento
        eliminar.addActionListener(funcioneliminar);

        JButton Agregar = new JButton("Agregar Usuario");
        Agregar.setBounds(10, 0, 200, 25);
        Agregar.setForeground(Color.BLACK);
        Agregar.setBackground(Color.WHITE);
        Agregar.setFont(new Font("Roboto black", Font.PLAIN, 22));
        p1.add(Agregar);

        ActionListener agregar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                crear();

            }
        };

        // Acción del evento
        Agregar.addActionListener(agregar);

    }

    private void crear() {
        String nombre1 = nombre;
        inicio.setVisible(false);
        JFrame crearUsuarios = new JFrame();
        JPanel p1 = new JPanel();
        crearUsuarios.setLocationRelativeTo(null);
        crearUsuarios.setTitle("Hoteles GT-Administrador");
        crearUsuarios.setBackground(Color.WHITE);

        crearUsuarios.setLayout(null);
        crearUsuarios.setBounds(750, 150, 500, 850);
        crearUsuarios.setVisible(true);
        crearUsuarios.setResizable(false);
        crearUsuarios.add(p1);
        p1.setSize(500, 850);
        p1.setLayout(null);
        p1.setVisible(true);
        p1.setBackground(Color.white);

        ButtonGroup bg = new ButtonGroup();
        JRadioButton activo = new JRadioButton("Activo");
        JRadioButton inactivo = new JRadioButton("Inactivo");
        bg.add(activo);
        bg.add(inactivo);

        activo.setFont(new Font("Serig", Font.PLAIN, 20));
        activo.setBounds(120, 670, 100, 25);
        p1.add(activo);

        inactivo.setFont(new Font("Serig", Font.PLAIN, 20));
        inactivo.setBounds(260, 670, 150, 25);
        p1.add(inactivo);

        JLabel l0 = new JLabel("Agregar Usuario");
        JLabel l1 = new JLabel("Nombre");
        JLabel l2 = new JLabel("Apellido");
        JLabel l3 = new JLabel("Telefono");
        JLabel l4 = new JLabel("Direccion");
        JLabel l5 = new JLabel("Correo");
        JLabel l6 = new JLabel("Fecha Nacimiento");
        JLabel formatoFecha = new JLabel("Año-Mes-Dia");
        JLabel l7 = new JLabel("Contraseña");

        JTextField T1 = new JTextField();
        JTextField T2 = new JTextField();
        JTextField T3 = new JTextField();
        JTextField T4 = new JTextField();
        JTextField T5 = new JTextField();
        JTextField T6 = new JTextField();
        JPasswordField T7 = new JPasswordField();

        JButton b1 = new JButton("Guardar");
        JButton b2 = new JButton("Cancelar");

        l0.setFont(new Font("Serig", Font.PLAIN, 20));
        l0.setBounds(175, 10, 250, 25);
        p1.add(l0);

        l1.setFont(new Font("Serig", Font.PLAIN, 20));
        l1.setBounds(50, 70, 100, 25);
        p1.add(l1);

        l2.setFont(new Font("Serig", Font.PLAIN, 20));
        l2.setBounds(50, 150, 100, 25);
        p1.add(l2);

        l3.setFont(new Font("Serig", Font.PLAIN, 20));
        l3.setBounds(50, 250, 125, 25);
        p1.add(l3);

        l4.setFont(new Font("Serig", Font.PLAIN, 20));
        l4.setBounds(50, 350, 100, 25);
        p1.add(l4);

        l5.setFont(new Font("Serig", Font.PLAIN, 20));
        l5.setBounds(75, 430, 100, 25);
        p1.add(l5);

        formatoFecha.setFont(new Font("Serig", Font.PLAIN, 20));
        formatoFecha.setBounds(200, 500, 200, 25);
        p1.add(formatoFecha);

        l6.setFont(new Font("Serig", Font.PLAIN, 20));
        l6.setBounds(20, 530, 200, 25);
        p1.add(l6);

        l7.setFont(new Font("Serig", Font.PLAIN, 20));
        l7.setBounds(50, 610, 200, 25);
        p1.add(l7);

        T1.setFont(new Font("Serig", Font.PLAIN, 20));
        T1.setBounds(200, 70, 200, 25);
        p1.add(T1);

        T2.setFont(new Font("Serig", Font.PLAIN, 20));
        T2.setBounds(200, 150, 200, 25);
        p1.add(T2);

        T3.setFont(new Font("Serig", Font.PLAIN, 20));
        T3.setBounds(200, 250, 200, 25);
        p1.add(T3);

        T4.setFont(new Font("Serig", Font.PLAIN, 20));
        T4.setBounds(200, 350, 200, 25);
        p1.add(T4);

        T5.setFont(new Font("Serig", Font.PLAIN, 22));
        T5.setBounds(200, 430, 200, 25);
        p1.add(T5);

        T6.setFont(new Font("Serig", Font.PLAIN, 20));
        T6.setBounds(200, 530, 200, 25);
        p1.add(T6);

        T7.setFont(new Font("Serig", Font.PLAIN, 20));
        T7.setBounds(200, 610, 200, 25);
        p1.add(T7);

        b1.setFont(new Font("Serig", Font.PLAIN, 20));
        b1.setForeground(Color.white);
        b1.setBounds(80, 740, 150, 50);
        p1.add(b1);

        b2.setFont(new Font("Serig", Font.PLAIN, 20));
        b2.setForeground(Color.white);
        b2.setBounds(250, 740, 150, 50);
        p1.add(b2);

        ActionListener agregarbd = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (activo.isSelected()) {
                    estado = 1;
                } else if (inactivo.isSelected()) {
                    estado = 0;
                }

                if (T1.getText().isEmpty() || T2.getText().isEmpty() || T3.getText().isEmpty() || T4.getText().isEmpty()
                        || T5.getText().isEmpty() || T6.getText().isEmpty() || T7.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "DATOS INCORRECTOS");
                } else {
                    agregar(T1.getText(), T2.getText(), Integer.parseInt(T3.getText()), T4.getText(), T5.getText(),
                            T6.getText(), rol, estado, T7.getText());
                    JOptionPane.showMessageDialog(null, "SE HA AGREGADO CORRECTAMENTE");
                    crearUsuarios.setVisible(false);
                    MenuPrincipal mp = new MenuPrincipal();
                    mp.setNombre(nombre1);
                    mp.ejecutar();

                }

            }
        };

        // Acción del evento
        b1.addActionListener(agregarbd);

        ActionListener cancelar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuarios.setVisible(false);
                MenuPrincipal mp = new MenuPrincipal();
                mp.setNombre(nombre1);
                mp.ejecutar();
            }
        };

        // Acción del evento
        b2.addActionListener(cancelar);
    }

    private void agregar_usuario() {

        JLabel titulo = new JLabel("Agregar Usuario");
        titulo.setFont(new Font("seriig", Font.PLAIN, 25));
        titulo.setBounds(350, 20, 400, 50);

        JLabel label = new JLabel("Nombre:");
        label.setBounds(30, 85, 500, 50);
        label.setFont(new Font("seriig", Font.PLAIN, 25));
        JTextField text = new JTextField(15);
        text.setBounds(150, 100, 250, 20);

        JLabel label1 = new JLabel("Apellido:");
        label1.setBounds(450, 85, 500, 50);
        label1.setFont(new Font("seriig", Font.PLAIN, 25));
        JTextField text1 = new JTextField(15);
        text.setBounds(560, 100, 250, 20);

        JLabel label2 = new JLabel("Telefono:");
        label2.setBounds(30, 85, 500, 50);
        label2.setFont(new Font("seriig", Font.PLAIN, 25));
        JTextField text2 = new JTextField(15);
        text.setBounds(150, 215, 250, 20);

        JLabel label3 = new JLabel("Direccion:");
        label3.setBounds(450, 200, 500, 50);
        label3.setFont(new Font("seriig", Font.PLAIN, 25));
        JTextField text3 = new JTextField(15);
        text.setBounds(560, 215, 250, 20);

        JLabel label4 = new JLabel("correo:");
        label4.setBounds(450, 200, 500, 50);
        label4.setFont(new Font("seriig", Font.PLAIN, 25));
        JTextField text4 = new JTextField(15);
        text.setBounds(150, 330, 250, 20);

        JLabel label5 = new JLabel("Fecha de Nacimiento:");
        label5.setBounds(450, 315, 500, 50);
        label.setFont(new Font("seriig", Font.PLAIN, 25));
        JTextField text5 = new JTextField(15);
        text5.setText("[dd/mm/yy]");
        text.setBounds(150, 445, 250, 20);

        JLabel label6 = new JLabel("Contraseña: ");
        label6.setBounds(30, 85, 500, 50);
        label6.setFont(new Font("Serig", Font.PLAIN, 20));
        JTextField text6 = new JTextField(15);
        text6.setBounds(150, 100, 250, 20);

        List<Usuarios> lista = dao.listarusuario();

    }

    private void tabla() {
        modelo.addColumn("No.");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Rol");
        modelo.addColumn("Correo");
        modelo.addColumn("Telefono");
        tabla.setModel(modelo);
        sp.setBounds(50, 150, 900, 400);
        p1.add(sp);

        List<Usuarios> lista = dao.listarusuario();

        for (int i = 0; i < lista.size(); i++) {
            Usuarios[0] = lista.get(i).getUsuario_id();
            Usuarios[1] = lista.get(i).getNombre();
            Usuarios[2] = lista.get(i).getApellido();
            Usuarios[3] = lista.get(i).getRol_id();
            Usuarios[4] = lista.get(i).getCorreo();
            Usuarios[5] = lista.get(i).getTelefono();
            modelo.addRow(Usuarios);
        }

    }

    private void modificar() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        } else {
            JFrame actualizar = new JFrame();
            JPanel p1 = new JPanel();
            p1.setLayout(null);
            actualizar.setSize(900, 650);
            actualizar.setLocationRelativeTo(null);
            actualizar.setMaximumSize(new Dimension(800, 100));
            actualizar.setVisible(true);
            actualizar.setTitle("Actualizar usuario");
            actualizar.getContentPane().add(p1);

            JLabel titulo = new JLabel("Modificar usuario");
            titulo.setFont(new Font("Serig", Font.PLAIN, 25));
            titulo.setBounds(350, 20, 400, 50);
            JLabel label = new JLabel("Nombre: ");
            label.setBounds(30, 85, 500, 50);
            label.setFont(new Font("Serig", Font.PLAIN, 20));
            JTextField text = new JTextField(15);
            text.setBounds(150, 100, 250, 20);

            JLabel label1 = new JLabel("Apellido: ");
            label1.setBounds(450, 85, 500, 50);
            label1.setFont(new Font("Serig", Font.PLAIN, 20));
            JTextField text1 = new JTextField(15);
            text1.setBounds(560, 100, 250, 20);

            JLabel label2 = new JLabel("Telefono: ");
            label2.setBounds(30, 200, 500, 50);
            label2.setFont(new Font("Serig", Font.PLAIN, 20));
            JTextField text2 = new JTextField(15);
            text2.setBounds(150, 215, 250, 20);

            JLabel label3 = new JLabel("Direccion: ");
            label3.setBounds(30, 85, 500, 50);
            label3.setFont(new Font("Serig", Font.PLAIN, 20));
            JTextField text3 = new JTextField(15);
            text3.setBounds(150, 100, 250, 20);

            JLabel label4 = new JLabel("Correo: ");
            label4.setBounds(30, 85, 500, 50);
            label4.setFont(new Font("Serig", Font.PLAIN, 20));
            JTextField text4 = new JTextField(15);
            text4.setBounds(150, 100, 250, 20);

            JLabel label5 = new JLabel("Fecha de nacimiento: ");
            label5.setBounds(30, 85, 500, 50);
            label5.setFont(new Font("Serig", Font.PLAIN, 20));
            JTextField text5 = new JTextField(15);
            text5.setBounds(150, 100, 250, 20);

            JLabel label6 = new JLabel("Contraseña: ");
            label6.setBounds(30, 85, 500, 50);
            label6.setFont(new Font("Serig", Font.PLAIN, 20));
            JTextField text6 = new JTextField(15);
            text6.setBounds(150, 100, 250, 20);

            List<Usuarios> lista = dao.listarusuario();

            text.setText(lista.get(fila).getNombre());
            text1.setText(lista.get(fila).getApellido());
            text2.setText("" + lista.get(fila).getTelefono());
            text3.setText(lista.get(fila).getDireccion());
            text4.setText(lista.get(fila).getCorreo());
            text5.setText(lista.get(fila).getFecha_nacimiento());
            text5.setText(lista.get(fila).getContraseña());
            text6.setText("" + lista.get(fila).getUsuario_id());

            p1.add(label1);
            p1.add(text1);

            p1.add(label2);
            p1.add(text2);

            p1.add(label3);
            p1.add(text3);

            p1.add(label4);
            p1.add(text4);

            p1.add(label5);
            p1.add(text5);

            p1.add(label6);
            p1.add(text6);

            b1 = new JButton();
            b2 = new JButton();

            b1.setText("Guardar");
            b1.setBounds(150, 500, 200, 40);
            p1.add(b1);

            b2.setText("Cancelar");
            b2.setBounds(500, 500, 200, 40);
            p1.add(b2);

            ActionListener cancelar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    text.setText("");
                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                    text4.setText("");
                    text5.setText("");
                    text6.setText("");

                    actualizar.setVisible(false);
                }
            };

            b2.addActionListener(cancelar);

            ActionListener guardar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClienteDao f = new ClienteDao();

                    JOptionPane.showMessageDialog(null, "Usuario actualizado");
                    text.setText("");
                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                    text4.setText("");
                    text5.setText("");
                    text6.setText("");
                    tabla.setVisible(false);
                    sp.setVisible(false);
                    //tabla();

                    actualizar.setVisible(false);
                }
            };

            b1.addActionListener(guardar);

        }
    }

    private void eliminar(int id) {

        int seleccionar = tabla.getSelectedRow();

        if (seleccionar != -1) {
            String nombre1 = nombre;
            int Rol1 = rol;
            inicio.setVisible(false);
            JFrame crearUsuarios = new JFrame();
            JPanel p1 = new JPanel();
            crearUsuarios.setLocationRelativeTo(null);
            crearUsuarios.setTitle("Hoteles GT-Administrador");
            crearUsuarios.setBackground(Color.WHITE);

            crearUsuarios.setLayout(null);
            // x y ancho y alto
            crearUsuarios.setBounds(750, 150, 500, 850);
            crearUsuarios.setVisible(true);
            crearUsuarios.setResizable(false);
            crearUsuarios.add(p1);
            p1.setSize(500, 850);
            p1.setLayout(null);
            p1.setVisible(true);
            p1.setBackground(Color.white);

            JLabel l0 = new JLabel("Eliminar Usuario");
            JLabel l1 = new JLabel("Nombre");
            JLabel l2 = new JLabel("Apellido");
            JLabel l3 = new JLabel("Telefono");
            JLabel l4 = new JLabel("Direccion");
            JLabel l5 = new JLabel("Correo");
            JLabel l6 = new JLabel("Fecha Nacimiento");
            JLabel formatoFecha = new JLabel("Año-Mes-Dia");
            JLabel l7 = new JLabel("Contraseña");

            JTextField t1 = new JTextField();
            JTextField t2 = new JTextField();
            JTextField t3 = new JTextField();
            JTextField t4 = new JTextField();
            JTextField t5 = new JTextField();
            JTextField t6 = new JTextField();
            JPasswordField t7 = new JPasswordField();
            JButton b1 = new JButton("Eliminar");
            JButton b2 = new JButton("Cancelar");

            l0.setFont(new Font("Serig", Font.PLAIN, 20));
            l0.setBounds(175, 10, 250, 25);
            p1.add(l0);

            l1.setFont(new Font("Serig", Font.PLAIN, 20));
            l1.setBounds(50, 70, 100, 25);
            p1.add(l1);

            l2.setFont(new Font("Serig", Font.PLAIN, 20));
            l2.setBounds(50, 150, 100, 25);
            p1.add(l2);

            l3.setFont(new Font("Serig", Font.PLAIN, 20));
            l3.setBounds(50, 250, 125, 25);
            p1.add(l3);

            l4.setFont(new Font("Serig", Font.PLAIN, 20));
            l4.setBounds(50, 350, 100, 25);
            p1.add(l4);

            l5.setFont(new Font("Serig", Font.PLAIN, 20));
            l5.setBounds(75, 430, 100, 25);
            p1.add(l5);

            formatoFecha.setFont(new Font("Serig", Font.PLAIN, 20));
            formatoFecha.setBounds(200, 500, 200, 25);
            p1.add(formatoFecha);

            l6.setFont(new Font("Serig", Font.PLAIN, 20));
            l6.setBounds(20, 530, 200, 25);
            p1.add(l6);

            l7.setFont(new Font("Serig", Font.PLAIN, 20));
            l7.setBounds(50, 610, 200, 25);
            p1.add(l7);

            t1.setFont(new Font("Serig", Font.PLAIN, 20));
            t1.setBounds(200, 70, 200, 25);
            p1.add(t1);

            t2.setFont(new Font("Serig", Font.PLAIN, 20));
            t2.setBounds(200, 150, 200, 25);
            p1.add(t2);

            t3.setFont(new Font("Serig", Font.PLAIN, 20));
            t3.setBounds(200, 250, 200, 25);
            p1.add(t3);

            t4.setFont(new Font("Serig", Font.PLAIN, 20));
            t4.setBounds(200, 350, 200, 25);
            p1.add(t4);

            t5.setFont(new Font("Serig", Font.PLAIN, 20));
            t5.setBounds(200, 430, 200, 25);
            p1.add(t5);

            t6.setFont(new Font("Serig", Font.PLAIN, 20));
            t6.setBounds(200, 530, 200, 25);
            p1.add(t6);

            t7.setFont(new Font("Serig", Font.PLAIN, 20));
            t7.setBounds(200, 610, 200, 25);
            p1.add(t7);

            b1.setFont(new Font("Serig", Font.PLAIN, 20));
            b1.setForeground(Color.white);

            b1.setBounds(40, 740, 250, 50);
            p1.add(b1);

            b2.setFont(new Font("Serig", Font.PLAIN, 20));
            b2.setForeground(Color.white);
            b2.setBounds(300, 740, 150, 50);
            p1.add(b2);

            con = ingresar.conectar();
            String sql = "SELECT nombre,apellido,telefono,direccion,correo_electronico,fecha_nacimiento,contraseña FROM usuarios WHERE usuario_id=? ";

            try {

                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {
                    t1.setText(rs.getString(1));
                    t2.setText(rs.getString(2));
                    t3.setText(rs.getInt(3) + "");
                    t4.setText(rs.getString(4));
                    t5.setText(rs.getString(5));
                    t6.setText(rs.getString(6));
                    t7.setText(rs.getString(7));
                    t1.setEditable(false);
                    t2.setEditable(false);
                    t3.setEditable(false);
                    t4.setEditable(false);
                    t5.setEditable(false);
                    t6.setEditable(false);
                    t7.setEditable(false);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            ActionListener cancelar = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    crearUsuarios.setVisible(false);
                    MenuPrincipal mp = new MenuPrincipal();
                    mp.setNombre(nombre1);
                    mp.ejecutar();
                }
            };

            // Acción del evento
            b2.addActionListener(cancelar);

            ActionListener borrar = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminar(id);
                    crearUsuarios.setVisible(false);
                    MenuPrincipal mp = new MenuPrincipal();
                    mp.setNombre(nombre1);
                    mp.ejecutar();
                }
            };

            // Acción del evento
            b1.addActionListener(borrar);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar a un usuario");
        }

    }
}
