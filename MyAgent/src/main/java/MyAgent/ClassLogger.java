package MyAgent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassLogger implements ClassFileTransformer {
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) {
		try {
			File file = new File("classes/" + className + ".class");
			File parentDir = file.getParentFile();
			
			if(!parentDir.exists())
				parentDir.mkdirs();
			
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(classfileBuffer);
			
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return classfileBuffer;
	}
}
