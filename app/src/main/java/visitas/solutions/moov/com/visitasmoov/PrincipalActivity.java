package visitas.solutions.moov.com.visitasmoov;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.omr.solutions.utils.preferences.PreferencesUtils;
import com.omr.solutions.utils.task.OnTaskCompleted;

import visitas.solutions.moov.com.visitasmoov.dao.SeguridadTO;
import visitas.solutions.moov.com.visitasmoov.tasks.ExisteUsuarioTask;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 */
public class PrincipalActivity extends Activity implements OnTaskCompleted, Runnable {

    public static final String TAG = PrincipalActivity.class.getSimpleName();
    public static final String TAG_ORIGEN_EXISTE_USUARIO = "ExisteUsuarioTask";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "visitasmoov.db";
    public static final String PRINCIPAL_USUARIO = "USUARIO";
    public static final String KEY_USER = "USER";
    public static final String KEY_PREFERENCES_VISITAS_MOOV = "MoovSolutions";


    public TextView userText;
    private boolean existeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final View contentView = findViewById(R.id.fullscreen_content);
        
        userText = (TextView)findViewById(R.id.principal_user_textview);

        PreferencesUtils preferencesUtils = new PreferencesUtils(getApplicationContext(),KEY_PREFERENCES_VISITAS_MOOV);
        String user = (String) preferencesUtils.getValue(KEY_USER,String.class);

        if (!TextUtils.isEmpty(user)) {
            Log.d(TAG, "onCreate user preferences: " + user);
        }else {
            Log.d(TAG, "onCreate user no preferences");
        }


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: start ExisteUsuario: " + TAG_ORIGEN_EXISTE_USUARIO);
        new ExisteUsuarioTask(getApplicationContext(), this, TAG_ORIGEN_EXISTE_USUARIO).execute();

    }


    @Override
    public void onTaskCompleted(final String tagOrigen,final Object object) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onTaskCompleted " + tagOrigen);

                if (tagOrigen.equalsIgnoreCase(TAG_ORIGEN_EXISTE_USUARIO)){

                    SeguridadTO seguridadTO = (SeguridadTO) object;

                    if ( seguridadTO.getId() == -1){
                        Log.d(TAG, "User No Found");
                        userText.setText("Bienvenido");

                        existeUsuario = false;

                    }else{
                        Log.d(TAG, "User found: " + seguridadTO.getUsuario());
                        userText.setText(seguridadTO.getUsuario());
                        existeUsuario = true;
                    }
                }

            }
        });

        Log.d(TAG, "onTaskCompleted Handlrer");
        Handler handler = new Handler();
        handler.postDelayed(this, 4000);

    }

    @Override
    public void onTaskCanceled(String tagOrigen) {

    }

    @Override
    public void run() {

        Intent intent = null;

        Log.d(TAG, "run ");

        if ( !existeUsuario ){
            intent =  new Intent(getApplicationContext(),LoginActivity.class);
            intent.putExtra(PrincipalActivity.PRINCIPAL_USUARIO, "ND");
        }else{
            intent =  new Intent(getApplicationContext(),VisitasActivity.class);
            intent.putExtra(PrincipalActivity.PRINCIPAL_USUARIO, userText.getText().toString());
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }
}


