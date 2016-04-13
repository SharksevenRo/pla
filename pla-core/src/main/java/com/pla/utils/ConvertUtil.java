package com.pla.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;

public class ConvertUtil {
    //public static final Object[] NULL_PARA_ARRAY = new Object[0];

    public static String clobToString(Clob clob) {
        if (clob == null) {
            return null;
        }
        Reader inStreamDoc = null;
        try {
            inStreamDoc = clob.getCharacterStream();

            char[] tempDoc = new char[(int) clob.length()];
            inStreamDoc.read(tempDoc);

            return new String(tempDoc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStreamDoc != null) {
                try {
                    inStreamDoc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static byte[] blobToBytes(Blob blob) {
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read;
            while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
