package com.hisense.hitv.mss.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.hisense.hitv.mss.MailVO;
import com.hisense.hitv.mss.dao.IExceptionretryMailDao;

public class ExceptionretryMailDaoImpl extends SqlMapClientDaoSupport implements IExceptionretryMailDao {

    @Override
    public int insert(MailVO mailVO) throws SQLException {
        return (Integer) getSqlMapClient().insert("sqlite.insert", mailVO);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MailVO> getAllExceptionRetryMsg() throws SQLException {
        return getSqlMapClient().queryForList("sqlite.getAllExceptionRetryMsg");
    }

    @Override
    public void delete(long id) throws SQLException {
        getSqlMapClient().delete("sqlite.delete", id);
    }

}
