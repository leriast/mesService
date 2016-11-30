/*
package com.common.dao.entity.usertype;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.hsqldb.types.Types;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

*/
/**
 * Created by root on 11/28/16.
 *//*

public class MyJsonType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    public Class<MyJson> returnedClass() {
        return MyJson.class;
    }

    @Override
    public boolean equals(Object x, Object y) {
        if (null == x || null == y) {
            return false;
        }
        return x == y;
    }

    @Override
    public int hashCode(Object x) {
        if (x != null) {
            return x.hashCode();
        }

        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        String a = "";
        try {
            a = resultSet.getString(strings[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            obj = (JSONObject) parser.parse(a);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        MyJson json = new MyJson(obj);
        if (json.getProp() == null) {
            System.out.println("nullSafeGet");
            return null;
        }
        else {
            return json;
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        MyJson json = (MyJson) o;
        if (json.getProp() == null) {
            System.out.println("nullSafeSet_JSON");
        } else {
            try {
                preparedStatement.setObject(i, json.getProp(), Types.OTHER);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(preparedStatement.getParameterMetaData());
            }
        }
    }





    @Override
    public Object deepCopy(final Object value) {
        MyJson myJson = (MyJson) value;

        if (myJson.getProp() == null) {
            System.out.println("hernia");
            return null;
        } else {
            MyJson copy = new MyJson(myJson.getProp());
            return copy;
        }


    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable cached, Object o) {
        return cached;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) {
        return o;
    }
}
*/
