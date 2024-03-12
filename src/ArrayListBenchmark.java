import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListBenchmark {

    ArrayList<Question> quiz = new ArrayList<Question>();
    static Scanner scanner = new Scanner(System.in);

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
            for(Question i:quiz){
                if(i.getQuestionID().equals(id)){
                    int j = quiz.indexOf(i);
                    quiz.remove(i);

                    while(j < quiz.size()){
                        quiz.get(j).setQuestionNumber(j+1);
                        j++;
                    }
                    return;
                }
            }
        }
    }


    public void editQuestion(String questionID, String questionChange, String newQuestion, String answerChange, String newAnswer){
        if(quiz.isEmpty()){
            return;
        }
        else{
            for(Question i:quiz){
                if(i.getQuestionID().equals(questionID)){
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
    }


    public void changeOrder(String questionID, int newNumber){
        if(quiz.isEmpty()){;
            return;
        }
        else{
            for(Question i:quiz){
                if(i.getQuestionID().equals(questionID)){
                    if(i.getQuestionNumber() == newNumber){
                        return;
                    }
                    else if(newNumber <= quiz.size() && newNumber > 0){
                        int indexOfi = quiz.indexOf(i);
                        int currentNumber = i.getQuestionNumber();

                        //setting the new number
                        if(currentNumber > newNumber){
                            quiz.add(newNumber-1, i);
                            quiz.remove(indexOfi+1);
                        }
                        else{
                            if(newNumber+1 > quiz.size()){
                                quiz.add(i);
                            }
                            else{
                                quiz.add(newNumber, i);
                            }
                            quiz.remove(indexOfi);
                        }

                        
                        //setting all the question number
                        for(int j=0; j<quiz.size(); j++){
                            quiz.get(j).setQuestionNumber(j+1);
                        }
                    }
                    else if(newNumber > quiz.size()){
                        return;
                    }

                    return;               
                }
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


    public void questionSearch(String questionID){
        if(quiz.isEmpty()){
            return;
        }
        else{
            for(Question i:quiz){
                if(i.getQuestionID().equals(questionID)){
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    return;
                }
            }


        }
    }


    
}
