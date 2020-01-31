package saicharan.com.collegeonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private SharedPreferences preferences;
  private SharedPreferences.Editor editor;
  private DbHelper dbHelper;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    preferences = this.getSharedPreferences("prefs", 0);
    editor = preferences.edit();

    dbHelper = new DbHelper(this);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close) {
          @Override public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);

            TextView tvName = drawerView.findViewById(R.id.tvName);
            TextView tvEmail = drawerView.findViewById(R.id.tvEmail);

            tvName.setText(preferences.getString("name", "User Name"));
            tvEmail.setText(preferences.getString("email", "Email"));
          }

          @Override public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
          }

          @Override public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
          }
        };
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_profile) {
      startActivity(new Intent(HomeActivity.this, Profile.class));
    } else if (id == R.id.nav_logout) {
      editor.clear().apply();
      startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    } else if (id == R.id.nav_timetable) {
      startActivity(new Intent(HomeActivity.this, Timetable.class));
    }
    else if (id == R.id.nav_gallery) {
      startActivity(new Intent(HomeActivity.this, Gallery.class));
    }
    else if (id == R.id.nav_exam_dates) {
      startActivity(new Intent(HomeActivity.this, examdates.class));
    }
    else if (id == R.id.nav_notes) {
      startActivity(new Intent(HomeActivity.this, notes.class));
    }
    else if (id == R.id.nav_courses) {
      startActivity(new Intent(HomeActivity.this, courses.class));
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
