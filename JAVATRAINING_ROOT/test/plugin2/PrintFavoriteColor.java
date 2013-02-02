

import java.io.*;
import java.net.*;

public class PrintFavoriteColor{

    private File jarFile;
    private String className;

    public PrintFavoriteColor(File jarFile,String className){
        super();
        this.jarFile=jarFile;
        this.className=className;
    }

    public void print(){

        try{
            URL url = jarFile.toURL();
            URLClassLoader loader = new URLClassLoader(new URL[]{url});

            Class c = loader.loadClass(className);
            Object obj = c.newInstance();
            IFavoriteColor fc=(IFavoriteColor)obj;
            System.out.println( " My favorite color is "+fc.getColor()+"." );
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        if(args.length==2){
            String jarFileName=args[0];
            String className=args[1];

            PrintFavoriteColor pfc=new PrintFavoriteColor(new File(jarFileName),className);
            pfc.print();
        }
    }
}