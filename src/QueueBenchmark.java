import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class QueueBenchmark {
    Queue<Question> quiz = new LinkedList<Question>();
    static Scanner scanner = new Scanner(System.in);

    public Question[] returnQuestions(){
        Question[] questions = new Question[250];
        int index=0;

        for(Question i: quiz){
            questions[index] = i;
            index +=1;
        }
        return questions;
    }

    public String returnID(int number){
        for(Question i:quiz){
            if(i.getQuestionNumber() == number){
                return i.getQuestionID();
            }
        }
        return "";
    }

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
        q1.setNumberID(quiz.size());
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
                    ArrayList<Question> temp = new ArrayList<>();
                    temp.addAll(quiz);
                    quiz.clear();

                    for(Question question:temp){
                        if(!(question.equals(i))){
                            if(temp.indexOf(question)+1 == newNumber){
                                if(currentNumber < newNumber){
                                    quiz.add(question);
                                    quiz.add(i);
                                }
                                else if (currentNumber > newNumber){
                                    quiz.add(i);
                                    quiz.add(question);
                                }  
                            }
                            else{
                                quiz.add(question);
                            } 
                        }
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
