import java.util.Scanner;

public class LinkedlistBenchmark {
    static Scanner scanner = new Scanner(System.in);
    Linkedlist quiz = new Linkedlist();

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
            Linkedlist.Node current = quiz.getNode(id);

            if(current!= null){
                quiz.deleteNode(current);
                quiz.resetNumber();
                return;
            }
            
        }
    }


    public void editQuestion(String id, String questionChange, String newQuestion, String answerChange, String newAnswer){
        if(quiz.isEmpty()){
            return;
        }
        else{
            Linkedlist.Node current = quiz.getNode(id);
            if (current != null){
                if(questionChange.equals("y")){
                    current.data.setQuestion(newQuestion);
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
    
                if(answerChange.equals("y")){
                    current.data.setCorrectAnswer(newAnswer);
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
    
                return;
            }

        }
    }


    public void changeOrder(String id, int newNumber){
        if(quiz.isEmpty()){
            return;
        }
        else if(quiz.head.equals(quiz.tail)){
            return;
        }
        else{
            Linkedlist.Node current = quiz.getNode(id);
            if(current != null){
                int currentNumber = current.data.getQuestionNumber();

                if(currentNumber == newNumber){
                    return;
                }

                else if(newNumber <= Linkedlist.nodeAmount && newNumber > 0){
                    quiz.changeNodeOrder(newNumber, current, currentNumber);
                    quiz.resetNumber();
                }

                else if(newNumber > Linkedlist.nodeAmount || newNumber < 0){
                    return;
            }

            return;
        }

        }
    }


    public void printQuestions(){
        if(quiz.isEmpty()){
            return;
        }
        else{
            Linkedlist.Node current = quiz.head;
            System.out.println("\nYour current quiz: ");
            while(current!=null){
                System.out.println("Question " + current.data.getQuestionNumber());
                System.out.println("Question ID: "+ current.data.getQuestionID());
                System.out.println("Question: "+ current.data.getQuestion());
                System.out.println("Answer: "+ current.data.getCorrectAnswer());
                System.out.println();
                current = current.next;
            }
            
        }
    }


    public void questionSearch(String id){
        if(quiz.isEmpty()){
            return;
        }
        else{
            Linkedlist.Node current = quiz.getNode(id);
        
            if(current != null){
                System.out.println("Question " + current.data.getQuestionNumber());
                System.out.println("Question ID: "+ current.data.getQuestionID());
                System.out.println("Question: "+ current.data.getQuestion());
                System.out.println("Answer: "+ current.data.getCorrectAnswer());
                System.out.println();
                return;
            }
        }
    }


}
