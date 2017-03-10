package sacamedelapuro.arg.com.sacamedelapuro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import sacamedelapuro.arg.com.sacamedelapuro.dao.UsuarioDao;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;

import static android.os.Environment.getExternalStoragePublicDirectory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int CODIGO_LOGIN=1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    public Usuario usuario;
    private UsuarioDao usuarioDao;

    private TextView TextUsername;
    private EditText TextNombre;
    private TextView TextCelular;
    private EditText TextDni;
    private ImageView ImgPerfil;

    private Bitmap imageBitmap;


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

        TextUsername = (TextView) findViewById(R.id.txt_username);
        TextNombre = (EditText) findViewById(R.id.txt_nombre);
        TextCelular = (TextView) findViewById(R.id.txt_celular);
        TextDni = (EditText) findViewById(R.id.txt_dni);
        ImgPerfil = (ImageView) findViewById(R.id.img_perfil);

        TextUsername.setText(usuario.getUsername());
        if(usuario.getNombre()==null || usuario.getNombre().length()<1) TextNombre.setText("");
        else TextNombre.setText(usuario.getNombre());
        TextCelular.setText(usuario.getCelular());
        if(usuario.getDni()==null || usuario.getDni().length()<1) TextDni.setText("");
        else TextDni.setText(usuario.getDni());
        if(usuario.getImagen()!=null && usuario.getImagen().length()>0) {
            imageBitmap = BitmapFactory.decodeFile(usuario.getImagen());

            //Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(usuario.getImagen()), 25, 25);

            ImgPerfil.setImageBitmap(imageBitmap);
        }

        final Button button = (Button) findViewById(R.id.button_actualizar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarDatos();
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

    private void actualizarDatos(){
        TextNombre = (EditText) findViewById(R.id.txt_nombre);
        TextDni = (EditText) findViewById(R.id.txt_dni);

        usuario.setNombre(TextNombre.getText().toString());
        usuario.setDni(TextDni.getText().toString());

        usuarioDao = new UsuarioDao(this);
        usuarioDao.update(usuario);

        Toast.makeText(this, "Datos actualizados.", Toast.LENGTH_SHORT).show();

        continuacionOnCreate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            /*Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");*/
            imageBitmap = BitmapFactory.decodeFile(usuario.getImagen());

            ImgPerfil.setImageBitmap(imageBitmap);
            usuarioDao = new UsuarioDao(this);
            usuarioDao.update(usuario);
        }
        else if(requestCode==CODIGO_LOGIN) {
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


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentPhotoPath = image.getAbsolutePath();

        /*Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);*/

        return image;
    }



    private void dispatchTakePictureIntent(){

        String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/perfil/";
        File file = new File(ruta_fotos);
        file.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String ruta_foto = ruta_fotos + timeStamp + ".jpg";
        File foto = new File(ruta_foto);

        try {
            foto.createNewFile();
        } catch (IOException ex) {
            Log.e("ERROR ", "Error:" + ex);
        }

        Uri uri = Uri.fromFile(foto);

        usuario.setImagen(ruta_foto);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            dispatchTakePictureIntent();

        } else if (id == R.id.btnBuscarPrestadoresCercanos) {
            Intent i = new Intent(MainActivity.this, BuscarCercanosActivity.class);
            startActivity(i);

        } else if (id == R.id.btnVerPedidos) {
            Intent i = new Intent(MainActivity.this, ListaPedidosActivity.class);
            i.putExtra("usuario", usuario);
            startActivity(i);

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
