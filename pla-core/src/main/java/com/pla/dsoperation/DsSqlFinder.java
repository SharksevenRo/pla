package com.pla.dsoperation;

import com.pla.query.Record;
import com.pla.utils.ConvertUtil;
import com.pla.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DsSqlFinder {
    private DsSqlOperation sqlConn;

    private String dsId;

    public DsSqlFinder() {
        this.dsId = "dataSource";
        sqlConn = new DsSqlOperation(this.dsId);
    }

    public DsSqlFinder(String dataSourceId) {
        this.dsId = dataSourceId;
        sqlConn = new DsSqlOperation(this.dsId);
    }

    public DsSqlFinder(DsSqlOperation sqlConn) {
        this.dsId = "dataSource";
        this.sqlConn = sqlConn;
    }

    public DsSqlFinder(String dataSourceId, DsSqlOperation sqlConn) {
        this.dsId = dataSourceId;
        this.sqlConn = sqlConn;
    }

    public static DsSqlFinder create() {
        return new DsSqlFinder();
    }

    public static DsSqlFinder create(String dataSourceId) {
        return new DsSqlFinder(dataSourceId);
    }


    List query(Connection conn, String sql, Object... paras) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            List result = new ArrayList();
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                pst.setObject(i + 1, paras[i]);
            }
            rs = pst.executeQuery();
            int colAmount = rs.getMetaData().getColumnCount();
            if (colAmount > 1) {
                while (rs.next()) {
                    Object[] temp = new Object[colAmount];
                    for (int i = 0; i < colAmount; i++) {
                        temp[i] = rs.getObject(i + 1);
                    }
                    result.add(temp);
                }
            } else if (colAmount == 1) {
                while (rs.next()) {
                    result.add(rs.getObject(1));
                }
            }

            return result;
        } finally {
            closeQuietly(rs, pst);
        }
    }

    public <T> List<T> query(String sql, Object... paras) {
        Connection conn = null;
        try {
            conn = sqlConn.getConn();
            return query(conn, sql, paras);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sqlConn.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T> List<T> query(String sql) {        // return  List<object[]> or List<object>
        return query(sql, Util.NULL_PARA_ARRAY);
    }

    public <T> T queryFirst(String sql, Object... paras) {
        List<T> result = query(sql, paras);
        return (result.size() > 0 ? result.get(0) : null);
    }

    public <T> T queryFirst(String sql) {
        // return queryFirst(sql, Util.NULL_PARA_ARRAY);
        List<T> result = query(sql, Util.NULL_PARA_ARRAY);
        return (result.size() > 0 ? result.get(0) : null);
    }

    /**
     * Execute sql query just return one column.
     *
     * @param <T>   the type of the column that in your sql's select statement
     * @param sql   an SQL statement that may contain one or more '?' IN parameter placeholders
     * @param paras the parameters of sql
     * @return List<T>
     */
    public <T> T queryColumn(String sql, Object... paras) {
        List<T> result = query(sql, paras);
        if (result.size() > 0) {
            T temp = result.get(0);
            if (temp instanceof Object[])
                throw new RuntimeException("Only ONE COLUMN can be queried.");
            return temp;
        }
        return null;
    }

    public <T> T queryColumn(String sql) {
        return (T) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public String queryStr(String sql, Object... paras) {
        return (String) queryColumn(sql, paras);
    }

    public String queryStr(String sql) {
        return (String) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Integer queryInt(String sql, Object... paras) {
        return (Integer) queryColumn(sql, paras);
    }

    public Integer queryInt(String sql) {
        return (Integer) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Long queryLong(String sql, Object... paras) {
        return (Long) queryColumn(sql, paras);
    }

    public Long queryLong(String sql) {
        return (Long) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Double queryDouble(String sql, Object... paras) {
        return (Double) queryColumn(sql, paras);
    }

    public Double queryDouble(String sql) {
        return (Double) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Float queryFloat(String sql, Object... paras) {
        return (Float) queryColumn(sql, paras);
    }

    public Float queryFloat(String sql) {
        return (Float) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public java.math.BigDecimal queryBigDecimal(String sql, Object... paras) {
        return (java.math.BigDecimal) queryColumn(sql, paras);
    }

    public java.math.BigDecimal queryBigDecimal(String sql) {
        return (java.math.BigDecimal) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public byte[] queryBytes(String sql, Object... paras) {
        return (byte[]) queryColumn(sql, paras);
    }

    public byte[] queryBytes(String sql) {
        return (byte[]) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public java.util.Date queryDate(String sql, Object... paras) {
        return (java.util.Date) queryColumn(sql, paras);
    }

    public java.util.Date queryDate(String sql) {
        return (java.util.Date) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Time queryTime(String sql, Object... paras) {
        return (Time) queryColumn(sql, paras);
    }

    public Time queryTime(String sql) {
        return (Time) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Timestamp queryTimestamp(String sql, Object... paras) {
        return (Timestamp) queryColumn(sql, paras);
    }

    public Timestamp queryTimestamp(String sql) {
        return (Timestamp) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Boolean queryBoolean(String sql, Object... paras) {
        return (Boolean) queryColumn(sql, paras);
    }

    public Boolean queryBoolean(String sql) {
        return (Boolean) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }

    public Number queryNumber(String sql, Object... paras) {
        return (Number) queryColumn(sql, paras);
    }

    public Number queryNumber(String sql) {
        return (Number) queryColumn(sql, Util.NULL_PARA_ARRAY);
    }
    // 26 queryXxx method under -----------------------------------------------


    private static void closeQuietly(ResultSet rs, Statement st) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Record> query4Records(String sql) {
        return this.query4Records(sql, Util.NULL_PARA_ARRAY);
    }

    public List<Record> query4Records(String sql, Object... paras) {
        Connection conn = null;
        try {
            conn = sqlConn.getConn();
            PreparedStatement pst = conn.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                pst.setObject(i + 1, paras[i]);
            }
            ResultSet rs = pst.executeQuery();
            List<Record> list = build(rs);
            closeQuietly(rs, pst);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sqlConn.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Record query4Record(String sql) {
        return this.query4Record(sql, Util.NULL_PARA_ARRAY);
    }

    public Record query4Record(String sql, Object... paras) {
        List<Record> result = query4Records(sql, paras);
        return result.size() > 0 ? result.get(0) : null;
    }

    private static List<Record> build(ResultSet rs) throws SQLException {
        List<Record> result = new ArrayList<Record>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] labelNames = new String[columnCount + 1];
        int[] types = new int[columnCount + 1];
        buildLabelNamesAndTypes(rsmd, labelNames, types);
        while (rs.next()) {
            Record record = new Record();
            for (int i = 1; i <= columnCount; i++) {
                Object value;
                if (types[i] < Types.BLOB)
                    value = rs.getObject(i);
                else if (types[i] == Types.CLOB)
                    value = ConvertUtil.clobToString(rs.getClob(i));
                else if (types[i] == Types.NCLOB)
                    value = ConvertUtil.clobToString(rs.getNClob(i));
                else if (types[i] == Types.BLOB)
                    value = ConvertUtil.blobToBytes(rs.getBlob(i));
                else
                    value = rs.getObject(i);

                record.put(labelNames[i], value);
            }
            result.add(record);
        }
        return result;
    }

    private static void buildLabelNamesAndTypes(ResultSetMetaData rsmd, String[] labelNames, int[] types) throws SQLException {
        for (int i = 1; i < labelNames.length; i++) {
            labelNames[i] = rsmd.getColumnLabel(i);
            types[i] = rsmd.getColumnType(i);
        }
    }
}
