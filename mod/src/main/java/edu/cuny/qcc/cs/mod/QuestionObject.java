package edu.cuny.qcc.cs.mod;

public class QuestionObject {
    int id;
    String text;
    String a;
    String b;
    String c;
    String d;
    String answer;
    String hint;
    int rank;

    @Override
    public String toString() {
        return id + " " + text + " " + a + " " + b + " " + c + " " + d;
    }
}
