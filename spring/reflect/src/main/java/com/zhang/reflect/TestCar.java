package com.zhang.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCar {

    //利用反射获取
    //1、获取class对象的多种方式
    @Test
    public void test01() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1、类名.class
        Class carClass1 = Car.class;

        //2、对象.getClass()
        Class carClass2 = new Car().getClass();

        //3、Class.forName("全路径")
        Class carClass3 = Class.forName("com.zhang.reflect.Car");

        //获取类的实例化对象
        Car car = (Car) carClass3.getDeclaredConstructor().newInstance();
        System.out.println(car);
    }

    //2、获取构造方法
    @Test
    public void test02() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        //获取Class对象(获取类的字节码文件，也就是class文件)
        Class carClass = Car.class;

        //获取所有的构造方法
        //getDeclaredConstructors()用来获取所有的构造方法，包括public和private
        Constructor[] constructors = carClass.getDeclaredConstructors();

        //getConstructors()用来获取所有的public的构造方法
        //Constructor[] constructors = carClass.getConstructors();

        for (Constructor c : constructors) {
            System.out.println("方法名称：" + c.getName() + " 参数个数：" + c.getParameterCount());
        }

        //给有参数的构造方法属性注入内容
        //public的构造方法
        Constructor c1 = carClass.getConstructor(String.class, int.class, String.class);
        Car car1 = (Car) c1.newInstance("宝马", 20, "白色");
        System.out.println(car1);

        //private的构造方法
        Constructor c2 = carClass.getDeclaredConstructor(String.class, int.class, String.class);
        c2.setAccessible(true);
        Car car2 = (Car) c2.newInstance("大奔", 22, "黑色");
        System.out.println(car2);

    }

    //3、获取属性
    @Test
    public void test03() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        //获取类的字节码文件（.class文件）
        Class carClass = Car.class;
        //获取类的实例化对象
        Car car = (Car) carClass.getDeclaredConstructor().newInstance();

        //获取所有的public属性
        //Field[] fields = carClass.getFields();

        //获取所有的属性（public和private）
        Field[] fields = carClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("name")) {
                //设置允许访问
                field.setAccessible(true);
                field.set(car, "迈巴赫");
            }

            System.out.println(field.getName());
            System.out.println(car);
        }
    }

    //4、获取普通方法
    @Test
    public void test04() throws InvocationTargetException, IllegalAccessException {
        Car car = new Car("奔驰", 10, "黑色");
        Class carClass = car.getClass();

        //获取public方法
        Method[] methods = carClass.getMethods();
        for (Method m1 : methods) {
            //执行toString方法
            if (m1.getName().equals("toString")) {
                String invoke = (String) m1.invoke(car);
                System.out.println("toString执行了：" + invoke);
            }
        }

        //获取private方法
        Method[] methodsAll = carClass.getDeclaredMethods();
        for (Method mAll : methodsAll) {
            if (mAll.getName().equals("run")) {
                mAll.setAccessible(true);
                mAll.invoke(car);
            }
        }
    }

}
