package GUI;

import Ciudad.Mundo;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GrafoFrame extends javax.swing.JFrame {
    private Mundo mundo;
    private String algoritmo = "";
    private String ciudad1 = "";
    private String ciudad2 = "";
    
    public GrafoFrame() {
        initComponents();
        this.setSize(1080, 500);
        mundo= new Mundo();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGrafoCopia = new javax.swing.JLabel();
        lblGrafoOriginal = new javax.swing.JLabel();
        btnSimular = new javax.swing.JButton();
        btnEjecuar = new javax.swing.JButton();
        cbxAlgortimos = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btnCargarGrafo = new javax.swing.JButton();
        lblAlgoritmo = new javax.swing.JLabel();
        lblCargarGrafos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblGrafoCopia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(lblGrafoCopia);
        lblGrafoCopia.setBounds(403, 6, 391, 447);

        lblGrafoOriginal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblGrafoOriginal);
        lblGrafoOriginal.setBounds(6, 6, 391, 447);

        btnSimular.setText("Simular");
        btnSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimular);
        btnSimular.setBounds(878, 72, 90, 26);

        btnEjecuar.setText("Ejecutar");
        getContentPane().add(btnEjecuar);
        btnEjecuar.setBounds(878, 104, 90, 26);

        cbxAlgortimos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1. Particion del Grafo", "2. Bloquear por Bienes", "3. Convertir a dirigido", "4 .Camino al mas poderoso", "5. Solo un recorrido", "6. Solo un recorrido - Mas visitado", "7. Entre dos ciudades", "8. Camino mas poderoso entre ciudades", "9. Todos los caminos entre ciudades", "10. Destruccion total" }));
        cbxAlgortimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAlgortimosActionPerformed(evt);
            }
        });
        getContentPane().add(cbxAlgortimos);
        cbxAlgortimos.setBounds(806, 40, 250, 26);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(978, 72, 162, 130);

        btnCargarGrafo.setText("Cargar");
        btnCargarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarGrafoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargarGrafo);
        btnCargarGrafo.setBounds(877, 176, 90, 26);

        lblAlgoritmo.setText("Algoritmos:");
        getContentPane().add(lblAlgoritmo);
        lblAlgoritmo.setBounds(806, 18, 83, 16);

        lblCargarGrafos.setText("Cargar Grafos");
        getContentPane().add(lblCargarGrafos);
        lblCargarGrafos.setBounds(880, 140, 90, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxAlgortimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAlgortimosActionPerformed
        if(cbxAlgortimos.getSelectedIndex() == 0){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
            
        }else if(cbxAlgortimos.getSelectedIndex() == 1 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
        }else if(cbxAlgortimos.getSelectedIndex() == 2 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
        }else if(cbxAlgortimos.getSelectedIndex() == 3 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
        }else if(cbxAlgortimos.getSelectedIndex() == 4 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
        }else if(cbxAlgortimos.getSelectedIndex() == 5 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
        }else if(cbxAlgortimos.getSelectedIndex() == 6 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
            ciudad1 = JOptionPane.showInputDialog("Nombre de la Ciudad 1:");
            ciudad2 = JOptionPane.showInputDialog("Nombre de la Ciudad 2:");
            if(mundo.buscarCiudad(ciudad1)== null ||  mundo.buscarCiudad(ciudad2) == null){
                JOptionPane.showMessageDialog(null, "Nombres de la ciudades no existen, intentalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                ciudad1 = "";
                ciudad2 = "";
            }
        }else if(cbxAlgortimos.getSelectedIndex() == 7 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
            ciudad1 = JOptionPane.showInputDialog("Nombre de la Ciudad 1:");
            ciudad2 = JOptionPane.showInputDialog("Nombre de la Ciudad 2:");
            if(mundo.buscarCiudad(ciudad1)== null ||  mundo.buscarCiudad(ciudad2) == null){
                JOptionPane.showMessageDialog(null, "Nombres de la ciudades no existen, intentalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                ciudad1 = "";
                ciudad2 = "";
            }
        }else if(cbxAlgortimos.getSelectedIndex() == 8 ){
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
            ciudad1 = JOptionPane.showInputDialog("Nombre de la Ciudad 1:");
            ciudad2 = JOptionPane.showInputDialog("Nombre de la Ciudad 2:");
            if(mundo.buscarCiudad(ciudad1)== null ||  mundo.buscarCiudad(ciudad2) == null){
                JOptionPane.showMessageDialog(null, "Nombres de la ciudades no existen, intentalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                ciudad1 = "";
                ciudad2 = "";
            }
            
        }else{
            algoritmo = (String) cbxAlgortimos.getSelectedItem();
        }
        
    }//GEN-LAST:event_cbxAlgortimosActionPerformed

    private void btnCargarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarGrafoActionPerformed
        mundo.getMundo().clear();
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            
            
            int ind = selectedFilePath.indexOf(".");
            if(selectedFilePath.substring(ind).equals(".json")){
                System.out.println("SELECTED PATH: " + selectedFilePath);
                mundo.cargarMundo(selectedFilePath);
                mundo.imprimir();
            }else
                JOptionPane.showMessageDialog(null, "El archivo tiene que ser de formato '.json', intentalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            
            
        } else 
            JOptionPane.showMessageDialog(null, "No se ha podido cargar el grafo, intentalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        
    
    }//GEN-LAST:event_btnCargarGrafoActionPerformed

    private void btnSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularActionPerformed
        if(mundo.getMundo().size() == 0 ){
            JOptionPane.showMessageDialog(null, "Cargue , intentalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnSimularActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GrafoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarGrafo;
    private javax.swing.JButton btnEjecuar;
    private javax.swing.JButton btnSimular;
    private javax.swing.JComboBox<String> cbxAlgortimos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAlgoritmo;
    private javax.swing.JLabel lblCargarGrafos;
    private javax.swing.JLabel lblGrafoCopia;
    private javax.swing.JLabel lblGrafoOriginal;
    // End of variables declaration//GEN-END:variables
}
