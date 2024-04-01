//import Java APIs
import java.util.Collections;
import java.util.LinkedList;

public class TreeRedBlackBenchmark {
    //initialize a red black tree to save the Questions
    TreeRedBlack quiz = new TreeRedBlack();
    static int nodeCount = 0; //total nodes in the tree
    
    public void addQuestion(String question, String correctAnswer) {
        //create a new Question based on the answer and question
        Question q1 = new Question(correctAnswer, question);
        //set the ID number of the Question
        q1.setNumberID(nodeCount+1);
        //insert the Question as data of the node into the tree
        quiz.insertNode(q1);
        //increment the node count
        nodeCount += 1;
    }

    public void deleteQuestion(String answer){
        //if the tree is empty, then return
        if(nodeCount == 0){
            return;
        }
        else{
            //get the current question number of the to be deleted node
            int number = quiz.getQuestionNumberfromNode(answer);
            //delete the node
            quiz.deleteNode(answer); 

            //decrement the node count
            if (number > 0) {
               nodeCount -= 1; 
            }
            //set the question number of all other nodes
            quiz.inOrderBackward(quiz.getRoot(), nodeCount+2, number);
        }
    }


    public void editQuestion(String answer, String questionChange, String newQuestion, String answerChange, String newAnswer){
        //if the tree is empty, then return
        if(nodeCount == 0){
            return;
        }
        else{
            //search node based on the given ID
            TreeNode result = quiz.searchNode(answer);
            //if there exists such a node
            if (result != null){
                //if user wants to change the Question
                if(questionChange.equals("y")){
                    //set the Question based on the new question
                    result.data.setQuestion(newQuestion);
                }
                //invalid command
                else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                    return;
                }

                //if user wants to change the answer
                if(answerChange.equals("y")){
                    //set the answer based on the new answer
                    result.data.setCorrectAnswer(newAnswer);
                }
                //invalid command
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
        //to check whether some string is numeric
        if (strNum == null) { 
            //if the string is null, then return false
            return false;
        }
        //if not null, then try to parse the string to integer
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            //if exception happens, then return false
            return false; 
        }
        return true; //if not, return true
    }

    public void changeOrder(String answer, int newNumber){
        //if the tree is empty, then return
        if (nodeCount == 0){            
            return;
        }
        else{
            //if the new number is not valid, then return
            if(!isNumeric(answer)){
                return;
            }
            //search the want to change node based on the given ID
            TreeNode want_to_change = quiz.searchNode(answer);

            //if the want to change node is not null
            if(want_to_change != null){ 
                //if the old number is the same as the new number, then return              
                if(want_to_change.data.getQuestionNumber() == newNumber){
                    return;
                
                //if the new number is valid
                }else if(newNumber <= nodeCount && newNumber > 0){
                    //search for the original one
                    int ori = want_to_change.data.getQuestionNumber();
                    //forward case
                    if (ori > newNumber){
                        //set the question number
                        quiz.inOrderForward(quiz.getRoot(), newNumber-1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }

                    //backward case
                    if (ori < newNumber) {
                        //set the question number
                        quiz.inOrderBackward(quiz.getRoot(), newNumber+1, ori);
                        want_to_change.data.setQuestionNumber(newNumber);
                    }
                }

            }


        }
    }

    public void printQuestions(){
        //if the tree is empty, then return
        if (nodeCount == 0){
            return;
        }else{
            //print all nodes in the tree
            System.out.println("Your current quiz: ");
            quiz.printFullNode(quiz.getRoot(), nodeCount);
        }
    }

    public int partition(LinkedList<TreeNode> arr, int low, int high){
        // Choosing the pivot
        int pivot = arr.get(high).data.getQuestionNumber();

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than the pivot
            if (arr.get(j).data.getQuestionNumber() < pivot) { 
                // Increment index of smaller element
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, high);
        return (i + 1);
    }
    
    public void quickSort(LinkedList<TreeNode> arr, int low, int high){
        //perform quick sort by first partition it first
        if (low < high) {
            int pi = partition(arr, low, high);

            //then recur on the range before and range after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    public void questionSearch(String answer){
        //if the tree is empty, then return
        if(nodeCount == 0){
            return;
        }
        else{
            //linked list to save the results
            LinkedList<TreeNode> results = new LinkedList<TreeNode>();
            //search the tree with the string
            results = quiz.searchNodeString(quiz.getRoot(), answer, results);
            //sort the results based on the question number
            quickSort(results,0,results.size()-1);
            
            //if there is no result, then return
            if (results == null || results.size() == 0){
                return;
            }else{
                //for every treenode inside the results linked list
                for (TreeNode result : results){
                    //print the information of the node data
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