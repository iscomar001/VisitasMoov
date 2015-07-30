package visitas.solutions.moov.com.visitasmoov;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 */
public class PrincipalActivity extends Activity {

    public static String LOG_TAG = PrincipalActivity.class.getSimpleName();
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

        new ExisteUsuario(getApplicationContext(),userText).execute();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }



}


