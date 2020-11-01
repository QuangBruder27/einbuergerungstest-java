package cf.quanganh.nhaptichduc.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cf.quanganh.nhaptichduc.QuestionActivity;
import cf.quanganh.nhaptichduc.R;

public class FragmentResult extends android.support.v4.app.Fragment {

    private ImageView img_bestanden, img_passport;
    static int point;

    private final String SHARED_PREFERENCES_NAME = "Score";

    public static FragmentResult newInstance(int p){
        point = p;
        return  new FragmentResult();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView text_result = (TextView) view.findViewById(R.id.id_text_result);

        img_bestanden = (ImageView) view.findViewById(R.id.id_bestanden);
        img_passport = (ImageView) view.findViewById(R.id.id_passport);
        img_bestanden.setImageResource(R.drawable.bestanden);

        double elapsedMillis;
            ((QuestionActivity)(getActivity())).getSupportActionBar().hide();
             elapsedMillis= (int) (SystemClock.elapsedRealtime()
                    - ((QuestionActivity) getActivity()).getChronometer().getBase());

        double t = (double)(Math.round(elapsedMillis/600))/100;

        //High Score und Average Time
        SharedPreferences sharedPreferences = ((QuestionActivity) (getActivity()))
                    .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("HIGH_SCORE",0);

        int score = sharedPreferences.getInt("HIGH_SCORE",0);

        text_result.setText(getString(R.string.highscore)+":"+score+"\n"+point+" "+getString(R.string.point)+" "+
                getString(R.string.in)+" "+t+" "+getString(R.string.min));

        if(point > score) {
            editor.putInt("HIGH_SCORE", point);
        } else { editor.putInt("HIGH_SCORE", score);
        }
        editor.commit();

        ((QuestionActivity) (getActivity())).setLayout_acti_ques();

        if(point >16){  // Bestanden
            img_bestanden.setImageResource(R.drawable.bestanden);

        } else{         // Durchgefallen
            img_bestanden.setImageResource((R.drawable.durch));
            img_passport.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
