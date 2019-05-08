package ClienteVisao;

import Controle.ClienteControle;
import java.net.InetAddress;


public class ClienteVisao extends javax.swing.JFrame {
    
    String nick;
    InetAddress IP;
    int porta;
    ClienteControle cc;

    public ClienteVisao() {
        initComponents();
    }

    public ClienteVisao(ClienteControle cc, String nick, InetAddress IP, int porta) {
        this.setLocationRelativeTo(null);
        this.setTitle("Cliente - ["+nick+"]");
        initComponents();
        
        this.cc = cc;
        this.nick = nick;
        this.IP = IP;
        this.porta = porta;
        jLNick.setText("~"+nick);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSPLog = new javax.swing.JScrollPane();
        jTALog = new javax.swing.JTextArea();
        jBSair = new javax.swing.JButton();
        jSPLista = new javax.swing.JScrollPane();
        jTLista = new javax.swing.JTable();
        jSPMensagem = new javax.swing.JScrollPane();
        jTAMensagem = new javax.swing.JTextArea();
        jBEnviar = new javax.swing.JButton();
        jLNick = new javax.swing.JLabel();
        jCBBroadcast = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTALog.setColumns(20);
        jTALog.setRows(5);
        jSPLog.setViewportView(jTALog);
        jTALog.getAccessibleContext().setAccessibleName("");

        jBSair.setText("Sair");
        jBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSairActionPerformed(evt);
            }
        });

        jTLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IP", "Porta", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jSPLista.setViewportView(jTLista);

        jTAMensagem.setColumns(20);
        jTAMensagem.setRows(5);
        jSPMensagem.setViewportView(jTAMensagem);

        jBEnviar.setText("ENVIAR");
        jBEnviar.setAlignmentY(0.0F);
        jBEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEnviarActionPerformed(evt);
            }
        });

        jLNick.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLNick.setText("~Nick");

        jCBBroadcast.setText("Broadcast");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSPMensagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEnviar))
                    .addComponent(jSPLog, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLNick)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCBBroadcast)
                        .addGap(58, 58, 58)
                        .addComponent(jBSair))
                    .addComponent(jSPLista, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBSair, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBBroadcast)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLNick)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSPLog, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jSPMensagem)))
                    .addComponent(jSPLista, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        cc.enviarMsg("5#");
        this.dispose();
    }//GEN-LAST:event_jBSairActionPerformed

    private void jBEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnviarActionPerformed
        String msg = jTAMensagem.getText();
        jTAMensagem.setText("");
        
        if((jTLista.getSelectedRow() == -1) || jCBBroadcast.isSelected()){
            cc.enviarMsg("3#999.999.999.999#99999#"+msg);
        }
        else{
            String ip = ((String) jTLista.getValueAt(jTLista.getSelectedRow(), 0));
            ip = ip.replace("/", "");
            String port = ((String) jTLista.getValueAt(jTLista.getSelectedRow(), 1));
            cc.enviarMsg("3#"+ip+"#"+port+"#"+msg);
        }
    }//GEN-LAST:event_jBEnviarActionPerformed

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
            java.util.logging.Logger.getLogger(ClienteVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteVisao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBEnviar;
    private javax.swing.JButton jBSair;
    private javax.swing.JCheckBox jCBBroadcast;
    private javax.swing.JLabel jLNick;
    private javax.swing.JScrollPane jSPLista;
    private javax.swing.JScrollPane jSPLog;
    private javax.swing.JScrollPane jSPMensagem;
    private javax.swing.JTextArea jTALog;
    private javax.swing.JTextArea jTAMensagem;
    private javax.swing.JTable jTLista;
    // End of variables declaration//GEN-END:variables

    public void atualizarLista(String mensagem) {
        String [] x = mensagem.split("#");
        int tam = x.length;
        String linha[] = new String[tam];
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) jTLista.getModel();
        dtm.setNumRows(0); //limpa tabela
        int i=1;
        while(i<tam){
            for(int j=0; j<3; j++){
                linha[j] = x[i];
                i++;
            }
            dtm.addRow(linha);
        }
    }

    public void adicionarMensagem(String mensagem) {
        String [] x = mensagem.split("#");
        jTALog.setText(jTALog.getText()+x[1]+" - "+x[2]+" Diz: \n"+x[3]+"\n\n");
    }
}
