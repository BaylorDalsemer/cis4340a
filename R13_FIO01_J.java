// noncompliant code example
Writer out = new FileWriter("file");

// compliant code example
Path file = new File("file").toPath();
 
// Throw exception rather than overwrite existing file
Set<OpenOption> options = new HashSet<OpenOption>();
options.add(StandardOpenOption.CREATE_NEW);
options.add(StandardOpenOption.APPEND);
 
// File permissions should be such that only user may read/write file
Set<PosixFilePermission> perms =
    PosixFilePermissions.fromString("rw-------");
FileAttribute<Set<PosixFilePermission>> attr =
    PosixFilePermissions.asFileAttribute(perms);
 
try (SeekableByteChannel sbc =
         Files.newByteChannel(file, options, attr)) {
  // Write data
};
