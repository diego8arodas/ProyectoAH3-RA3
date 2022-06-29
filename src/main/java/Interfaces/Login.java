/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author diego
 */
public class Login extends JFrame {

    JLabel nombre = new JLabel();
    JLabel contraseña = new JLabel();
    JTextField nom_ingresar = new JTextField();
    JPasswordField con_ingresar = new JPasswordField();
    JPanel p1 = new JPanel();
    JButton b1 = new JButton();
    int oportunidades = 3;

    ClienteDao dao = new ClienteDao();

    public void frame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setMaximumSize(new Dimension(1000, 1000));
        paneles();

    }

    private void paneles() {

        p1.setLayout(null);
        p1.setSize(300, 300);

        p1.setBackground(Color.WHITE);
        this.getContentPane().add(p1);
        etiquetas();
        botones();
        cajastexto();

    }

    private void etiquetas() {

        nombre.setText("Nombre");
        nombre.setBounds(20, 50, 80, 20);

        contraseña.setText("Contraseña");
        contraseña.setBounds(20, 150, 100, 20);

        p1.add(nombre);
        p1.add(contraseña);

    }

    private void cajastexto() {

        nom_ingresar.setBounds(118, 50, 150, 20);
        con_ingresar.setBounds(115, 150, 150, 20);
        p1.add(nom_ingresar);
        p1.add(con_ingresar);

    }

    private void botones() {

        b1.setText("Ingresar");
        b1.setBounds(100, 200, 100, 40);
        p1.add(b1);

        ActionListener accion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };

        b1.addActionListener(accion);

    }

    private void login() {
        Boolean prueba = false;
        int y = 0;
        String nombre = "";
        List<Usuarios> lista = dao.listarusuario();
        for (int i = 0; i < lista.size(); i++) {
            y = y + 1;
            System.out.println(lista.get(i));
        }
        Object[][] object = new Object[y][3];

        for (int i = 0; i < lista.size(); i++) {
            object[i][0] = lista.get(i).getcorreo_electronico();
            object[i][1] = lista.get(i).getContraseña();
            object[i][2] = lista.get(i).getNombre();
        }

        for (int i = 0; i < object.length; i++) {

            if (nom_ingresar.getText().equals(object[i][0]) && con_ingresar.getText().equals(object)) {
                prueba = true;
                nombre = (String) object[i][2].toString();
                break;
            } else if (oportunidades == 0) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos, intente mas tarde");
            } else {

            }

        }

        if (prueba.equals(true)) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.Frame(nombre);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            oportunidades = oportunidades - 1;
        }

    }
    
    public static void main(String[] args) {
        Login p = new Login ();
        p.frame ();
    }

}
