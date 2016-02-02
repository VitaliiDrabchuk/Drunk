package drunk.vitalii.vid.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import drunk.vitalii.vid.com.R;

public class SetupContacts extends Activity {
    /* ---Service--- */
    String[] contactDetails = new String[]{ContactsContract.CommonDataKinds.Phone._ID,
                                           ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                           ContactsContract.CommonDataKinds.Phone.NUMBER};

    /* ---UI--- */
    private TextView captionLabel;
    private ListView contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_contacts);

        /* Greeting toast message */
        Toast.makeText(SetupContacts.this, R.string.message_label, Toast.LENGTH_SHORT).show();

        setupCaption();
        setupContacts();
    }

    /**
     * Receiving contacts from phone and displaying it
     */
    private void setupContacts() {
        Uri contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contentUri, contactDetails, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            ArrayList<String> contactsFromPhone = new ArrayList<>();
            String importedContact = "";

            while (cursor.moveToNext()) {
                importedContact = cursor.getString(1) + " (" + cursor.getString(2) + ")";
//                Log.i("DATA", importedContact);
                contactsFromPhone.add(importedContact);
            }
            contacts = (ListView)findViewById(R.id.contacts);
            contacts.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsFromPhone));
        }
    }

    /**
     * Receives message from previous activity and displays it with captionLabel
     */
    private void setupCaption() {
        Intent intent = getIntent();
        boolean newAccount = intent.getBooleanExtra(MainActivityDrunk.SETUP_ACCOUNT, false);
        Log.i(SetupContacts.class.toString(), String.valueOf(newAccount));

        captionLabel = (TextView)this.findViewById(R.id.newAccountMessage);
        captionLabel.setText(captionLabel.getText() + " " + String.valueOf(newAccount));
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
