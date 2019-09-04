package com.projeto.maskoti.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.projeto.maskoti.R;

public class RecuperarSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        //Sumir a ActionBar
        getSupportActionBar().hide();

        //Configurar toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        mTitle.setText("Recuperar Senha");
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirIntroCadastro();
                }
            });

        }
    }

    public void abrirIntroCadastro() {
        startActivity(new Intent(this, IntroCadastro.class));
    }
}