package cf.quanganh.nhaptichduc;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler{
    private Button btnstart, btn_exit, btn_info, btn_states, btn_trial, btn_pp;
    private AdView mAdView;
    BillingProcessor bp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buy App
        bp = new BillingProcessor(this,"",this);


        //no title
        this.getSupportActionBar().hide();

        //MobileAds
        mAdView = findViewById(R.id.adView_main);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        // Start to play general question
        btnstart = (Button) findViewById(R.id.id_start);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bp.isPurchased(getString(R.string.product_id))){
                    Toast.makeText(MainActivity.this,getString(R.string.thanks), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, DialogUnit.class);
                    startActivity(intent);
                } else {
                    bp.purchase(MainActivity.this,getString(R.string.product_id));
                    Toast.makeText(MainActivity.this,getString(R.string.buy_app_request), Toast.LENGTH_LONG).show();
                }
            }
        });

        // Start to play States question
        btn_states = (Button) findViewById(R.id.id_states);
        btn_states.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bp.isPurchased(getString(R.string.product_id))){
                    Intent it = new Intent(MainActivity.this, DialogStates.class);
                    startActivity(it);
                } else {
                    bp.purchase(MainActivity.this,getString(R.string.product_id));
                    Toast.makeText(MainActivity.this,getString(R.string.buy_app_request), Toast.LENGTH_LONG).show();
                }

            }
        });

        //Trial Version
        btn_trial = (Button) findViewById(R.id.id_trial);
        btn_trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this,QuestionActivity.class);
                it.putExtra("nr",99);
                startActivity(it);
            }
        });


        // Exit
        btn_exit = (Button)findViewById(R.id.id_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        // Info
        btn_info = (Button) findViewById(R.id.id_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, Info.class);
                startActivity(it);
            }
        });
        // Privacy Policy
        btn_pp = (Button) findViewById(R.id.id_privacypolicy);
        btn_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://quangbruder.wixsite.com/home/post/privacy-policy-for-nhap-tich-duc-einbuergerungstest"));
                startActivity(browserIntent);
            }
        });


    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }


    @Override
    public void onBillingInitialized() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }



}
