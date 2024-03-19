package org.example;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DB {
    public final static int idLn = 4;
    public final static int nameLn = 20;
    public final static int ageLn = 4;
    public final static int countryLn = 20;

    private final static int recordLn = idLn+2*nameLn+ageLn+2*countryLn;

    RandomAccessFile raf;

    public DB(String path) throws IOException
    {
        this.raf = new RandomAccessFile(path,"rw");
    }

    public int recordsCount() throws IOException {
        return (int)raf.length()/recordLn;
    }

    public void append(Data d) throws IOException {
        raf.seek(raf.length());
        write(d);
    }
    public void update(Data d) throws IOException {
        if (d.getId() < 0 || d.getId() >= recordsCount())
            throw new IllegalArgumentException(d.getId() + " out of range");
        raf.seek((long) d.getId() *recordLn);
        write(d);
    }

    public Data select(int id) throws IOException {
        if (id < 0 || id >= recordsCount())
            throw new IllegalArgumentException(id + " out of range");
        raf.seek((long) id *recordLn);
        return read();
    }

    public Data read() throws IOException {
        int id = raf.readInt();
        StringBuffer reader = new StringBuffer();
        for (int i = 0;i<nameLn;i++)
            reader.append(raf.readChar());
        String name = reader.toString().trim();
        int age = raf.readInt();
        reader.setLength(0);
        for (int i = 0;i<countryLn;i++)
            reader.append(raf.readChar());
        String country = reader.toString().trim();
        return new Data(id,name,age,country);
    }

    public void write(Data d) throws IOException {
        raf.writeInt(d.getId());
        StringBuffer appender = new StringBuffer();
        appender.append(d.getName());
        if(appender.length()>nameLn)
        {
            appender.setLength(nameLn);
        }
        else if(appender.length()<nameLn)
        {
            for(int i = nameLn-appender.length();i>0;i--)
                appender.append(" ");
        }
        raf.writeChars(appender.toString());
        raf.writeInt(d.getAge());
        appender= new StringBuffer();
        appender.append(d.getCountry());
        if(appender.length()>countryLn)
        {
            appender.setLength(countryLn);
        }
        else if(appender.length()<countryLn)
        {
            for(int i = countryLn-appender.length();i>0;i--)
                appender.append(" ");
        }
        raf.writeChars(appender.toString());
    }
}
