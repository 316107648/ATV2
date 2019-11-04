package com.ems.bdsqlitefull.crud;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.MainActivity;
import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Carro;
import com.ems.bdsqlitefull.utils.Message;

public class EditRecord extends AppCompatActivity {

    TextView id;
    EditText placa, marca, modelo, valor;
    Button btSalvar;

    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Edição");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.id);
        placa = findViewById(R.id.placa);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        valor = findViewById(R.id.valor);
        btSalvar = findViewById(R.id.btDeletar);

        final Intent itCarro = getIntent();
        final Carro carro = (Carro) itCarro.getExtras().getSerializable("objCarro");
        id.setText(String.valueOf(carro.getId()));
        placa.setText(carro.getPlaca());
        marca.setText(carro.getMarca());
        modelo.setText(carro.getModelo());
        valor.setText(String.valueOf(carro.getValor()));

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Coleta os dados digitados nos campos
                ContentValues values = new ContentValues();
                values.put("placa", placa.getText().toString());
                values.put("marca", marca.getText().toString());
                values.put("modelo", modelo.getText().toString());
                values.put("valor", valor.getText().toString());

                Carro novosDados = new Carro();
                novosDados.setPlaca(placa.getText().toString());
                novosDados.setMarca(marca.getText().toString());
                novosDados.setModelo(modelo.getText().toString());
                novosDados.setValor(Float.parseFloat(valor.getText().toString()));

                // Atualiza os dados na tabela
                db = openOrCreateDatabase("db_carro", Context.MODE_PRIVATE, null);
                db.execSQL("UPDATE carro SET " +
                        "placa='" + novosDados.getPlaca() + "'," +
                        "marca='" + novosDados.getMarca() + "'," +
                        "modelo='" + novosDados.getModelo() + "'," +
                        "valor='" + novosDados.getValor() + "' " +
                        "WHERE id=" + carro.getId()
                );

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Message message = new Message(EditRecord.this);
                message.show(
                        "Dados Atualizados com Sucesso!",
                        novosDados.getDados(),
                        R.drawable.ic_add);
                ;
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
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
}