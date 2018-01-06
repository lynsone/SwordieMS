package server;

import client.Client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on 12/12/2017.
 */
public class EventManager {

    public static Timer addEvent(Object clazz, String methodName, long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Class c = clazz.getClass();
                    Method method = c.getMethod(methodName);
                    method.invoke(clazz);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }, delay);
        return timer;
    }

    public static Timer addEvent(Object clazz, String methodName, long delay, Object arg) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Class c = clazz.getClass();
                    Method method = c.getMethod(methodName, arg.getClass());
                    method.invoke(clazz, arg);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }, delay);
        return timer;
    }

    public static Timer addEvent(Object clazz, String methodName, long delay, Object arg1, Object arg2) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Class c = clazz.getClass();
                    Method method = c.getMethod(methodName, arg1.getClass(), arg2.getClass());
                    method.invoke(clazz, arg1, arg2);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }, delay);
        return timer;
    }

    public static Timer addRecurringEvent(Object clazz, String methodName, long delay, int count, Object arg1) {
        Timer timer = new Timer();
        for (int i = 1; i <= count; i++) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Class c = clazz.getClass();
                        Method method = c.getMethod(methodName, arg1.getClass());
                        method.invoke(clazz, arg1);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }, count * delay);
        }
        return timer;
    }

    public static Timer addEvent(Object clazz, String methodName, long delay, Object arg1, Object arg2, Object arg3) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Class c = clazz.getClass();
                    Method method = c.getMethod(methodName, arg1.getClass(), arg2.getClass(), arg3.getClass());
                    method.invoke(clazz, arg1, arg2, arg3);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }, delay);
        return timer;
    }
}
