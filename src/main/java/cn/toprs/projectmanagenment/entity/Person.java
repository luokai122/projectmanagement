package cn.toprs.projectmanagenment.entity;


public class Person {
    private String name;
    private String age;
    private String addr;
    private String sex;

    public Person() {
    }

    public Person(String name, String age, String addr, String sex) {
        this.name = name;
        this.age = age;
        this.addr = addr;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
