public class TestInstrumented {
    public static void main(String[] args) {
        int[] a = new int [10];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
//            MemoryAccessTrace.traceArrayWrite(a,i);
        }
        SomeClass someObj = new SomeClass();
        SomeClass.staticField = 1;
//        MemoryAccessTrace.traceStaticWrite(SomeClass.class, "staticField");
        someObj.field = SomeClass.staticField;
        someObj.otherField = someObj.field;
//        MemoryAccessTrace.traceFieldRead(someObj,"field");
//        MemoryAccessTrace.traceFieldWrite(someObj,"otherField");

    }
}
