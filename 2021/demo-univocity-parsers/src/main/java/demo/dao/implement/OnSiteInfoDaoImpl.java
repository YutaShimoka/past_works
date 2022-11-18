package demo.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import demo.dao.OnSiteInfoDao;
import demo.entity.OnSiteInfo;
import demo.util.DbManager;

public class OnSiteInfoDaoImpl implements OnSiteInfoDao {

    @Override
    public void init() {

        Connection conn = DbManager.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("TRUNCATE on_site_info");
            ps.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DbManager.close(conn);
        }
    }

    @Override
    public void create(OnSiteInfo entity) {

        Connection conn = DbManager.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO on_site_info (employee_id, last_name, first_name, start_date, team_name, rookie_flag) values (?, ?, ?, ?, ?, ?)");
            ps.setString(1, entity.getEmployeeId());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getFirstName());
            ps.setString(4, entity.getStartDate());
            ps.setString(5, entity.getTeamName());
            ps.setObject(6, entity.getRookieFlag());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DbManager.close(conn);
        }
    }

}
