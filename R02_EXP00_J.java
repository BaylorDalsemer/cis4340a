// noncompliant code example
public void deleteFile(){
 
  File someFile = new File("someFileName.txt");
  // Do something with someFile
  someFile.delete();
 
}

//compliant code example
public void deleteFile(){
 
  File someFile = new File("someFileName.txt");
  // Do something with someFile
  if (!someFile.delete()) {
    // Handle failure to delete the file
  }
 
}
