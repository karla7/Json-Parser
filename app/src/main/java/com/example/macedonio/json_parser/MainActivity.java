package com.example.macedonio.json_parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {
    private Context context;
   //direccion del archivo
    private static String url = "http://resources.260mb.net/accesorios.json";


    static final String KEY_ID_ACCESORIO = "id_accesorio";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_COLOR = "color";
    static final String KEY_TIPO = "tipo";


    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        // private List<Message> messages;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */

        /** application context. */
        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.list_item,new String[] { KEY_ID_ACCESORIO, KEY_NOMBRE, KEY_COLOR, KEY_TIPO}, new int[] {
                    R.id.id_accesorio, R.id.v_nombre,R.id.v_color, R.id.v_tipo});

            setListAdapter(adapter);

            // selecting single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {



                    // getting values from selected ListItem
                    String tx_id_accesorio = ((TextView) view.findViewById(R.id.id_accesorio)).getText().toString();
                    String tx_nombre= ((TextView) view.findViewById(R.id.v_nombre)).getText().toString();
                    String txt_color=((TextView) view.findViewById(R.id.v_color)).getText().toString();
                    String txt_tipo=((TextView) view.findViewById(R.id.v_tipo)).getText().toString();


                    // Es para comenzar nuevo intento
                    Intent in = new Intent(getApplicationContext(), vista_individual.class);
                    in.putExtra(KEY_ID_ACCESORIO, tx_id_accesorio);
                    in.putExtra(KEY_NOMBRE, tx_nombre);
                    in.putExtra(KEY_COLOR, txt_color);
                    in.putExtra(KEY_TIPO, txt_tipo);


                    startActivity(in);

                }

        });
        }


        protected Boolean doInBackground(final String... args) {

            JSONParser jParser = new JSONParser();

            // getting JSON string from URL
            JSONArray json = jParser.getJSONFromUrl(url);

            for (int i = 0; i < json.length(); i++) {

                try {
                    JSONObject c = json.getJSONObject(i);
                    String id_accesorio = c.getString(KEY_ID_ACCESORIO);

                    String nombre = c.getString(KEY_NOMBRE);
                    String color = c.getString(KEY_COLOR);
                    String tipo = c.getString(KEY_TIPO);


                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(KEY_ID_ACCESORIO, id_accesorio);
                    map.put(KEY_NOMBRE, nombre);
                    map.put(KEY_COLOR, color);
                    map.put(KEY_TIPO, tipo);

                    jsonlist.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;

        }

    }
}









