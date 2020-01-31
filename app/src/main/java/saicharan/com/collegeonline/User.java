package saicharan.com.collegeonline;

public class  User {

  private String name;
  private String email;
  private String roll_no;
  private String password;

  public User() {
  }

  public User(String name, String email, String roll_no, String password) {
    this.name = name;
    this.email = email;
    this.roll_no = roll_no;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRoll_no() {
    return roll_no;
  }

  public void setRoll_no(String roll_no) {
    this.roll_no = roll_no;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
