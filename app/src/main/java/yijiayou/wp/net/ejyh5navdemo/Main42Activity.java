package yijiayou.wp.net.ejyh5navdemo;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.feyon.myapplication.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Main42Activity extends AppCompatActivity {

    private String TAG="TAG";
    private ReferenceQueue referenceQueue=new ReferenceQueue();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main42);

        WeakReference<TextBean> weakReference=new WeakReference<TextBean>(new TextBean(),referenceQueue);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.gc();
                try { // 下面休息几分钟，让上面的垃圾回收线程运行完成
                    Thread.currentThread().sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (referenceQueue.poll()!=null)
                {
                    Reference reference= referenceQueue.poll();
                    Object o= reference.get();
                    System.out.println();
                }
            }
        });

        test11();
    }

    public class TextBean{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            Log.i("TextBean", "finalize: ");
        }
    }

    //byteArrayInputStream
    public void test()
    {
        byte[] arrayLetters = {
                0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
                0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
        };

        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(arrayLetters);

        if (byteArrayInputStream.available()==0)
            return;

        int len=10;
        byte[] strs=new byte[len];

//            int result= byteArrayInputStream.read(strs);
//            String tos=new String(strs);

        for (int i = 0; i <len ; i++) {
            int result= byteArrayInputStream.read();
            if (result!=-1)
            {
                strs[i]= (byte) result;
            }else {
                break;
            }
        }
        String tos=new String(strs);
        System.out.println(tos);



        if (!byteArrayInputStream.markSupported())
        return;

        byteArrayInputStream.mark(0);

        long skip= byteArrayInputStream.skip(2);
        byte[] text2=new byte[10];
        try {
            int result= byteArrayInputStream.read(text2);
            String tos2=new String(text2);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byteArrayInputStream.reset();
        byte[] test3=new byte[10];
        try {
            byteArrayInputStream.read(test3);
            String tos3=new String(test3);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //byteArrayOutStream
    public void text2(){
        byte[] arrayLetters = {
                0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
                0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
        };

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(arrayLetters);
            String tos= byteArrayOutputStream.toString();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void test3()
    {
        MyPipeReceiver myPipeReceiver=new MyPipeReceiver();
        MyPipeWrite myPipeWrite=new MyPipeWrite();

        try {
            myPipeReceiver.pipedInputStream.connect(myPipeWrite.pipedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        myPipeReceiver.start();
        myPipeWrite.start();
    }


    public  class MyPipeReceiver extends Thread{

        private PipedInputStream pipedInputStream=new PipedInputStream();
        private int total=0;
        @Override
        public void run() {
            super.run();

            read();
        }

        public void read(){
            byte[] strs=new byte[10];
            try {
               int len=  pipedInputStream.read(strs);
                total+=len;
                String s=new String(strs);
                Log.i(TAG, "read: "+s);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MyPipeWrite extends Thread{
        private PipedOutputStream pipedOutputStream=new PipedOutputStream();

        @Override
        public void run() {
            super.run();
            write();
        }

        public void write(){
            String s="asdasdamsdadmadskakdasdkasmdkamdamdkmalkdmakmdskamkdsmkamdssssssssssssssssssssssssssQ";
            try {
               pipedOutputStream.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void test4(){
        AssetManager assetManager=getAssets();
        try {
          InputStream inputStream= assetManager.open("stream.txt");
          byte[] s=new byte[10];
          int len= inputStream.read(s);
          String ss=new String(s);
            Log.i(TAG, "test4: "+ss);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void test5()
    {
        Person person=new Person();
        person.name="ces";

        File file=new File(getExternalFilesDir(null)+"/test3.txt");
        if (file.exists()) {
           boolean s= file.delete();
            System.out.println();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeInt(1);
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
        try {
            ObjectInput objectInput=new ObjectInputStream(new FileInputStream(file));
            //                Person person2= (Person) objectInput.readObject();
            int ss= objectInput.readInt();
            try {
                Person person1= (Person) objectInput.readObject();
                System.out.println();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println();
            objectInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Person implements Serializable {
        private String name;
    }


    public void  test6(){
        //Assets 不能写 只能读
        AssetManager assetManager=getAssets();
        try {
            AssetFileDescriptor fileDescriptor=assetManager.openFd("stream.txt");
            //------------------------------------------------------------------------
            FileOutputStream fileOutputStream=fileDescriptor.createOutputStream();

            String s="最加";
            fileOutputStream.write(s.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void test7(){

//        PrintStream printStream=new PrintStream()
        try {
            FileOutputStream out = new FileOutputStream(FileDescriptor.out);
            out.write('A');
            out.close();
        } catch (IOException e) {
        }
    }

    public void  test8(){
        char[] chars=new char[]{'1','2','3','a','b'};
        CharArrayReader charArrayReader=new CharArrayReader(chars);

        char[] chars1=new char[2];
        try {
            charArrayReader.read(chars1);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test9(){
        String s="s";
        char[] chars=new char[]{'1','2','3','a','b','c','d'};
        CharArrayWriter charArrayWriter=new CharArrayWriter(5);
        try {
            charArrayWriter.write(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[] t= charArrayWriter.toCharArray();
        System.out.println();
    }

    public void test10(){
        File file=new File(getExternalFilesDir(null)+File.separator+"text4");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintStream printStream=new PrintStream(file);
            String s="(1)public boolean mkdirs();既可以在不存在的目录中创建文件夹又可以创建多级目录（个人推荐使用此方法）\n" +
                    "\n" +
                    "(2)public boolean mkdir();只能在已近存在的目录中创建文件夹";
            printStream.append(s);
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fileInputStream=new FileInputStream(file);

            try {
                InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"utf-8");
                char[] chars=new char[20];
                StringBuffer s=new StringBuffer();
                while (inputStreamReader.read(chars)!=-1)
                {
                    s.append(chars);
                }
                s.append(chars);
                System.out.println(s);
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void test11(){
        try {
            FileWriter writer=new FileWriter(getExternalFilesDir(null)+File.separator+"test5");

            writer.append('A');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileReader fileReader=new FileReader(getExternalFilesDir(null)+File.separator+"test6");
            try {
                fileReader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
