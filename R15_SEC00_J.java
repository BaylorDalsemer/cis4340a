// noncompliant coding example
public class PasswordManager {
 
  public static void changePassword() throws FileNotFoundException {
    FileInputStream fin = openPasswordFile();
 
    // Test old password with password in file contents; change password,
    // then close the password file
  }
 
  public static FileInputStream openPasswordFile()
      throws FileNotFoundException {
    final String password_file = "password";
    FileInputStream fin = null;
    try {
      fin = AccessController.doPrivileged(
        new PrivilegedExceptionAction<FileInputStream>() {
          public FileInputStream run() throws FileNotFoundException {
            // Sensitive action; can't be done outside privileged block
            FileInputStream in = new FileInputStream(password_file);
            return in;
          }
      });
    } catch (PrivilegedActionException x) {
      Exception cause = x.getException();
      if (cause instanceof FileNotFoundException) {
        throw (FileNotFoundException) cause;
      } else {
        throw new Error("Unexpected exception type", cause);
      }
    }
    return fin;
  }
}

// compliant coding example (hiding exceptions)
class PasswordManager {
 
  public static void changePassword() {
    FileInputStream fin = openPasswordFile();
    if (fin == null) {
      // No password file; handle error
    }
 
    // Test old password with password in file contents; change password
  }
 
  private static FileInputStream openPasswordFile() {
    final String password_file = "password";
    final FileInputStream fin[] = { null };
    AccessController.doPrivileged(new PrivilegedAction<Void>() {
        public Void run() {
          try {
            // Sensitive action; can't be done outside
            // doPrivileged() block
            fin[0] = new FileInputStream(password_file);
          } catch (FileNotFoundException x) {
            // Report to handler
          }
          return null;
        }
    });
    return fin[0];
  }
}
