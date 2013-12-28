/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.dms.view;

import cine.dms.interfaceMV.CinemaSystem;

/**
 *
 * @author raul
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotonesSuperior = new javax.swing.JPanel();
        btnSiguientePaso = new javax.swing.JButton();
        btnSimulacionCompleta = new javax.swing.JButton();
        lblReloj = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnIniciarSimulacion = new javax.swing.JButton();
        jPanelContenedorPestanas = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelVariablesEntrada = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        spnNumTaquillas = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spnFrecuenciaClientes = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        spnTiempoServicioTaquillas = new javax.swing.JSpinner();
        spnTiempoServicioPalomitas = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        spnProbabilidadCompraEntradas = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        spnProbabilidadCompraPalomitas = new javax.swing.JSpinner();
        spnNumPuestoPalomitas = new javax.swing.JSpinner();
        jPanelEstado = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTamMedioColasTaquilla = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNumTaquillasOcupadas = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNumPuestosPalomitasOcupados = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNumMedioAtendidosTaquilla = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNumMedioAtendidosPuestoPalomitas = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTiempoMedioEnCola = new javax.swing.JTextField();
        txtTamMedioColasPuestosPalomitas = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanelResultados = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanelLog = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulación Cine - DMS");
        setMinimumSize(new java.awt.Dimension(600, 460));

        btnSiguientePaso.setText("Realizar paso a paso");
        btnSiguientePaso.setEnabled(false);
        btnSiguientePaso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiguentePasoClick(evt);
            }
        });

        btnSimulacionCompleta.setText("Realizar simulación completa");
        btnSimulacionCompleta.setEnabled(false);
        btnSimulacionCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimulacionCompletaClick(evt);
            }
        });

        lblReloj.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        lblReloj.setText("00:00:00");

        jLabel1.setText("Reloj: ");

        btnIniciarSimulacion.setText("Iniciar simulación");
        btnIniciarSimulacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarSimulacionClick(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesSuperiorLayout = new javax.swing.GroupLayout(jPanelBotonesSuperior);
        jPanelBotonesSuperior.setLayout(jPanelBotonesSuperiorLayout);
        jPanelBotonesSuperiorLayout.setHorizontalGroup(
            jPanelBotonesSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesSuperiorLayout.createSequentialGroup()
                .addComponent(btnIniciarSimulacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguientePaso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimulacionCompleta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblReloj))
        );
        jPanelBotonesSuperiorLayout.setVerticalGroup(
            jPanelBotonesSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSiguientePaso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSimulacionCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnIniciarSimulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblReloj)
                .addComponent(jLabel1))
        );

        jPanelVariablesEntrada.setPreferredSize(new java.awt.Dimension(270, 15));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Número de taquillas");
        jLabel4.setPreferredSize(null);

        spnNumTaquillas.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), Integer.valueOf(0), null, Integer.valueOf(1)));

        jLabel5.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel5.setText("Introduzca las variables de entrada");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Número de puestos de palomitas");
        jLabel6.setPreferredSize(null);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Frecuencia de llegada de clientes");
        jLabel7.setPreferredSize(null);

        spnFrecuenciaClientes.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(30), Integer.valueOf(1), null, Integer.valueOf(1)));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Tiempo de servicio de taquillas");
        jLabel8.setPreferredSize(null);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tiempo de servicio de puestos de palomitas");
        jLabel9.setPreferredSize(null);

        spnTiempoServicioTaquillas.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));

        spnTiempoServicioPalomitas.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(20), Integer.valueOf(1), null, Integer.valueOf(1)));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Probabilidad de compra múltiple de entradas");
        jLabel10.setPreferredSize(null);

        spnProbabilidadCompraEntradas.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.05f), Float.valueOf(0.05f), Float.valueOf(1.0f), Float.valueOf(0.05f)));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Probabilidad de compra de palomitas");
        jLabel11.setPreferredSize(null);

        spnProbabilidadCompraPalomitas.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.05f), Float.valueOf(0.05f), Float.valueOf(1.0f), Float.valueOf(0.05f)));

        spnNumPuestoPalomitas.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3), Integer.valueOf(0), null, Integer.valueOf(1)));

        javax.swing.GroupLayout jPanelVariablesEntradaLayout = new javax.swing.GroupLayout(jPanelVariablesEntrada);
        jPanelVariablesEntrada.setLayout(jPanelVariablesEntradaLayout);
        jPanelVariablesEntradaLayout.setHorizontalGroup(
            jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVariablesEntradaLayout.createSequentialGroup()
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVariablesEntradaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnProbabilidadCompraPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnProbabilidadCompraEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnTiempoServicioPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnTiempoServicioTaquillas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnFrecuenciaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnNumPuestoPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnNumTaquillas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelVariablesEntradaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanelVariablesEntradaLayout.setVerticalGroup(
            jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVariablesEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(23, 23, 23)
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnNumTaquillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnNumPuestoPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnFrecuenciaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnTiempoServicioTaquillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnTiempoServicioPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnProbabilidadCompraEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelVariablesEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnProbabilidadCompraPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Variables de entrada", jPanelVariablesEntrada);

        jLabel12.setText("Tamaño medio de las colas de las Taquillas");

        txtTamMedioColasTaquilla.setText("0");

        jLabel13.setText("Número de taquillas ocupadas");

        txtNumTaquillasOcupadas.setText("0");

        jLabel14.setText("Número de puestos de palomitas ocupados");

        txtNumPuestosPalomitasOcupados.setText("0");

        jLabel15.setText("Número medio de personas atendidas por taquilla");

        txtNumMedioAtendidosTaquilla.setText("0");

        jLabel16.setText("Número medio de personas atendidas por puesto de palomitas");

        txtNumMedioAtendidosPuestoPalomitas.setText("0");

        jLabel24.setText("Tiempo medio en cola para n clientes");

        txtTiempoMedioEnCola.setText("0");

        txtTamMedioColasPuestosPalomitas.setText("0");
        txtTamMedioColasPuestosPalomitas.setToolTipText("");

        jLabel25.setText("Tamaño medio de las colas de los puestos de palomitas");

        javax.swing.GroupLayout jPanelEstadoLayout = new javax.swing.GroupLayout(jPanelEstado);
        jPanelEstado.setLayout(jPanelEstadoLayout);
        jPanelEstadoLayout.setHorizontalGroup(
            jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTamMedioColasPuestosPalomitas)
                    .addComponent(txtTamMedioColasTaquilla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(txtNumTaquillasOcupadas, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumPuestosPalomitasOcupados, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumMedioAtendidosTaquilla, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumMedioAtendidosPuestoPalomitas, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTiempoMedioEnCola, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanelEstadoLayout.setVerticalGroup(
            jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTamMedioColasTaquilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNumTaquillasOcupadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTamMedioColasPuestosPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNumPuestosPalomitasOcupados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNumMedioAtendidosTaquilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtNumMedioAtendidosPuestoPalomitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTiempoMedioEnCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estado de la simulación", jPanelEstado);

        jLabel17.setText("Total de personas atendidas en taquillas");

        jLabel18.setText("Total de personas atendidas en puestos de palomitas");

        jLabel19.setText("Número medio de clientes en taquillas");

        jLabel20.setText("Número medio de clientes en puestos de palomitas");

        jLabel21.setText("Grado de ocupación de las taquillas");

        jLabel22.setText("Grado de ocupación de los puesto de las palomitas");

        jLabel23.setText("Tiempo medio en cola para n clientes");

        javax.swing.GroupLayout jPanelResultadosLayout = new javax.swing.GroupLayout(jPanelResultados);
        jPanelResultados.setLayout(jPanelResultadosLayout);
        jPanelResultadosLayout.setHorizontalGroup(
            jPanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultadosLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(jPanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanelResultadosLayout.setVerticalGroup(
            jPanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultadosLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Análisis de resultados", jPanelResultados);

        txtaLog.setColumns(20);
        txtaLog.setRows(5);
        jScrollPane1.setViewportView(txtaLog);

        javax.swing.GroupLayout jPanelLogLayout = new javax.swing.GroupLayout(jPanelLog);
        jPanelLog.setLayout(jPanelLogLayout);
        jPanelLogLayout.setHorizontalGroup(
            jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelLogLayout.setVerticalGroup(
            jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Log", jPanelLog);
        jPanelLog.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanelContenedorPestanasLayout = new javax.swing.GroupLayout(jPanelContenedorPestanas);
        jPanelContenedorPestanas.setLayout(jPanelContenedorPestanasLayout);
        jPanelContenedorPestanasLayout.setHorizontalGroup(
            jPanelContenedorPestanasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanelContenedorPestanasLayout.setVerticalGroup(
            jPanelContenedorPestanasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("entradas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContenedorPestanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotonesSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBotonesSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelContenedorPestanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSimulacionClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSimulacionClick
        // TODO add your handling code here:
        if (btnIniciarSimulacion.getText().equals("Iniciar simulación")) {
            // Habilitar simulación
            btnIniciarSimulacion.setText("Parar simulación");
            btnSimulacionCompleta.setEnabled(true);
            btnSiguientePaso.setEnabled(true);

            jTabbedPane1.setSelectedIndex(1);  
            
            cine = new CinemaSystem();
            //Conexión con el sistema
            this.conexion();
        } else {
            // Deshabilitar simulación
            btnIniciarSimulacion.setText("Iniciar simulación");
            btnSimulacionCompleta.setEnabled(false);
            btnSiguientePaso.setEnabled(false);
            
            jTabbedPane1.setSelectedIndex(0);            
        }
    }//GEN-LAST:event_btnIniciarSimulacionClick

    private void btnSimulacionCompletaClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimulacionCompletaClick
        if (btnSimulacionCompleta.isEnabled()) {
            cine.run();

            datosPasoaPaso();
            
            if (cine.fin()) {
                btnIniciarSimulacionClick(evt);
            }
        }
    }//GEN-LAST:event_btnSimulacionCompletaClick

    private void btnSiguentePasoClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguentePasoClick
        if (btnSiguientePaso.isEnabled()) {
            cine.temporizacion();

            datosPasoaPaso();

            if (cine.fin()) {
                btnIniciarSimulacionClick(evt);
            }
        }
    }//GEN-LAST:event_btnSiguentePasoClick

    private void datosPasoaPaso(){
        lblReloj.setText(cine.getReloj().getTime());
        txtaLog.setText(cine.getLog().toString());
            
        txtTamMedioColasTaquilla.setText(cine.tamMedioColasTaquillas().toString());
        txtNumTaquillasOcupadas.setText(cine.numTaquillasOcupadas().toString());
        txtTamMedioColasPuestosPalomitas.setText(cine.tamMedioColasPuestosPalomitas().toString());
        txtNumPuestosPalomitasOcupados.setText(cine.numPuestosPalomitasOcupados().toString());
        txtNumMedioAtendidosTaquilla.setText(null);
        txtNumMedioAtendidosPuestoPalomitas.setText(null);
        txtTiempoMedioEnCola.setText(null);
    }
    
    private void conexion() {
        cine.initialize(
                Integer.parseInt(spnNumTaquillas.getValue().toString()),
                Integer.parseInt(spnNumPuestoPalomitas.getValue().toString()),
                Float.parseFloat(spnFrecuenciaClientes.getValue().toString()),
                Integer.parseInt(spnTiempoServicioTaquillas.getValue().toString()),
                Integer.parseInt(spnTiempoServicioPalomitas.getValue().toString()),
                Float.parseFloat(spnProbabilidadCompraEntradas.getValue().toString()),
                Float.parseFloat(spnProbabilidadCompraPalomitas.getValue().toString())
        );
        datosPasoaPaso();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSimulacion;
    private javax.swing.JButton btnSiguientePaso;
    private javax.swing.JButton btnSimulacionCompleta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelBotonesSuperior;
    private javax.swing.JPanel jPanelContenedorPestanas;
    private javax.swing.JPanel jPanelEstado;
    private javax.swing.JPanel jPanelLog;
    private javax.swing.JPanel jPanelResultados;
    private javax.swing.JPanel jPanelVariablesEntrada;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JSpinner spnFrecuenciaClientes;
    private javax.swing.JSpinner spnNumPuestoPalomitas;
    private javax.swing.JSpinner spnNumTaquillas;
    private javax.swing.JSpinner spnProbabilidadCompraEntradas;
    private javax.swing.JSpinner spnProbabilidadCompraPalomitas;
    private javax.swing.JSpinner spnTiempoServicioPalomitas;
    private javax.swing.JSpinner spnTiempoServicioTaquillas;
    private javax.swing.JTextField txtNumMedioAtendidosPuestoPalomitas;
    private javax.swing.JTextField txtNumMedioAtendidosTaquilla;
    private javax.swing.JTextField txtNumPuestosPalomitasOcupados;
    private javax.swing.JTextField txtNumTaquillasOcupadas;
    private javax.swing.JTextField txtTamMedioColasPuestosPalomitas;
    private javax.swing.JTextField txtTamMedioColasTaquilla;
    private javax.swing.JTextField txtTiempoMedioEnCola;
    private javax.swing.JTextArea txtaLog;
    // End of variables declaration//GEN-END:variables

    private CinemaSystem cine;

}
