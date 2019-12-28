package jmtrace;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASMTest {
    public static void redefineHelloWorldClass() {
        try {
            InputStream inputStream = new FileInputStream("C:\\Users\\95880\\Documents\\IDEA Project\\mymaventest\\src\\test\\java\\TestInstrumented.class");
            ClassReader reader = new ClassReader(inputStream);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
            ClassVisitor change = new ClassAdapter(writer);
            reader.accept(change, ClassReader.EXPAND_FRAMES);

            System.out.println("Instrument Success!");
            byte[] code = writer.toByteArray();
            try {
                // 将二进制流写到本地磁盘上
                FileOutputStream fos = new FileOutputStream("C:\\Users\\95880\\Documents\\IDEA Project\\mymaventest\\src\\test\\java\\TestInstrumentedChange.class");
                fos.write(code);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failure!");
        }
    }
}
