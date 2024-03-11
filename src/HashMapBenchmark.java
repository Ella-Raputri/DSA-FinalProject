import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class HashMapBenchmark {
    HashMap<Question, String> quiz = new HashMap<Question, String>();
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


    public void changeOrder(String questionID, int newNumber){
        if(quiz.isEmpty()){
            return;
        }
        else{
            if(quiz.containsValue(questionID)){
                Question key = getKeyByValue(quiz, questionID);
                if(key.getQuestionNumber() == newNumber){
                    return;
                }

                else if(newNumber <= quiz.size() && newNumber > 0){
                    int tracker = newNumber;
                    int currentNumber = key.getQuestionNumber();
                    
                    for(Question i:quiz.keySet()){
                        if(currentNumber > newNumber){
                            if(i.getQuestionNumber() > newNumber && i.getQuestionNumber()<currentNumber){
                                i.setQuestionNumber(tracker+2);
                                tracker++;
                            }
                            else if(i.getQuestionNumber() == newNumber){
                                i.setQuestionNumber(i.getQuestionNumber()+1);
                                tracker++;
                            }
                        }

                        else{
                            if(i.getQuestionNumber() < newNumber && i.getQuestionNumber()>currentNumber){
                                i.setQuestionNumber(tracker-2);
                                tracker++;
                                
                            }
                            else if(i.getQuestionNumber() == newNumber){
                                i.setQuestionNumber(i.getQuestionNumber()-1);
                                tracker++;
                            }
                        }
                        
                    }
                    key.setQuestionNumber(newNumber);
                }


                else if(newNumber > quiz.size()){
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

        }
    }


    public static void main(String[] args) {
         
        HashMapDemo demo = new HashMapDemo();

        while(true){
            System.out.println("\n************************************");
            System.out.println("\nQuestion Editing");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)dit Question");
            System.out.println("(C)hange Question's Order");
            System.out.println("(P)rint List of Questions");
            System.out.println("(S)earch");
            System.out.println("(Q)uit");
            System.out.println("************************************");
            System.out.print("Please enter a command: ");
            String command = scanner.nextLine();
            command = command.toUpperCase();

            switch (command) {
                case "A":
                    demo.addQuestion();
                    break;
                case "D":
                    demo.deleteQuestion();
                    break;
                case "E":
                    demo.editQuestion();
                    break;
                case "C":
                    demo.changeOrder();
                    break;
                case "P":
                    demo.printQuestions();
                    break;
                case "S":
                    demo.questionSearch();
                    break;
                case "Q":
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }


    

    }
}


