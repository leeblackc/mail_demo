package com.lhc.mail.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lhc.mail.entity.JobEntity;
import com.lhc.mail.mapper.JobEntityMapper;
import com.lhc.mail.service.IJobEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhc
 * @since 2019-07-20
 */
@Service
public class JobEntityServiceImpl extends ServiceImpl<JobEntityMapper, JobEntity> implements IJobEntityService {

}
