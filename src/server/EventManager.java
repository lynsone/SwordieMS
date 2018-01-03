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

    public static void addEvent(Object clazz, String methodName, long delay) {
        new Timer().schedule(new TimerTask() {
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
    }

    public static void addEvent(Object clazz, String methodName, long delay, Object arg) {
        new Timer().schedule(new TimerTask() {
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
    }
}
