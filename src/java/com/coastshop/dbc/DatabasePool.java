/*
 * 通过数据库连接池访问数据库
 */
package com.coastshop.dbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;
import javax.naming.*;

/**
 *
 * @author Coast
 */
public class DatabasePool {
    private static final String DSNAME = "java:comp/env/jdbc/dbcompany";
    private Connection conn;

    public DatabasePool() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup(DSNAME);
            this.conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
