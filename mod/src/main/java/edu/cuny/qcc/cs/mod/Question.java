package edu.cuny.qcc.cs.mod;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

public class Question extends Fragment {
    CallToServlet c = new CallToServlet();
    int questionNum = 1;
    String userID;
//    String userJson4 = "[{'id': 1,'text': 'this is the questions1', 'a': 'yo', 'b': 'hiiiii', 'c':'hello', 'd':'wassup', 'answer': 1, 'hint':'choose 1', 'rank':1}, "
//            + "{'id': 2,'text': 'this is the question2', 'a': 'yo', 'b': 'hey', 'c':'helasdasd', 'd':'wassup', 'answer': 2, 'hint':'choose 2', 'rank':1}, "
//            + "{'id': 3,'text': 'this is the question3', 'a': 'yo', 'b': 'hey', 'c':'helasdasd', 'd':'wassup', 'answer': 3, 'hint':'choose 3', 'rank':1}, "
//            + "{'id': 4,'text': 'this is the questions4', 'a': 'yo', 'b': 'hey', 'c':'hello', 'd':'wassup', 'answer': 4, 'hint':'choose 4', 'rank':1}]";
    QuestionObject[] questionArray;


    Gson g = new Gson();
    QuestionObject[] questionObjects;

    TextView question_header;
    TextView question_content;
    Button button_a;
    Button button_b;
    Button button_c;
    Button button_d;

    Button button_hint;
    TextView hint_text;

    Button button_next;

    ImageButton button_end;
    @Override
    public View onCreateView(

            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.question, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Log.d("Works", getContext().getFilesDir().toString());

        question_header = (TextView) view.findViewById(R.id.question_header);
        question_content = (TextView) view.findViewById(R.id.question_content);

        button_a = (Button) view.findViewById(R.id.button_a);
        button_b = (Button) view.findViewById(R.id.button_b);
        button_c = (Button) view.findViewById(R.id.button_c);
        button_d = (Button) view.findViewById(R.id.button_d);

        button_hint = (Button) view.findViewById(R.id.button_hint);
        hint_text = (TextView) view.findViewById(R.id.hint_text);

        button_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hint_text.getVisibility() == View.VISIBLE) {
                    hint_text.setVisibility(View.GONE);
                    button_hint.setText("Show Hint");
                }
                else {
                    hint_text.setVisibility(View.VISIBLE);
                    button_hint.setText("Hide Hint");
                }
            }
        });

        button_next = (Button) view.findViewById(R.id.button_next);

        button_end = (ImageButton) view.findViewById(R.id.button_end);
        button_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Question.this)
                        .navigate(R.id.action_Question_to_Summary);
            }
        });

        //get arguments from FirstFragment

        int getRank1 = 0;
        int getRank2 = 0;
        int getRank3 = 0;
        if(getArguments() != null) {
            QuestionArgs args = QuestionArgs.fromBundle(getArguments());
            userID = args.getUserId();
            getRank1 = args.getGetRank1();
            getRank2 = args.getGetRank2();
            getRank3 = args.getGetRank3();
            Log.i("Rank", String.valueOf(getRank1));
            Log.i("Rank", String.valueOf(getRank2));
            Log.i("Rank", String.valueOf(getRank3));
        }

        try {

            questionArray = c.getQuestions(getRank1,getRank2,getRank3);
////            c.getQuestions();
//            for(QuestionObject q : questionArray) {
//                Log.d("Works", q.toString());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int i = 0;
//        while (questionArray == null) {
//            Log.d("COUNT", String.valueOf(i));
//        }
        loadQuestion(questionArray[0]);


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadQuestion(QuestionObject q) {
        //hacky way to reset the buttons to the normal style
        button_a.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
        button_b.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
        button_c.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
        button_d.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));

        button_a.setEnabled(true);
        button_b.setEnabled(true);
        button_c.setEnabled(true);
        button_d.setEnabled(true);

        //putting in the text
        question_header.setText("Question " + questionNum);
        question_content.setText(q.text);
        button_a.setText(q.a);
        button_b.setText(q.b);
        button_c.setText(q.c);
        button_d.setText(q.d);

        hint_text.setText(q.hint);

        hint_text.setVisibility(View.GONE);
        button_hint.setText("Show Hint");

        button_next.setVisibility(View.GONE);

        Button button_next = (Button) getView().findViewById(R.id.button_next);

        //method below adds onclick events. kept separate to avoid clutter
        addButtonAnswersClick();

        //decides what the next button should based on how many questions have been done
        if(questionNum < questionArray.length) {
            button_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadQuestion(questionArray[questionNum-1]);
                }
            });
        }
        else {
            button_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(Question.this)
                            .navigate(R.id.action_Question_to_Summary);
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addButtonAnswersClick() {
        button_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button_a);
            }
        });

        button_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button_b);
            }
        });

        button_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button_c);
            }
        });

        button_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button_d);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void checkAnswer(Button btn) {
        //method to connect to servlet to check answer

        String userAnswer = btn.getText().toString();


        String answer = new String();
        try {
            answer = c.getAnswer(userID, userAnswer, questionArray[questionNum - 1].id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        button_a.setEnabled(false);
        button_b.setEnabled(false);
        button_c.setEnabled(false);
        button_d.setEnabled(false);

        if(answer.equals("Correct")) {
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03DAC5")));
            btn.setClickable(false);
            btn.setEnabled(true);
        }
        else
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FC0303")));

        questionNum++;
        button_next.setVisibility(View.VISIBLE);


    }

}
