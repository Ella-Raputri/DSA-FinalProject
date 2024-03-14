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
            
            quiz.setQuestionNumberForward(quiz.getRoot(), number);
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
    

    public void changeOrder(String answer, int newNumber){
        if (nodeCount == 0){            
            return;
        }
        else{
            TreeNode want_to_change = quiz.searchNode(answer);

            if(want_to_change != null){               
                if(want_to_change.data.getQuestionNumber() == newNumber){
                    return;

                }else if(newNumber <= nodeCount && newNumber > 0){
                    //search for the original one
                    TreeNode nodeAfter = quiz.searchNodeBasedonNumber(quiz.getRoot(), newNumber);
                    int ori = want_to_change.data.getQuestionNumber();
                    //forward
                    quiz.resetQuestionNumber(quiz.getRoot(), want_to_change, nodeAfter, ori, newNumber);

                    //backward
                    if (ori < newNumber) {
                        Question temp1 = quiz.searchNode(want_to_change.data.getQuestionID()).data;
                        quiz.setQuestionNumberForward(quiz.getRoot(), ori);
                        quiz.deleteNode(want_to_change.data.getQuestionID());

                        quiz.setQuestionNumberBackward(quiz.getRoot(), newNumber-1);
                        temp1.setQuestionNumber(newNumber);
                        quiz.insertNode(temp1);
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


    public void questionSearch(String answer){
        if(nodeCount == 0){
            return;
        }
        else{
            TreeNode result = quiz.searchNode(answer);

            if (result == null){                
                return;
            }else{
                System.out.println("Question " + result.data.getQuestionNumber());
                System.out.println("Question ID: "+ result.data.getQuestionID());
                System.out.println("Question: "+ result.data.getQuestion());
                System.out.println("Answer: "+ result.data.getCorrectAnswer());
                System.out.println();
                return;
            }

        }
    }
}