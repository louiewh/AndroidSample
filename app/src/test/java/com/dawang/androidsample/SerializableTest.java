package com.dawang.androidsample;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static org.junit.Assert.assertEquals;

/**
 * Created by louie.wang on 2018/4/4.
 */

public class SerializableTest {

    @Test
    public  void serializableTest() throws IOException {

        Person person1 = serializable();
        Person person2 = deSerializable();

        assertEquals("Hello", person2, person1);
    }

    private Person serializable() throws IOException {
        Person person = new Person(1234, "wang");
        System.out.println("Person serializable" + person);
        FileOutputStream fos = new FileOutputStream("Person.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.flush();
        oos.close();

        return person;
    }

    public Person deSerializable() throws IOException {
        Person person = null;

        FileInputStream fis = new FileInputStream("Person.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            person = (Person) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
        System.out.println("Person deSerializable" + person);

        return person;
    }

    public static class Person implements Serializable{
        private static final long serialVersionUID = 123456789L;
        public int id;
        public String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return "Person: " + id + " " + name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            } else if (obj == null) {
                return false;
            } else if (getClass() != obj.getClass()) {
                return false;
            }

            Person other = (Person) obj;


            if (name.equals(other.name) && id == other.id) {
                return true;
            }

            return false;
        }
    }
}
