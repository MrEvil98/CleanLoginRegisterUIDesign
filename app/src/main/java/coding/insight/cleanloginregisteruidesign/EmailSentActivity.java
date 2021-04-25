package coding.insight.cleanloginregisteruidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmailSentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sent);
    }

    public void backToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}