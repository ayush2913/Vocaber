package com.vocaber;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

public class VocaberApplication {

   private JFrame mainFrame;
   private JTextArea inputText;
   private JTextArea suggestionText;
   private JPanel controlPanel;
   private JLabel footer;
   String text;

   public VocaberApplication(){
      text="";
      inputText=new JTextArea(20,20);
      suggestionText=new JTextArea(20,20);
   }

   public static void main(String[] args){
	   VocaberApplication Ob = new VocaberApplication();  
	   Ob.prepareGUI();
      Ob.showEventDemo();       
   }
      
   private void prepareGUI(){
      mainFrame = new JFrame("V O C A B E R");
      mainFrame.setSize(800,800);
      mainFrame.setLayout(new GridBagLayout());   
      
      inputText.setEditable(true);
      suggestionText.setEditable(false);
      
      JScrollPane scrollPane1 = new JScrollPane(inputText,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      
      JScrollPane scrollPane2 = new JScrollPane(suggestionText,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      
      footer = new JLabel("",JLabel.CENTER);
      footer.setText("prW pvt. ltd.");

      GridBagConstraints gridCons1 = new GridBagConstraints();
      gridCons1.fill=GridBagConstraints.HORIZONTAL;
      gridCons1.weightx = 1.0;
      gridCons1.weighty = 1.0;
      gridCons1.gridx=0;
      gridCons1.gridy=0;
      gridCons1.ipadx=10;
      gridCons1.ipady=10;
      
      GridBagConstraints gridCons2 = new GridBagConstraints();
      gridCons2.fill=GridBagConstraints.HORIZONTAL;
      gridCons2.weightx = 1.0;
      gridCons2.weighty = 1.0;
      gridCons2.gridx=0;
      gridCons2.gridy=1;
      
      GridBagConstraints gridCons3 = new GridBagConstraints();
      gridCons3.fill=GridBagConstraints.HORIZONTAL;
      gridCons3.weightx = 1.0;
      gridCons3.weighty = 1.0;
      gridCons3.gridx=0;
      gridCons3.gridy=2;
      gridCons3.ipadx=10;
      gridCons3.ipady=10;
      
      GridBagConstraints gridCons4 = new GridBagConstraints();
      gridCons4.fill=GridBagConstraints.HORIZONTAL;
      gridCons4.weightx = 1.0;
      gridCons4.weighty = 1.0;
      gridCons4.gridx=0;
      gridCons4.gridy=3;
      gridCons4.ipadx=10;
      gridCons4.ipady=10;
      
      
      mainFrame.add(inputText,gridCons1);
      mainFrame.add(controlPanel,gridCons2);
      mainFrame.add(suggestionText,gridCons3);
      mainFrame.add(footer,gridCons4);
      mainFrame.setVisible(true);  
   }

   private void showEventDemo(){

      JButton getSuggestion = new JButton("-->");

      getSuggestion.setActionCommand("-->");

      getSuggestion.addActionListener(new ButtonClickListener()); 

      controlPanel.add(getSuggestion); 

      mainFrame.setVisible(true);  
   }

   private class ButtonClickListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();  
         if( command.equals("-->"))  {
        	 
        	 MainVocaber sug=new MainVocaber(inputText.getText());
        	 try {
				sug.getparsedString();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            suggestionText.setText(sug.suggestions);
         }
      }	
   }
}