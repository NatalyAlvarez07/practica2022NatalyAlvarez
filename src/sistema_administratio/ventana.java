package sistema_administratio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion;
    JPanel panelControl;
    JPanel panelCrearUsuario;
    int control = 2;
    cliente clientes[] = new cliente[100];
    int controlCliente = 0;
    JPanel panelControlClientes;
    producto productos[] = new producto[100];
    int controlProducto = 0;
    JPanel panelControlProductos;
    int controlClientes = 2;
    int controlProductos = 2;

    //Método constructor
    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
        crearProductos();
    }
    //Creacion del adiministrador

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "nataly";
        usuSistema[0].nombre = "administrador";
        usuSistema[0].contra = "123";
        //Usuario de prueba
        usuSistema[1] = new usuario();
        usuSistema[1].nombreUsuario = "Lian2";
        usuSistema[1].nombre = "Lian Aquilar";
        usuSistema[1].contra = "170";
    }
//Creación de clientes

    public void crearClientes() {
        clientes[0] = new cliente();
        clientes[0].nombre = "cliente 1";
        clientes[0].edad = 22;
        clientes[0].genero = 'M';
        clientes[0].nit = 150;

        clientes[1] = new cliente();
        clientes[1].nombre = "cliente 2";
        clientes[1].edad = 30;
        clientes[1].genero = 'F';
        clientes[1].nit = 300;
    }
