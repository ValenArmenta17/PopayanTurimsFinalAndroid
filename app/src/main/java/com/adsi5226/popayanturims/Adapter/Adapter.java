package com.adsi5226.popayanturims.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.adsi5226.popayanturims.DashboardActivity;
import com.adsi5226.popayanturims.Modelo.TipoServicio;
import com.adsi5226.popayanturims.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    private LayoutInflater inflater;
    private static ArrayList<TipoServicio> tiposervicios;
    private ArrayList<TipoServicio> TipoServiciosOri = new ArrayList<>();
    //private RecyclerItemClick itemClick;
    // private String doamin_image = "http://10.0.2.2/retrofit/noticia_imagenes/";

    public Adapter(Context context, ArrayList<TipoServicio> tipoServicios) {
        this.inflater = LayoutInflater.from(context);
        this.tiposervicios = tipoServicios;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String titulo = tiposervicios.get(position).getNombre();


        holder.txtTitulo.setText(titulo);


    }

    @Override
    public int getItemCount() {
        return tiposervicios.size();
    }

    public interface OnQueryTextListener {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtFecha;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                    intent.putExtra("id", tiposervicios.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);


                }
            });
            txtTitulo = itemView.findViewById(R.id.txtTitulo);


        }

    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            tiposervicios.clear();
            tiposervicios.addAll(tiposervicios);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<TipoServicio> collect = tiposervicios.stream()
                        .filter(i -> i.getNombre()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                tiposervicios.clear();
                tiposervicios.addAll(collect);
            } else {
            }
        }
    }
}


