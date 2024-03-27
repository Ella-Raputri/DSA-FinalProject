import java.util.Stack;
import java.util.Scanner;

public class StackBenchmark {
    Stack<Question> quiz = new Stack<Question>();
    static Scanner scanner = new Scanner(System.in);

    public Question getQuestionfromID(String id){   
        for(Question i: quiz){
            if(i.getQuestionID().equals(id)){
                return i;
            }
        }
        return null;
    }


    public void resetNumber(){
        int tracker = 1;
        for(Question i: quiz){
            i.setQuestionNumber(tracker);
            tracker++;
        }
    }


    public void addQuestion(String question, String correctAnswer){
        Question q1 = new Question(correctAnswer, question);
        quiz.add(q1);
        q1.setNumberID(quiz.indexOf(q1)+1);
    }


    public void deleteQuestion(String id){
        if(quiz.isEmpty()){
            return;
        }
        else{
            Question i = getQuestionfromID(id);
            if(i!= null){
                quiz.remove(i);
                resetNumber();
                return;
            }                    
        }
    }


    public void editQuestion(String id, String questionChange, String newQuestion, String answerChange, String newAnswer){
        if(quiz.isEmpty()){
            return;
        }
        else{
            Question i = getQuestionfromID(id);
            if(i != null){
                if(questionChange.equals("y")){
                    i.setQuestion(newQuestion);
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }

                if(answerChange.equals("y")){
                    i.setCorrectAnswer(newAnswer);
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
        else{
            Question i = getQuestionfromID(id);

            if(i!=null){
                if(i.getQuestionNumber() == newNumber){
                    return;
                }

                else if(newNumber <= quiz.size() && newNumber > 0){                    
                    int currentNumber = i.getQuestionNumber();
                    if(currentNumber < newNumber){
                        quiz.insertElementAt(i, newNumber);
                        quiz.remove(currentNumber-1);
                    }
                    else if (currentNumber > newNumber){
                        quiz.insertElementAt(i, newNumber-1);
                        quiz.remove(currentNumber);
                    }

                    resetNumber();
                }
                    

                else if(newNumber > quiz.size() || newNumber < 0) {
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
            System.out.println("\nYour current quiz: ");
            for(Question i: quiz){
                System.out.println("Question " + i.getQuestionNumber());
                System.out.println("Question ID: "+ i.getQuestionID());
                System.out.println("Question: "+ i.getQuestion());
                System.out.println("Answer: "+ i.getCorrectAnswer());
                System.out.println();
            }
        }
    }


    public void questionSearch(String str){
        if(quiz.isEmpty()){
            return;
        }
        else{
            boolean track = false;
            
            for(Question i: quiz){
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();
                if(question.contains(str) || answer.contains(str)){
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true;
                }
            }

            if(track){
                return;
            }
            else{
                System.out.println("No question or answer with such string.");
            }
        }
    }


}
