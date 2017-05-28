package com.example.guswn_000.a170525hw;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    ImageView imageView;
    TextView textView;
    EditText editText;
    int gap,index,time,temptime;
    int[] images = {R.drawable.fork,R.drawable.okkono,R.drawable.pasta,
            R.drawable.pizza,R.drawable.ramen,R.drawable.steak};
    String[] names = {"돼지고기","오꼬노미야끼","파스타","피자","라멘","스테이크"};
    boolean started = false;
    texttask ttask;
    imagetask itask;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.tv);
        editText = (EditText)findViewById(R.id.editText);
//        gap = Integer.parseInt(editText.getText().toString());
        setTitle("오늘 뭐 먹지?");

    }

    class texttask extends AsyncTask<Integer,Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setVisibility(View.VISIBLE);
            time = 0;
        }

        @Override
        protected Void doInBackground(Integer... params)
        {
            time++;
            while(isCancelled()==false)
            {
                try
                {
//                    Thread.sleep(gap);//에딧텍스트에넣은숫자
                    Thread.sleep(1000);
                    publishProgress(time++);

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText("시작부터 "+values[0]+"초");
//            imageView.setImageResource(images[values[0]]);
            temptime = values[0];
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            textView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            temptime = time;
        }
    }


    class imagetask extends AsyncTask<Integer,Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            index = 0;
            gap = Integer.parseInt(editText.getText().toString()) * 1000;
            imageView.setImageResource(images[0]);
        }

        @Override
        protected Void doInBackground(Integer... params)
        {
            while (isCancelled() == false)
            {
                try
                {
                    Thread.sleep(gap);
                    if(index >= 5)
                    {
                        index = -1;
                    }
                    publishProgress(++index);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            imageView.setImageResource(images[values[0]]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            textView.setText(names[index]+" 선택 ("+(temptime - 1)+"초)");
        }
    }


    public void onClick(View v)
    {
        if(v.getId() == R.id.imageView)
        {
            if(started) //이미지가 돌아가는중이면
            {
                started = false;
                ttask.cancel(true);
                itask.cancel(true);
            }
            else
            {
                if(editText.getText().toString().equals(""))
                {
                    Toast.makeText(this, "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                else
                {
                    ttask = new texttask();
                    itask = new imagetask();
                    started = true;
                    itask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    ttask.execute(1);
                }
            }
        }
        else if (v.getId() == R.id.button)
        {
            imageView.setImageResource(R.drawable.start);
            textView.setVisibility(View.INVISIBLE);
            started = false;
            editText.setText(null);
        }
    }

}
