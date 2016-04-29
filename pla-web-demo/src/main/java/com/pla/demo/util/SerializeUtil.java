package com.pla.demo.util;

import com.pla.demo.model.Dic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeUtil {
    public static String serialize(Object obj) throws IOException {
        return serialize(obj, true);
    }

    public static <T> T unSerialize(String serStr) throws IOException, ClassNotFoundException {
        return unSerialize(serStr, true);
    }

    public static String serialize(Object obj, boolean compress) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            // 压缩
            if (compress)
                bytes = GzipUtil.compress(bytes);
            return new String(bytes, "ISO-8859-1");
        } catch (IOException e) {
            throw e;
        } finally {
            if (objectOutputStream != null)
                objectOutputStream.close();
            if (byteArrayOutputStream != null)
                byteArrayOutputStream.close();
        }

    }

    public static <T> T unSerialize(String serStr, boolean compress) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byte[] data = serStr.getBytes("ISO-8859-1");
            //解压
            if (compress)
                data = GzipUtil.uncompress(data);
            byteArrayInputStream = new ByteArrayInputStream(data);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            T obj = (T) objectInputStream.readObject();

            objectInputStream.close();
            byteArrayInputStream.close();
            return obj;
        } catch (IOException e) {
            throw e;
        } finally {
            if (objectInputStream != null)
                objectInputStream.close();
            if (byteArrayInputStream != null)
                byteArrayInputStream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Dic dic = new Dic();
        dic.setId(1l);
        dic.setDicKey("a");
        dic.setDicValue("b");
        dic.setDicContent("测试");

        Dic dic2 = new Dic();
        dic2.setId(2l);
        dic2.setDicKey("a2");
        dic2.setDicValue("b2");
        dic2.setDicContent("测试2");

        List<Dic> list = new ArrayList<Dic>();
        list.add(dic);
        list.add(dic2);


        String aa = serialize(list);
        System.out.println(aa.getBytes("ISO-8859-1").length);

        List<Dic> dics = unSerialize(aa);
        for (Dic dicTmp:dics){
            System.out.println(dicTmp);
        }
//        System.out.println(dic1);

    }
}
