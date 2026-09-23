package com.example.lab33;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements PersonaAdapter.OnPersonaClickListener {

    private RecyclerView rvPersonas;
    private PersonaAdapter adapter;
    private DatabaseHelper dbHelper;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        rvPersonas = findViewById(R.id.rvPersonas);
        btnAdd = findViewById(R.id.btnAdd);
        setupRecyclerView();
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditPersonaActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }
    private void setupRecyclerView() {
        rvPersonas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PersonaAdapter(dbHelper.getAllPersonas(), this);
        rvPersonas.setAdapter(adapter);
    }
    private void refreshList() {
        adapter.updateList(dbHelper.getAllPersonas());
    }
    @Override
    public void onPersonaClick(Persona persona) {
        Intent intent = new Intent(this, AddEditPersonaActivity.class);
        intent.putExtra("persona", persona);
        startActivity(intent);
    }
    @Override
    public void onDeleteClick(Persona persona) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Persona")
                .setMessage("¿Estás seguro de que deseas eliminar este registro?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    dbHelper.deletePersona(persona.getId());
                    refreshList();
                    Toast.makeText(this, "Persona eliminada", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}