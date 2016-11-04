package com.jonathanlieblich.myapplication;

import android.animation.LayoutTransition;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button addWish;
    private ViewGroup viewGroup;
    private EditText wishText;
    private TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addWish = (Button)findViewById(R.id.add_wish);
        wishText = (EditText)findViewById(R.id.wish_enter);
        temp = (TextView)findViewById(R.id.temp);

        viewGroup = (ViewGroup)findViewById(R.id.wish_container);
        LayoutTransition transition = new LayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
        viewGroup.setLayoutTransition(transition);

        addWish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.send_to_list);
        if(wishText.getText().length() > 0) {
            temp.setText(wishText.getText().toString());
            temp.startAnimation(animation);
            wishText.setText("");
            Handler handler = new Handler();
            final TextView tv = new TextView(this);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv.setText(temp.getText());
                    viewGroup.addView(tv, 0);
                    temp.setText("");
                }
            }, 1000);
        }
    }
}
