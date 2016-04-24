package com.pla.dsoperation;

import com.pla.transaction.IAtom;
import com.pla.utils.SpringUtil;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

class DsSqlOperation {
    private String dsId;

    public DsSqlOperation() {
        this.dsId = "dataSource";
    }

    public DsSqlOperation(String dsId) {
        this.dsId = dsId;
    }

    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public DataSource getDataSource() {
        return SpringUtil.getBean(dsId);
    }

    public Connection getConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn != null)
            return conn;
        else
            return getDataSource().getConnection();
    }

    /**
     * Support transaction with Transaction interceptor
     */
    public final void setThreadLocalConnection(Connection connection) {
        threadLocal.set(connection);
    }

    public final void removeThreadLocalConnection() {
        threadLocal.remove();
    }

    public void close(Connection conn) throws SQLException {
        if (threadLocal.get() == null)
            if (conn != null)
                conn.close();
    }

    public static DsSqlOperation create() {
        return new DsSqlOperation();
    }

    public static DsSqlOperation create(String dsId) {
        return new DsSqlOperation(dsId);
    }

//    public DSSQLExecutor executor() {
//        return new DSSQLExecutor(this);
//    }
//
//    public DSSQLExecutor executor(String dsId) {
//        return new DSSQLExecutor(dsId, this);
//    }
//
//    public DSSQLFinder finder() {
//        return new DSSQLFinder(this);
//    }
//
//    public DSSQLFinder finder(String dsId) {
//        return new DSSQLFinder(dsId, this);
//    }

    /**
     * Execute transaction with default transaction level.
     *
     * @see #tx(int, IAtom)
     */
    public void tx(IAtom atom) {
        tx(Connection.TRANSACTION_READ_COMMITTED, atom);
    }

    /**
     * Execute transaction.
     *
     * @param transactionLevel the transaction level
     * @param atom             the atom operation
     */
    public void tx(int transactionLevel, IAtom atom) {
        Connection conn = null;

        Boolean autoCommit = null;
        try {
            conn = getDataSource().getConnection();
            setThreadLocalConnection(conn);
            autoCommit = conn.getAutoCommit();
            conn.setTransactionIsolation(transactionLevel);
            conn.setAutoCommit(false);
            atom.run();
            conn.commit();
        } catch (Exception e) {
            if (conn != null) try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    if (autoCommit != null)
                        conn.setAutoCommit(autoCommit);
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                removeThreadLocalConnection();    // prevent memory leak
            }
        }
    }
}
