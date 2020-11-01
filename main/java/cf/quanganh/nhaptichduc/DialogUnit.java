package cf.quanganh.nhaptichduc;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class DialogUnit extends AppCompatActivity {

    private Button btn_unit1,btn_unit2,btn_unit3,btn_unit4,btn_unit5,btn_unit6,
            btn_unit7,btn_unit8,btn_unit9,btn_unit10,btn_unit11;
    int nr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_unit);
        this.getSupportActionBar().hide();
        final List<Button> listbtn = new ArrayList<>();

        btn_unit1 = (Button) findViewById(R.id.id_unit1);   listbtn.add(btn_unit1);
        btn_unit2 = (Button) findViewById(R.id.id_unit2);   listbtn.add(btn_unit2);
        btn_unit3 = (Button) findViewById(R.id.id_unit3);   listbtn.add(btn_unit3);
        btn_unit4 = (Button) findViewById(R.id.id_unit4);   listbtn.add(btn_unit4);
        btn_unit5 = (Button) findViewById(R.id.id_unit5);   listbtn.add(btn_unit5);
        btn_unit6 = (Button) findViewById(R.id.id_unit6);   listbtn.add(btn_unit6);
        btn_unit7 = (Button) findViewById(R.id.id_unit7);   listbtn.add(btn_unit7);
        btn_unit8 = (Button) findViewById(R.id.id_unit8);   listbtn.add(btn_unit8);
        btn_unit9 = (Button) findViewById(R.id.id_unit9);   listbtn.add(btn_unit9);
        btn_unit10 = (Button) findViewById(R.id.id_unit10); listbtn.add(btn_unit10);
        btn_unit11 = (Button) findViewById(R.id.id_unit11); listbtn.add(btn_unit11);

        for(final Button btn : listbtn){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nr = listbtn.indexOf(btn) + 1;
                    Intent it = new Intent(DialogUnit.this, QuestionActivity.class);
                    it.putExtra("nr",nr);
                    DialogUnit.this.startActivity(it);
                }
            });
        }


    }


}
