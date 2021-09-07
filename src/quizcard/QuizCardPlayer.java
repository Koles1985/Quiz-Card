/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizcard;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author oreho
 */
public class QuizCardPlayer {
    
    private JFrame frame = new JFrame("Quiz Card Player");
    private JTextArea answer = new JTextArea(6,20);
    private JTextArea display = new JTextArea(6,20);
    private ArrayList<QCard> cardList;
    private QCard qcard;
    private int qcardIndex;
    private JButton nextButton = new JButton("Show Question");
    private boolean isShowAnswer;
    private JPanel mainPanel = new JPanel();
    private Font bigFont = new Font("sanserif", Font.BOLD, 24);
    private JScrollPane dScroll;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem loudItem = new JMenuItem("Loud");
        
    
    
    public void go(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setEditable(false);
        
        dScroll = new JScrollPane(display);
        dScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        dScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        nextButton.addActionListener(new NextCardListener());
        
        mainPanel.add(dScroll);
        mainPanel.add(nextButton);
        
        loudItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loudItem);
        menuBar.add(fileMenu);
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500,600);
        frame.setVisible(true);
    }
    
    public class NextCardListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(isShowAnswer){
                display.setText(qcard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            }else{
                if(qcardIndex < cardList.size()){
                    showNextCard();
                }else{
                    display.setText("That was last card");
                    nextButton.setEnabled(false);
                }
            }
               
        }
        
    }
    
    public class OpenMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loudFile(fileOpen.getSelectedFile());  
        }
        
    }
    
    private void loudFile(File file){
        cardList = new ArrayList<QCard>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null){
                makeCard(line);
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Couldn't read the card file!");
            e.printStackTrace();
        }
        showNextCard();
    }
    
    private void makeCard(String lineToParse){
        String[] result = lineToParse.split("/");
        QCard card = new QCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }
    
    private void showNextCard(){
        qcard = cardList.get(qcardIndex);
        qcardIndex++;
        display.setText(qcard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }
}
