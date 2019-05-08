package ServidorVisao;


public class ServidorVisao extends javax.swing.JFrame {

    public ServidorVisao() {
        this.setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSPLog = new javax.swing.JScrollPane();
        jTALog = new javax.swing.JTextArea();
        jLLog = new javax.swing.JLabel();
        jSPUsuarios = new javax.swing.JScrollPane();
        jTUsuarios = new javax.swing.JTable();
        jLUsuarios = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor");

        jTALog.setColumns(20);
        jTALog.setRows(5);
        jSPLog.setViewportView(jTALog);

        jLLog.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLLog.setText("LOG");

        jTUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IP", "Porta", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jSPUsuarios.setViewportView(jTUsuarios);

        jLUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLUsuarios.setText("Usuários Conectados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSPLog, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLLog)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSPUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLUsuarios))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLLog)
                    .addComponent(jLUsuarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSPUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(jSPLog))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServidorVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorVisao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLLog;
    private javax.swing.JLabel jLUsuarios;
    private javax.swing.JScrollPane jSPLog;
    private javax.swing.JScrollPane jSPUsuarios;
    private javax.swing.JTextArea jTALog;
    private javax.swing.JTable jTUsuarios;
    // End of variables declaration//GEN-END:variables


    public void mostraMsg(String msg) {
        String tmp = jTALog.getText();
        jTALog.setText(tmp + msg + "\n");
        System.out.println(msg);
    }

    public String atualizaLista(){
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) jTUsuarios.getModel();
        String lista ="2";
        for (int i = 0; i < jTUsuarios.getRowCount(); i++) {
            lista += "#"+((String)dtm.getValueAt(i, 0)+"#"+(String)dtm.getValueAt(i, 1)+"#"+(String)dtm.getValueAt(i, 2));
        }
        return lista;
        //retorna 2#IPCLIENTE1#PORTACLIENTE1#NOMECLIENTE1#IPCLIENTE2#PORTACLIENTE2#NOMECLIENTE2...
    }
    
    public String adicionaLista(String string) {
        //recebe 1#NOMECLIENTE1#IPCLIENTE1#PORTACLIENTE1
        String [] x = string.split("#");
        x[2] = x[2].replace("/", "");
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) jTUsuarios.getModel();
        String[] linha = {"" + x[2], "" + x[3], "" + x[1]};
        dtm.addRow(linha);
        
        return atualizaLista();
    }
    
    public String removeCliente(String IPparam, int Portaparam) {
        String ipparam = String.valueOf(IPparam);
        String portaparam = String.valueOf(Portaparam);
        
        for (int i = 0; i < jTUsuarios.getRowCount(); i++) {
            String ip = jTUsuarios.getValueAt(i, 0).toString();
            String porta = jTUsuarios.getValueAt(i, 1).toString();
            
            if(ip.equals(ipparam) && porta.equals(portaparam)){
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) jTUsuarios.getModel();
                dtm.removeRow(i);
            }
        }
        return atualizaLista();
    }
}
