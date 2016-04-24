package com.pla.dsoperation;

import com.pla.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DsSqlExecutor {
    private DsSqlOperation sqlConn;

    private String dsId;

    public DsSqlExecutor() {
        this.dsId = "dataSource";
        sqlConn = new DsSqlOperation(this.dsId);
    }

    public DsSqlExecutor(String dataSourceId) {
        this.dsId = dataSourceId;
        sqlConn = new DsSqlOperation(this.dsId);
    }

    public DsSqlExecutor(DsSqlOperation sqlConn) {
        this.dsId = "dataSource";
        this.sqlConn = sqlConn;
    }

    public DsSqlExecutor(String dataSourceId, DsSqlOperation sqlConn) {
        this.dsId = dataSourceId;
        this.sqlConn = sqlConn;
    }

    public static DsSqlExecutor create() {
        return new DsSqlExecutor();
    }

    public static DsSqlExecutor create(String dataSourceId) {
        return new DsSqlExecutor(dataSourceId);
    }

    void execute(Connection conn, String sql, Object... paras) throws SQLException {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                pst.setObject(i + 1, paras[i]);
            }
            pst.execute();
        } finally {
            closeQuietly(pst);
        }

    }

    int executeUpdate(Connection conn, String sql, Object... paras) throws SQLException {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                pst.setObject(i + 1, paras[i]);
            }
            return pst.executeUpdate();
        } finally {
            closeQuietly(pst);
        }
    }

    private void closeQuietly(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void execute(String sql, Object... paras) {
        Connection conn = null;
        try {
            conn = sqlConn.getConn();
            execute(conn, sql, paras);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sqlConn.close(conn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void execute(String sql) {
        execute(sql, Util.NULL_PARA_ARRAY);
    }

    public int executeUpdate(String sql, Object... paras) {
        Connection conn = null;
        try {
            conn = sqlConn.getConn();
            return executeUpdate(conn, sql, paras);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sqlConn.close(conn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void executeUpdate(String sql) {
        executeUpdate(sql, Util.NULL_PARA_ARRAY);
    }


}
