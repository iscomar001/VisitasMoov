package visitas.solutions.moov.com.visitasmoov;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.omr.solutions.utils.dialogs.DialogFinishListener;


public class VisitasActivity extends ActionBarActivity implements DialogFinishListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visitas, menu);

        if (menu == null) {
            return true;
        }


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
        if (id == R.id.action_dialog_simple) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishDialog(String tagDialog, Object valueDialog) {




    }
}
