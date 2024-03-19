package org.example;

class Data
{
    private int id;
    private String name;
    private int age;
    private String country;

    public Data(int id,String name, int age, String country)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public String getCountry()
    {
        return country;
    }
}