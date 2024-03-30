public class Question {
    //Question attributes
    private int questionNumber;
    private String correctAnswer;
    private String question;
    private String questionID;
    static private int AdderID = 0; //for setting ID number

    //constructor
    public Question(String correctAnswer, String question){
        this.correctAnswer = correctAnswer;
        this.question = question;
    }

    //setting ID number
    //Question ID is unique for each question
    public void setNumberID(int questionNumber){
        Question.AdderID +=1;
        this.questionNumber = questionNumber;
        this.questionID = "00" + Question.AdderID;
    }

    //setters
    public void setQuestionNumber(int questionNumber){
        this.questionNumber = questionNumber;
    }
    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer = correctAnswer;
    }
    public void setQuestion(String question){
        this.question =question;
    }

    //getters
    public String getCorrectAnswer(){
        return this.correctAnswer;
    }
    public String getQuestion(){
        return this.question;
    }
    public int getQuestionNumber(){
        return this.questionNumber;
    }
    public String getQuestionID(){
        return this.questionID;
    }
}
