/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nuggets_apnea;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.util.Random;
/**
 *
 * @author seabirds
 */
public class MainFrame1 extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    String val[][];
    String cls[];
    int featSize;
    ArrayList clsFeat=new ArrayList();
    ArrayList score=new ArrayList();
    double R=0.25;
    public MainFrame1() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 250, 250));

        jLabel1.setFont(new java.awt.Font("Century Schoolbook", 0, 30)); // NOI18N
        jLabel1.setText("Identifying Critical Sleep Apnea");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Century Schoolbook", 0, 18)); // NOI18N
        jLabel2.setText("Choose Train Set");

        jTextField1.setFont(new java.awt.Font("Century Schoolbook", 0, 15)); // NOI18N

        jButton1.setFont(new java.awt.Font("Century Schoolbook", 0, 15)); // NOI18N
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Century Schoolbook", 0, 15)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setFont(new java.awt.Font("Century Schoolbook", 0, 15)); // NOI18N
        jButton2.setText("Find Neighborhood of Data");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(272, 272, 272))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try
        {
            JFileChooser ch1=new JFileChooser();
            int v=ch1.showOpenDialog(this);

            if (v == JFileChooser.APPROVE_OPTION)
            {
		String path = ch1.getSelectedFile().getPath();
		jTextField1.setText(path);
                File fe=new File(path);
                FileInputStream fis=new FileInputStream(fe);
                byte data[]=new byte[fis.available()];
                fis.read(data);
                fis.close();
                
                jTextArea1.setText(new String(data));
                String g1[]=new String(data).split("\n");
                String g2[]=g1[0].split("\t");
                
                featSize=g2.length-1; // remove cls
                
               /*String g3[]=g1[0].split(" ");
                for(int i=0;i<g3.length;i++)
                {
                    featName.add(g3[i]);
                }*/
                        
                
                val=new String[g1.length-1][g2.length-1];
                //cls=new String[val.length-1];
                cls=new String[val.length];
                
                
                int k=0;
                for(int i=1;i<g1.length;i++)
                {
                    String s[]=g1[i].split("\t");
                    for(int j=0;j<s.length-1;j++)
                    {
                        String sk=s[j].trim();
                        val[k][j]=sk;
                    }
                    cls[k]=s[s.length-1].trim();
                    k++;
                }
                
                for(int i=0;i<cls.length;i++)
                {
                   // System.out.println(cls[i]);
                    if(!(clsFeat.contains(cls[i])))
                            clsFeat.add(cls[i]);
                        
                }
                System.out.println(clsFeat);
                System.out.println(val.length+" : "+cls.length);
              /*  for(int i=0;i<val.length;i++)
                {
                   for(int j=0;j<val[0].length;j++)
                    {
                        System.out.print(" "+val[i][j]);
                    }
                   System.out.println();
                }
                
                for(int i=0;i<cls.length;i++)
                {
                    System.out.print(" "+cls[i]);
                }
                System.out.println();*/
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            Random rn=new Random();
            int center=rn.nextInt(val.length);
            String cnCls=cls[center];
            System.out.println("center "+center);
            System.out.println("center class "+cnCls);
            String cn1[]=val[center];
            ArrayList N=new ArrayList();
            int cnt1=0;
            for(int i=0;i<cls.length;i++)
            {
                if(cls[i].equals(cnCls))
                {
                    cnt1++;
                }
            }
            String nn="";
            for(int i=0;i<cls.length;i++)
            {
                if(cls[i].equals(cnCls))
                {
                    String cn2[]=val[i];
                    double e1=0;
                    for(int j=0;j<cn1.length;j++)
                    {
                        double d1=Double.parseDouble(cn1[j])-Double.parseDouble(cn2[j]);
                        e1=e1+(d1*d1);
                       // e1=e1+Math.pow((Double.parseDouble(cn1[j])-Double.parseDouble(cn2[j])),2);
                    }
                    double e2=Math.sqrt(e1)/(double)cnt1;
                    if(e2<=R)
                    {
                        N.add(i);
                        System.out.println(i+" : "+e2);
                        //nn=nn+val[i]+"\n";
                        for(int k=0;k<val[0].length;k++)
                            nn=nn+val[i][k]+"\t";
                        
                        nn=nn.trim()+"\t"+cls[i]+"\n";
                    }
                    
                    //System.out.println(i+" : "+e2+" :  " +(double)e2/(double)cnt1);
                }
            }
            nn=nn.trim();
           System.out.println(val.length+"  :  "+N.size());
           NDataFrame nd=new NDataFrame(this,cnCls);
           nd.setVisible(true);
           nd.setTitle("Neighborhood Data");
           nd.setResizable(false);
           nd.jTextArea1.setText(nn);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
