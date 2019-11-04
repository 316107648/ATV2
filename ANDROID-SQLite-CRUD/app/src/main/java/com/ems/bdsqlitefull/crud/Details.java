package com.ems.bdsqlitefull.crud;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Carro;

public class Details extends AppCompatActivity{
    Button btEditar, btDeletar;
    TextView id, placa, marca, modelo, valor;
    ArrayAdapter<Carro> adaptador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Detalhes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.id);
        placa = findViewById(R.id.placa);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        valor = findViewById(R.id.valor);
        btEditar = findViewById(R.id.btDeletar);

        Intent itCarro = getIntent();
        final Carro carro = (Carro) itCarro.getExtras().getSerializable("objCarro");
        id.setText(String.valueOf(carro.getId()));
        placa.setText(carro.getPlaca());
        marca.setText(carro.getMarca());
        modelo.setText(carro.getModelo());
        valor.setText(String.valueOf(carro.getValor()));

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editar = new Intent(getApplicationContext(), EditRecord.class);
                editar.putExtra("objCarro", carro);
                startActivity(editar);
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