# tarlib

Basic POSIX implementation of tar in Java

## Example

### Dumping all entries

```java
TarInputStream tis = new TarInputStream(new FileInputStream("test.tar"));

for (Header h : tis.getHeaders()) {
    System.out.println("Writing " + h.getName());

    tis.openEntry(h);

    FileOutputStream fos = new FileOutputStream(h.getName());
    
    byte[] b = new byte[1024];

    int read;
    while ((read = tis.read(b, 0, b.length)) != -1) {
        fos.write(b, 0, read);
    }

    fos.close();

    tis.closeEntry();
}
```
