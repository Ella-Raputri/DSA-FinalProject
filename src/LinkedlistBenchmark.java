//import scanner
import java.util.Scanner;

public class LinkedlistBenchmark {
    //static scanner for user input
    static Scanner scanner = new Scanner(System.in);
    //linked list for the Questions
    Linkedlist quiz = new Linkedlist();

    public void addQuestion(String question, String correctAnswer){   
        //create a new Question object based on the given question and answer   
        Question q1 = new Question(correctAnswer, question);
        //add the Question object to the linked list
        quiz.addNode(q1);

        //set the ID number of the Question
        int tracker = 1;
        q1.setNumberID(tracker);
        //reset all the question number
        quiz.resetNumber();
        tracker++;
    }


    public void deleteQuestion(String id){
        //if linked list is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //get the node that want to be deleted
            Linkedlist.Node current = quiz.getNode(id);

            //if there exists such a node
            if(current!= null){
                //delete that node and reset the question number
                quiz.deleteNode(current);
                quiz.resetNumber();
                return;
            }
            
        }
    }


    public void editQuestion(String id, String questionChange, String newQuestion, String answerChange, String newAnswer){
        //if linked list is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //get the want to be edited node
            Linkedlist.Node current = quiz.getNode(id);
            //if there exists such a node
            if (current != null){
                //if user wants to change the question
                if(questionChange.equals("y")){
                    //set the question based on the new question
                    current.data.setQuestion(newQuestion);
                }
                //invalid user input
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
                
                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //set the answer based on the new answer
                    current.data.setCorrectAnswer(newAnswer);
                }
                //invalid user input
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }
    
                return;
            }

        }
    }


    public void changeOrder(String id, int newNumber){
        //if linked list is empty, return
        if(quiz.isEmpty()){
            return;
        }
        //if there is only one node, return
        else if(quiz.head.equals(quiz.tail)){
            return;
        }
        else{
            //get the node that its order want to be changed 
            Linkedlist.Node current = quiz.getNode(id);
            //if there exists such a node
            if(current != null){
                //get the node's current number
                int currentNumber = current.data.getQuestionNumber();

                //if the node's number is the same as the new number, return
                if(currentNumber == newNumber){
                    return;
                }

                //if the new number is valid
                else if(newNumber <= Linkedlist.nodeAmount && newNumber > 0){
                    //change the node order and reset all of the question number
                    quiz.changeNodeOrder(newNumber, current, currentNumber);
                    quiz.resetNumber();
                }
                //if new number is not valid, return
                else if(newNumber > Linkedlist.nodeAmount || newNumber < 0){
                    return;
            }

            return;
        }

        }
    }


    public void printQuestions(){
        //if linked list is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //set the current node to be the head
            Linkedlist.Node current = quiz.head;
            System.out.println("\nYour current quiz: ");

            //print all of the nodes information
            while(current!=null){
                System.out.println("Question " + current.data.getQuestionNumber());
                System.out.println("Question ID: "+ current.data.getQuestionID());
                System.out.println("Question: "+ current.data.getQuestion());
                System.out.println("Answer: "+ current.data.getCorrectAnswer());
                System.out.println();
            
                current = current.next;//proceed to the next node
            }
            
        }
    }


    public void questionSearch(String str){
        //if linked list is empty, return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //to track whether exists a search result
            boolean track = false;
            //start the search from the head node
            Linkedlist.Node current = quiz.head;

            while(current!=null){
                //get the current node question and answer
                String question = current.data.getQuestion();
                String answer = current.data.getCorrectAnswer();
                String id = current.data.getQuestionID();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str) || id.equals(str)){
                    //print the information of the current node data
                    System.out.println("Question " + current.data.getQuestionNumber());
                    System.out.println("Question ID: "+ current.data.getQuestionID());
                    System.out.println("Question: "+ current.data.getQuestion());
                    System.out.println("Answer: "+ current.data.getCorrectAnswer());
                    System.out.println();
                    track = true; //set the track to true
                }
                //proceed to the next node
                current = current.next;
            }
            //if track is true, then return
            if(track){
                return;
            }
            //if track is false (no result), then print the error message
            else{
                System.out.println("No question or answer with such string.");
            }
        }
    }


}
