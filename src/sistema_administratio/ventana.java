
package sistema_administratio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ventana extends JFrame{
     usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion = new JPanel();
    JPanel panelControl = new JPanel();
    JPanel panelCrearUsuario = new JPanel();
    int control = 2;
    cliente clientes[] = new cliente[100];
    int controlCliente = 0;
    JPanel panelControlClientes = new JPanel();
    
    //Método constructor
    public ventana(){
       objetos();
       crearAdmin();
}

      public void crearAdmin(){
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "admin";
        usuSistema[0].nombre = "administrador";
        usuSistema[0].contra = "123";
        //Usuario de prueba
        usuSistema[1] = new usuario();
        usuSistema[1].nombreUsuario = "Lian2";
        usuSistema[1].nombre = "Lian Aquilar";
        usuSistema[1].contra = "170";
       } 

    public void objetos(){
      
      this.getContentPane().add(panelInicioSesion);
      panelInicioSesion.setLayout(null);
      
      JLabel lblLOGIN = new JLabel("Login");
      lblLOGIN.setBounds(20,7,100,15);
      panelInicioSesion.add(lblLOGIN);
      
      JLabel lblUSUARIO = new JLabel("Usuario");
      lblUSUARIO.setFont(new Font("Century Gothic",Font.BOLD,12));
      lblUSUARIO.setBounds(60,40,100,15);
      panelInicioSesion.add(lblUSUARIO);
      
      JLabel lblCONTRA = new JLabel("Contraseña");
      lblCONTRA.setFont(new Font("Century Gothic",Font.BOLD,12));
      lblCONTRA.setBounds(60,100,100,15);
      panelInicioSesion.add(lblCONTRA);
      
      JTextField txtUsuario = new JTextField();
      txtUsuario.setBounds(150, 40, 200, 25);
      panelInicioSesion.add(txtUsuario);
      
      JTextField txtContra = new JTextField();
      txtContra.setBounds(150, 100, 200, 25);
      panelInicioSesion.add(txtContra);
      
      JButton btnIngresar = new JButton("Ingresar");
      btnIngresar.setBackground(new Color(234, 223, 196));
      btnIngresar.setFont(new Font("Century Gothic",Font.BOLD,12));
      btnIngresar.setBounds(120, 145, 180, 35);
      panelInicioSesion.add(btnIngresar);
      ActionListener ingresar = new ActionListener(){
          
          
          public void actionPerformed(ActionEvent e) {
              if(txtUsuario.getText().equals("") || txtContra.getText().equals("")){
                  JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
              }else{ 
               recorrerUsuario(txtUsuario.getText(), txtContra.getText());              
              }
                
           }
         };
         btnIngresar.addActionListener(ingresar);
         
         JButton btnCrearUsuario = new JButton("Registrarse");
         btnCrearUsuario.setBackground(new Color(234, 223, 196));
         btnCrearUsuario.setFont(new Font("Century Gothic",Font.BOLD,12));
         btnCrearUsuario.setBounds(120, 200, 180, 35);
         panelInicioSesion.add(btnCrearUsuario);
         ActionListener crearUsuario = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) { 
             crearUsuario();
             panelCrearUsuario.setVisible(true);
          }
      };
      btnCrearUsuario.addActionListener(crearUsuario);
    }
    
    public void recorrerUsuario(String nombreUsuario, String contra){
        boolean encontrado = false;
        for(int i = 0; i<10; i++){
              if(usuSistema[i] != null){
                if(usuSistema[i].nombreUsuario.equals(nombreUsuario) && usuSistema[i].contra.equals(contra)){
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema usuario " + nombreUsuario);
                panelControl();
                encontrado = true;
                 break;
           }else{
               encontrado = false;             
            }
        }
                                                  
        }
        if(encontrado == false){
           JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }
    public void panelControl(){       
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(600, 500);
        this.setTitle("Control Principal");
        panelInicioSesion.setVisible(false);
        
        JButton btnadminClientes = new JButton("Administración de clientes");
        btnadminClientes.setBackground(new Color(230, 234, 196));
        btnadminClientes.setFont(new Font("Century Gothic",Font.BOLD,12));
        btnadminClientes.setBounds(150, 10, 250, 25);
        panelControl.add(btnadminClientes);
        ActionListener administrarClientes = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               panelControlCli();
               panelControlClientes.setVisible(true);
            }
            
        };
        btnadminClientes.addActionListener(administrarClientes);
        
        JButton btnadminProducto = new JButton("Administración de productos");
        btnadminProducto.setBackground(new Color(230, 234, 196));
        btnadminProducto.setFont(new Font("Century Gothic",Font.BOLD,12));
        btnadminProducto.setBounds(150, 80, 250, 25);
        panelControl.add(btnadminProducto);
        
        JButton btnReportes = new JButton("Reportes");
        btnReportes.setBackground(new Color(230, 234, 196));
        btnReportes.setFont(new Font("Century Gothic",Font.BOLD,12));
        btnReportes.setBounds(150, 150, 250, 25);
        panelControl.add(btnReportes);
    }
    
    public void crearUsuario(){
     this.getContentPane().add(panelCrearUsuario);
     panelCrearUsuario.setLayout(null);
     this.setSize(500, 450);
     this.setTitle("Registro de nuevo usuario");
     panelInicioSesion.setVisible(false);
     
     JLabel lblregistro = new JLabel("Registro de usuario");
     lblregistro.setBounds(20, 20, 150, 20);
     panelCrearUsuario.add(lblregistro);
     
     JLabel lblUsuario = new JLabel("Usuario");
     lblUsuario.setBounds(50, 70, 150, 20);
     panelCrearUsuario.add(lblUsuario);
     
     JLabel lblNombre = new JLabel("Nombre");
     lblNombre.setBounds(50, 120, 150, 20);
     panelCrearUsuario.add(lblNombre);
     
     JLabel lblContra = new JLabel("Contraseña");
     lblContra.setBounds(50, 170, 150, 20);
     panelCrearUsuario.add(lblContra);
     
     JLabel lblConfirmar = new JLabel("Confirmar contraseña");
     lblConfirmar.setBounds(50, 220, 150, 20);
     panelCrearUsuario.add(lblConfirmar);
     
     JTextField txtUsuario = new JTextField();
     txtUsuario.setBounds(200, 70, 150, 20);
     panelCrearUsuario.add(txtUsuario);
     
     JTextField txtNombreUsuario = new JTextField();
     txtNombreUsuario.setBounds(200, 120, 150, 20);
     panelCrearUsuario.add(txtNombreUsuario);
     
     JTextField txtContra = new JTextField();
     txtContra.setBounds(200, 170, 150, 20);
     panelCrearUsuario.add(txtContra);
     
     JTextField txtConfContra = new JTextField();
     txtConfContra.setBounds(200, 220, 150, 20);
     panelCrearUsuario.add(txtConfContra);
     
     JButton btnRegistrar = new JButton("Registrar");
     btnRegistrar.setBackground(new Color(218, 234, 196));
     btnRegistrar.setFont(new Font("Century Gothic",Font.BOLD,12));
     btnRegistrar.setBounds(130, 270, 200, 35);
     panelCrearUsuario.add(btnRegistrar);
     ActionListener registro = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
             if(txtUsuario.getText().equals("") || txtNombreUsuario.getText().equals("") || txtContra.getText().equals("") || txtConfContra.getText().equals("")){
               JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");
             }else{
                 
                 if(txtContra.getText().equals(txtConfContra.getText())){
                   guardarUsuario(txtUsuario.getText(),txtNombreUsuario.getText(), txtContra.getText());
                   txtUsuario.setText("");
                   txtNombreUsuario.setText("");                   
                   txtContra.setText("");
                   txtConfContra.setText("");
                 }else{
                   JOptionPane.showMessageDialog(null, "Las contraseñas NO coinciden");
                 }
             }
         }
     };
      btnRegistrar.addActionListener(registro);
      
       JButton btnVolver = new JButton("Volver al inicio");
       btnVolver.setBackground(new Color(218, 234, 196));
       btnVolver.setFont(new Font("Century Gothic",Font.BOLD,12));
       btnVolver .setBounds(130, 350, 200, 35);
       panelCrearUsuario.add(btnVolver);
       ActionListener volverInicio = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
            panelInicioSesion.setVisible(true);            
            panelCrearUsuario.setVisible(false);
            volverInicio();
        }
     };
     btnVolver.addActionListener(volverInicio);
     
    }
    
        public void volverInicio(){
         this.setTitle("Sistema administrativo de clientes y recursos");
         this.setSize(450,350);
        }
    
    
          public void guardarUsuario(String nombre, String nombreUsuario, String contra){
          int posicion = 0;
          if(control < 10){                       
            for(int i = 0;i < 9;i++){
                if(usuSistema[i] == null){
                  posicion = i;
                  break; 
             }
          }          
          //System.out.println("La posición libre es " + posicion);
          usuSistema[posicion] = new usuario();
          usuSistema[posicion].nombreUsuario = nombre;
          usuSistema[posicion].nombre = nombreUsuario;
          usuSistema[posicion].contra = contra;
          control++;
          JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente, total de usuarios " + control);
          
          }else{
           JOptionPane.showMessageDialog(null, "no se puede registrar más usuarios");
         }
      
    }
     
     public void panelControlCli(){
     this.getContentPane().add(panelControlClientes);
     panelControlClientes.setLayout(null);
     this.setSize(750, 500);
     this.setTitle("Administración de clientes");
     panelControl.setVisible(false);        
     }
}


