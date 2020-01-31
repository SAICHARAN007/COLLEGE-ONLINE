package saicharan.com.collegeonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

  private EditText etRollNo, etPassword;
  private Button btnLogin,btnRegister;
  private DbHelper dbHelper;
  private SharedPreferences preferences;
  private SharedPreferences.Editor editor;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    preferences = this.getSharedPreferences("prefs", 0);

    if (preferences.getString("name", "").length() > 0) {
      startActivity(new Intent(LoginActivity.this, HomeActivity.class));
      finish();
    }

    dbHelper = new DbHelper(this);

    etRollNo = findViewById(R.id.etRollNo);
    etPassword = findViewById(R.id.etPassword);
    btnLogin = findViewById(R.id.btnLogin);
    btnRegister = findViewById(R.id.btnRegister);

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        if (etRollNo.getText().toString().trim().isEmpty()) {
          etRollNo.setError("Please Enter Roll No");
          etRollNo.requestFocus();
        } else if (etPassword.getText().toString().trim().isEmpty()) {
          etPassword.setError("Please enter password");
          etPassword.requestFocus();
        } else {
          if (dbHelper.isUserRegistered(etRollNo.getText().toString().trim(),
              etPassword.getText().toString().trim())) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
          }else {
            Toast.makeText(LoginActivity.this, "Invalid Login credentials", Toast.LENGTH_SHORT)
                .show();

          }
        }
      }
    });

    btnRegister.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        Intent intent = new Intent(LoginActivity.this, Register.class);
        startActivity(intent);
        finish();
      }
    });
  }
}

