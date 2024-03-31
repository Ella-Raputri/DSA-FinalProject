//import APIs
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class QueueBenchmark {
    //initialize a queue that was implemented using linked list
    Queue<Question> quiz = new LinkedList<Question>();
    static Scanner scanner = new Scanner(System.in); //a static scanner for user input

    public Question[] returnQuestions(){
        //to add all the questions in the queue to an array
        //initialize the array
        Question[] questions = new Question[250];
        int index=0;

        //add the questions to the array
        for(Question i: quiz){
            questions[index] = i;
            index +=1;
        }
        return questions;
    }

    public String returnID(int number){
        for(Question i:quiz){
            //check for all Questions, if the question number is the same
            if(i.getQuestionNumber() == number){
                //then return the ID of the Question
                return i.getQuestionID();
            }
        }
        return ""; //if there is no such question number
    }

    public Question getQuestionfromID(String id){   
        for(Question i: quiz){
            //check for all Questions, if the question ID is the same
            if(i.getQuestionID().equals(id)){
                //then return the Question object
                return i;
            }
        }
        return null; //if there is no such Question
    }


    public void resetNumber(){
        int tracker = 1;
        //reset the question number in order
        for(Question i: quiz){
            i.setQuestionNumber(tracker);
            tracker++;
        }
    }


    public void addQuestion(String question, String correctAnswer){
        //create a new Question object based on the inputted question and answer
        Question q1 = new Question(correctAnswer, question);
        //add the Question to the queue
        quiz.add(q1);
        //set the ID number of the Question
        q1.setNumberID(quiz.size());
    }


    public void deleteQuestion(String id){
        //if the queue is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i!= null){
                //remove or delete the Question
                quiz.remove(i);
                //reset all the question number
                resetNumber();
                return;
            }                    
        }
    }


    public void editQuestion(String id, String questionChange, String newQuestion, String answerChange, String newAnswer){
        //if the queue is empty then return
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
                //if the input is invalid, return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }

                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //set the answer based on the inputted answer
                    i.setCorrectAnswer(newAnswer);
                }
                //if the input is invalid, return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
                
                return;
            }
        }
    }


    public void changeOrder(String id, int newNumber){
        //if the queue is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i!=null){
                //if the old number and new number are the same, return
                if(i.getQuestionNumber() == newNumber){
                    return;
                }

                //if the new number is valid
                else if(newNumber <= quiz.size() && newNumber > 0){
                    //get current number
                    int currentNumber = i.getQuestionNumber();
                    //add all the Question in the queue to a temporary ArrayList
                    ArrayList<Question> temp = new ArrayList<>();
                    temp.addAll(quiz);
                    //clear the queue
                    quiz.clear();

                    for(Question question:temp){
                        //if the Question is not the one that want to change
                        if(!(question.equals(i))){
                            //if that Question has the number of new number
                            if(temp.indexOf(question)+1 == newNumber){
                                //if new number is greater than current number, then
                                if(currentNumber < newNumber){
                                    //add that Question first before the want to change one
                                    quiz.add(question);
                                    quiz.add(i);
                                }
                                else if (currentNumber > newNumber){
                                    //else, add the want to change Question first
                                    quiz.add(i);
                                    quiz.add(question);
                                }  
                            }
                            //if the Question doesn't have the number of new number
                            else{
                                //then simply add it to the queue
                                quiz.add(question);
                            } 
                        }
                    }
                    //reset all the Question number based on their order
                    resetNumber();
                }
                    
                //if new number is not valid, return
                else if(newNumber > quiz.size() || newNumber < 0) {
                    return;
                }

                return;
            }
        }
    }


    public void printQuestions(){
        //if the queue is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            System.out.println("\nYour current quiz: ");
            //print all the attributes for every Question in the queue
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
        //if the queue is empty then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //variable to track whether there exists a result for the search
            boolean track = false;
            
            for(Question i: quiz){
                //get question and answer of the Question
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str)){
                    //print the information
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();

                    //set the track to true
                    track = true;
                }
            }
            //if the track is true, return
            if(track){
                return;
            }
            //if track is false (no search result), print error message
            else{
                System.out.println("No question or answer with such string.");
            }

        }
    }


    
}
