package cf.quanganh.nhaptichduc.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cf.quanganh.nhaptichduc.Models.Question;
import cf.quanganh.nhaptichduc.QuestionActivity;
import cf.quanganh.nhaptichduc.R;

public class FragmentQuestion extends android.support.v4.app.Fragment
        implements View.OnClickListener{
    private List<Question> list;
    static int i,p;
    private Button a,b,c,d;
    private TextView score;
    private ImageView boxa,boxb,boxc,boxd, imageview;
    private int question_id;



    public static FragmentQuestion newInstance(List<Question> _list, int index, int point){
        FragmentQuestion f = new FragmentQuestion();
        f.list = _list;
        i = index;
        p = point;
        return  f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, container, false);


        TextView question = (TextView) view.findViewById(R.id.id_question);
        a = (Button) view.findViewById(R.id.id_a);
        b = (Button) view.findViewById(R.id.id_b);
        c = (Button) view.findViewById(R.id.id_c);
        d = (Button) view.findViewById(R.id.id_d);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);

        question.setText((i+1)+". "+list.get(i).getQuest());
        a.setText("A. "+list.get(i).getA());
        b.setText("B. "+list.get(i).getB());
        c.setText("C. "+list.get(i).getC());
        d.setText("D. "+list.get(i).getD());

        boxa = (ImageView) view.findViewById(R.id.id_boxa);
        boxb = (ImageView) view.findViewById(R.id.id_boxb);
        boxc = (ImageView) view.findViewById(R.id.id_boxc);
        boxd = (ImageView) view.findViewById(R.id.id_boxd);

        imageview = (ImageView) view.findViewById(R.id.id_imageview);
        //imageview.setImageResource(0);
        question_id = Integer.parseInt(list.get(i).getId());
        setImageview(question_id);
        return view;
    }

    public void setImageview(int quest_id){
        int imgid;
        switch (quest_id){
            case 55: imgid = R.drawable.a55; break;
            case 176: imgid = R.drawable.a176; break;
            case 181: imgid = R.drawable.a181; break;
            case 187: imgid = R.drawable.a187; break;
            case 216: imgid = R.drawable.a216; break;
            default: imgid = 0;// setSizeforImageView();
        }
        imageview.setImageResource(imgid);
    }
    @Override
    public void onClick(View view) {

        int getID = view.getId();
        int result = getID;

        // Find result
        switch(list.get(i).getResult()){
            case "A" :  result = R.id.id_a; boxa.setVisibility(View.VISIBLE); break;
            case "B" :  result = R.id.id_b; boxb.setVisibility(View.VISIBLE); break;
            case "C" :  result = R.id.id_c; boxc.setVisibility(View.VISIBLE); break;
            case "D" :  result = R.id.id_d; boxd.setVisibility(View.VISIBLE); break;
            default: break;
        }

        // True Answer
        if(getID == result){
            Toast.makeText(getActivity(),getString(R.string.true_ans)+"", Toast.LENGTH_LONG).show();
            p++;
            ((QuestionActivity)getActivity()).setPoint(p);


        }  else {   // False Answer
            view.findViewById(getID).setBackgroundColor(Color.RED);

            Toast.makeText(getActivity(), getString(R.string.false_ans)+" "+list.get(i).getResult(), Toast.LENGTH_LONG).show();
        }

        a.setClickable(false);
        b.setClickable(false);
        c.setClickable(false);
        d.setClickable(false);

        // Display Score
        ((QuestionActivity)(getActivity())).setScore(getString(R.string.point)+":"+p);
        //if(!mp.isPlaying()) mp.release();
    }

}
