package saicharan.com.collegeonline;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

  public static final String DATABASE_NAME = "collegeonlinedb";
  public static final String USERS_TABLE_NAME = "users";

  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_EMAIL = "email";
  public static final String COLUMN_ROLL_NO = "roll_no";
  public static final String COLUMN_PASSWORD = "password";

  private SharedPreferences preferences;
  private SharedPreferences.Editor editor;

  public DbHelper(Context context) {
    super(context, DATABASE_NAME, null, 1);
    preferences = context.getSharedPreferences("prefs", 0);
    editor = preferences.edit();
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table users "
        + "(id integer primary key, name text,email text, roll_no text,password text)");
  }

  @Override public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS users");
    onCreate(db);
  }

  public boolean insertUser(User user) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COLUMN_NAME, user.getName());
    contentValues.put(COLUMN_EMAIL, user.getEmail());
    contentValues.put(COLUMN_ROLL_NO, user.getRoll_no());
    contentValues.put(COLUMN_PASSWORD, user.getPassword());
    db.insert(USERS_TABLE_NAME, null, contentValues);
    return true;
  }

  public boolean isUserRegistered(String roll_no, String password) {
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor cursor = db.rawQuery("SELECT * FROM "
        + USERS_TABLE_NAME
        + " WHERE "
        + COLUMN_ROLL_NO
        + " = '"
        + roll_no
        + "' AND "
        + COLUMN_PASSWORD
        + " = '"
        + password
        + "'", null);

    if (cursor.getCount() == 1 && cursor.moveToFirst() && roll_no.contentEquals(
        cursor.getString(cursor.getColumnIndex(COLUMN_ROLL_NO))) && password.contentEquals(
        cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))) {
      User user = new User();
      user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
      user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
      user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
      user.setRoll_no(cursor.getString(cursor.getColumnIndex(COLUMN_ROLL_NO)));
      editor.putString("roll_no", user.getRoll_no()).apply();
      editor.putString("name", user.getName()).apply();
      editor.putString("email", user.getEmail()).apply();
      editor.putString("password", user.getPassword()).apply();

      return true;
    } else {
      return false;
    }
  }

  public boolean updateUser(User user) {
    SQLiteDatabase db = this.getWritableDatabase();

    try {
      db.execSQL("UPDATE users SET name = '"
          + user.getName()
          + "', email = '"
          + user.getEmail()
          + "', password = '"
          + user.getPassword()
          + "' WHERE roll_no = "
          + user.getRoll_no());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
