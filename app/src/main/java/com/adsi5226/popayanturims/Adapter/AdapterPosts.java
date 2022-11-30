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
import com.adsi5226.popayanturims.Modelo.Posts;
import com.adsi5226.popayanturims.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Posts> posts;
    private ArrayList<Lugars> TipoServiciosOri = new ArrayList<>();
    //private RecyclerItemClick itemClick;
    private String doamin_image = "http://10.0.2.2:8000/v1/post";

    public AdapterPosts(Context context, ArrayList<Posts> posts) {
        this.inflater = LayoutInflater.from(context);
        this.posts =posts;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_view_posts, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nombre = posts.get(position).getContenido();
        String imagen = posts.get(position).getImg_post();
        holder.txtcontenido.setText(nombre);

        Picasso.get().load(imagen)
                .error(R.drawable.sinimagen)
                .into(holder.imagen);
        Log.d("url imagen", imagen);


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public interface OnQueryTextListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtcontenido;
        ImageView imagen;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                    intent.putExtra("id", posts.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);


                }
            });
            txtcontenido = itemView.findViewById(R.id.txtcontenido);
            imagen = itemView.findViewById(R.id.imgpots);


        }

    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
         posts.clear();
           posts.addAll(posts);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<Posts> collect = posts.stream()
                        .filter(i -> i.getId()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                posts.clear();
                posts.addAll(collect);
            } else {
            }
        }
    }
}


