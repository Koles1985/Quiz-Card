/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizcard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author oreho
 */
public class QuizCardBuilder{
    
    private JFrame frame = new JFrame("Quiz Card Builder");
    private JLabel question = new JLabel("Question");
    private JLabel answer = new JLabel("Answer");
    private JButton nextCard = new JButton("Next Card");
    private JPanel border_1 = new JPanel();
    private JTextArea questions = new JTextArea(6,20);
    private JTextArea answers = new JTextArea(6,20);
    private Font bigFont = new Font("sunserif", Font.BOLD, 24);
    private JScrollPane qScroll = new JScrollPane(questions);;
    private JScrollPane aScroll = new JScrollPane(answers);
    private ArrayList<QCard> cardList = new ArrayList<QCard>();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem newMenuItem = new JMenuItem("New");
    private JMenuItem saveMenuItem = new JMenuItem("Save");
    private JPanel questPanel = new JPanel();
    private JPanel answerPanel = new JPanel();
    
    
    public void go(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Quiz Card Builder");
        
        questions.setLineWrap(true);
        questions.setWrapStyleWord(true);
        questions.setFont(bigFont);
        
        qScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        answers.setLineWrap(true);
        answers.setWrapStyleWord(true);
        answers.setFont(bigFont);
        
        aScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        border_1.add(question);
        border_1.add(qScroll);
        border_1.add(answer);
        border_1.add(aScroll);
        border_1.add(questPanel);
        border_1.add(answerPanel);
        border_1.add(nextCard);
        
        nextCard.addActionListener(new NextCardListener());
        newMenuItem.addActionListener(new NewMenuItemListener());
        saveMenuItem.addActionListener(new SaveMenuItemListener());
        
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        
        
        
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, border_1);
        frame.setSize(500,600);
        frame.setVisible(true);
    }

   public class NextCardListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QCard card = new QCard(questions.getText(), answers.getText());
            cardList.add(card);
            clearCard();
        }
       
   }
   
   public class SaveMenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QCard card = new QCard(questions.getText(), answers.getText());
            cardList.add(card);
            
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
   }
   
   public class NewMenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cardList.clear();
            clearCard();
        }
       
   }
   
   private void clearCard(){
       questions.setText("");
       answers.setText("");
       question.requestFocus();
   }
   
   private void saveFile(File file){
       try{
           BufferedWriter writer = new BufferedWriter(new FileWriter(file));
           for(QCard card : cardList){
               writer.write(card.getQuestion() + "/");
               writer.write(card.getAnswer() + "\n");
           }
           writer.close();
       }catch(IOException e){
           System.out.println("couldn't write the cardList out.");
           e.printStackTrace();
       }
   }
}
