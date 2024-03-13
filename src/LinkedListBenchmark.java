import java.util.Scanner;

public class LinkedlistBenchmark {
    static Scanner scanner = new Scanner(System.in);
    LLbench quiz = new LLbench();

    public void addQuestion(String question, String correctAnswer){      
        Question q1 = new Question(correctAnswer, question);
        quiz.addNode(q1);

        int tracker = 1;
        q1.setNumberID(tracker);
        quiz.resetNumber();
        tracker++;
    }


    public void deleteQuestion(String id){
        if(quiz.isEmpty()){
            return;
        }
        else{
            quiz.deleteNode(id);
            quiz.resetNumber();
        }
    }


    public void editQuestion(String id, String questionChange, String newQuestion, String answerChange, String newAnswer){
        if(quiz.isEmpty()){
            return;
        }
        else{
            quiz.editNode(id, scanner, questionChange, newQuestion, answerChange, newAnswer);
        }
    }


    public void changeOrder(String id, int newNumber){
        if(quiz.isEmpty()){
            return;
        }
        else{
            quiz.changeNodeOrder(id, scanner, newNumber);
        }
    }


    public void printQuestions(){
        if(quiz.isEmpty()){
            return;
        }
        else{
            quiz.printLinkedList();
        }
    }


    public void questionSearch(String id){
        if(quiz.isEmpty()){
            return;
        }
        else{
            quiz.searchNode(id);
        }
    }


    
}
