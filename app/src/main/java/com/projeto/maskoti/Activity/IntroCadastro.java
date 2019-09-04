package com.projeto.maskoti.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.projeto.maskoti.Config.FirebaseConfig;
import com.projeto.maskoti.Model.Usuario;
import com.projeto.maskoti.R;

public class IntroCadastro extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoAcessar, botaoSeCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_cadastro);

        //Summir a Toobar
        getSupportActionBar().hide();

        //Configuraçoes Iniciais
        campoEmail = findViewById(R.id.editEmailIntro);
        campoSenha = findViewById(R.id.editSenhaIntro);
        botaoAcessar = findViewById(R.id.btnAcessar);
        botaoSeCadastrar = findViewById(R.id.btnSeCadastrar);

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin();

                    } else {
                        Toast.makeText(IntroCadastro.this, "Preenche a senha",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(IntroCadastro.this, "Preenche o E-mail",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        botaoSeCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                irCadastro();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
       // usuarioLogado();
    }

    public void usuarioLogado(){
        autenticacao = FirebaseConfig.getFirebaseAutenticacao();

        if (autenticacao.getCurrentUser() !=null ){
            abrirClientePrincial();
        }
    }


    public void validarLogin() {
        autenticacao = FirebaseConfig.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                campoEmail.getText().toString(), campoSenha.getText().toString()

        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                   abrirClientePrincial();

                }  else {
                    String excecao = "";
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuario nao esta cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "E-mail e senha nao correspondem ao usuario cadastradro";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(IntroCadastro.this, excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void abrirClientePrincial() {

        startActivity(new Intent(this, ClientePrincipal.class));
    }

    public void irCadastro() {
        startActivity(new Intent(this, TelaCadastro.class));
    }

    public void recuperarSenha(View v){
        startActivity(new Intent(this, RecuperarSenha.class));
    }

}

