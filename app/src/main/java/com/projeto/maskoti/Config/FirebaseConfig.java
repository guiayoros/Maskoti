package com.projeto.maskoti.Config;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConfig {

    private static FirebaseAuth autenticacao;

    //retorna a instancia do FirebaseAuth

    public static FirebaseAuth getFirebaseAutenticacao() {
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
