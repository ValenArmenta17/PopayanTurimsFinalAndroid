package com.adsi5226.popayanturims.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adsi5226.popayanturims.DashboardActivity;
import com.adsi5226.popayanturims.Modelo.Evento;
import com.adsi5226.popayanturims.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AdapterEvento extends RecyclerView.Adapter<AdapterEvento.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Evento> evento;
    private ArrayList<Evento> TipoServiciosOri = new ArrayList<>();
    //private RecyclerItemClick itemClick;
    private String doamin_image = "http://10.0.2.2:8000/v1/Evento";

    public AdapterEvento(Context context, ArrayList<Evento> evento) {
        this.inflater = LayoutInflater.from(context);
        this.evento =evento;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_view_evento, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String nombre = evento.get(position).getNombre();
        String ubicacion = evento.get(position).getUbicacion();
        String horarios = evento.get(position).getHorarios();
        String fechafin = evento.get(position).getFechafin();
        String fechainicio = evento.get(position).getFechainicio();
        String foto_url = evento.get(position).getFoto_url();


        holder.txtNombreEvento.setText(nombre);
        holder.txtUbicacionEvento.setText(ubicacion);
        holder.txtHorariosevento.setText(horarios);
        holder.txtfechafinevento.setText(fechafin);
        holder.txtfechainiEvento.setText(fechainicio);

        Picasso.get().load(foto_url)
                .error(R.drawable.sinimagen)
                .into(holder.foto_url);


    }

    @Override
    public int getItemCount() {
        return evento.size();
    }

    public interface OnQueryTextListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreEvento,txtUbicacionEvento,txtfechainiEvento,txtHorariosevento,txtfechafinevento;
        ImageView foto_url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                    intent.putExtra("id", evento.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);


                }
            });

           txtNombreEvento = itemView.findViewById(R.id.txtNombreEvento);
            txtUbicacionEvento = itemView.findViewById(R.id.txtUbicacionEvento);
            txtHorariosevento = itemView.findViewById(R.id.txtHorariosevento);
            txtfechainiEvento= itemView.findViewById(R.id.txtinitio);
            txtfechafinevento = itemView.findViewById(R.id.txtfechafinevento);
            foto_url = itemView.findViewById(R.id.imageNt);

        }

    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
         evento.clear();
           evento.addAll(evento);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<Evento> collect = evento.stream()
                        .filter(i -> i.getId()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                evento.clear();
                evento.addAll(collect);
            } else {
            }
        }
    }
}


