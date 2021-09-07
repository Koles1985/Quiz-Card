/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizcard;

/**
 *
 * @author oreho
 */
public class QCard {
    private String question;
    private String answer;
    
    public QCard(String q, String a){
        question = q;
        answer = a;
    }
    
    public String getQuestion(){
        return question;
    }
    
    public String getAnswer(){
        return answer;
    }
    
    public void setQuestion(String s){
        question = s;
    }
    
    public void setAnswer(String s){
        answer = s;
    }
}
