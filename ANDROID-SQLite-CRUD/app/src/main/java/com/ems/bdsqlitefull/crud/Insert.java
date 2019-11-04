package com.ems.bdsqlitefull.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Carro;
import com.ems.bdsqlitefull.utils.Message;

public class Insert extends AppCompatActivity {
    EditText placa, marca, modelo, valor;
    Button btInserir;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        // Abertura ou criação do Banco de Dados
        db = openOrCreateDatabase("db_carro", Context.MODE_PRIVATE, null);

        // Cria a tabela se não existir, senão carrega a tabela para uso
        db.execSQL("CREATE TABLE IF NOT EXISTS carro(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "placa VARCHAR NOT NULL, " +
                "marca VARCHAR NOT NULL, " +
                "modelo VARCHAR NOT NULL, " +
                "valor INTERGER NOT NULL);");

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Inserir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        placa = findViewById(R.id.placa);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        valor = findViewById(R.id.valor);
        btInserir = findViewById(R.id.btInserir);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um objeto Carro para receber os dados
                Carro carro = new Carro();
                carro.setPlaca(placa.getText().toString());
                carro.setMarca(marca.getText().toString());
                carro.setModelo(modelo.getText().toString());
                carro.setValor(Float.parseFloat(valor.getText().toString()));

                // Coleta os dados digitados nos campos
                ContentValues values = new ContentValues();
                values.put("placa", carro.getPlaca());
                values.put("marca", carro.getMarca());
                values.put("modelo", carro.getModelo());
                values.put("valor", carro.getValor());

                // Insere os dados na tabela
                db.insert("carro", null, values);

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Message message = new Message(Insert.this);
                message.show(
                        "Dados incluídos com sucesso!",
                        carro.getDados(),
                        R.drawable.ic_add);

                // Limpa os campos de entrada
                clearText();
            }
        });
    }

    // Configura o botão (seta) na ActionBar (Barra Superior)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Limpa os campos de entrada e fecha o teclado
     */
    public void clearText() {
        placa.setText("");
        marca.setText("");
        modelo.setText("");
        valor.setText("");
        placa.requestFocus();

        // fecha o teclado virtual
        ((InputMethodManager) Insert.this.getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), 0);
    }
}
