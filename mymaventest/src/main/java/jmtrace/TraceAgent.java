package jmtrace;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassReader;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class TraceAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader l, String name, Class c,
                                    ProtectionDomain d, byte[] b)
                    throws IllegalClassFormatException {
                if (name.startsWith("java") || name.startsWith("sun") || name.startsWith("jdk")
                        || name.startsWith("jmtrace")) {
                    return null;
                }
                ClassReader cr = new ClassReader(b);
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
                ClassAdapter cv = new ClassAdapter(cw);
                cr.accept(cv, 0);
                return cw.toByteArray();
            }
        }, true);
    }
}
