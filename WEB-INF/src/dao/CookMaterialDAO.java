package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CookMaterialDAO extends DAO {
    public int insert(Connection con, int cookingNameLastId, int materialLastId, List<String> materialNames)
            throws Exception {
        int updateCount = 0;
        PreparedStatement st = con.prepareStatement("insert into CookingName_Material values(null, ?, ?)");
        for (int i = 0, j = materialLastId - materialNames.size() + 1; i < materialNames.size(); i++, j++) {
            st.setInt(1, cookingNameLastId);
            st.setInt(2, j);
            updateCount += st.executeUpdate();
        }
        st.close();
        return updateCount;
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

    // 料理名とその材料/量をデータベースに登録する
    public boolean CookMaterialInsert(String cookName, List<String> materialNames, List<String> materialAmounts,
            int expectedUpdateNums) throws Exception {

        boolean updateSuccess;
        int updatedCheckCount = 0;
        int cookingNameLastId = 0;
        int materialLastId = 0;
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;
        CookDAO c_dao = new CookDAO();
        MaterialDAO m_dao = new MaterialDAO();
        CookMaterialDAO cm_dao = new CookMaterialDAO();

        con.setAutoCommit(false);

        // 料理名をCookingNameテーブルに保存する。ここでは1が返される想定
        updatedCheckCount += c_dao.insert(con, cookName);
        // CookingNameテーブルの最後のidを取得する
        cookingNameLastId = c_dao.selectLastId(con);
        // 材料名と量をMaterialテーブルに保存する。更新された行が返される想定。
        updatedCheckCount += m_dao.insert(con, materialNames, materialAmounts);
        // Materialテーブルの最後のidを取得する
        materialLastId = m_dao.selectLastId(con);
        // CookingName_Materialテーブルに登録する
        updatedCheckCount += cm_dao.insert(con, cookingNameLastId, materialLastId, materialNames);

        if (updatedCheckCount == expectedUpdateNums) {
            con.commit();
            updateSuccess = true;
        } else {
            con.rollback();
            updateSuccess = false;
        }

        con.close();

        return updateSuccess;
    }
}