//Creacion de prodcutos

    public void crearProductos() {
        productos[0] = new producto();
        productos[0].Nombre = "Productos 1";
        productos[0].Precio = 230;
        productos[0].Cantidad = 12;

        productos[1] = new producto();
        productos[1].Nombre = "Productos 2";
        productos[1].Precio = 450;
        productos[1].Cantidad = 20;
    }

    public void objetos() {
        panelInicioSesion = new JPanel();
        this.getContentPane().add(panelInicioSesion);
        panelInicioSesion.setLayout(null);

        JLabel lblLOGIN = new JLabel("Login");
        lblLOGIN.setBounds(20, 7, 100, 15);
        panelInicioSesion.add(lblLOGIN);

        JLabel lblUSUARIO = new JLabel("Usuario");
        lblUSUARIO.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblUSUARIO.setBounds(60, 40, 100, 15);
        panelInicioSesion.add(lblUSUARIO);

        JLabel lblCONTRA = new JLabel("Contraseña");
        lblCONTRA.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lblCONTRA.setBounds(60, 100, 100, 15);
        panelInicioSesion.add(lblCONTRA);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 40, 200, 25);
        panelInicioSesion.add(txtUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(150, 100, 200, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(212, 223, 186));
        btnIngresar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnIngresar.setBounds(120, 145, 180, 35);
        panelInicioSesion.add(btnIngresar);
        ActionListener ingresar = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().equals("") || txtContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                } else {
                    recorrerUsuario(txtUsuario.getText(), txtContra.getText());
                }

            }
        };
        btnIngresar.addActionListener(ingresar);

        JButton btnCrearUsuario = new JButton("Registrarse");
        btnCrearUsuario.setBackground(new Color(212, 223, 186));
        btnCrearUsuario.setFont(new Font("Century Gothic", Font.BOLD, 12));
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

    public void recorrerUsuario(String nombreUsuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (usuSistema[i] != null) {
                if (usuSistema[i].nombreUsuario.equals(nombreUsuario) && usuSistema[i].contra.equals(contra)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema usuario " + nombreUsuario);
                    panelControl();
                    encontrado = true;
                    break;
                } else {
                    encontrado = false;
                }
            }

        }
        if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public void panelControl() {
        panelControl = new JPanel();
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(500, 400);
        this.setTitle("Control Principal");
        panelInicioSesion.setVisible(false);

        JButton btnadminClientes = new JButton("Administración de clientes");
        btnadminClientes.setBackground(new Color(230, 234, 196));
        btnadminClientes.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnadminClientes.setBounds(100, 10, 250, 25);
        panelControl.add(btnadminClientes);
        ActionListener administrarClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelControlCli();
                panelControlClientes.setVisible(true);
            }

        };
        btnadminClientes.addActionListener(administrarClientes);
        

        JButton btnadminProducto = new JButton("Administración de productos");
        btnadminProducto.setBackground(new Color(230, 234, 196));
        btnadminProducto.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnadminProducto.setBounds(100, 80, 250, 25);
        panelControl.add(btnadminProducto);
        ActionListener administrarProductos = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panelControlPro();
                panelControlProductos.setVisible(true);
            }

        };
        btnadminProducto.addActionListener(administrarProductos);
                
        
    }
    public void crearUsuario() {
        panelCrearUsuario = new JPanel();
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
        btnRegistrar.setBackground(new Color(223, 234, 196));
        btnRegistrar.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnRegistrar.setBounds(130, 270, 200, 35);
        panelCrearUsuario.add(btnRegistrar);
        ActionListener registro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsuario.getText().equals("") || txtNombreUsuario.getText().equals("") || txtContra.getText().equals("") || txtConfContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");
                } else {

                    if (txtContra.getText().equals(txtConfContra.getText())) {
                        guardarUsuario(txtUsuario.getText(), txtNombreUsuario.getText(), txtContra.getText());
                        txtUsuario.setText("");
                        txtNombreUsuario.setText("");
                        txtContra.setText("");
                        txtConfContra.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas NO coinciden");
                    }
                }
            }
        };
        btnRegistrar.addActionListener(registro);

        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBackground(new Color(218, 234, 196));
        btnVolver.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnVolver.setBounds(130, 350, 200, 35);
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

    public void volverInicio() {
        this.setTitle("Sistema administrativo de clientes y recursos");
        this.setSize(450, 350);
    }

    public void guardarUsuario(String nombre, String nombreUsuario, String contra) {
        int posicion = 0;
        if (control < 10) {
            for (int i = 0; i < 9; i++) {
                if (usuSistema[i] == null) {
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

        } else {
            JOptionPane.showMessageDialog(null, "no se puede registrar más usuarios");
        }

    }

    public void panelControlCli() {
        panelControlClientes = new JPanel();
        this.getContentPane().add(panelControlClientes);
        panelControlClientes.setLayout(null);
        this.setSize(950, 500);
        this.setTitle("Administración de clientes");
        panelControl.setVisible(false);

        //creación de la trabla clientes
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Género");
        datosTabla.addColumn("Nit");

        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                String fila[] = {clientes[i].nombre, String.valueOf(clientes[i].edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].nit)};
                datosTabla.addRow(fila);
            }

        }

        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 300, 100);
        panelControlClientes.add(barraTablaClientes);

        //creación de gráfico circular
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", totalhombres());
        datos.setValue("Femenino", totalmujeres());

        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos en el sistema", datos);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(10, 120, 300, 300);
        panelControlClientes.add(panelCircular);

        //creación de gráfico de columnas de clientes
        //Rango 1 -> 18-30
        //Rango 2 -> 31-45
        //Rango 3 -> mayor a 45
        //System.out.println("Total de 18 a 30 " + rango18a30());
        //System.out.println("Total de 31 a 45 " + rango31a45());
        //System.out.println("Total de 45 o más " + rango45mas());
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango18a30(), "18-30", "Edad");
        datos2.addValue(rango31a45(), "31-45", "Edad");
        datos2.addValue(rango45mas(), "Mayor a 45", "Edad");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de edades", "Edad", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(350, 120, 300, 300);
        panelControlClientes.add(panelColumnas);
        
        //Cargar el archivo para que se visualice en la tabla
        JButton btnCargarArchivo = new JButton("Buscar archivo CSV");
        btnCargarArchivo.setBackground(new Color(227, 237, 204));
        btnCargarArchivo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnCargarArchivo.setBounds(350, 10, 200, 25);
        panelControlClientes.add(btnCargarArchivo);
        ActionListener BuscarArchivo = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                System.out.println("La ubicación del archivo es " + archivoSeleccionado.getPath());
                leerArchivoCSV(archivoSeleccionado.getPath());
                panelControlClientes.setVisible(false);
                panelControlCli();

            }
        };
        btnCargarArchivo.addActionListener(BuscarArchivo);
        
                      
        //Boton de reporte HTML Clientes
        JButton btnReporte = new JButton("Crear reporte HTML");
        btnReporte.setBackground(new Color(227, 237, 204));
        btnReporte.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnReporte.setBounds(570, 15, 200, 25);
        panelControlClientes.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearReporte();

            }
        };
        btnReporte.addActionListener(crearHTML);

        //Boton para volver al menú clientes
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setBackground(new Color(227, 237, 204));
        btnVolver.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnVolver.setBounds(450, 60, 200, 25);
        panelControlClientes.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlClientes.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
    }
