package com.projeto.maskoti.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.projeto.maskoti.R;

public class ClientePrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_principal);

        //Sumir a ActionBar
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        mTitle.setText("Maskoti");
    }
}
