package cn.jiiiiiin.module.mngauth.service.impl;

import cn.jiiiiiin.ManagerApp;
import cn.jiiiiiin.module.common.dto.mngauth.AdminDto;
import cn.jiiiiiin.module.common.entity.mngauth.Role;
import cn.jiiiiiin.module.common.enums.common.ChannelEnum;
import cn.jiiiiiin.module.common.mapper.mngauth.RoleMapper;
import cn.jiiiiiin.module.mngauth.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;


/**
 * 测试Service
 * https://mrbird.cc/Spring-Boot%20TESTing.html
 */
// 使用spring runner执行测试用例
@RunWith(SpringRunner.class)
// 声明为spring boot的测试用例
@SpringBootTest(classes = ManagerApp.class)
@Slf4j
public class AdminServiceImplTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // https://waylau.gitbooks.io/spring-security-tutorial/docs/password-encoder.html
    @Test
    public void testBCryptPasswordEncoder() {
        CharSequence rawPassword = "123456";
        String encodePasswd = passwordEncoder.encode(rawPassword);
        boolean isMatch = passwordEncoder.matches(rawPassword, encodePasswd);
        System.out.println("encodePasswd:" + encodePasswd);
        System.out.println(isMatch);
    }

    @Transactional
    @Test
    public void findByUsername() {
//        new Admin().setUsername("TEMP").setPassword("$2a$10$XQi3SDI8aU8VL8PQkkyddOYk62OmDBtLwD9f9EEKf0AZBI0Y7pwPq").setChannel(ChannelEnum.MNG);
//        val admin = adminService.signInByUsername("TEMP", ChannelEnum.MNG);
//        Assert.assertEquals("TEMP", admin.getUsername());
        // 测试EHCache缓存
        val admin2 = adminService.signInByUsername("admin", ChannelEnum.MNG);
//        log.debug("findByUsername res: {}", JSONObject.toJSONString(admin2));
    }


    @Transactional
    @Test
    public void saveRelationRoleRecords() {
        Set<Role> roles = new HashSet<>();
        val adminRole = roleMapper.selectOne(new QueryWrapper<Role>().eq(Role.AUTHORITY_NAME, "ADMIN"));
        val userRole = roleMapper.selectOne(new QueryWrapper<Role>().eq(Role.AUTHORITY_NAME, "DB_ADMIN"));
        roles.add(adminRole);
        roles.add(userRole);
        val admin = adminService.signInByUsername("admin", ChannelEnum.MNG);
        admin.setRoles(roles);
        boolean res = adminService.saveRelationRoleRecords(admin);
        assertTrue(res);
    }

    @Test
    public void saveAdminAndRelationRecords() {
        AdminDto admin = (AdminDto) new AdminDto().setRoleIds(new String[]{"1061277220292595713"}).setUsername("test").setPassword("$2a$10$XQi3SDI8aU8VL8PQkkyddOYk62OmDBtLwD9f9EEKf0AZBI0Y7pwPq").setChannel(ChannelEnum.MNG);
        val res = adminService.saveAdminAndRelationRecords((AdminDto) admin);
        assertTrue(res);
    }

    @Test
    public void updateAdminAndRelationRecords() {
        AdminDto admin = (AdminDto) new AdminDto().setId(1069587774891364354L).selectById();
        admin.setRoleIds(new String[]{"1061277220292595713"})
                .setUsername("User")
                .setPhone("153989999999")
                .setEmail("153989999999@163.com");
        val res = adminService.updateAdminAndRelationRecords((AdminDto) admin);
        assertTrue(res);
    }

    @Transactional
    @Test
    public void delAdminAndRelationRecords() {
        val res = adminService.removeAdminAndRelationRecord(1069587774891364354L);
        assertTrue(res);
    }

}