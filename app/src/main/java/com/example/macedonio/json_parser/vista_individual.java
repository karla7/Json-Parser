package com.example.macedonio.json_parser;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//es la clase que se esta creando
public class vista_individual extends Activity {
    static final String KEY_ID_ACCESORIO = "id_accesorio";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_COLOR = "color";
    static final String KEY_TIPO = "tipo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        Intent in = getIntent();

        // Get XML values from previous intent
        String id_accesorio = in.getStringExtra(KEY_ID_ACCESORIO);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String color= in.getStringExtra(KEY_COLOR);
        String tipo= in.getStringExtra(KEY_TIPO);


        // Displaying all values on the screen
        TextView lbl_id_accesorio = (TextView) findViewById(R.id.id_accesorio);
        TextView lblnombre = (TextView) findViewById(R.id.v_nombre);
        TextView lblcolor = (TextView) findViewById(R.id.v_color);
        TextView lbltipo = (TextView) findViewById(R.id.v_tipo);


        lbl_id_accesorio.setText(id_accesorio);
        lblnombre.setText(nombre);
        lblcolor.setText(color);
        lbltipo.setText(tipo);


    }


}


