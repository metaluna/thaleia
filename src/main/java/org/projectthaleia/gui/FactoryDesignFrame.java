/*
 * The MIT License
 *
 * Copyright 2011 Simon Hardijanto
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.projectthaleia.gui;

/**
 *
 * @author Simon Hardijanto
 */
public class FactoryDesignFrame extends javax.swing.JFrame
{

  /** Creates new form FactoryDesignFrame */
  public FactoryDesignFrame()
  {
    initComponents();
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jComboBox1 = new javax.swing.JComboBox();
    jLabel1 = new javax.swing.JLabel();
    jComboBox2 = new javax.swing.JComboBox();
    jLabel2 = new javax.swing.JLabel();
    jComboBox3 = new javax.swing.JComboBox();
    jLabel3 = new javax.swing.JLabel();
    jComboBox4 = new javax.swing.JComboBox();
    jLabel4 = new javax.swing.JLabel();
    jComboBox5 = new javax.swing.JComboBox();
    jLabel5 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();

    setTitle("Food Factory Designer");
    setResizable(false);

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Land animal", "Fish", "Grain", "Algae" }));

    jLabel1.setText("Factory Type");

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Earth", "Thaleia", "Beteigeuze" }));

    jLabel2.setText("Planet");

    jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Speed x 1.25, Quality x 0.75", "Normal", "Speed x 0.75, Quality x 1.25" }));
    jComboBox3.setSelectedIndex(1);

    jLabel3.setText("Processing");

    jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "90%", "85%", "80%", "75%" }));

    jLabel4.setText("Yield");

    jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liquid nitrogen (x 3)", "Conventional refrigerator (x 2)", "Tetrapak (x 1)", "Bags/Crates (x 0.5)" }));

    jLabel5.setText("Packaging");

    jButton1.setText("OK");
    jButton1.setMaximumSize(new java.awt.Dimension(65, 23));
    jButton1.setMinimumSize(new java.awt.Dimension(65, 23));

    jButton2.setText("Cancel");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jLabel1)
              .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jComboBox1, 0, 213, Short.MAX_VALUE)))
          .addGroup(layout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jLabel3)
              .addComponent(jLabel4)
              .addComponent(jLabel5))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jComboBox4, 0, 213, Short.MAX_VALUE)
              .addComponent(jComboBox3, 0, 213, Short.MAX_VALUE)
              .addComponent(jComboBox5, 0, 213, Short.MAX_VALUE)))
          .addGroup(layout.createSequentialGroup()
            .addGap(73, 73, 73)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(34, 34, 34)
            .addComponent(jButton2)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton2)
          .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public static void main(String args[])
  {
    java.awt.EventQueue.invokeLater(new Runnable()
    {

      public void run()
      {
        new FactoryDesignFrame().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JComboBox jComboBox1;
  private javax.swing.JComboBox jComboBox2;
  private javax.swing.JComboBox jComboBox3;
  private javax.swing.JComboBox jComboBox4;
  private javax.swing.JComboBox jComboBox5;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  // End of variables declaration//GEN-END:variables
}
