//import Java APIs
import java.util.Stack;
import java.util.Scanner;

public class StackBenchmark {
    //initialise a stack for the Questions
    Stack<Question> quiz = new Stack<Question>();
    static Scanner scanner = new Scanner(System.in); //static scanner for user input

    public Question getQuestionfromID(String id){   
        for(Question i: quiz){
            //check for all Questions in the stack
            //if its ID is the same as the searched one
            if(i.getQuestionID().equals(id)){
                //return the Question
                return i;
            }
        }
        return null; //if no Question with the same ID is found
    }


    public void resetNumber(){
        int tracker = 1;
        //for all Questions in the stack, reset the number
        for(Question i: quiz){
            //renumber it based on the order 
            i.setQuestionNumber(tracker);
            tracker++;
        }
    }


    public void addQuestion(String question, String correctAnswer){
        //create a new Question object based on the given question and answer
        Question q1 = new Question(correctAnswer, question);
        //add the Question to the stack
        quiz.add(q1);
        //set the ID number of the Question
        q1.setNumberID(quiz.indexOf(q1)+1);
    }


    public void deleteQuestion(String id){
        //if the stack is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i!= null){
                //remove that Question
                quiz.remove(i);
                //reset all the Question number
                resetNumber();
                return;
            }                    
        }
    }


    public void editQuestion(String id, String questionChange, String newQuestion, String answerChange, String newAnswer){
        //if the stack is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i != null){
                //if user wants to change the question
                if(questionChange.equals("y")){
                    //set the question based on the new question
                    i.setQuestion(newQuestion);
                }
                //if the input is invalid, then return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //set the answer based on the new given answer
                    i.setCorrectAnswer(newAnswer);
                }
                //if the input is invalid, then return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
                
                return;
            }
        }
    }


    public void changeOrder(String id, int newNumber){
        //if the stack is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            Question i = getQuestionfromID(id);
            //if there exists such a question 
            if(i!=null){
                //if the new number is the same as the current number, return
                if(i.getQuestionNumber() == newNumber){
                    return;
                }

                //if the new number is valid
                else if(newNumber <= quiz.size() && newNumber > 0){ 
                    //get the current number                   
                    int currentNumber = i.getQuestionNumber();
                    //if the current number is less than the new number
                    if(currentNumber < newNumber){
                        //insert the Question into the new number
                        quiz.insertElementAt(i, newNumber);
                        //remove the old Question
                        quiz.remove(currentNumber-1);
                    }
                    //if the current number is greater than the new number
                    else if (currentNumber > newNumber){
                        //insert the Question at new number -1
                        quiz.insertElementAt(i, newNumber-1);
                        //remove the old Question
                        quiz.remove(currentNumber);
                    }

                    //reset all the question number
                    resetNumber();
                }
                    
                //if new number is not valid, then return
                else if(newNumber > quiz.size() || newNumber < 0) {
                    return;
                }

                return;
            }
        }
    }


    public void printQuestions(){
        //if the stack is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            System.out.println("\nYour current quiz: ");
            for(Question i: quiz){
                //print all the attributes of each Question
                System.out.println("Question " + i.getQuestionNumber());
                System.out.println("Question ID: "+ i.getQuestionID());
                System.out.println("Question: "+ i.getQuestion());
                System.out.println("Answer: "+ i.getCorrectAnswer());
                System.out.println();
            }
        }
    }


    public void questionSearch(String str){
        //if the stack is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //variable to track whether exists a search result
            boolean track = false;
            
            for(Question i: quiz){
                //get the question and answer
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str) || i.getQuestionID().equals(str)){
                    //print that Question attributes
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true; //set the track to true
                }
            }
            //if track is true then return
            if(track){
                return;
            }
            //if false, then there is no search result, print the error message
            else{
                System.out.println("No question or answer with such string.");
            }
        }
    }


}
