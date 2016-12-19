package zous.autovaluesample;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {
  public static User create(String name, int age) {
    return new AutoValue_User(name, age);
  }

  public abstract String name();

  public abstract int age();
}
