package visitas.solutions.moov.com.visitasmoov.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.omr.solutions.utils.sqllite.TableDAO;
import com.omr.solutions.utils.sqllite.exception.SQLUtilsException;
import com.omr.solutions.utils.task.OnTaskCompleted;

import java.util.HashMap;
import java.util.Map;

import visitas.solutions.moov.com.visitasmoov.PrincipalActivity;
import visitas.solutions.moov.com.visitasmoov.dao.SeguridadTO;

/**
 * Created by omar on 30/07/15.
 */
public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

    public static final String LOG_TAG = UserLoginTask.class.getSimpleName();

    private final String usuario;
    private final String password;
    OnTaskCompleted listener;
    String tagOrigen;
    Context context;

    public UserLoginTask(Context context,OnTaskCompleted listener, String tagOrigen, String usuario, String password) {

        this.context = context;
        this.usuario = usuario;
        this.password = password;
        this.listener = listener;
        this.tagOrigen = tagOrigen;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        Map<String,String> mapParams = new HashMap<String,String>();
        mapParams.put("usuario", usuario);


        TableDAO<SeguridadTO>  tableDAO = new TableDAO<SeguridadTO>(context, PrincipalActivity.DB_VERSION,PrincipalActivity.DB_NAME,SeguridadTO.class);

        try {
            SeguridadTO seguridadTO = tableDAO.selectBean(mapParams) ;

            if(seguridadTO == null){

                SeguridadTO seguridadNewTO = new SeguridadTO();
                seguridadNewTO.setUsuario(usuario);
                seguridadNewTO.setPassword(password);

                tableDAO.insertBean(seguridadNewTO);
                return true;
            }else{
                return seguridadTO.getPassword().equalsIgnoreCase(password);
            }


        } catch (SQLUtilsException e) {
            Log.d(LOG_TAG, "Error al aextraer usuarios:  " + e.getMessage());
            return false;
        }

    }

    @Override
    protected void onPostExecute(final Boolean success) {

        listener.onTaskCompleted(tagOrigen,success);

    }

    @Override
    protected void onCancelled() {
        listener.onTaskCanceled(tagOrigen);
    }
}