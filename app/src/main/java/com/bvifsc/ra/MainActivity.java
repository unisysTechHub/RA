package com.bvifsc.ra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.bvifsc.core.MBCHomeActivity;

public class MainActivity extends AppCompatActivity  {
Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ra_mbc_login);
        setTitle("RA App");

          Intent intent = new Intent(this, MBCHomeActivity.class);
          startActivity(intent);


    }


}
