package demo.dao;

import demo.dao.implement.OnSiteInfoDaoImpl;

public class DaoFactory {

    public OnSiteInfoDao getOnSiteInfoDao() {
        return new OnSiteInfoDaoImpl();
    }

}
