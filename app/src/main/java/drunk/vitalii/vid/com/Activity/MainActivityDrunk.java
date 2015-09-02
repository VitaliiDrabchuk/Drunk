package drunk.vitalii.vid.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import drunk.vitalii.vid.com.R;


public class MainActivityDrunk extends Activity {
    public static final String SETUP_ACCOUNT = "drunk.vitalii.vid.com.Activity.SETUP_ACCOUNT";

    private static final String VERSION = "version: ";
    private static boolean newAccount = true;

    private Button clickButton;
    private TextView text;
    private TextView versionLabel;
    private boolean isTextVisible;
    private PackageInfo packageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_drunk);

        versionLabel = (TextView) this.findViewById(R.id.versionInfoLabel);
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (packageInfo != null) {
            versionLabel.setText(VERSION + packageInfo.versionName);
        } else {
            versionLabel.setVisibility(View.GONE);
        }

        clickButton = (Button) this.findViewById(R.id.clickBtn);
        text = (TextView) this.findViewById(R.id.messageTxt);
        text.setVisibility(View.GONE);

//        applyListeners();
    }

    private void applyListeners() {
        final TextView textObj = this.text;

        this.clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("RED_BUTTON", "is clicked");
                if (newAccount) {
                    Log.i("ACCOUNT", "is new");
                    newAccount = false;
                } else {
                    Log.i("ACCOUNT", "exists");
                    if (isTextVisible) {
                        textObj.setVisibility(View.GONE);
                    } else {
                        textObj.setVisibility(View.VISIBLE);
                    }
                    isTextVisible = !isTextVisible;
                }

            }
        });
    }

    public void doEverythingFine(View view) {
        Intent intent = new Intent(this, SetupContacts.class);
        intent.putExtra(SETUP_ACCOUNT, true);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_drunk, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
