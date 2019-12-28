package jmtrace;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class MethodAdapter extends MethodVisitor implements Opcodes {
    public MethodAdapter(final MethodVisitor mv) {
        super(ASM5, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        switch (opcode) {
            case GETSTATIC:
                mv.visitLdcInsn(Type.getType("L" + owner + ";"));
                mv.visitLdcInsn(owner+"."+name);
                mv.visitMethodInsn(INVOKESTATIC, "MemoryAccessTrace", "traceStaticRead",
                        "(Ljava/lang/Object;Ljava/lang/String;)V", false);
                break;
            case PUTSTATIC:
                mv.visitLdcInsn(Type.getType("L" + owner + ";"));
                mv.visitLdcInsn(owner+"."+name);
                mv.visitMethodInsn(INVOKESTATIC, "MemoryAccessTrace", "traceStaticWrite",
                        "(Ljava/lang/Object;Ljava/lang/String;)V", false);
                break;
            case GETFIELD:
                mv.visitInsn(DUP);
                mv.visitLdcInsn(owner+"."+name);
                mv.visitMethodInsn(INVOKESTATIC, "MemoryAccessTrace", "traceFieldRead",
                        "(Ljava/lang/Object;Ljava/lang/String;)V", false);
                break;
            case PUTFIELD:
                mv.visitInsn(SWAP);
                mv.visitInsn(DUP);
                mv.visitLdcInsn(name);
                mv.visitMethodInsn(INVOKESTATIC,
                        "MemoryAccessTrace",
                        "traceFieldWrite",
                        "(Ljava/lang/Object;Ljava/lang/String;)V",
                        false);
                mv.visitInsn(SWAP);
                break;
        }
        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }
}
