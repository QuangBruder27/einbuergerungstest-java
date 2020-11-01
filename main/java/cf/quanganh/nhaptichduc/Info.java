package cf.quanganh.nhaptichduc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Info extends AppCompatActivity {
    private Button btn1, btn2;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //no title
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_info);

        //Admob
        mAdView = findViewById(R.id.adView_info);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btn1 = (Button)findViewById(R.id.id_info_cond);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Info.this, Info_Cond.class);
                startActivity(it);
            }
        });
        btn2 = (Button) findViewById(R.id.id_info_test);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Info.this, Info_Test.class);
                startActivity(it);
            }
        });
    }

}
