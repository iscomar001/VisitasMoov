package visitas.solutions.moov.com.visitasmoov;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
public class PrincipalActivity extends Activity implements OnTaskCompleted {

    public static final String TAG = PrincipalActivity.class.getSimpleName();
    public static final String TAG_ORIGEN_EXISTE_USUARIO = "ExisteUsuarioTask";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "visitasmoov.db";
    public static final String PRINCIPAL_USUARIO = "USUARIO";
    public static final String KEY_USER = "USER";
    public static final String KEY_PREFERENCES_VISITAS_MOOV = "MoovSolutions";


    public TextView userText;
    public Button btnCambio;
    public Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final View contentView = findViewById(R.id.fullscreen_content);
        
        userText = (TextView)findViewById(R.id.principal_user_textview);
        btnCambio = (Button)findViewById(R.id.principal_btn_cambio);
        btnLogin = (Button)findViewById(R.id.principal_btn_login);

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
    public void onTaskCompleted(String tagOrigen,Object object) {

        Log.d(TAG, "onTaskCompleted " + tagOrigen);

        if (tagOrigen.equalsIgnoreCase(TAG_ORIGEN_EXISTE_USUARIO)){

            SeguridadTO seguridadTO = (SeguridadTO) object;

            btnCambio.setVisibility(View.VISIBLE);

            if ( seguridadTO.getId() == -1){
                Log.d(TAG, "User No Found");
                userText.setText(seguridadTO.getUsuario());
            }else{
                Log.d(TAG, "User found: " + seguridadTO.getUsuario());
                userText.setText(seguridadTO.getUsuario());
                btnCambio.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onTaskCanceled(String tagOrigen) {

    }

    public void btnOnClick(View view) {
        if (view == btnCambio) {
            Log.d(TAG, "btnCambioOnClick");
            Intent intent =  new Intent(getApplicationContext(),LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(PrincipalActivity.PRINCIPAL_USUARIO,"ND");
            getApplicationContext().startActivity(intent);
        }else {
            Log.d(TAG, "btnLoginOnClick ");
            Intent intent =  new Intent(getApplicationContext(),VisitasActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(PrincipalActivity.PRINCIPAL_USUARIO,userText.getText().toString());
            getApplicationContext().startActivity(intent);
        }

    }

}


