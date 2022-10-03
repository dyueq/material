package com.material;

import com.material.bean.*;
import com.material.bean.infomation.RecordForm;
import com.material.service.MaterialService;
import com.material.service.MenuService;
import com.material.service.RecordService;
import com.material.service.UserInfoService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestUser {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MenuService menuService;
    @Autowired
    private RecordService recordService;
    @Test
    public void test1(){
        //查询全部用户
        //userInfoService.getAllUser();
        //分页查询全部用户
        userInfoService.getUserPage(1,5);
//        materialService.getAllMaterial(1,5);
    }
    @Test
    public void test2(){
        //注册
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("阿斯顿");
        userInfo.setUserIdcard("33344455");
        userInfo.setUserEmail("123@14444.com");
        userInfo.setUserPhone("888");
        userInfo.setUserPassword("123");
        userInfoService.register(userInfo);
    }

    @Test
    public void test3(){
        //获取菜单
        List<Menu> menuList = menuService.getMenu(1);
        System.out.println(menuList);
    }
    @Test
    public void test4(){
        //添加物资
        List<Material> list = new ArrayList<>();
        Material material1 = new Material();
        material1.setGoods("口罩");
        material1.setTotal(1);
        material1.setUnit("包");
        material1.setType("衣物");
        list.add(material1);
        Material material2 = new Material();
        material2.setGoods("面包");
        material2.setTotal(1);
        material2.setUnit("箱");
        material2.setType("食品");
        list.add(material2);
        materialService.addMaterial(list);
    }
    @Test
    public void test5(){
        //分页查询该用户捐赠记录
        recordService.getUserRecords(1,5,"arr");
    }
    @Test
    public void test6(){
        //按条件分页查询该用户捐赠记录
        RecordForm recordForm = new RecordForm();
        recordForm.setGoods("火腿肠");
        recordService.getUSerRecordsBy(1,5,recordForm,"arr");
    }
    @Test
    public void test7(){
        //查询全部用户捐赠记录
        recordService.getUserRecords(1,5,"arr");
    }
    @Test
    public void test8(){
        recordService.getUncheckRecord(1,5);
    }
    @Test
    public void test9(){
        materialService.getName();
    }

}
