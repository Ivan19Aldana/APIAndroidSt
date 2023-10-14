package com.example.apilaravel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apilaravel.R;
import com.example.apilaravel.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormularioActivity extends AppCompatActivity {

    EditText edtCod, edtNomb, edtTel, edtCorreo, edtDirecc, edtDepart;
    Button btnEdit;
    Button btnBusc;
    Button btnElim;
    Button btnReg;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        edtCod=(EditText) findViewById(R.id.edtCod);
        edtNomb=(EditText) findViewById(R.id.edtNomb);
        edtTel=(EditText) findViewById(R.id.edtTel);
        edtCorreo=(EditText) findViewById(R.id.edtCorreo);
        edtDirecc=(EditText) findViewById(R.id.edtDirecc);
        edtDepart=(EditText) findViewById(R.id.edtDepart);
        btnEdit=(Button) findViewById(R.id.btnEdit);
        btnBusc=(Button) findViewById(R.id.btnBusc);
        btnElim=(Button) findViewById(R.id.btnElim);
        btnReg=(Button) findViewById(R.id.btnReg);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarEmpleado("http://apilaravel.norwayeast.cloudapp.azure.com/api/editar-empleado/");
            }
        });

        btnBusc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarEmpleado("http://apilaravel.norwayeast.cloudapp.azure.com/api/get-empleado/"+edtCod.getText()+"");
            }
        });

        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarEmpleado("http://apilaravel.norwayeast.cloudapp.azure.com/api/delete-empleados/9");
            }

        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearEmpleado("http://apilaravel.norwayeast.cloudapp.azure.com/api/save-empleados");
            }
        });
    }

    private void editarEmpleado(String URL){

        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "CAMBIOS GUARDADOS", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                String tipocontrol = "api";
                parametros.put("codigo_empleado",edtCod.getText().toString());
                parametros.put("nombre_empleado",edtNomb.getText().toString());
                parametros.put("numero_telefono",edtTel.getText().toString());
                parametros.put("correo",edtCorreo.getText().toString());
                parametros.put("direccion",edtDirecc.getText().toString());
                parametros.put("departamento",edtDepart.getText().toString());
                parametros.put("control", tipocontrol);
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void crearEmpleado(String URL){

        StringRequest stringRequest= new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "EMPLEADO CREADO", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                Map<String,String> parametros = new HashMap<String, String>();
                String tipocontrol = "api";
                parametros.put("codigo_empleado",edtCod.getText().toString());
                parametros.put("nombre_empleado",edtNomb.getText().toString());
                parametros.put("numero_telefono",edtTel.getText().toString());
                parametros.put("correo",edtCorreo.getText().toString());
                parametros.put("direccion",edtDirecc.getText().toString());
                parametros.put("departamento",edtDepart.getText().toString());
                parametros.put("control", tipocontrol);
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void buscarEmpleado(String URL){
        StringRequest postRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "USUARIO ENCONTRADO", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    edtCod.setText(jsonObject.getString("codigo_empleado"));
                    edtNomb.setText(jsonObject.getString("nombre_empleado"));
                    edtTel.setText(jsonObject.getString("numero_telefono"));
                    edtCorreo.setText(jsonObject.getString("correo"));
                    edtDirecc.setText(jsonObject.getString("direccion"));
                    edtDepart.setText(jsonObject.getString("departamento"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage());
            }
        }
        );
        Volley.newRequestQueue(this).add(postRequest);

    }

    private void eliminarEmpleado(String URL){
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "USUARIO ELIMINADO", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    edtCod.setText(jsonObject.getString("codigo_empleado"));
                    edtNomb.setText(jsonObject.getString("nombre_empleado"));
                    edtTel.setText(jsonObject.getString("numero_telefono"));
                    edtCorreo.setText(jsonObject.getString("correo"));
                    edtDirecc.setText(jsonObject.getString("direccion"));
                    edtDepart.setText(jsonObject.getString("departamento"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage());
            }
        }
        );
        Volley.newRequestQueue(this).add(postRequest);

    }

    public void regresarMenu(View view){
        Intent regresarmenu = new Intent(this, MainActivity.class);
        startActivity(regresarmenu);

    }
}