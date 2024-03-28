import java.util.LinkedList;

public class TreeRedBlackBenchmark {

    TreeRedBlack quiz = new TreeRedBlack();
    static int nodeCount = 0;

    public void addQuestion(String question, String correctAnswer) {
        Question q1 = new Question(correctAnswer, question);
        q1.setNumberID(nodeCount+1);
        quiz.insertNode(q1);
        nodeCount += 1;
    }

    public void deleteQuestion(String answer){
        if(nodeCount == 0){
            return;
        }
        else{
            int number = quiz.getQuestionNumberfromNode(answer);
            quiz.deleteNode(answer);

            if (number > 0) {
               nodeCount -= 1; 
            }
            quiz.inOrderBackward(quiz.getRoot(), nodeCount+2, number);
        }
    }


    public void editQuestion(String answer, String questionChange, String newQuestion, String answerChange, String newAnswer){
        if(nodeCount == 0){
            return;
        }
        else{
            TreeNode result = quiz.searchNode(answer);
            if (result != null){
                if(questionChange.equals("y")){
                    result.data.setQuestion(newQuestion);
                }
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }

                if(answerChange.equals("y")){
                    result.data.setCorrectAnswer(newAnswer);
                }
                else if(!(answerChange.equals("y")) && !(answerChange.equals("n"))){
                    return;
                }
                
                return;

            }else{ //if result is null, then there is no such node
                return;
            }
        }
    }
    

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void changeOrder(String answer, int newNumber){
        if (nodeCount == 0){            
            return;
        }
        else{
            if(!isNumeric(answer)){
                return;
            }
            TreeNode want_to_change = quiz.searchNode(answer);

            if(want_to_change != null){               
                if(want_to_change.data.getQuestionNumber() == newNumber){
                    return;

                }else if(newNumber <= nodeCount && newNumber > 0){
                    //search for the original one
                    int ori = want_to_change.data.getQuestionNumber();
                    //forward
                    if (ori > newNumber){
                        quiz.inOrderForward(quiz.getRoot(), newNumber-1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }

                    //backward
                    if (ori < newNumber) {
                        quiz.inOrderBackward(quiz.getRoot(), newNumber+1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }
                }

            }


        }
    }


    public void printQuestions(){
        if (nodeCount == 0){
            return;
        }else{
            System.out.println("Your current quiz: ");
            quiz.printFullNode(quiz.getRoot(), nodeCount);
        }
    }

    public LinkedList<TreeNode> bubbleSort(LinkedList<TreeNode> al){
        int i, j;
        boolean swapped;

        for (i = 0; i < al.size() - 1; i++) {
            swapped = false;
            for (j = 0; j < al.size() - i - 1; j++) {
                if (al.get(j).data.getQuestionNumber() > al.get(j+1).data.getQuestionNumber()) {
                    TreeNode temp = al.get(j);
                    al.set(j, al.get(j+1));
                    al.set(j+1, temp);
                    // Collections.swap(al, j, j+1);
                    swapped = true;
                }
            }
 
            if (swapped == false){
                break;
            }
        }
        return al;
    }


    public void questionSearch(String answer){
        if(nodeCount == 0){
            return;
        }
        else{
            LinkedList<TreeNode> results = new LinkedList<TreeNode>();
            results = quiz.searchNodeString(quiz.getRoot(), answer, results);
            results = bubbleSort(results);
            
            if (results == null || results.size() == 0){
                return;
            }else{
                for (TreeNode result : results){
                    System.out.println("Question " + result.data.getQuestionNumber());
                    System.out.println("Question ID: "+ result.data.getQuestionID());
                    System.out.println("Question: "+ result.data.getQuestion());
                    System.out.println("Answer: "+ result.data.getCorrectAnswer());
                    System.out.println();                 
                }
                return;
            }

        }
    }
}