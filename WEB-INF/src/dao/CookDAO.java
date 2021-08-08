package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;

public class CookDAO extends DAO {
    // この関数ではCookingNameテーブルのみに登録する
    public int insert(Connection con, String cookName) throws Exception {
        PreparedStatement st = con.prepareStatement("insert into CookingName values(null,?)");
        st.setString(1, cookName);
        int updateCount = st.executeUpdate();
        st.close();
        return updateCount;
    }

    public int selectLastId(Connection con) throws Exception {
        PreparedStatement st = con.prepareStatement("select * from CookingName order by id desc limit 1");
        int id = 0;
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        st.close();
        return id;
    }

    public List<String> search() throws Exception {
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;
        List<String> CookingNames = new ArrayList<>();

        st = con.prepareStatement("select * from CookingName");
        rs = st.executeQuery();

        while (rs.next()) {
            CookingNames.add(rs.getString("name"));
        }

        st.close();
        con.close();

        return CookingNames;
    }

    public int delete(String cook_name) throws Exception {
        int line = 0;
        int CookingNameID = 0;
        List<Integer> MaterialID = new ArrayList<>();
        List<Integer> ProcessID = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;

        st = con.prepareStatement("select * from CookingName where name like ?");
        st.setString(1, cook_name);
        rs = st.executeQuery();
        while (rs.next()) {
            CookingNameID = rs.getInt("id");
        }

        st = con.prepareStatement("select * from CenterTable where CookingNameID like ?");
        st.setInt(1, CookingNameID);
        rs = st.executeQuery();
        while (rs.next()) {
            MaterialID.add(rs.getInt("materialID"));
        }

        st = con.prepareStatement("delete from CenterTable where CookingNameID like ?");
        st.setInt(1, CookingNameID);
        line += st.executeUpdate();

        st = con.prepareStatement("delete from Material where id like ?");
        for (Integer m : MaterialID) {
            st.setInt(1, m);
            line += st.executeUpdate();
        }

        st = con.prepareStatement("select * from CookingName_Process where CookingNameID like ?");
        st.setInt(1, CookingNameID);
        rs = st.executeQuery();
        while (rs.next()) {
            ProcessID.add(rs.getInt("ProcessID"));
        }

        st = con.prepareStatement("delete from CookingName_Process where CookingNameID like ?");
        st.setInt(1, CookingNameID);
        line += st.executeUpdate();

        st = con.prepareStatement("delete from Process where id like ?");
        for (Integer p : ProcessID) {
            st.setInt(1, p);
            line += st.executeUpdate();
        }

        st = con.prepareStatement("delete from CookingName where id like ?");
        st.setInt(1, CookingNameID);
        line += st.executeUpdate();

        return line;
    }
}
