package visitas.solutions.moov.com.visitasmoov.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.omr.solutions.utils.sqllite.TableDAO;
import com.omr.solutions.utils.sqllite.exception.SQLUtilsException;
import com.omr.solutions.utils.task.OnTaskCompleted;

import java.util.ArrayList;
import java.util.List;

import visitas.solutions.moov.com.visitasmoov.PrincipalActivity;
import visitas.solutions.moov.com.visitasmoov.dao.SeguridadTO;

/**
 * Created by omar on 30/07/15.
 */
public class ExisteUsuarioTask extends AsyncTask<Void,Void,SeguridadTO>  {

    public static final String LOG_TAG = ExisteUsuarioTask.class.getSimpleName();

    OnTaskCompleted listener;
    Context context;
    String tagOrigen;

    public ExisteUsuarioTask(Context context, OnTaskCompleted listener, String tagOrigen){
        this.context = context;
        this.listener = listener;
        this.tagOrigen = tagOrigen;
    }


    @Override
    protected SeguridadTO doInBackground(Void... params) {

        Log.d(LOG_TAG,"doInBackground");

        SeguridadTO seguridadTO = new SeguridadTO();

        List<SeguridadTO> listSeguridad = new ArrayList<>();

        TableDAO<SeguridadTO> tableDAO = new TableDAO<SeguridadTO>(context, PrincipalActivity.DB_VERSION,PrincipalActivity.DB_NAME,SeguridadTO.class);

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
        listener.onTaskCompleted(tagOrigen, seguridadTO);
    }
}
