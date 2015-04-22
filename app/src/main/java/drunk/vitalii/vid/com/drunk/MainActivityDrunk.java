package drunk.vitalii.vid.com.drunk;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivityDrunk extends ActionBarActivity {
    private Button clickButton;
    private Button closeButton;
    private TextView text;
    private boolean isTextVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_drunk);
        this.clickButton = (Button) this.findViewById(R.id.clickBtn);
        this.closeButton = (Button) this.findViewById(R.id.closeBtn);
        this.text = (TextView) this.findViewById(R.id.messageTxt);

        this.applyListeners();
    }

    private void applyListeners() {
        final TextView textObj = this.text;

        this.clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextVisible) {
                    textObj.setVisibility(View.GONE);
                } else {
                    textObj.setVisibility(View.VISIBLE);
                }
                isTextVisible = !isTextVisible;
            }
        });


        this.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
