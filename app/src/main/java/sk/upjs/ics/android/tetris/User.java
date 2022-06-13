package sk.upjs.ics.android.tetris;

public class User {
    public String meno,email;
    public int score;

    public User(){

    }

    public User(String meno, String email, int score){
        this.meno= meno;
        this.email= email;
        this.score = score;
    }

}
