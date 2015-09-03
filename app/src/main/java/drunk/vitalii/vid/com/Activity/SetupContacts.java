package drunk.vitalii.vid.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import drunk.vitalii.vid.com.R;

public class SetupContacts extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(SetupContacts.class.toString(), " - in new Activity");
        setContentView(R.layout.activity_setup_contacts);


        Intent intent = getIntent();
        boolean newAccount = intent.getBooleanExtra(MainActivityDrunk.SETUP_ACCOUNT, false);
        Log.i(SetupContacts.class.toString(), String.valueOf(newAccount));


        TextView textView = (TextView)this.findViewById(R.id.newAccountMessage);
        textView.setText("U R Drunk body! - " + String.valueOf(newAccount));

//        setContentView(textView);


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
}
