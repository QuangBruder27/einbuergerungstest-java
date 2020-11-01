package cf.quanganh.nhaptichduc.Fragment;


import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import cf.quanganh.nhaptichduc.Models.Question;
import cf.quanganh.nhaptichduc.QuestionStates;
import cf.quanganh.nhaptichduc.R;

public class FragmentQuestionStates extends android.support.v4.app.Fragment implements View.OnClickListener{
    private List<Question> list;
    static int i,p;
    private Button a,b,c,d;
    private TextView score;
    private ImageView boxa,boxb,boxc,boxd,map;
    private ImageButton imga,imgb,imgc,imgd;
    private int question_id;
    private SoundPool soundPool;
    private int soundTrue, soundFalse;

    public static FragmentQuestionStates newInstance(List<Question> _list, int index, int point){
        FragmentQuestionStates f = new FragmentQuestionStates();
        f.list = _list;
        i = index;
        p = point;
        return  f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question_states, container, false);


        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        this.soundTrue = this.soundPool.load((QuestionStates)getActivity(),R.raw.trueans,1);


        TextView question = (TextView) view.findViewById(R.id.id_question_states);
        a = (Button) view.findViewById(R.id.id_a_states);
        b = (Button) view.findViewById(R.id.id_b_states);
        c = (Button) view.findViewById(R.id.id_c_states);
        d = (Button) view.findViewById(R.id.id_d_states);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);

        question.setText((i+1)+". "+list.get(i).getQuest());
        a.setText("A. "+list.get(i).getA());
        b.setText("B. "+list.get(i).getB());
        c.setText("C. "+list.get(i).getC());
        d.setText("D. "+list.get(i).getD());

        boxa = (ImageView) view.findViewById(R.id.id_boxa_states);
        boxb = (ImageView) view.findViewById(R.id.id_boxb_states);
        boxc = (ImageView) view.findViewById(R.id.id_boxc_states);
        boxd = (ImageView) view.findViewById(R.id.id_boxd_states);

        imga = (ImageButton) view.findViewById(R.id.id_a_states_img);
        imgb = (ImageButton) view.findViewById(R.id.id_b_states_img);
        imgc = (ImageButton) view.findViewById(R.id.id_c_states_img);
        imgd = (ImageButton) view.findViewById(R.id.id_d_states_img);

        //SetImageResource(0);
        question_id = Integer.parseInt(list.get(i).getId());
        map = (ImageView) view.findViewById(R.id.id_map);
        map.setImageResource(0);
        setImageview(question_id);
        return view;
    }

    public void setImageview(int quest_id){
        int imgid;
        switch (quest_id){  //Baden
            case 301: imga.setImageResource(R.drawable.b_baden);
                imgb.setImageResource(R.drawable.b_thueringen);
                imgc.setImageResource(R.drawable.b_sachan);
                imgd.setImageResource(R.drawable.b_ham);break;
            case 308: map.setImageResource(R.drawable.m308); break;

                //Bayern
            case 311 :  imga.setImageResource(R.drawable.b_baden);
                        imgb.setImageResource(R.drawable.b_bayern);
                        imgc.setImageResource(R.drawable.b_sachan);
                        imgd.setImageResource(R.drawable.b_meck);break;
            case 318:   map.setImageResource(R.drawable.m318);break;

                // Berlin
            case 321 : imga.setImageResource(R.drawable.b_ham);
                imgb.setImageResource(R.drawable.b_bremen);
                imgc.setImageResource(R.drawable.b_hessen);
                imgd.setImageResource(R.drawable.b_berlin);break;
            case 328:    map.setImageResource(R.drawable.m328);break;

                //Brandenburg
            case 331 : imga.setImageResource(R.drawable.b_brb);
                imgb.setImageResource(R.drawable.b_rlf);
                imgc.setImageResource(R.drawable.b_saarl);
                imgd.setImageResource(R.drawable.b_baden);break;
            case 338 :  map.setImageResource(R.drawable.m338);break;

                //Bremen
            case 341 : imga.setImageResource(R.drawable.b_schles);
                imgb.setImageResource(R.drawable.b_baden);
                imgc.setImageResource(R.drawable.b_bremen);
                imgd.setImageResource(R.drawable.b_bayern);break;
            case 348 :    map.setImageResource(R.drawable.m348);break;
                //Hamburg
            case 351 : imga.setImageResource(R.drawable.b_niedersa);
                imgb.setImageResource(R.drawable.b_ham);
                imgc.setImageResource(R.drawable.b_nrw);
                imgd.setImageResource(R.drawable.b_sachan);break;
            case 358 :    map.setImageResource(R.drawable.m358);break;
                //Hessen
            case 361 : imga.setImageResource(R.drawable.b_hessen);
                imgb.setImageResource(R.drawable.b_berlin);
                imgc.setImageResource(R.drawable.b_schles);
                imgd.setImageResource(R.drawable.b_sachsen);break;
            case 368 :    map.setImageResource(R.drawable.m368);break;
                //Mecklenburg-Vorpommern
            case 371 : imga.setImageResource(R.drawable.b_hessen);
                imgb.setImageResource(R.drawable.b_brb);
                imgc.setImageResource(R.drawable.b_meck);
                imgd.setImageResource(R.drawable.b_niedersa);break;
            case 378 :    map.setImageResource(R.drawable.m378);break;
                //Niedersachsen
            case 381 : imga.setImageResource(R.drawable.b_bayern);
                imgb.setImageResource(R.drawable.b_schles);
                imgc.setImageResource(R.drawable.b_niedersa);
                imgd.setImageResource(R.drawable.b_sachsen); break;
            case 388 :    map.setImageResource(R.drawable.m388);break;
                //NRW
            case 391 : imga.setImageResource(R.drawable.b_bremen);
                imgb.setImageResource(R.drawable.b_nrw);
                imgc.setImageResource(R.drawable.b_sachan);
                imgd.setImageResource(R.drawable.b_baden);break;
            case 398 :    map.setImageResource(R.drawable.m398);break;
                //Rheinland-Pfalz
            case 401 : imga.setImageResource(R.drawable.b_rlf);
                imgb.setImageResource(R.drawable.b_ham);
                imgc.setImageResource(R.drawable.b_thueringen);
                imgd.setImageResource(R.drawable.b_schles);break;
            case 408 :    map.setImageResource(R.drawable.m408);break;
                //Saarland
            case 411 : imga.setImageResource(R.drawable.b_rlf);
                imgb.setImageResource(R.drawable.b_hessen);
                imgc.setImageResource(R.drawable.b_meck);
                imgd.setImageResource(R.drawable.b_saarl);break;
            case 418 :    map.setImageResource(R.drawable.m418);break;
                // Sachsen
            case 421 : imga.setImageResource(R.drawable.b_bayern);
                imgb.setImageResource(R.drawable.b_berlin);
                imgc.setImageResource(R.drawable.b_niedersa);
                imgd.setImageResource(R.drawable.b_sachsen);break;
            case 428:    map.setImageResource(R.drawable.m428);break;
                // Sachsen Anhalt
            case 431 : imga.setImageResource(R.drawable.b_bayern);
                imgb.setImageResource(R.drawable.b_ham);
                imgc.setImageResource(R.drawable.b_rlf);
                imgd.setImageResource(R.drawable.b_sachan);break;
            case 438 : map.setImageResource(R.drawable.m438);break;

                // Schleswigholstein
            case 441 : imga.setImageResource(R.drawable.b_sachan);
                imgb.setImageResource(R.drawable.b_berlin);
                imgc.setImageResource(R.drawable.b_schles);
                imgd.setImageResource(R.drawable.b_thueringen);break;
            case 448 : map.setImageResource(R.drawable.m448);break;
                // Thüringen
            case 451 : imga.setImageResource(R.drawable.b_baden);
                imgb.setImageResource(R.drawable.b_meck);
                imgc.setImageResource(R.drawable.b_sachsen);
                imgd.setImageResource(R.drawable.b_thueringen);break;
            case 458 : map.setImageResource(R.drawable.m458);break;
        }
    }

    @Override
    public void onClick(View view) {
        int getID = view.getId();
        int result = getID;

        // Find result
        switch(list.get(i).getResult()){
            case "A" :  result = R.id.id_a_states; boxa.setVisibility(View.VISIBLE); break;
            case "B" :  result = R.id.id_b_states; boxb.setVisibility(View.VISIBLE); break;
            case "C" :  result = R.id.id_c_states; boxc.setVisibility(View.VISIBLE); break;
            case "D" :  result = R.id.id_d_states; boxd.setVisibility(View.VISIBLE); break;
            default: break;
        }

        // True Answer
        if(getID == result){
            this.soundPool.play(soundTrue, 1, 1, 0, 0, 1);
            Toast.makeText(getActivity(), getString(R.string.true_ans)+"", Toast.LENGTH_LONG).show();
            p++;
            ((QuestionStates)getActivity()).setPoint(p);

        }  else {   // False Answer

            view.findViewById(getID).setBackgroundColor(Color.RED);
            Toast.makeText(getActivity(), getString(R.string.false_ans)+" "+list.get(i).getResult(), Toast.LENGTH_LONG).show();
        }

        a.setClickable(false);
        b.setClickable(false);
        c.setClickable(false);
        d.setClickable(false);

        // Display Score
        ((QuestionStates)(getActivity())).setScore(getString(R.string.point)+":"+p);
    }

}
