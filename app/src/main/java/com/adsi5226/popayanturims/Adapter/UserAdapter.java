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
import com.adsi5226.popayanturims.Modelo.TipoServicio;
import com.adsi5226.popayanturims.Modelo.User;
import com.adsi5226.popayanturims.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>  {

    private LayoutInflater inflater;
    private ArrayList<User> users;
    private ArrayList<User> TipoServiciosOri = new ArrayList<>();
    //private RecyclerItemClick itemClick;
    private String doamin_image = "http://10.0.2.2:8000/v1/user";

    public UserAdapter(Context context, ArrayList<User> users) {
        this.inflater = LayoutInflater.from(context);
        this.users =users;


    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_view_user, parent, false);

        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        String name = users.get(position).getName();
        String email = users.get(position).getEmail();
        String imagen = users.get(position).getAvatar();


        holder.txtNombre.setText(name);
        holder.txtemail.setText(email);


        Picasso.get().load(imagen)
                .error(R.drawable.sinimagen)
                .into(holder.imagen);
        Log.d("url imagen", imagen);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public interface OnQueryTextListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtemail;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                    intent.putExtra("id", users.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);


                }
            });
            txtNombre = itemView.findViewById(R.id.txtname);
            txtemail = itemView.findViewById(R.id.txtEmail);
            imagen = itemView.findViewById(R.id.Avatar);



        }

    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            users.clear();
            users.addAll(users);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<User> collect = users.stream()
                        .filter(i -> i.getName()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                users.clear();
                users.addAll(collect);
            } else {
            }
        }
    }
}


