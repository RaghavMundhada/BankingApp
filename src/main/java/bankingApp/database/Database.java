package bankingApp.database;

import bankingApp.utils.Util;
import org.apache.log4j.Logger;

import java.sql.*;

public class Database {
    private static Logger logger = Logger.getLogger(Database.class.getName());
    protected Connection connection = null;
    protected boolean DB_AUTOCOMMIT = false;
    protected String DB_DRIVER = null;
    protected String DB_URL = null;
    protected String DB_USER = null;
    protected String DB_PASSWORD = null;

    protected Database() {
    }

    public Database(String driver, String url, String user, String password, boolean autoCommit) throws Exception {
        this.DB_DRIVER = driver;
        this.DB_URL = url;
        this.DB_USER = user;
        this.DB_PASSWORD = password;
        this.DB_AUTOCOMMIT = autoCommit;
        logger.debug("Database connecting string :: " + this.DB_URL);
        this.getConnection();
    }

    protected void getConnection() throws Exception {
        logger.debug("Inside getConnection");

        try {
            if (Util.parseData(this.DB_DRIVER) == null) {
                logger.error("DB_DRIVER not available");
                throw new Exception("DB_DRIVER not available");
            } else if (Util.parseData(this.DB_URL) == null) {
                logger.error("DB_URL not available");
                throw new Exception("DB_URL not available");
            } else {
                Class.forName(this.DB_DRIVER).newInstance();
                if (this.DB_USER == null && this.DB_PASSWORD == null) {
                    this.connection = DriverManager.getConnection(this.DB_URL);
                } else {
                    if (this.DB_USER == null) {
                        this.DB_USER = "";
                    }

                    if (this.DB_PASSWORD == null) {
                        this.DB_PASSWORD = "";
                    }

                    this.connection = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
                }

                this.connection.setAutoCommit(this.DB_AUTOCOMMIT);
                logger.debug("Setting auto commit to :: " + this.DB_AUTOCOMMIT);
            }
        } catch (SQLException var2) {
            logger.fatal("Database connection error", var2);
            throw new Exception("There was an error while connection to the database. Please retry. ", var2);
        } catch (Exception var3) {
            logger.fatal("Database connection error", var3);
            throw new Exception(var3);
        }
    }

    public ResultSet executeQuery(String query) throws Exception {
        logger.debug("Inside executeQuery");

        try {
            Statement statement = this.connection.createStatement();
            logger.info("SQL Select Query : " + query);
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException var4) {
            logger.error("Database access error", var4);
            throw new Exception(var4);
        }
    }

    public ResultSet executeQuery(StringBuffer query) throws Exception {
        return this.executeQuery(query.toString());
    }

    public int executeUpdate(String query) throws Exception {
        logger.info("Inside executeUpdate");

        try {
            logger.info("SQL Non-Select Query : " + query);
            Statement statement = this.connection.createStatement();
            int result = statement.executeUpdate(query);
            return result;
        } catch (SQLException var4) {
            logger.error("Database access error", var4);
            throw new Exception(var4);
        } catch (Exception var5) {
            logger.error("Database access error", var5);
            throw new Exception(var5);
        }
    }

    public CallableStatement getCallableStatement(String query) throws Exception {
        logger.debug("Inside getCallableStatement");

        try {
            CallableStatement callableStatement = this.connection.prepareCall(query);
            return callableStatement;
        } catch (SQLException var4) {
            logger.error("Database access error", var4);
            throw new Exception(var4);
        }
    }

    public PreparedStatement getPreparedStatement(String query) throws Exception {
        logger.debug("Inside getTermVectorPreparedStatement");

        try {
            logger.debug("PreparedStatemnt :: " + query);
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            return preparedStatement;
        } catch (SQLException var4) {
            logger.error("Database access error", var4);
            throw new Exception(var4);
        }
    }

    public PreparedStatement getPreparedStatement(StringBuffer query) throws Exception {
        return this.getPreparedStatement(query.toString());
    }

    public CallableStatement getCallableStatement(StringBuffer query) throws Exception {
        return this.getCallableStatement(query.toString());
    }

    public void commit() throws Exception {
        logger.debug("Inside commit");

        try {
            this.connection.commit();
        } catch (SQLException var2) {
            logger.error("Database access error", var2);
            throw new Exception(var2);
        }
    }

    public void rollback() throws Exception {
        logger.debug("Inside rollback");

        try {
            this.connection.rollback();
        } catch (SQLException var2) {
            logger.error("Database access error", var2);
            throw new Exception(var2);
        }
    }

    public void close() throws Exception {
        logger.debug("Inside close with connection = " + this.connection);

        try {
            if (this.connection != null) {
                this.connection.close();
                this.connection = null;
            }

        } catch (SQLException var2) {
            logger.error("Database access error", var2);
            throw new Exception(var2);
        }
    }

    public boolean getAutoCommit() throws SQLException {
        return this.connection.getAutoCommit();
    }
}
