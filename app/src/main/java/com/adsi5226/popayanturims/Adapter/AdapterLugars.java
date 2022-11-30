package com.adsi5226.popayanturims.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adsi5226.popayanturims.DashboardActivity;
import com.adsi5226.popayanturims.Modelo.Lugars;
import com.adsi5226.popayanturims.R;
import com.google.android.material.transition.Hold;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AdapterLugars extends RecyclerView.Adapter<AdapterLugars.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Lugars> lugars;
    private ArrayList<Lugars> TipoServiciosOri = new ArrayList<>();
    //private RecyclerItemClick itemClick;
     private String doamin_image = "http://10.0.2.2:8000/v1/lugar";

    public AdapterLugars(Context context, ArrayList<Lugars> lugars) {
        this.inflater = LayoutInflater.from(context);
        this.lugars =lugars;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_view_lugares, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nombre = lugars.get(position).getNombre();
        String direccion = lugars.get(position).getDireccion();
        String horarios = lugars.get(position).getHorarios();
        String descripcion = lugars.get(position).getDescripcion();
        String imagen = lugars.get(position).getFoto_url();
        holder.txtNombre.setText(nombre);
        holder.txtDireccion.setText(direccion);
        holder.txtHorarios.setText(horarios);
        holder.txtDescripcion.setText(descripcion);

        Picasso.get().load(imagen)
                .error(R.drawable.sinimagen)
                .into(holder.imagen);
        Log.d("url imagen", imagen);
    }

    @Override
    public int getItemCount() {
        return lugars.size();
    }

    public interface OnQueryTextListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtDireccion, txtHorarios,txtDescripcion;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                    intent.putExtra("id", lugars.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);


                }
            });
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtHorarios = itemView.findViewById(R.id.txtHorarios);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtDireccion = itemView.findViewById(R.id.txtDirecion);
            imagen = itemView.findViewById(R.id.imageNt);



        }

    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
         lugars.clear();
           lugars.addAll(lugars);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<Lugars> collect = lugars.stream()
                        .filter(i -> i.getNombre()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                lugars.clear();
                lugars.addAll(collect);
            } else {
            }
        }
    }
}


