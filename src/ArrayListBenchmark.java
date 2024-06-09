//import ArrayList API
import java.util.ArrayList;

public class ArrayListBenchmark {
    //Initialize a new ArrayList
    ArrayList<Question> quiz = new ArrayList<Question>();

    public Question getQuestionfromID(String id){  
        //get the question from the ID 
        for(Question i: quiz){
            //check for all questions in the ArrayList
            //if the ID is the same as the inputted one, then return the Question object
            if(i.getQuestionID().equals(id)){
                return i;
            }
        }
        //if there is no Question that matches the ID, then return null
        return null;
    }


    public void addQuestion(String question, String correctAnswer){
        //initialize a new Question object based on the given question and correct answer
        Question q1 = new Question(correctAnswer, question);
        //add the Question to the ArrayList
        quiz.add(q1);
        //set the ID number for the Question
        q1.setNumberID(quiz.indexOf(q1)+1);
    }


    public void deleteQuestion(String id){
        //if ArrayList is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //if not, then get the Question based on the inputted ID
            Question i = getQuestionfromID(id);
            //if there exists such a Question
            if(i!= null){
                //remove that Question from ArrayList
                int j = quiz.indexOf(i);
                quiz.remove(i);

                //reset all the Question number in ArrayList
                while(j < quiz.size()){
                    quiz.get(j).setQuestionNumber(j+1);
                    j++;
                }
                return;
            }
                    
        }
    }


    public void editQuestion(String questionID, String questionChange, String newQuestion, String answerChange, String newAnswer){
        //If ArrayList is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //get the Question based on the inputted ID
            Question i = getQuestionfromID(questionID);
            //if there exists such a Question
            if(i!= null){
                //if user wants to change the question, then set the new question
                if(questionChange.equals("y")){
                    i.setQuestion(newQuestion);
                }
                //if user inputs not the string 'y' or 'n', then return because it is invalid
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }

                //if user wants to change the answer, then set the new answer
                if(answerChange.equals("y")){
                    i.setCorrectAnswer(newAnswer);
                }
                //if user inputs not the string 'y' or 'n', then return because it is invalid
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
                
                return;
            }

        }
    }


    public void changeOrder(String questionID, int newNumber){
        //If ArrayList is empty, return
        if(quiz.isEmpty()){;
            return;
        }
        else{
            //get Question object based on the inputted ID
            Question i = getQuestionfromID(questionID);

            //if there exists such a Question
            if(i!=null){
                //if the new number that user wants to change is the same as old number, return
                if(i.getQuestionNumber() == newNumber){
                    return;
                }
                //if the new number is valid (lesser than the ArrayList size and greater than 0)
                else if(newNumber <= quiz.size() && newNumber > 0){
                    int indexOfi = quiz.indexOf(i); //index of the want to change question
                    int currentNumber = i.getQuestionNumber(); //number of the want to change question

                    //setting the new number
                    if(currentNumber > newNumber){
                        //if it is forward case or current number wants to move forward
                        //then add the want to change Question to index newNumber-1 
                        //and remove it from its old position 
                        quiz.add(newNumber-1, i);
                        quiz.remove(indexOfi+1);
                    }
                    else{
                        //if it is backward case
                        if(newNumber+1 > quiz.size()){
                            //if the new number is the last number in the ArrayList,
                            //then add the want to change Question to the last
                            quiz.add(i);
                        }
                        else{
                            //if new number is not the last number, then add
                            //the want to change Question to the desired position
                            quiz.add(newNumber, i);
                        }
                        //remove it from its old position
                        quiz.remove(indexOfi);
                    }

                    
                    //resetting all the question number
                    for(int j=0; j<quiz.size(); j++){
                        quiz.get(j).setQuestionNumber(j+1);
                    }
                }
                //if new number is not valid, then return
                else if(newNumber > quiz.size() || newNumber < 0){
                    return;
                }

                return;
            }

        }
    }


    public void printQuestions(){
        //If ArrayList is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //print all the Question attributes for each Question in the ArrayList
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
        //If ArrayList is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //variable to track whether exists a result for the search
            boolean track = false;

            for(Question i: quiz){
                //check all Question in the ArrayList
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                //if the question or correct answer contains the searched string, then
                //print the Question information out and set the track to true
                if(question.contains(str) || answer.contains(str) || i.getQuestionID().equals(str)){
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true;
                }
            }            
            //if track is true, then return
            if(track){
                return;
            }
            //if false, then there is no result
            else{
                System.out.println("No question or answer with such string.");
            }

        }
    }


    
}