//ORDEN DE LA EDAD DE MANERA ACENDENTE CLIENTES
    public void ordenarCli() {
        cliente auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (clientes[j + 1] == null) {
                    break;
                } else {
                    if (clientes[j].edad > clientes[j + 1].edad) {
                        auxiliar = clientes[j + 1];
                        clientes[j + 1] = clientes[j];
                        clientes[j] = auxiliar;
                    }
                }
            }
        }

    }

    public void crearReporte() {
        try {
            //CSS
            ordenarCli();
            PrintWriter escribirCSS = new PrintWriter("reporte_cliente/estilo.css", "UTF-8");
            escribirCSS.println("html{  font-size: 20px; font-family: 'Consolas', sans-serif ;}");
            escribirCSS.println("h1{  font-size: 60px; text-align: center; }");
            escribirCSS.println("table{table-layout: fixed; width: 500px;} td{border-collapse: collapse center;}");
            escribirCSS.println("html{background-color: #C8D8BA ;}");
            escribirCSS.println("body{ width: 970px; margin: 0 auto; background-color: #B0C79D; panding: 0 20px 20px 20px; border: 500px ;}");
            escribirCSS.println("h1{ margin: 0; padding: 20px; color: #6C8259; text-shadown: 3px 3px 1px black; }");
            escribirCSS.close();

            //HTML
            PrintWriter escribir = new PrintWriter("reporte_cliente/index.html", "UTF-8");
            escribir.println("<!DOCTYPE html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema</title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de clientes en el sistema</h1>");

            //Creación de la tabla
            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>NIT</td> <td>Nombre</td> <td>Edad</td> <td>Genero</td>");
            escribir.println("</tr>");
            for (int i = 0; i < 99; i++) {
                if (clientes[i] != null) {
                    escribir.println("<tr>");
                    escribir.println("<td>" + clientes[i].nit + "</td><td>" + clientes[i].nombre + "</td><td>" + clientes[i].edad + "</td><td>" + clientes[i].genero + "</td>");
                    escribir.println("</tr>");
                }
            }

            escribir.println("</table");
            escribir.println("</body>");
            escribir.println("</html>");

            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con exito, este se encuentra en la carpeta reporte_cliente");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }
    }

    public int totalhombres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'M') {
                    total++;
                }
            }
        }
        return total;
    }

    public int totalmujeres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'F') {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango18a30() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 18 && clientes[i].edad <= 30) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango31a45() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 31 && clientes[i].edad <= 45) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango45mas() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad > 45) {
                    total++;
                }
            }
        }
        return total;
    }

//LECTURA DEL ARCHIVO CSS CLIENTES
    public void leerArchivoCSV(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");

                    int posicion = 0;
                    if (controlCliente < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (clientes[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        clientes[posicion] = new cliente();
                        clientes[posicion].nombre = datosSeparados[0];
                        clientes[posicion].edad = Integer.parseInt(datosSeparados[1]);
                        clientes[posicion].genero = datosSeparados[2].charAt(0);
                        clientes[posicion].nit = Integer.parseInt(datosSeparados[3]);
                        controlCliente++;
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede registrar más clientes");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clientes registrado exitosamente, total de clientes " + controlCliente);
            archivoTemporal.close();

        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No puedo abrir el archivo CSV");
        }
    }
// -----------------------------------------------DIVISION DE DATOS DE PRODUCTOS--------------------------------------------------------------
        public void panelControlPro(){
        panelControlProductos = new JPanel();
        this.getContentPane().add(panelControlProductos);
        panelControlProductos.setLayout(null);
        this.setSize(600, 500);
        this.setTitle("Administración de productos");
        panelControl.setVisible(false);

        
        //creación de la trabla productos
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Precio");
        datosTabla.addColumn("Cantidad");

        //tabal de productos
        JTable tablaProductos = new JTable(datosTabla);
        JScrollPane barraTablaProductos = new JScrollPane(tablaProductos);
        barraTablaProductos.setBounds(10, 10, 300, 100);
        panelControlProductos.add(barraTablaProductos);
        
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                String fila[] = {productos[i].Nombre, String.valueOf(productos[i].Precio), String.valueOf(productos[i].Cantidad)};
                datosTabla.addRow(fila);
            }
        //creación de gráfico de columnas de clientes
        //Rango 1 -> 100-200
        //Rango 2 -> 201-300
        //Rango 3 -> mayor a 301
        //System.out.println("Total de 18 a 30 " + rango100a200);
        //System.out.println("Total de 31 a 45 " + rango201a300());
        //System.out.println("Total de 45 o más " + rango301mas());
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango100a200(), "100-200", "Precio");
        datos2.addValue(rango201a300(), "201-300", "Precio");
        datos2.addValue(rango301mas(), "mayor a 301", "Precio");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de precios", "Precio", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(15, 120, 300, 300);
        panelControlProductos.add(panelColumnas);
        
        
        // BOTON DE VISUALIZACION DEL CSV PRODUCTOS
        JButton btnCargarArchivo = new JButton("Buscar archivo CSV");
        btnCargarArchivo.setBackground(new Color(227, 237, 204));
        btnCargarArchivo.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnCargarArchivo.setBounds(350, 10, 200, 25);
        panelControlProductos.add(btnCargarArchivo);
        ActionListener BuscarArchivo = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                System.out.println("La ubicación del archivo es " + archivoSeleccionado.getPath());
                leerArchivoCSV2(archivoSeleccionado.getPath());
                panelControlProductos.setVisible(false);
                panelControlPro();

            }
        };
        btnCargarArchivo.addActionListener(BuscarArchivo);
        
         //Boton de reporte HTML Productos
        JButton btnReporte = new JButton("Crear reporte HTML");
        btnReporte.setBackground(new Color(227, 237, 204));
        btnReporte.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnReporte.setBounds(350, 50, 200, 25);
        panelControlProductos.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                crearReporte2();

            }
        };
        btnReporte.addActionListener(crearHTML);

        //Boton para volver al menú 
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setBackground(new Color(227, 237, 204));
        btnVolver.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btnVolver.setBounds(350, 100, 200, 25);
        panelControlProductos.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlProductos.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
        }
     
   }
