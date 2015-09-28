package com.hisense.hitv.mss.dao;

import java.sql.SQLException;
import java.util.List;

import com.hisense.hitv.mss.MailVO;

public interface IExceptionretryMailDao {

    /**
     * 插入消息数据到ExceptionRetryMsg表
     * @param m
     * @return
     * @throws SQLException
     */
    public int insert(MailVO mailVO) throws SQLException;

    public List<MailVO> getAllExceptionRetryMsg() throws SQLException;

    public void delete(long i) throws SQLException;
}
