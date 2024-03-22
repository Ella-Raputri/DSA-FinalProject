import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class HashMapBenchmark {
    static HashMap<Question, String> quiz = new HashMap<Question, String>();
    static Scanner scanner = new Scanner(System.in);

    //to avoid concurrent modification exception in the benchmark file
    public Question[] returnKeySet(){
        Question[] setofkey = new Question[250];
        int index=0;
        
        for(Question key: quiz.keySet()){
            setofkey[index] = key;
            index++;
        }
        return setofkey;
    }

    public String returnID(int number){
        for(Question key:quiz.keySet()){
            if(key.getQuestionNumber() == number){
                return key.getQuestionID();
            }
        }
        return "";
    }


    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getIDfromNumber(int num){
        for(Question i: quiz.keySet()){
            if(i.getQuestionNumber() == num){
                return i.getQuestionID();
            }
        }
        return null;
    }


    public void addQuestion(String question, String correctAnswer){
        Question q1 = new Question(correctAnswer, question);
        q1.setNumberID(quiz.size()+1);
        quiz.put(q1, q1.getQuestionID());
    }


    public void deleteQuestion(String questionID){
        if(quiz.isEmpty()){
            return;
        }
        else{
            if(quiz.containsValue(questionID)){
                quiz.remove(getKeyByValue(quiz, questionID));

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
        if(quiz.isEmpty()){
            return;
        }
        else{
            if(quiz.containsValue(questionID)){
                Question key = getKeyByValue(quiz, questionID);
                if(questionChange.equals("y")){
                    key.setQuestion(newQuestion);
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }

                if(answerChange.equals("y")){
                    getKeyByValue(quiz, questionID).setCorrectAnswer(newAnswer);;
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
            if(quiz.containsValue(id)){
                Question currentkey = getKeyByValue(quiz, id);
                int currentNumber = currentkey.getQuestionNumber();
                
                String newNumid = getIDfromNumber(newNumber);
                Question oldkey = getKeyByValue(quiz, newNumid);
                

                if(currentNumber == newNumber){
                    return;
                }

                else if(newNumber <= quiz.size() && newNumber > 0){                    
                    if(currentNumber < newNumber){
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()-1);
                        for(Question i: quiz.keySet()){
                            if(i.getQuestionNumber() < newNumber && i.getQuestionNumber()>currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()-1);
                            }
                        }
                    }
                    else{
                        oldkey.setQuestionNumber(oldkey.getQuestionNumber()+1);
                        for(Question i: quiz.keySet()){
                            if(i.getQuestionNumber() > newNumber && i.getQuestionNumber()<currentNumber && i!=oldkey){
                                i.setQuestionNumber(i.getQuestionNumber()+1);
                            }
                        }
                    }

                    currentkey.setQuestionNumber(newNumber);
                }


                else if(newNumber > quiz.size() || newNumber < 0){
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

            for(int i=1; i<=quiz.size(); i++){
                for(Question key: quiz.keySet()){
                    if(key.getQuestionNumber() == i){
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


    public void questionSearch(String questionID){
        if(quiz.isEmpty()){
            return;
        }
        else{
            if(quiz.containsValue(questionID)){
                Question key = getKeyByValue(quiz, questionID);
                System.out.println("Question " + key.getQuestionNumber());
                System.out.println("Question ID: "+ key.getQuestionID());
                System.out.println("Question: "+ key.getQuestion());
                System.out.println("Answer: "+ key.getCorrectAnswer());
                System.out.println();
                return;
            }
            System.out.println("ID invalid. Please try again.");
        }
    }


    
}


