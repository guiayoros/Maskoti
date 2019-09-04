package com.projeto.maskoti.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.projeto.maskoti.Config.FirebaseConfig;
import com.projeto.maskoti.Model.Usuario;
import com.projeto.maskoti.R;

public class TelaCadastro2 extends AppCompatActivity {

    private EditText email, senha,senhaConfirma;
    private Usuario usuario;
    private Button casdastrar;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro2);

        //Sumir a ActionBar
        getSupportActionBar().hide();

        //Configurar toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        mTitle.setText("Cadastrar-se");
        if (null != toolbar) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirTelaCadastro();
                }
            });

        }

        email = findViewById(R.id.editEmailCadastro);
        senha = findViewById(R.id.editSenhaCadastro);
        senhaConfirma = findViewById(R.id.editSenhaConfirma);
        casdastrar = findViewById(R.id.btnFinalizarCadastro);


        casdastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString();
                String textSenha = senha.getText().toString();
                String textConfirmarSenha = senhaConfirma.getText().toString();
                //Validar si os campos foram preenchidos
                if (!textEmail.isEmpty()) {
                    if (!textSenha.isEmpty()) {
                        if (!textConfirmarSenha.isEmpty() || textConfirmarSenha.equals(textSenha)) {
                            //usuario.setEmail(textEmail);
                            usuario = new Usuario();
                            usuario.setEmail(textEmail);
                            usuario.setSenha(textSenha);
                            cadastrarUsuario();

                        } else {
                            Toast.makeText(TelaCadastro2.this, "A senha não é a mesma escolhida",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TelaCadastro2.this, "Escolha uma senha",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(TelaCadastro2.this, "Informe seu email",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

   public void clientePrincipal() {
       startActivity(new Intent(this, ClientePrincipal.class));
   }

    public void cadastrarUsuario(){
        autenticacao = FirebaseConfig.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(TelaCadastro2.this,"Cadastro feito com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            clientePrincipal();
                            finish();


                        } else {
                            Toast.makeText(TelaCadastro2.this,"Erro ao cadastrar o usuario",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }

    private void abrirTelaCadastro(){
        startActivity(new Intent(TelaCadastro2.this, TelaCadastro.class));
    }

}