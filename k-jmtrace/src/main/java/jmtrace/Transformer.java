package jmtrace;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.startsWith("java") || className.startsWith("sun") || className.startsWith("jdk")
        || className.startsWith("jmtrace")) {
            return null;
        }
        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassAdapter ct = new ClassAdapter(cw);

        cr.accept(ct, 0);

        return cw.toByteArray();
    }

}

