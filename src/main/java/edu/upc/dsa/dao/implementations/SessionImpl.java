package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.Session;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
public class SessionImpl implements Session {

    private final Connection conn;
    private static SessionImpl instance;

    SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public static SessionImpl getInstance(){
        if(instance==null){
            Connection conn = FactorySession.getConnection();
            instance = new SessionImpl(conn);
        }
        return instance;
    }

    @Override
    public void save(Object entity) {
        String insertQuery = QueryHelper.createQueryINSERT(entity);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(insertQuery);

            int i = 0;
            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(++i, ObjectHelper.getter(entity,field));
            }
            pstm.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            this.conn.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Object> findAll(Class theClass) {
        String selectAllQuery = QueryHelper.createQuerySELECTAll(theClass);
        PreparedStatement pstm = null;
        List<Object> result = new LinkedList<Object>();

        try {
            pstm = conn.prepareStatement(selectAllQuery);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
                Object entity = theClass.newInstance();
                for (int i = 1; i<rsmd.getColumnCount() + 1; i++) {
                    ObjectHelper.setter(entity,rsmd.getColumnName(i),rs.getObject(i));
                }
                result.add(entity);
            }
            return result;
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
