package dao;

import bean.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class TestDAO extends DAO {
    public int search(String keyword) throws Exception {
        // List<Test> list = new ArrayList<>();
        Test t = new Test();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from CookingName where name like ?");
        st.setString(1, "%" + keyword + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            t.setId(rs.getInt("id"));
            t.setName(rs.getString("name"));
            // list.add(t);
        }

        st.close();
        con.close();

        return t.getId();
    }

    public List<Integer> search2(int id) throws Exception {
        List<Integer> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from CenterTable where CookingNameID like ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getInt("materialID"));
        }
        st.close();
        con.close();

        return list;
    }

    public List<String> search3(List<Integer> MaterialList) throws Exception {
        List<String> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from material where id like ?");

        for (Integer id : MaterialList) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        }
        st.close();
        con.close();

        return list;
    }

    public List<String> search4(String keyword) throws Exception {
        int CookingNameID = 0;
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;
        List<Integer> MaterialIDList = new ArrayList<>();
        List<String> MaterialNameList = new ArrayList<>();

        st = con.prepareStatement("select * from CookingName where name like ?");
        st.setString(1, keyword);
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

        st = con.prepareStatement("select * from material where id like ?");

        for (Integer i : MaterialListID) {
            st.setInt(1, i);
            rs = st.executeQuery();
            while (rs.next()) {
                MaterialNameList.add(rs.getString("name"));
            }
        }
        st.close();
        con.close();

        return MaterialNameList;
    }

    // public int insert(Product product) throws Exception {
    // Connection con = getConnection();

    // PreparedStatement st = con.prepareStatement("insert into product values(null,
    // ?, ?)");
    // st.setString(1, product.getName());
    // st.setInt(2, product.getPrice());
    // int line = st.executeUpdate();

    // st.close();
    // con.close();
    // return line;
    // }
}
