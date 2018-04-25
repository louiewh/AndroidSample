package com.dawang.androidsample;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class DynamicProxyTest {

    @Test
    public void dynamicProxyTest() throws Exception {
        // Context of the app under test.

        DynamicProxy<Person> dynamicProxy = new DynamicProxy<>();
        Person proxyInstance = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[] {Person.class}, dynamicProxy);

        proxyInstance.work();
        Student student = new Student();

        dynamicProxy.setTarget(student);
        proxyInstance.work();

        Teacher teacher = new Teacher();
        dynamicProxy.setTarget(teacher);
        proxyInstance.work();

        assertTrue(true);
    }

    interface Person {
        void work();
    }

    static class Student implements Person {

        @Override
        public void work() {
            System.err.println("Student working");
        }
    }

    static class Teacher implements Person {

        @Override
        public void work() {
            System.err.println("Teacher working");
        }
    }

    static class DynamicProxy<T extends Person> implements InvocationHandler {

        private T mTarget;

        public void setTarget(T target){
            mTarget = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if(mTarget != null) {
                return method.invoke(mTarget, args);
            } else {
                System.err.println("DynamicProxy invoke  target is null, do nothing");
                return null;
            }
        }
    }
}
