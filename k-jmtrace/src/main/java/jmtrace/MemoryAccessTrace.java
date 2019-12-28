package jmtrace;

public class MemoryAccessTrace {

    public static boolean isLibraryFunction(final String obj) {
        return obj.startsWith("java");
    }

    public static void traceArrayRead(Object arr, int index) {
        String member = arr.getClass().getCanonicalName();
        member = member.replaceAll("]", index + "]");
        long hashcode = (long) System.identityHashCode(arr) << 32 + index;
        System.out.printf("R %d %016x %s\n", Thread.currentThread().getId(), hashcode, member);
    }

    public static void traceArrayWrite(Object arr, int index) {
        String member = arr.getClass().getCanonicalName();
        member = member.replaceAll("]", index + "]");
        long hashcode = (long) System.identityHashCode(arr) << 32 + index;
        System.out.printf("W %d %016x %s\n", Thread.currentThread().getId(), hashcode, member);
    }

    public static void traceRead(String obj, String field) {
        if (isLibraryFunction(obj))
            return;
        obj = obj.replace("/", ".");
        long hashcode = (long) System.identityHashCode(obj) << 32 + field.hashCode();

        System.out.printf("R %d %016x %s\n", Thread.currentThread().getId(), hashcode, obj + "." + field);
    }

    public static void traceWrite(String obj, String field) {
        if (isLibraryFunction(obj))
            return;
        obj = obj.replace("/", ".");
        long hashcode = (long) System.identityHashCode(obj) << 32 + field.hashCode();
        System.out.printf("W %d %016x %s\n", Thread.currentThread().getId(), hashcode, obj + "." + field);
    }

}
