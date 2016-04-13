package com.pla.finder;

import com.pla.query.Record;
import com.pla.utils.ConvertUtil;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.sql.Blob;
import java.sql.Clob;

@SuppressWarnings("serial")
public class AliasToEntityMapResultTransformer extends AliasedTupleSubsetResultTransformer {
    public static final AliasToEntityMapResultTransformer INSTANCE = new AliasToEntityMapResultTransformer();

    private AliasToEntityMapResultTransformer() {
    }

    public static AliasToEntityMapResultTransformer create() {
        return new AliasToEntityMapResultTransformer();
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        Record record = new Record(tuple.length);

        for (int i = 0; i < tuple.length; ++i) {
            String alias = aliases[i];
            if (alias != null) {
                Object value = tuple[i];
                if (value instanceof Clob) {
                    record.put(alias, ConvertUtil.clobToString((Clob) value));
                } else if (value instanceof Blob) {
                    record.put(alias, ConvertUtil.blobToBytes((Blob) value));
                } else {
                    record.put(alias, value);
                }
            }
        }

        return record;
    }

    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
