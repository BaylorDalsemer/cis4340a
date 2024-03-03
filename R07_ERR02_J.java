// noncompliant code example 

try {
  // ...
} catch (SecurityException se) {
  System.err.println(se);
  // Recover from exception
}

// compliant code example
try {
  // ...
} catch(SecurityException se) {
  logger.log(Level.SEVERE, se);
  // Recover from exception
}
