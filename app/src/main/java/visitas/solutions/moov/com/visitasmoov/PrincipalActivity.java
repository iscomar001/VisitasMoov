package visitas.solutions.moov.com.visitasmoov;

import visitas.solutions.moov.com.visitasmoov.dao.SeguridadTO;
import visitas.solutions.moov.com.visitasmoov.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.omr.solutions.utils.sqllite.TableDAO;
import com.omr.solutions.utils.sqllite.exception.SQLUtilsException;
import com.omr.solutions.utils.view.ViewUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
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



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new ExisteUsuario(getApplicationContext(),userText).execute();
    }



}


class ExisteUsuario extends AsyncTask<Void,Void,SeguridadTO> {

    Context context;
    TextView userText;

    public ExisteUsuario(Context context, TextView userText){
        this.context = context;
        this.userText = userText;
    }


    @Override
    protected SeguridadTO doInBackground(Void... params) {
        SeguridadTO seguridadTO = new SeguridadTO();

        List<SeguridadTO> listSeguridad = new ArrayList<>();

        TableDAO<SeguridadTO> tableDAO = new TableDAO<SeguridadTO>(context,PrincipalActivity.DB_VERSION,PrincipalActivity.DB_NAME,SeguridadTO.class);

        try {
            listSeguridad = tableDAO.selectAll();

            if (!listSeguridad.isEmpty()){
                seguridadTO = listSeguridad.get(0);
            }


        } catch (SQLUtilsException e) {
            Log.d(PrincipalActivity.LOG_TAG, "ERROR EXISTE USUARIO " + e.getMessage());
        }


        return seguridadTO;
    }

    @Override
    protected void onPostExecute(SeguridadTO seguridadTO) {

        if (seguridadTO == null){
            userText.setText(seguridadTO.getUsuario());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent =  new Intent(context,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(PrincipalActivity.PRINCIPAL_ID_USUARIO,0);


            context.startActivity(intent);

        }else{
            userText.setText("NUEVO");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent =  new Intent(context,LoginActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(PrincipalActivity.PRINCIPAL_ID_USUARIO,seguridadTO.getId());


            context.startActivity(intent);
        }


    }
}