//ORDEN EL PRECIO DE PRODUCTOS
    public void ordenarPro() {
        producto auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (productos[j + 1] == null) {
                    break;
                } else {
                    if (productos[j].Precio > productos[j + 1].Precio) {
                        auxiliar = productos[j + 1];
                        productos[j + 1] = productos[j];
                        productos[j] = auxiliar;
                    }
                }
            
            }
        }
    } 
   //CREACION DE REPORTE PRODUCTOS
        public void crearReporte2() {
        try {
            //CSS
            ordenarPro();
            PrintWriter escribirCSS = new PrintWriter("reporte_producto/estilo.css", "UTF-8");
            escribirCSS.println("html{  font-size: 20px; font-family: 'Consolas', sans-serif ;}");
            escribirCSS.println("h1{  font-size: 60px; text-align: center; }");
            escribirCSS.println("table{table-layout: fixed; width: 500px;} td{border-collapse: collapse center;}");
            escribirCSS.println("html{background-color: #D2E2C5;}"); 
            escribirCSS.println("body{ width: 970px; margin: 0 auto; background-color: #B4D598; panding: 0 20px 20px 20px; border: 500px ;}");
            escribirCSS.println("h1{ margin: 0; padding: 20px; color: #354726; text-shadown: 3px 3px 1px black; }");
            escribirCSS.close();

            //HTML
            PrintWriter escribir = new PrintWriter("reporte_producto/index.html", "UTF-8");
            escribir.println("<!DOCTYPE html>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema</title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de productos en el sistema</h1>");

            //Creación de la tabla
            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println(" <td>Nombre</td> <td>Precio</td> <td>Cantidad</td>");
            escribir.println("</tr>");
            for (int i = 0; i < 99; i++) {
                if (productos[i] != null) {
                    escribir.println("<tr>");
                    escribir.println("<td>" + productos[i].Nombre + "</td><td>" + productos[i].Precio + "</td><td>"  + productos[i].Cantidad + "</td>");
                    escribir.println("</tr>");
                }
            }

            escribir.println("</table");
            escribir.println("</body>");
            escribir.println("</html>");

            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con exito, este se encuentra en la carpeta reporte_producto");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }        
    } 
        public void leerArchivoCSV2(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");

                    int posicion = 0;
                    if (controlProducto < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (productos[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        productos[posicion] = new producto();
                        productos[posicion].Nombre= datosSeparados[0];
                        productos[posicion].Precio = Float.parseFloat(datosSeparados[1]);
                        productos[posicion].Cantidad = Integer.parseInt(datosSeparados[2]);
                        controlProducto++;
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede registrar más productos");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Productos registrado exitosamente, total de productos " + controlProducto);
            archivoTemporal.close();

        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No puedo abrir el archivo CSV");
        }    
    } 
    //RANGO DE PRECIO PRODUCTOS
        public int rango100a200(){
      int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].Precio >= 100 && productos[i].Precio <= 200) {
                    total++;
                }
            }
        }
        return total;
    }
    
    public int rango201a300(){
      int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].Precio >= 200 && productos[i].Precio <= 300) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango301mas() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].Precio > 301) {
                    total++;
                }
            }
        }
        return total;
    }
}
