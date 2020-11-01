package cf.quanganh.nhaptichduc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Info_Cond extends AppCompatActivity {

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //no title
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_info__cond);

        mAdView = findViewById(R.id.adView_info_cond);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}
