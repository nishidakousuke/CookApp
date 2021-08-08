package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class MaterialDAO extends DAO {

    public int selectLastId(Connection con) throws Exception {
        int id = 0;
        PreparedStatement st = con.prepareStatement("select * from Material order by id desc limit 1");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        st.close();
        return id;
    }

    public Map<String, String> search(String keyword) throws Exception {
        int CookingNameID = 0;
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;
        List<Integer> MaterialIDList = new ArrayList<>();
        Map<String, String> MaterialMap = new HashMap<String, String>();

        st = con.prepareStatement("select * from CookingName where name like ?");
        st.setString(1, "%" + keyword + "%");
        rs = st.executeQuery();

        while (rs.next()) {
            CookingNameID = rs.getInt("id");
        }

        st = con.prepareStatement("select * from CenterTable where CookingNameID like ?");
        st.setInt(1, CookingNameID);
        rs = st.executeQuery();

        while (rs.next()) {
            MaterialIDList.add(rs.getInt("materialID"));
        }

        st = con.prepareStatement("select * from Material where id like ?");

        for (Integer i : MaterialIDList) {
            st.setInt(1, i);
            rs = st.executeQuery();
            while (rs.next()) {
                MaterialMap.put(rs.getString("name"), rs.getString("amount"));
            }
        }
        st.close();
        con.close();

        return MaterialMap;
    }

    public int insert(Connection con, List<String> MaterialNames, List<String> MaterialAmounts) throws Exception {
        int updateCount = 0;
        PreparedStatement st = con.prepareStatement("insert into Material values(null, ?, ?)");
        for (int i = 0; i < MaterialNames.size(); i++) {
            st.setString(1, MaterialNames.get(i));
            st.setString(2, MaterialAmounts.get(i));
            updateCount += st.executeUpdate();
        }
        st.close();
        return updateCount;
    }
}
