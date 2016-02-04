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

import android.widget.Toast;
import drunk.vitalii.vid.com.R;


public class MainActivityDrunk extends Activity {
    /* ---Service--- */
    public static final String SETUP_ACCOUNT = "drunk.vitalii.vid.com.Activity.SETUP_ACCOUNT";
    private static final String TAG = "MainActivityDrunk";
    private static final String CLICKS_INDEX = "CLICKS_INDEX";
    private static int CLICKS;
    private PackageInfo packageInfo;


    /* ---UI--- */
    private Button theButton;
    private TextView versionLabel;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main_activity_drunk );
        Log.d( TAG, "onCreate()called" );

        setupTheButton();
        setupVersionLabel();
    }

    /**
     * Setup THE_BUTTON
     */
    private void setupTheButton() {
        theButton = (Button) this.findViewById( R.id.theButton );
        theButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                doEverythingFine( view );
            }
        } );
    }


    /**
     * Displaying version of app.
     * Receiving version from packageInfo and displaying it.
     */
    private void setupVersionLabel() {
        versionLabel = (TextView) this.findViewById( R.id.versionInfoLabel );
        try {
            packageInfo = getPackageManager().getPackageInfo( getPackageName(), 0 );
            if( packageInfo != null ) {
                versionLabel.setText( versionLabel.getText() + packageInfo.versionName );
            } else {
                versionLabel.setVisibility( View.GONE );
            }
        } catch ( PackageManager.NameNotFoundException e ) {
            e.printStackTrace();
        }
    }

    /**
     * The main action in app
     */
    private void doEverythingFine( View view ) {
        Log.d( TAG, "---THE_BUTTON is clicked---" );
        Log.d( TAG, "doEverythingFine() called" );
        if( isFirstTimeClicked() ) {
            openSetupContacts();
        } else {
            doMainStuff();
        }
    }

    /**
     * TODO checking in app when user makes first time click after installing app
     */
    private boolean isFirstTimeClicked() {
        Log.d( TAG, "isFirstTimeClicked() called" );

        boolean firstTimeClicked = CLICKS++ <= 0;
        Log.d( TAG, "isFirstTimeClicked() == " + firstTimeClicked );
        return firstTimeClicked;
    }

    public void onSaveInstanceState( Bundle bundle ) {
        super.onSaveInstanceState( bundle );
        Log.d( TAG, "onSaveInstanceState() called" );
    }


    /**
     * TODO sending message - The main function in app
     */
    private void doMainStuff() {
        Log.d( TAG, "doMainStuff() called" );
        Toast.makeText( MainActivityDrunk.this, "click - " + CLICKS, Toast.LENGTH_SHORT ).show();
    }

    /**
     * TODO open setup contacts activity
     */
    private void openSetupContacts() {
        Log.d( TAG, "openSetupContacts() called" );
        Intent intent = new Intent( this, SetupContacts.class );
        intent.putExtra( SETUP_ACCOUNT, true );
        startActivity( intent );
    }

    /**
     * Some overriding stuff
     */
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main_activity_drunk, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if( id == R.id.action_settings ) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    /**
     * TODO implementation is required
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d( TAG, "onPause() called" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d( TAG, "onResume() called" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d( TAG, "onResume() called" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d( TAG, "onDestroy() called" );
        Log.d( TAG, "CLICKS are reset" );
        CLICKS = 0;
    }
}
