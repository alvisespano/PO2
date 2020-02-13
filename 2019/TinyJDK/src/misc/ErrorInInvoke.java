package misc;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class ErrorInInvoke {
    public static void run() {
        throw new AbstractMethodError("Not really, just testing");
    }

    public static void main(String[] args) {
        Method m;

        try {
            m = ErrorInInvoke.class.getMethod("run", new Class[]{});
        } catch (Throwable t) {
            throw new RuntimeException("Test failed (getMethod() failed");
        }

        try {
            m.invoke(null, null);
        } catch (AbstractMethodError e) {
            throw new RuntimeException("Test failed (AbstractMethodError passed through)");
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (!(t instanceof AbstractMethodError)) {
                throw new RuntimeException("Test failed (InvocationTargetException didn't wrap AbstractMethodError)");
            }
        } catch (Throwable t) {
            throw new RuntimeException("Test failed (Unexpected exception)");
        }
    }
}
