package com.ems.bdsqlitefull.crud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Carro;

import java.util.ArrayList;

public class ListAll extends AppCompatActivity {
    ListView listViewCarros;
    ArrayList<Carro> carros = new ArrayList<>();
    ArrayAdapter<Carro> adaptador;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Listagem Geral");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        // Abreo banco de dados existente
        db = openOrCreateDatabase("db_carro", Context.MODE_PRIVATE, null);

        listViewCarros = findViewById(R.id.listagem);

        // Carrega os registros em ordem alfabética no ArrayList para anexar ao adaptador
        carros.clear();
        Cursor c = db.rawQuery("SELECT * FROM carro ORDER BY marca ASC", null);
        while (c.moveToNext()) {
            carros.add(new Carro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getFloat(4)));
        }
        // Configura o adaptador
        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                carros);


        // Anexa o adaptador à ListView
        listViewCarros.setAdapter(adaptador);

        listViewCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carro = (Carro) listViewCarros.getItemAtPosition(position);
                Intent itCarro = new Intent(getApplicationContext(), Details.class);
                itCarro.putExtra("objCarro", carro);
                startActivity(itCarro);
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