package visitas.solutions.moov.com.visitasmoov;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.omr.solutions.utils.task.OnTaskCompleted;

import visitas.solutions.moov.com.visitasmoov.dao.SeguridadTO;
import visitas.solutions.moov.com.visitasmoov.tasks.ExisteUsuarioTask;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 */
public class PrincipalActivity extends Activity implements OnTaskCompleted {

    public static String LOG_TAG = PrincipalActivity.class.getSimpleName();
    public static String TAG_ORIGEN_EXISTE_USUARIO = "ExisteUsuarioTask";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "visitasmoov.db";
    public static final String PRINCIPAL_ID_USUARIO = "ID_USUARIO";


    public TextView userText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);

        final View contentView = findViewById(R.id.fullscreen_content);

        userText = (TextView)findViewById(R.id.principal_user_textview);

        Log.d(LOG_TAG,"onCreate");


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(LOG_TAG, "onPostCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");

        new ExisteUsuarioTask(getApplicationContext(),this,TAG_ORIGEN_EXISTE_USUARIO).execute();
    }

    @Override
    public void onTaskCompleted(String tagOrigen,Object object) {


        if (tagOrigen.equalsIgnoreCase(TAG_ORIGEN_EXISTE_USUARIO)){

            SeguridadTO seguridadTO = (SeguridadTO) object;

            Log.d(LOG_TAG, "onPostExecute");

            if ( seguridadTO.getId() == -1){

                Log.d(LOG_TAG,"User No Found");

                userText.setText(seguridadTO.getUsuario());

                Intent intent =  new Intent(getApplicationContext(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(PrincipalActivity.PRINCIPAL_ID_USUARIO,0);


                getApplicationContext().startActivity(intent);

            }else{

                Log.d(LOG_TAG,"User found: " + seguridadTO.getUsuario());

                userText.setText("NUEVO");

                Intent intent =  new Intent(getApplicationContext(),VisitasActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(PrincipalActivity.PRINCIPAL_ID_USUARIO,seguridadTO.getId());


                getApplicationContext().startActivity(intent);
            }
        }

    }

    @Override
    public void onTaskCanceled(String tagOrigen) {

    }
}


