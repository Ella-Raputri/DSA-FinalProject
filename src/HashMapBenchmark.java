//import Java APIs
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class HashMapBenchmark {
    //initialize hashmap to contain the Questions
    static HashMap<Question, String> quiz = new HashMap<Question, String>();
    static Scanner scanner = new Scanner(System.in); //static Scanner for user input

    //to avoid concurrent modification exception in the benchmark file
    public Question[] returnKeySet(){
        //initialize array to contain the set of key
        Question[] setofkey = new Question[250];
        int index=0;
        
        //add all the key in the hashmap to the setofkey array
        for(Question key: quiz.keySet()){
            setofkey[index] = key;
            index++;
        }
        return setofkey;
    }

    public String returnID(int number){
        for(Question key:quiz.keySet()){
            //check for all key in the hashmap
            //if the key has the same number as the number inputted
            if(key.getQuestionNumber() == number){
                //return the ID of that key (Question object)
                return key.getQuestionID();
            }
        }
        return "";//if there is no such Question
    }


    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        //return the key of the map by using its value
        for (Entry<T, E> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getIDfromNumber(int num){
        //get the Question ID using the question number
        for(Question i: quiz.keySet()){
            if(i.getQuestionNumber() == num){
                return i.getQuestionID();
            }
        }
        return null; //if there is no such Question with the question number
    }

    static int partition(ArrayList<Question> arr, int low, int high){
        // Choosing the pivot
        int pivot = arr.get(high).getQuestionNumber();

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than the pivot
            if (arr.get(j).getQuestionNumber() < pivot) {

                // Increment index of smaller element
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, high);
        return (i + 1);
    }
    
    static void quickSort(ArrayList<Question> arr, int low, int high){
        //perform quick sort by first partition it first
        if (low < high) {
            int pi = partition(arr, low, high);

            //then recur on the range before and range after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    public void addQuestion(String question, String correctAnswer){
        //create a new Question object based on the question and answer
        Question q1 = new Question(correctAnswer, question);
        //set the ID number of the Question
        q1.setNumberID(quiz.size()+1);
        //put the Question inside the hashmap as key and ID as value
        quiz.put(q1, q1.getQuestionID());
    }


    public void deleteQuestion(String questionID){
        //if hashmap is empty, then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //if there exists a Question that has the same ID as the inputted 
            if(quiz.containsValue(questionID)){
                //remove that Question
                quiz.remove(getKeyByValue(quiz, questionID));
                
                //reset all the question number
                int j=1;
                for(Question i: quiz.keySet()){
                    i.setQuestionNumber(j);
                    quiz.put(i, i.getQuestionID());
                    j++;
                }
                return;
            }
        }
    }


    public void editQuestion(String questionID, String questionChange, String newQuestion, String answerChange, String newAnswer){
        //if hashmap is empty, then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //if there exists a Question that has the same ID as the inputted
            if(quiz.containsValue(questionID)){
                Question key = getKeyByValue(quiz, questionID);

                //if user wants to change the question
                if(questionChange.equals("y")){
                    //then set the question based on the new question
                    key.setQuestion(newQuestion);
                }
                //if the input is invalid, then return
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }

                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //set the answer based on the new answer
                    getKeyByValue(quiz, questionID).setCorrectAnswer(newAnswer);;
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
        //if hashmap is empty, then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            //if there exists a Question with the inputted ID
            if(quiz.containsValue(id)){
                //get current Question and its number
                Question currentkey = getKeyByValue(quiz, id);
                int currentNumber = currentkey.getQuestionNumber();
                
                //get the ID number and Question of the original new number Question
                String newNumid = getIDfromNumber(newNumber);
                Question oldkey = getKeyByValue(quiz, newNumid);
                
                //if current number is the same as new number, return
                if(currentNumber == newNumber){
                    return;
                }

                //if the new number is valid
                else if(newNumber <= quiz.size() && newNumber > 0){    
                    //if current number lesser than new number (move backwards)                
                    if(currentNumber < newNumber){
                        //set the old new number Question to move forward by 1
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()-1);
                        for(Question i: quiz.keySet()){
                            //set all the Question greater than current number and lesser than new number forward by 1
                            if(i.getQuestionNumber() < newNumber && i.getQuestionNumber()>currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()-1);
                            }
                        }
                    }
                    else{ //current number greater than new number (move forwards)
                        //set the old new number Question to move backwards by 1
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()+1);
                        for(Question i: quiz.keySet()){
                            //set all the Question greater than new number and lesser than current number backwards by 1
                            if(i.getQuestionNumber() > newNumber && i.getQuestionNumber()<currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()+1);
                            }
                        }
                    }
                    //set the want to change Question to new number
                    currentkey.setQuestionNumber(newNumber);
                }

                //if new number is not valid, return
                else if(newNumber > quiz.size() || newNumber < 0){
                    return;
                }
                return;
            }

        }
    }


    public void printQuestions(){
        //if hashmap is empty, then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            System.out.println("\nYour current quiz: ");

            for(int i=1; i<=quiz.size(); i++){ //to print the Questions in order
                for(Question key: quiz.keySet()){ //to access all the Questions in the hashmap key

                    //if the question number is the same as the current print order
                    if(key.getQuestionNumber() == i){
                        //print all the attributes of the Question
                        System.out.println("Question " + key.getQuestionNumber());
                        System.out.println("Question ID: "+ key.getQuestionID());
                        System.out.println("Question: "+ key.getQuestion());
                        System.out.println("Answer: "+ key.getCorrectAnswer());
                        System.out.println();

                    }
                }
            }
           
        }
    }


    public void questionSearch(String str){
        //if hashmap is empty, then return
        if(quiz.isEmpty()){
            return;
        }
        else{
            boolean track = false; //to track whether there exists a search result

            //temporary array list to store all the keys (Questions)
            ArrayList<Question> al = new ArrayList<>();
            al.addAll(quiz.keySet());
            //sort the array list based on the question number
            quickSort(al, 0, al.size()-1); 

            for(Question i:al){
                //get the question and answer
                String question = i.getQuestion();
                String answer = i.getCorrectAnswer();

                //if the question or answer contains the searched string
                if(question.contains(str) || answer.contains(str)){
                    //print information of the Question
                    System.out.println("Question " + i.getQuestionNumber());
                    System.out.println("Question ID: "+ i.getQuestionID());
                    System.out.println("Question: "+ i.getQuestion());
                    System.out.println("Answer: "+ i.getCorrectAnswer());
                    System.out.println();
                    track = true; //set the track to true
                }
            }             

            //if track is true, return
            if(track){
                return;
            }
            //if track is false (no search result), then print the error message
            else{
                System.out.println("No question or answer with such string.");
            }

        }
    }


    
}


