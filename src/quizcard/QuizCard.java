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
public class QuizCard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuizCardBuilder qcb = new QuizCardBuilder();
        qcb.go();
        QuizCardPlayer qcp = new QuizCardPlayer();
        qcp.go();
    }
    
}
