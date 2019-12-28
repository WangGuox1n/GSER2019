package jmtrace;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class MethodAdapter extends MethodVisitor implements Opcodes {

    MethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        switch (opcode) {
            case GETFIELD:
            case GETSTATIC:
                mv.visitLdcInsn(owner);
                mv.visitLdcInsn(name);
                mv.visitMethodInsn(INVOKESTATIC, "jmtrace/MemoryAccessTrace", "traceRead", "(Ljava/lang/String;Ljava/lang/String;)V", false);
                break;
            case PUTFIELD:
            case PUTSTATIC:
                mv.visitLdcInsn(owner);
                mv.visitLdcInsn(name);
                mv.visitMethodInsn(INVOKESTATIC, "jmtrace/MemoryAccessTrace", "traceWrite", "(Ljava/lang/String;Ljava/lang/String;)V", false);
                break;
        }
        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public void visitInsn(int opcode) {
        switch (opcode) {
            case IALOAD:
            case LALOAD:
            case FALOAD:
            case DALOAD:
            case AALOAD:
            case BALOAD:
            case CALOAD:
            case SALOAD:
                // v1, v2 -> v1, v2, v1, v2
                mv.visitInsn(DUP2);
                mv.visitMethodInsn(INVOKESTATIC, "jmtrace/MemoryAccessTrace", "traceArrayRead", "(Ljava/lang/Object;I)V", false);
                break;
            case IASTORE:
            case FASTORE:
            case AASTORE:
            case BASTORE:
            case CASTORE:
            case SASTORE:
                                            // arr, index, value
                mv.visitInsn(DUP_X2);       // value, arr, index, value
                mv.visitInsn(POP);          // value, arr, index,
                mv.visitInsn(DUP2_X1);         // arr, index, value, arr, index,
                mv.visitMethodInsn(INVOKESTATIC, "jmtrace/MemoryAccessTrace", "traceArrayWrite", "(Ljava/lang/Object;I)V", false);
                                            // value, arr, index,
                break;
            case LASTORE:
            case DASTORE:
                // store long or double array
                // stack: before [arrayref, index, value] → []         note: value 在栈中占两个栈帧，所以需单独处理， 按两个value处理

                                            // arr, index, value, value
                mv.visitInsn(DUP2_X2);      // value, value, arr, index, value, value
                mv.visitInsn(POP2);         // value, value, arr, index,
                mv.visitInsn(DUP2_X2);      // value, value, arr, index, arr, index,
                mv.visitMethodInsn(INVOKESTATIC, "jmtrace/MemoryAccessTrace", "traceArrayWrite", "(Ljava/lang/Object;I)V", false);
                                            //  value, value, arr, index,
                break;
        }
        mv.visitInsn(opcode);
    }
}
