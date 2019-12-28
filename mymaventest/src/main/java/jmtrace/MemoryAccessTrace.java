package jmtrace;

public class MemoryAccessTrace {

    public static void printField(String rw, long identifier, String member){
        System.out.printf("%s %d %x %s\n", rw, Thread.currentThread().getId(), identifier, member);
    }

    public static void printArray(String rw, long identifier, String member, int index){
        System.out.printf("%s %d %x %s[%d]\n", rw, Thread.currentThread().getId(), identifier, member, index);
    }

    public static void traceFieldRead(Object obj, String field) {
        long identifier = System.identityHashCode(obj);
        String member = obj.getClass().getCanonicalName() + "." + field;
        printField("R", identifier, member);
    }

    public static void traceFieldWrite(Object obj, String field) {
        long identifier = System.identityHashCode(obj);
        String member = obj.getClass().getCanonicalName() + "." + field;
        printField("W", identifier, member);
    }

    public static void traceStaticRead(Class<?> cls, String field) {
        long identifier = System.identityHashCode(cls);
        String member = cls.getCanonicalName() + "." + field;
        printField("R", identifier, member);
    }

    public static void traceStaticWrite(Class<?> cls, String field) {
        long identifier = System.identityHashCode(cls);
        String member = cls.getCanonicalName() + "." + field;
        printField("W", identifier, member);
    }

    public static void traceArrayRead(Object obj, int index) {
        long identifier = System.identityHashCode(obj);
        String member = obj.getClass().getCanonicalName();

        printArray("w", identifier, member, index);
    }

    public static void traceArrayWrite(Object obj, int index) {
        long identifier = System.identityHashCode(obj);
        String member = obj.getClass().getCanonicalName();
        printArray("w", identifier, member, index);
    }
}
