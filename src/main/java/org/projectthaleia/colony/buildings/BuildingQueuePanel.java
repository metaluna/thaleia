/*
 * The MIT License
 *
 * Copyright 2012 Simon Hardijanto.
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
package org.projectthaleia.colony.buildings;

/**
 *
 * @author Simon Hardijanto
 */
public class BuildingQueuePanel extends javax.swing.JPanel
{
  
  /**
   * Sets the building queue to display.
   * @param _buildingQueue the building queue
   */
  public void setBuildingQueue(final BuildingQueue _buildingQueue)
  {
    if (_buildingQueue == null) {
      throw new NullPointerException("Cannot set building queue of "
                                + "building queue panel " + this + " to null.");
    }
    this.buildingQueue = _buildingQueue;
    
    this.initActiveQueuePanels();
    this.initInactiveQueuePanels();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jSplitPane1 = new javax.swing.JSplitPane();
        inactiveBuildingsScrollPane = new javax.swing.JScrollPane();
        inactiveBuildingsPanel = new javax.swing.JPanel();
        activeBuildingsScrollPane = new javax.swing.JScrollPane();
        activeBuildingsPanel = new javax.swing.JPanel();
        newBuildingPanel = new javax.swing.JPanel();
        newBuildingTypesComboBox = new javax.swing.JComboBox();
        newBuildingMultiplyLbl = new javax.swing.JLabel();
        newBuildingAmountSpinner = new javax.swing.JSpinner();
        newBuildingBtn = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Produktion"));

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        inactiveBuildingsScrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inactiveBuildingsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        inactiveBuildingsPanel.setLayout(new javax.swing.BoxLayout(inactiveBuildingsPanel, javax.swing.BoxLayout.LINE_AXIS));
        inactiveBuildingsScrollPane.setViewportView(inactiveBuildingsPanel);

        jSplitPane1.setRightComponent(inactiveBuildingsScrollPane);

        activeBuildingsScrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        activeBuildingsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        activeBuildingsPanel.setLayout(new javax.swing.BoxLayout(activeBuildingsPanel, javax.swing.BoxLayout.LINE_AXIS));
        activeBuildingsScrollPane.setViewportView(activeBuildingsPanel);

        jSplitPane1.setLeftComponent(activeBuildingsScrollPane);

        newBuildingTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fabrik", "Labor", "Mine", "Werft" }));
        newBuildingPanel.add(newBuildingTypesComboBox);

        newBuildingMultiplyLbl.setText("x");
        newBuildingPanel.add(newBuildingMultiplyLbl);

        newBuildingAmountSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        newBuildingPanel.add(newBuildingAmountSpinner);

        newBuildingBtn.setText("Hinzufügen");
        newBuildingPanel.add(newBuildingBtn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newBuildingPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newBuildingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  /**
   * Creates new form BuildingQueuePanel
   */
  BuildingQueuePanel()
  {
    initComponents();
  }
  
  //----------------  PRIVATE  ----------------
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel activeBuildingsPanel;
    private javax.swing.JScrollPane activeBuildingsScrollPane;
    private javax.swing.JPanel inactiveBuildingsPanel;
    private javax.swing.JScrollPane inactiveBuildingsScrollPane;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSpinner newBuildingAmountSpinner;
    private javax.swing.JButton newBuildingBtn;
    private javax.swing.JLabel newBuildingMultiplyLbl;
    private javax.swing.JPanel newBuildingPanel;
    private javax.swing.JComboBox newBuildingTypesComboBox;
    // End of variables declaration//GEN-END:variables
    
  private BuildingQueue buildingQueue;

  private void initActiveQueuePanels()
  {
    for (IQueueable building : this.buildingQueue.getActiveBuildings()) {
      QueueablePanel panel = this.constructQueueablePanel(building);
      this.activeBuildingsPanel.add(panel);
    }
  }

  private void initInactiveQueuePanels()
  {
    for (IQueueable building : this.buildingQueue.getInactiveBuildings()) {
      QueueablePanel panel = this.constructQueueablePanel(building);
      this.inactiveBuildingsPanel.add(panel);
    }
  }
  
  private QueueablePanel constructQueueablePanel(final IQueueable _building) 
  {
    final QueueablePanel panel = new QueueablePanel(_building);
    return panel;
  }
}