package com.example.guswn_000.a170525hw;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    ImageView imageView;
    TextView textView;
    EditText editText;
    int[] images = {R.drawable.fork,R.drawable.okkono,R.drawable.pasta,R.drawable.pizza,R.drawable.ramen,R.drawable.steak};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.tv);
        editText = (EditText)findViewById(R.id.editText);
    }

    class myThread extends AsyncTask<Integer,Integer,Void>
    {

        @Override
        protected Void doInBackground(Integer... params)
        {

            return null;
        }
    }

    public void onClick(View v)
    {
        
    }

}
