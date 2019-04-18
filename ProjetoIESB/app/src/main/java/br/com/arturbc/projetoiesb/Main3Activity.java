package br.com.arturbc.projetoiesb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final EditText txtEmail = findViewById(R.id.txtEmail);
        final EditText pwSenha = findViewById(R.id.pwSenha);
        final EditText pwSenhaOk = findViewById(R.id.pwSenhaOk);

        Button Confirmar = findViewById(R.id.btnConfirmar);
        Button Cancelar = findViewById(R.id.btnCancelar);
    }
}
