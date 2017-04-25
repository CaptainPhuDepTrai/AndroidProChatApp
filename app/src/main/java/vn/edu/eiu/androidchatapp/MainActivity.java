package vn.edu.eiu.androidchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class MainActivity extends AppCompatActivity {

    static final String APP_ID = "56914";
    static final String AUTH_KEY = "wGsqaFNMLmbNzPQ";
    static final String AUTH_SECRET = "KWHwJUUN67AfXFh";
    static final String ACCOUT_KEY ="cq5SMjXYz2NizfXMBrCy";

    Button btnLogin,btnSignup;
    EditText edtUser,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFramwork();

        btnLogin = (Button)findViewById(R.id.main_btnLogin);
        btnSignup =(Button)findViewById(R.id.main_btnSignup);

        edtPassword = (EditText)findViewById(R.id.main_editPassword);
        edtUser = (EditText)findViewById(R.id.main_editLogin);

        btnSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,activity_sign_up.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String password = edtPassword.getText().toString();

                QBUser qbUser = new QBUser(user,password);
                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getBaseContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(getBaseContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });


    }

    private void initializeFramwork() {
        QBSettings.getInstance().init(getApplicationContext(),APP_ID,AUTH_KEY,AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUT_KEY);
    }
}
