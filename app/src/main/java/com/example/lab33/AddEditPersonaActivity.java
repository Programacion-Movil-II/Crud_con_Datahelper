package com.example.lab33;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class AddEditPersonaActivity extends AppCompatActivity {
    private EditText etNombres, etApellidos, etCi;
    private Button btnSave;
    private TextView tvTitle;
    private DatabaseHelper dbHelper;
    private Persona existingPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_persona);
        dbHelper = new DatabaseHelper(this);
        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etCi = findViewById(R.id.etCi);
        btnSave = findViewById(R.id.btnGuardar);
        tvTitle = findViewById(R.id.tvTitle);
        if (getIntent().hasExtra("persona")) {
            existingPersona = (Persona) getIntent().getSerializableExtra("persona");
            etNombres.setText(existingPersona.getNombres());
            etApellidos.setText(existingPersona.getApellidos());
            etCi.setText(existingPersona.getCi());
            tvTitle.setText("Editar Persona");
            btnSave.setText("Actualizar");
        }
        btnSave.setOnClickListener(v -> savePersona());
    }

    private void savePersona() {
        String nombres = etNombres.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String ciStr = etCi.getText().toString().trim();
        if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(ciStr)) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (existingPersona != null) {
            existingPersona.setNombres(nombres);
            existingPersona.setApellidos(apellidos);
            existingPersona.setCi(ciStr);
            dbHelper.updatePersona(existingPersona);
            Toast.makeText(this, "Persona actualizada", Toast.LENGTH_SHORT).show();
        } else {
            Persona newPersona = new Persona(nombres, apellidos, ciStr);
            dbHelper.addPersona(newPersona);
            Toast.makeText(this, "Persona guardada", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}