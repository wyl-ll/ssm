package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.ISysLogDao;
import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    public void save(SysLog sysLog) {

        sysLogDao.save(sysLog);
    }
}
