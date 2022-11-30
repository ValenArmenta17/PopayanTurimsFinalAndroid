package com.adsi5226.popayanturims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adsi5226.popayanturims.Modelo.RegisterRequest;
import com.adsi5226.popayanturims.Modelo.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ReActivity extends AppCompatActivity {


    EditText name, email, password, password_confirmation;
    Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re);



        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_confirmation = findViewById(R.id.password_confirmation);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(password_confirmation.getText().toString())) {

                    String message = "todos los campos requeridos";
                    Toast.makeText(ReActivity.this, message, Toast.LENGTH_SHORT).show();

                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setName(name.getText().toString());
                    registerRequest.setEmail(email.getText().toString());
                    registerRequest.setPassword(password.getText().toString());
                    registerRequest.setPassword_confirmation(password_confirmation.getText().toString());
                    registerUser(registerRequest);
                }
            }
        });
    }

    public void registerUser(RegisterRequest registerRequest) {

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {

                    String message = "successfull";
                    Toast.makeText(ReActivity.this, message, Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ReActivity.this, UsersActivity.class));
                    finish();

                } else {

                    String message = "ocurrio un error intenta de nuevo";
                    Toast.makeText(ReActivity.this, message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(ReActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Guardar(View view){
        Intent intent = new Intent(ReActivity.this,UsersActivity.class);
        startActivity(intent);
        Toast.makeText(ReActivity.this, "Usuaario resgitrado correctamente", Toast.LENGTH_SHORT).show();
    }


    }

