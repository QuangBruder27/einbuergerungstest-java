package cf.quanganh.nhaptichduc.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cf.quanganh.nhaptichduc.Models.ImageResource;
import cf.quanganh.nhaptichduc.Models.Question;
import cf.quanganh.nhaptichduc.QuestionActivity;
import cf.quanganh.nhaptichduc.R;

public class FragmentQuestionWithImage extends android.support.v4.app.Fragment implements View.OnClickListener{
    private List<Question> list;
    private List<ImageResource> listimg;
    static int i,p;
    private ImageButton a,b,c,d;
    private TextView score;
    private ImageView boxa,boxb,boxc,boxd;

    public static FragmentQuestionWithImage newInstance(List<Question> _list,List<ImageResource> _listimg, int index, int point){
        FragmentQuestionWithImage f = new FragmentQuestionWithImage();
        f.list = _list;
        f.listimg = _listimg;
        i = index;
        p = point;
        return  f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question_with_image, container, false);

        TextView question = (TextView) view.findViewById(R.id.id_questionImg);
        a = (ImageButton) view.findViewById(R.id.imgbtnA);
        b = (ImageButton) view.findViewById(R.id.imgbtnB);
        c = (ImageButton) view.findViewById(R.id.imgbtnC);
        d = (ImageButton) view.findViewById(R.id.imgbtnD);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);

        question.setText((i+1)+". "+list.get(i).getQuest());


        a.setImageResource(listimg.get(i).getResourceA());
        b.setImageResource(listimg.get(i).getResourceB());
        c.setImageResource(listimg.get(i).getResourceC());
        d.setImageResource(listimg.get(i).getResourceD());

        boxa = (ImageView) view.findViewById(R.id.id_box_imga);
        boxb = (ImageView) view.findViewById(R.id.id_box_imgb);
        boxc = (ImageView) view.findViewById(R.id.id_box_imgc);
        boxd = (ImageView) view.findViewById(R.id.id_box_imgd);
             return view;
    }


    @Override
    public void onClick(View view) {
        int getID = view.getId();
        int result = getID;

        // Find result
        switch(list.get(i).getResult()){
            case "A" :  result = R.id.imgbtnA; boxa.setVisibility(View.VISIBLE); break;
            case "B" :  result = R.id.imgbtnB; boxb.setVisibility(View.VISIBLE); break;
            case "C" :  result = R.id.imgbtnC; boxc.setVisibility(View.VISIBLE); break;
            case "D" :  result = R.id.imgbtnD; boxd.setVisibility(View.VISIBLE); break;
            default: break;
        }


        // True Answer
        if(getID == result){
            Toast.makeText(getActivity(), getString(R.string.true_ans)+"", Toast.LENGTH_LONG).show();
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
        ((QuestionActivity)(getActivity())).setScore("Điểm:"+p);
    }

    public List<ImageResource> initDataImg(){
        List<ImageResource> list = new ArrayList<>();
        ImageResource a21 = new ImageResource(21,R.drawable.a21a,R.drawable.a21b,R.drawable.a21c,R.drawable.a21d);
        ImageResource a130 = new ImageResource(130,R.drawable.a130a,R.drawable.a130b,R.drawable.a130c,R.drawable.a130d);
        ImageResource a209 = new ImageResource(21,R.drawable.a21a,R.drawable.a21b,R.drawable.a21c,R.drawable.a21d);
        ImageResource a226 = new ImageResource(130,R.drawable.a226a,R.drawable.a226b,R.drawable.a226c,R.drawable.a226d);
        list.add(a21);list.add(a130);list.add(a209);list.add(a226);
        //Collections.shuffle(list);
        return list;
    }
}
