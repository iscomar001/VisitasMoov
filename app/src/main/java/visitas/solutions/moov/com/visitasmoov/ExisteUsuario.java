package visitas.solutions.moov.com.visitasmoov;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.omr.solutions.utils.sqllite.TableDAO;
import com.omr.solutions.utils.sqllite.exception.SQLUtilsException;

import java.util.ArrayList;
import java.util.List;

import visitas.solutions.moov.com.visitasmoov.dao.SeguridadTO;

/**
 * Created by omar on 30/07/15.
 */
public class ExisteUsuario extends AsyncTask<Void,Void,SeguridadTO> {

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
