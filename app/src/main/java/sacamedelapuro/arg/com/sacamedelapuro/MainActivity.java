package sacamedelapuro.arg.com.sacamedelapuro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int CODIGO_LOGIN=1;
    public Usuario usuario;

    private Button btnBuscarCercanos;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,CODIGO_LOGIN);

    }


    // Prueba - NO USADO
    protected void intentEjemplo(){

        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
        Intent mapIntent = new Intent();
        mapIntent.setAction(Intent.ACTION_VIEW);
        mapIntent.putExtra("uri", gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");

        String title = "titulo";

        // Forzar el selector de aplicación
        Intent chooser = Intent.createChooser(mapIntent, title);

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    // Metodo creado para cuando el login es exitoso
    protected void continuacionOnCreate(){

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBuscarCercanos = (Button) findViewById(R.id.btnBuscarPrestadoresCercanos);
        btnBuscarCercanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BuscarCercanosActivity.class);
                startActivity(i);
            }
        });

        btnListar = (Button) findViewById(R.id.btnListarPrestadores);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: completar
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==CODIGO_LOGIN) {
            // Resultado
            if (resultCode == Activity.RESULT_OK) {
                usuario = (Usuario) data.getExtras().get("usuario");
                continuacionOnCreate();
            } else finish();
        }
        else finish();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
