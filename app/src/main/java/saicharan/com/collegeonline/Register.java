package saicharan.com.collegeonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

  private EditText etName, etEmail, etRollNo, etPassword, etConfPassword;
  private Button btnRegister, btnLogin;

  private DbHelper dbHelper;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    dbHelper = new DbHelper(this);

    etName = findViewById(R.id.etName);
    etEmail = findViewById(R.id.etEmail);
    etRollNo = findViewById(R.id.etRollNo);
    etPassword = findViewById(R.id.etPassword);
    etConfPassword = findViewById(R.id.etConfPassword);
    btnRegister = findViewById(R.id.btnRegister);
    btnLogin = findViewById(R.id.btnLogin);

    btnRegister.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        if (etName.getText().toString().trim().isEmpty()) {
          etName.setError("Please enter name");
          etName.requestFocus();
        } else if (etEmail.getText().toString().isEmpty()) {
          etEmail.setError("Please enter email");
          etEmail.requestFocus();
        } else if (etRollNo.getText().toString().isEmpty()) {
          etRollNo.setError("Please enter roll no");
          etRollNo.requestFocus();
        } else if (etPassword.getText().toString().trim().isEmpty()) {
          etPassword.setError("Please enter password");
          etPassword.requestFocus();
        } else if (etConfPassword.getText().toString().trim().isEmpty()) {
          etConfPassword.setError("Please enter confirm password");
          etConfPassword.requestFocus();
        } else if (!etPassword.getText()
            .toString()
            .trim()
            .contentEquals(etConfPassword.getText().toString().trim())) {
          etConfPassword.setError("Password and Confirm password not matching");
          etConfPassword.requestFocus();
        } else if (!etRollNo.getText().toString().trim().startsWith("1601")) {
          etRollNo.setError("Please enter valid CBIT roll number");
          etRollNo.requestFocus();
        } else {

          User user = new User();
          user.setName(etName.getText().toString().trim());
          user.setEmail(etEmail.getText().toString().trim());
          user.setRoll_no(etRollNo.getText().toString().trim());
          user.setPassword(etPassword.getText().toString().trim());

          if (dbHelper.insertUser(user)) {
            Toast.makeText(Register.this, "User successfully registered", Toast.LENGTH_SHORT)
                .show();
            startActivity(new Intent(Register.this, LoginActivity.class));
            finish();
          }
        }
      }
    });

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(Register.this, LoginActivity.class);
        startActivity(intent);
        finish();
      }
    });
  }
}
