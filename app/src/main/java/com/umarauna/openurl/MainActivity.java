package com.umarauna.openurl;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    Button Search;
    EditText Querys;
    String Find;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Search =findViewById(R.id.btnSearch);
        Querys =findViewById(R.id.edtSearch);







        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Find = Querys.getText().toString().trim();
                if(Find.isEmpty()){
                    Querys.setError("Please Enter Something to Search");
                }else{
                    OpeninCustomTab(Find);
                }
            }
        });

    }

    public void OpeninCustomTab(String url){
        Uri website;
        if(!url.contains("https://") && !url.contains("http://")){
            website = Uri.parse("http://"+"www.google.com/search?q="+url);
        }else{
            website = Uri.parse("www.google.com/search?q="+url);
        }

        CustomTabsIntent.Builder customtabIntent = new CustomTabsIntent.Builder();
        customtabIntent.setToolbarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        customtabIntent.setShowTitle(true);
        customtabIntent.addDefaultShareMenuItem();
        customtabIntent.setStartAnimations(this, R.anim.left_in, R.anim.left_out);
        customtabIntent.setExitAnimations(this, R.anim.right_in, R.anim.right_out);
        Intent copyIntent = new Intent(this,CopyURL.class);
        PendingIntent copypendingIntent = PendingIntent.getBroadcast(this,0, copyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        customtabIntent.addMenuItem("Copy Link",copypendingIntent);

        if(chromeInstalled()){
            customtabIntent.build().intent.setPackage("com.android.chrome");
        }
        customtabIntent.build().launchUrl(this,website);
    }

    private boolean chromeInstalled(){
        try{
            getPackageManager().getPackageInfo("com.android.chrome",0);
            return true;
        }catch (Exception e){
            return false;

        }
    }
}
