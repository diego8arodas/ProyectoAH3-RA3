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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

    JTabbedPane menu = new JTabbedPane();
    ClienteDao dao = new ClienteDao();
    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JLabel t1 = new JLabel();
    JLabel t2 = new JLabel();
    JTable tabla1;
    JScrollPane sp;
    private String nombre;
    JLabel nUsuario = new JLabel();
    Color color1 = new Color(0x516FFF);
    Conexion ingresar = new Conexion();
    int estado = 1, rol;
    DefaultTableModel modelo = new DefaultTableModel();
    Object[] usuarios = new Object[6];
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    Login login = new Login();
    Usuarios p = new Usuarios();
    JFrame inicio = new JFrame();
    JPanel p1 = new JPanel();
    JTable tabla = new JTable();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public void Frame(String nombre) {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setMaximumSize(new Dimension(1000, 1000));
        setVisible(true);

        setTitle("Hoteles GT - Usuarios");

        menu.addTab("Tabla de Informacion", panel1);

        menu.addTab("Agregar Usuario", panel2);

        JButton cerrar = new JButton("Salir");
        cerrar.setBackground(Color.red);
        cerrar.setBounds(767, 0, 115, 20);
        this.getContentPane().add(cerrar);
        this.getContentPane().add(menu);

        panel1.setLayout(null);
        panel2.setLayout(null);

        ActionListener cerrar_accion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                login.frame();
            }
        };

        cerrar.addActionListener(cerrar_accion);

        t1.setText("Usuario: ");
        t1.setBounds(600, 10, 100, 20);
        t2.setText(nombre);
        t2.setBounds(670, 10, 200, 20);

        panel1.add(t1);
        panel1.add(t2);

        boton();
        tabla();
        agregar_usuario();
    }

    private void boton() {
        b1.setText("Modificar usuario");
        b1.setBounds(150, 500, 200, 40);
        panel1.add(b1);

        ActionListener modificar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                modificar();
            }
        };

        b1.addActionListener(modificar);
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    private void agregar_usuario() {

        JLabel titulo = new JLabel("Agregar usuario");
        titulo.setFont(new Font("Serig", Font.PLAIN, 25));
        titulo.setBounds(350, 20, 400, 50);

        JLabel label = new JLabel("Nombre: ");
        label.setFont(new Font("Serig", Font.PLAIN, 20));
        label.setBounds(30, 85, 500, 50);
        JTextField text = new JTextField(15);
        text.setBounds(150, 100, 250, 20);

        JLabel label1 = new JLabel("Apellido: ");
        label1.setBounds(450, 85, 500, 50);
        label1.setFont(new Font("Serig", Font.PLAIN, 20));
        JTextField text1 = new JTextField(15);
        text1.setBounds(560, 100, 250, 20);

        JLabel label2 = new JLabel("Teléfono: ");
        label2.setBounds(450, 85, 500, 50);
        label2.setFont(new Font("Serig", Font.PLAIN, 20));
        JTextField text2 = new JTextField(15);
        text2.setBounds(150, 215, 250, 20);

        JLabel label3 = new JLabel("Direccion: ");
        label3.setBounds(450, 200, 500, 50);
        label3.setFont(new Font("Serig", Font.PLAIN, 20));
        JTextField text3 = new JTextField(15);

    }

    public void ejecutar() {

        Frame(nombre);
        tabla();

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
            usuarios[0] = lista.get(i).getUsuario_id();
            usuarios[1] = lista.get(i).getNombre();
            usuarios[2] = lista.get(i).getApellido();
            usuarios[3] = lista.get(i).getRol_id();
            usuarios[4] = lista.get(i).getCorreo();
            usuarios[5] = lista.get(i).getTelefono();
            modelo.addRow(usuarios);
        }

    }

    private void modificar() {
        int fila = tabla1.getSelectedRow();

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
            text4.setText(lista.get(fila).getFecha_nacimiento());
            text5.setText(lista.get(fila).getContraseña());

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

                public void actionPerformed(ActionEvent e) {
                    ClienteDao p = new ClienteDao();
                    //p.modificar(lista.get(fila).getUsuarios_id(), text.getText(), text1.getText(), text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText());
                    JOptionPane.showMessageDialog(null, "Usuario actualizado");
                    text.setText("");
                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                    text4.setText("");
                    text5.setText("");
                    text6.setText("");
                    tabla1.setVisible(false);
                    sp.setVisible(false);
                    tabla();

                    actualizar.setVisible(false);
                }
            };

            b1.addActionListener(guardar);

        }
    }

    private void eliminar() {
        int seleccionar = tabla1.getSelectedRow();

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
            JLabel formatoFecha = new JLabel("Año/Mes/Dia");
            JLabel l7 = new JLabel("Contraseña");

            JTextField T1 = new JTextField();
            JTextField T2 = new JTextField();
            JTextField T3 = new JTextField();
            JTextField T4 = new JTextField();
            JTextField T5 = new JTextField();
            JTextField T6 = new JTextField();
            JPasswordField T7 = new JPasswordField();
            JButton B1 = new JButton("Eliminar");
            JButton B2 = new JButton("Cancelar");

            l0.setFont(new Font("Roboto Black", Font.PLAIN, 22));
            l0.setBounds(175, 10, 250, 25);
            p1.add(l0);

            l1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            l1.setBounds(50, 70, 100, 25);
            p1.add(l1);

            l2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            l2.setBounds(50, 150, 100, 25);
            p1.add(l2);

            l3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            l3.setBounds(50, 250, 125, 25);
            p1.add(l3);

            l4.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            l4.setBounds(50, 350, 100, 25);
            p1.add(l4);

            l5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            l5.setBounds(75, 430, 100, 25);
            p1.add(l5);

            formatoFecha.setFont(new Font("Roboto Black", Font.PLAIN, 22));
            formatoFecha.setBounds(200, 500, 200, 25);
            p1.add(formatoFecha);

            l6.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            l6.setBounds(20, 530, 200, 25);
            p1.add(l6);

            l7.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            l7.setBounds(50, 610, 200, 25);
            p1.add(l7);

            T1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            T1.setBounds(200, 70, 200, 25);
            p1.add(T1);

            T2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            T2.setBounds(200, 150, 200, 25);
            p1.add(T2);

            T3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            T3.setBounds(200, 250, 200, 25);
            p1.add(T3);

            T4.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            T4.setBounds(200, 350, 200, 25);
            p1.add(T4);

            T5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            T5.setBounds(200, 430, 200, 25);
            p1.add(T5);

            T6.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            T6.setBounds(200, 530, 200, 25);
            p1.add(T6);

            T7.setFont(new Font("Roboto Light", Font.PLAIN, 22));
            T7.setBounds(200, 610, 200, 25);
            p1.add(T7);

            B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
            B1.setForeground(Color.white);
            B1.setBackground(color1);
            B1.setBounds(40, 740, 250, 50);
            p1.add(B1);

            B2.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
            B2.setForeground(Color.white);
            B2.setBackground(color1);
            B2.setBounds(300, 740, 150, 50);
            p1.add(B2);

            con = ingresar.conectar();
            String sql = "SELECT nombre,apellido,telefono,direccion,correo_electronico,fecha_nacimiento,contraseña FROM usuarios WHERE usuario_id=? ";

            int id = Integer.parseInt(this.tabla.getValueAt(seleccionar, 0).toString());

            try {

                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {
                    T1.setText(rs.getString(1));
                    T2.setText(rs.getString(2));
                    T3.setText(rs.getInt(3) + "");
                    T4.setText(rs.getString(4));
                    T5.setText(rs.getString(5));
                    T6.setText(rs.getString(6));
                    T7.setText(rs.getString(7));
                    T1.setEditable(false);
                    T2.setEditable(false);
                    T3.setEditable(false);
                    T4.setEditable(false);
                    T5.setEditable(false);
                    T6.setEditable(false);
                    T7.setEditable(false);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            ActionListener cancelar = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    crearUsuarios.setVisible(false);
                    MenuPrincipal mp = new MenuPrincipal();
                    mp.setName(nombre);
                    mp.setRol(Rol1);

                }
            };

            // Acción del evento
            B2.addActionListener(cancelar);

            ActionListener borrar = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminar();
                    crearUsuarios.setVisible(false);
                    MenuPrincipal mp = new MenuPrincipal();
                    mp.setName(nombre);
                    mp.setRol(Rol1);

                }
            };

            // Acción del evento
            B1.addActionListener(borrar);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar a un usuario");
        }

    }
}
