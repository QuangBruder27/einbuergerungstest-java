package cf.quanganh.nhaptichduc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DialogStates extends AppCompatActivity {

    private Button btn_unit12,btn_unit13,btn_unit14,btn_unit15,btn_unit16,btn_unit17,
            btn_unit18,btn_unit19,btn_unit20,btn_unit21, btn_unit22, btn_unit23, btn_unit24,
    btn_unit25, btn_unit26, btn_unit27;
    int nr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_states);
        this.getSupportActionBar().hide();
        final List<Button> listbtn = new ArrayList<>();

        btn_unit12 = (Button) findViewById(R.id.id_baden);      listbtn.add(btn_unit12);
        btn_unit13 = (Button) findViewById(R.id.id_bayern);     listbtn.add(btn_unit13);
        btn_unit14 = (Button) findViewById(R.id.id_berlin);     listbtn.add(btn_unit14);
        btn_unit15 = (Button) findViewById(R.id.id_branden);    listbtn.add(btn_unit15);
        btn_unit16 = (Button) findViewById(R.id.id_bremen);     listbtn.add(btn_unit16);
        btn_unit17 = (Button) findViewById(R.id.id_ham);        listbtn.add(btn_unit17);
        btn_unit18 = (Button) findViewById(R.id.id_hessen);     listbtn.add(btn_unit18);
        btn_unit19 = (Button) findViewById(R.id.id_meck);       listbtn.add(btn_unit19);
        btn_unit20 = (Button) findViewById(R.id.id_niedersa);   listbtn.add(btn_unit20);
        btn_unit21 = (Button) findViewById(R.id.id_nrw);        listbtn.add(btn_unit21);
        btn_unit22 = (Button) findViewById(R.id.id_rlf);        listbtn.add(btn_unit22);
        btn_unit23 = (Button) findViewById(R.id.id_saarl);      listbtn.add(btn_unit23);
        btn_unit24 = (Button) findViewById(R.id.id_sachan);     listbtn.add(btn_unit24);
        btn_unit25 = (Button) findViewById(R.id.id_sachsen);    listbtn.add(btn_unit25);
        btn_unit26 = (Button) findViewById(R.id.id_schless);    listbtn.add(btn_unit26);
        btn_unit27 = (Button) findViewById(R.id.id_thuer);      listbtn.add(btn_unit27);

        for(final Button btn : listbtn){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nr = listbtn.indexOf(btn) + 12;
                    Intent it = new Intent(DialogStates.this, QuestionStates.class);
                    it.putExtra("nr",nr);
                    DialogStates.this.startActivity(it);
                }
            });
        }
    }
}
