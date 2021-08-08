package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ProcessDAO extends DAO {
    public void search(String cooking_name, List<String> processes, List<String> times) throws Exception {
        int CookingNameID = 0;
        List<Integer> ProcessID = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;

        st = con.prepareStatement("select * from CookingName where name like ?");
        st.setString(1, cooking_name);
        rs = st.executeQuery();
        while (rs.next()) {
            CookingNameID = rs.getInt("id");
        }

        st = con.prepareStatement("select * from CookingName_Process where CookingNameID like ?");
        st.setInt(1, CookingNameID);
        rs = st.executeQuery();
        while (rs.next()) {
            ProcessID.add(rs.getInt("ProcessID"));
        }

        st = con.prepareStatement("select * from Process where id like ?");
        for (Integer i : ProcessID) {
            st.setInt(1, i);
            rs = st.executeQuery();
            while (rs.next()) {
                processes.add(rs.getString("name"));
                times.add(rs.getString("time"));
            }
        }

        st.close();
        con.close();
    }

    public int insert(String name, List<String> processes, List<String> times) throws Exception {
        int line = 0;
        int CookingNameID = 0;
        int ProcessNum = 0;
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;
        Object test = "";

        st = con.prepareStatement("select * from CookingName where name like ?");
        st.setString(1, name);
        rs = st.executeQuery();

        while (rs.next()) {
            CookingNameID = rs.getInt("id");
        }

        // st = con.prepareStatement("select count(*) as ProcessNum from Process");
        // rs = st.executeQuery();
        // while(rs.next()) {
        // ProcessNum = rs.getInt("ProcessNum");
        // }

        st = con.prepareStatement("insert into Process values(null, ?, ?)");
        for (int i = 0; i < processes.size(); i++) {
            st.setString(1, processes.get(i));
            st.setString(2, times.get(i));
            line += st.executeUpdate();
        }

        st = con.prepareStatement("select * from Process order by id desc limit 1");
        rs = st.executeQuery();
        while (rs.next()) {
            ProcessNum = rs.getInt("id");
        }

        st = con.prepareStatement("insert into CookingName_Process values(null, ?, ?)");
        for (int i = 0, j = ProcessNum - processes.size() + 1; i < processes.size(); i++, j++) {
            st.setInt(1, CookingNameID);
            st.setInt(2, j);
            line += st.executeUpdate();
        }

        st.close();
        con.close();

        return line;
    }
}
