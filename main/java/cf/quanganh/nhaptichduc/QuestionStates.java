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
import cf.quanganh.nhaptichduc.Fragment.FragmentQuestionStates;
import cf.quanganh.nhaptichduc.Fragment.FragmentResultStates;
import cf.quanganh.nhaptichduc.Models.Question;
import cf.quanganh.nhaptichduc.database.DatabaseAccess;

public class QuestionStates extends AppCompatActivity {


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

    //Press Back Button
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        dialog_out = new Dialog(QuestionStates.this);
        dialog_out.setTitle("Test");
        dialog_out.setContentView(R.layout.dialog_out_request);
        dialog_out.show();

        btn_out_no = (Button) dialog_out.findViewById(R.id.id_out_no);
        btn_out_yes = (Button) dialog_out.findViewById(R.id.id_out_yes);
        btn_out_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                Toast.makeText(QuestionStates.this, "Bạn làm đúng "+point+" câu.", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_question_states);
        layout_acti_ques = findViewById(R.id.layout_acti_ques_states);

        // Intent receive
        Intent it = this.getIntent();
        unit_nr = it.getIntExtra("nr",0);
        setFragment(unit_nr);

        // Unit display
        Unit = (TextView) findViewById(R.id.id_states_nr);
        setUnitStates(unit_nr);

        // Score Display
        score = (TextView)findViewById(R.id.id_score_states);
        this.setScore(getString(R.string.point)+":"+ point);

        //AdView
        mAdView = findViewById(R.id.adView_question_states);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // time start
        chronometer = (Chronometer) findViewById(R.id.id_time_states);
        chronometer.setBase(SystemClock.elapsedRealtime() + stoptime);
        chronometer.start();
    }

    // Time
    public Chronometer getChronometer(){
        return chronometer;
    }

    public void setFragment(int u){
        final List<Question> list =  DatabaseAccess.getData(u, QuestionStates.this);

            // mix
            Collections.shuffle(list);

            //Send data to fragment Question States
            Fragment fragment = FragmentQuestionStates.newInstance(list, i, point);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_question_states, fragment).commit();

            btn_next = (Button) findViewById(R.id.id_next_states);

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i++;
                    replaceFragment(list);
                }
            });

    }

    // Replace Fragment
    public void replaceFragment(List<Question> list){
        if((i>-1) && (i< list.size())){
            // change to the next question
            android.support.v4.app.Fragment fragmentShow = FragmentQuestionStates.newInstance(list,i, point);
            android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.activity_question_states, fragmentShow);
            fragmentTransaction.commit();
        } else {
            // change to Fragment of Result
            btn_next.setVisibility(View.INVISIBLE);
            chronometer.stop();  android.support.v4.app.Fragment fragmentShow = FragmentResultStates.newInstance(point);
            android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.activity_question_states, fragmentShow);
            fragmentTransaction.commit();
        }
    }

    public void setUnitStates(int u){
        switch (u){
            case 12 : Unit.setText("Baden");                break;    // Baden
            case 13 : Unit.setText("Bayern");               break;    // Bayern
            case 14 : Unit.setText("Berlin");               break;    // Berlin
            case 15 : Unit.setText("Brandenburg");          break;    // Brandenburg
            case 16 : Unit.setText("Bremen");               break;    // Bremen
            case 17 : Unit.setText("Hamburg");              break;    // Hamburg
            case 18 : Unit.setText("Hessen");               break;    // Hessen
            case 19 : Unit.setText("Mecklenburg-Vorpommern");break;    // Meckenburg
            case 20 : Unit.setText("Niedersachsen");        break;    // Niedersachsen
            case 21 : Unit.setText("Nordrhein-Westfalen")   ;break;    // NRW
            case 22 : Unit.setText("Rheinland-Pfalz");      break;    // Rheinland pfalz
            case 23 : Unit.setText("Saarland");             break;    // Saarland
            case 24 : Unit.setText("Sachsen-Anhalt");       break;    // Sachsen
            case 25 : Unit.setText("Sachsen");              break;    // Sachsen Anhalt
            case 26 : Unit.setText("Schleswig-Holstein");   break;    // Schleswig Holstein
            case 27 : Unit.setText("Thüringen");            break;    // Thüringen
        }}



}
