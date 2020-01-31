package saicharan.com.collegeonline;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

  private EditText etName, etEmail, etPassword, etConfPassword;
  private Button btnUpdate;
  private DbHelper dbHelper;

  private SharedPreferences preferences;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    preferences = this.getSharedPreferences("prefs", 0);

    dbHelper = new DbHelper(this);

    etName = findViewById(R.id.etName);
    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    etConfPassword = findViewById(R.id.etConfPassword);
    btnUpdate = findViewById(R.id.btnUpdate);

    etName.setText(preferences.getString("name", ""));
    etEmail.setText(preferences.getString("email", ""));
    etPassword.setText(preferences.getString("password", ""));
    etConfPassword.setText(preferences.getString("password", ""));


    btnUpdate.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        if (etName.getText().toString().trim().isEmpty()) {
          etName.setError("Please enter name");
          etName.requestFocus();
        } else if (etEmail.getText().toString().isEmpty()) {
          etEmail.setError("Please enter email");
          etEmail.requestFocus();
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
        } else {

          User user = new User();
          user.setName(etName.getText().toString().trim());
          user.setEmail(etEmail.getText().toString().trim());
          user.setRoll_no(preferences.getString("roll_no", ""));
          user.setPassword(etPassword.getText().toString().trim());

          if (dbHelper.updateUser(user)) {
            Toast.makeText(Profile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
          }else {
            Toast.makeText(Profile.this, "Failed to update, please try again", Toast.LENGTH_SHORT)
                .show();
          }
        }
      }
    });
  }
}
