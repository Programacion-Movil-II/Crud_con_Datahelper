package com.example.lab33;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {
    private List<Persona> personaList;
    private OnPersonaClickListener listener;

    public interface OnPersonaClickListener {
        void onPersonaClick(Persona persona);
        void onDeleteClick(Persona persona);
    }
    public PersonaAdapter(List<Persona> personaList, OnPersonaClickListener listener) {
        this.personaList = personaList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona persona = personaList.get(position);
        holder.tvNombres.setText(persona.getNombres());
        holder.tvApellidos.setText(persona.getApellidos());
        holder.tvCi.setText("CI: " + persona.getCi());

        holder.btnEdit.setOnClickListener(v -> listener.onPersonaClick(persona));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(persona));
    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

    public void updateList(List<Persona> newList) {
        this.personaList = newList;
        notifyDataSetChanged();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombres, tvApellidos, tvCi;
        Button btnDelete, btnEdit;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombres = itemView.findViewById(R.id.tvNombres);
            tvApellidos = itemView.findViewById(R.id.tvApellidos);
            tvCi = itemView.findViewById(R.id.tvCi);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
