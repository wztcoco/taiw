package com.colacoco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.entity.TwUser;
import com.colacoco.mapper.TwUserMapper;
import com.colacoco.service.ITwUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author colacoco
 * @since 2020-07-09
 */
@Service
public class TwUserServiceImpl extends ServiceImpl<TwUserMapper, TwUser> implements ITwUserService {

}
