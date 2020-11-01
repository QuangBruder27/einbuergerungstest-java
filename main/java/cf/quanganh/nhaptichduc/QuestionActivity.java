package cf.quanganh.nhaptichduc;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.Collections;
import java.util.List;

import cf.quanganh.nhaptichduc.Fragment.FragmentQuestion;
import cf.quanganh.nhaptichduc.Fragment.FragmentQuestionWithImage;
import cf.quanganh.nhaptichduc.Fragment.FragmentResult;
import cf.quanganh.nhaptichduc.Models.ImageResource;
import cf.quanganh.nhaptichduc.Models.Question;
import cf.quanganh.nhaptichduc.Models.QuestionImage;
import cf.quanganh.nhaptichduc.R;
import cf.quanganh.nhaptichduc.database.DatabaseAccess;

public class QuestionActivity extends AppCompatActivity {

    int i = 0;
    long stoptime = 0;
    private Button btn_next;
    private Chronometer chronometer;
    int point = 0;
    private AdView mAdView;
    public void setPoint(int p){
        point = p;
    }
    private Dialog dialog_out, dialog_unit;
    private Button btn_out_yes, btn_out_no;
    int unit_nr = 0;
    private TextView score, Unit;
    private RelativeLayout layout_acti_ques;
    public void setScore(String s){
        score.setText(s);
    }
    public void setUnit(String s){
        Unit.setText(s);
    }

    //Press Back Button
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        dialog_out = new Dialog(QuestionActivity.this);
        dialog_out.setTitle("Test");
        dialog_out.setContentView(R.layout.dialog_out_request);
        dialog_out.show();

        btn_out_no = (Button) dialog_out.findViewById(R.id.id_out_no);
        btn_out_yes = (Button) dialog_out.findViewById(R.id.id_out_yes);
        btn_out_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                Toast.makeText(QuestionActivity.this, getString(R.string.point)+":"+point, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_out_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_out.dismiss();
            }
        });
    }

    public void setLayout_acti_ques(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layout_acti_ques.setBackground(null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stoptime = chronometer.getBase()- SystemClock.elapsedRealtime();
        chronometer.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        chronometer.setBase(SystemClock.elapsedRealtime() + stoptime);
        chronometer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // no title
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_question);
        layout_acti_ques = findViewById(R.id.layout_acti_ques);

        // Intent receive
        Intent it = this.getIntent();
        unit_nr = it.getIntExtra("nr",0);
        setFragment(unit_nr);

        // Unit display
        Unit = (TextView) findViewById(R.id.id_unit_nr);
        setUnit(getString(R.string.unit)+" "+unit_nr);

        // Score Display
        score = (TextView)findViewById(R.id.id_score);
        this.setScore(getString(R.string.point)+":"+point);

        //AdView
        mAdView = findViewById(R.id.adView_question_activity);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // time start
        chronometer = (Chronometer) findViewById(R.id.id_time);
        chronometer.setBase(SystemClock.elapsedRealtime() + stoptime);
        chronometer.start();
    }

    // Time
    public Chronometer getChronometer(){
        return chronometer;
    }

    public void setFragment(int u){
        final List<Question> list =  DatabaseAccess.getData(u, QuestionActivity.this);
        if(u<11 || u == 99) {

            // mix
            Collections.shuffle(list);

            //Send data to fragment Question
                       Fragment fragment = FragmentQuestion.newInstance(list, i, point);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_question, fragment).commit();

            btn_next = (Button) findViewById(R.id.id_next);

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i++;
                    replaceFragment(list);
                }
            });

        } else {
            //Send data to fragment Question With Image
            final List<ImageResource> listimg = new FragmentQuestionWithImage().initDataImg();
            Fragment fragment = FragmentQuestionWithImage.newInstance(list,listimg,i, point);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_question, fragment).commit();
            btn_next = (Button) findViewById(R.id.id_next);

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i++;
                    replaceFragmentWithImage(list,listimg);
                }
            });

        }

    }

    // Replace Fragment
    public void replaceFragment(List<Question> list){
            if((i>-1) && (i< list.size())){
                // change to the next question
                android.support.v4.app.Fragment fragmentShow = FragmentQuestion.newInstance(list,i, point);
                android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.activity_question, fragmentShow);
                fragmentTransaction.commit();
            } else {
                // change to Fragment of Result
                btn_next.setVisibility(View.INVISIBLE);
                chronometer.stop();  android.support.v4.app.Fragment fragmentShow = FragmentResult.newInstance(point);
                android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.activity_question, fragmentShow);
                fragmentTransaction.commit();
            }
    }

    public void replaceFragmentWithImage(List<Question> list, List<ImageResource> listimg){

                if((i>-1) && (i< list.size())){
                    // change to the next question
                    android.support.v4.app.Fragment fragmentShow = FragmentQuestionWithImage.newInstance(list,listimg,i, point);
                    android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.activity_question, fragmentShow);
                    fragmentTransaction.commit();
                } else {
                    // change to Fragment of Result
                    btn_next.setVisibility(View.INVISIBLE);
                    chronometer.stop();
                    android.support.v4.app.Fragment fragmentShow = FragmentResult.newInstance(point);
                    android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.activity_question, fragmentShow);
                    fragmentTransaction.commit();
                }

    }

}
