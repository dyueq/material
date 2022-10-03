package com.material.controller;

import com.material.bean.*;
import com.material.bean.infomation.*;
import com.material.config.Commons;
import com.material.config.Result;
import com.material.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 控制层
 * author dyq
 */
@RestController
public class ActionController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private AnnoService annoService;


    /**
     * 注册用户
     * @param userInfo 用户信息
     * @return
     */
    @PostMapping("/user")
    public Result addUser(@RequestBody UserInfo userInfo){
        return userInfoService.register(userInfo);
    }

    /**
     * 登录
     * @param loginUser 登录时用户输入的信息
     * @return
     */

    @PostMapping("/user/login")
    public Result login(@RequestBody LoginUser loginUser , HttpServletRequest request) {
        UserInfo userInfo = userInfoService.login(loginUser);
        if (userInfo != null) {
            if(userInfo.getUserDisable() == 0){
                if (userInfo.getUserPassword().equals(DigestUtils.md5DigestAsHex(loginUser.getPassword().getBytes(StandardCharsets.UTF_8))) ) {
                    HttpSession session = request.getSession();
                    session.setAttribute(Commons.SESSION_USER_KEY, userInfo);
                    session.setAttribute("userName",userInfo.getUserName());
                    return new Result(true, "登陆成功");
                }
                return new Result(false, "密码不正确");
            }
            return new Result(false,"该账号已被冻结");
        }
        return new Result(false, "用户不存在");
    }

    /**
     * 得到session中的用户信息
     * @param request request
     * @return
     */
    @GetMapping("/sessionUser")
    public Result getSessionUser(HttpServletRequest request){
        UserInfo user = (UserInfo) request.getSession().getAttribute(Commons.SESSION_USER_KEY);
        return new Result(true,user);
    }

    /**
     * 分页查找全部物资明细
     * @param current 当前页码
     * @param size 每页总条数
     * @return
     */
    @GetMapping ("/user/material/{current}/{size}")
    public Result getAllMaterial(@PathVariable("current") Integer current,@PathVariable("size") Integer size){
        return new Result(true,materialService.getAllMaterial(current,size));
    }

    /**
     * 按条件分页查询物资
     * @param current 当前页
     * @param size 每页条数
     * @param materialForm 暂存查询条件
     * @return
     */
    @PostMapping("/user/materialBy/{current}/{size}")
    public Result getMaterialBy(@PathVariable("current") Integer current,@PathVariable("size") Integer size,@RequestBody MaterialForm materialForm){
        return new Result(true,materialService.getMaterialBy(current,size,materialForm));
    }

    /**
     * 获取菜单
     * @param request request
     * @return
     */
    @GetMapping("/getMenu")
    public List<Menu> getMenu(HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Commons.SESSION_USER_KEY);
        return menuService.getMenu(userInfo.getUserId());
    }

    /**
     * 1.用户提交捐赠订单后，会生成未审核记录，
     * 2.在管理员的订单审核页面展示出未审核记录
     * @param materialList 用户传入的捐赠物资信息的集合
     * @param request request
     * @return
     */
    @PostMapping("toCheck")
    public Result toCheck(@RequestBody List<Material> materialList, HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(Commons.SESSION_USER_KEY);
        if (userInfo != null){
            recordService.toCheck(materialList,userInfo);
            return new Result(true,"添加成功，请等待管理员审核");
        }
        return new Result(false,"没有用户信息");
    }

    /**
     * 分页查询该用户捐赠记录
     * @param current 当前页
     * @param size 每页条数
     * @param request request
     * @return
     */
    @GetMapping("getUserRecord/{current}/{size}")
    public Result getUserRecords(@PathVariable Integer current,@PathVariable Integer size, HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(Commons.SESSION_USER_KEY);
        return new Result(true,recordService.getUserRecords(current,size,userInfo.getUserName()));
    }

    /**
     * 按条件分页查询该用户捐赠记录
     * @param current 当前页
     * @param size 每页条数
     * @param recordForm 暂存的查询条件
     * @param request request
     * @return
     */
    @PostMapping("/getUserRecordsBy/{current}/{size}")
    public Result getUserRecordsBy(@PathVariable Integer current, @PathVariable Integer size, @RequestBody RecordForm recordForm, HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(Commons.SESSION_USER_KEY);
        return new Result(true,recordService.getUSerRecordsBy(current,size,recordForm,userInfo.getUserName()));
    }

    /**
     * 分页查询全部捐赠记录
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @GetMapping("/getAllRecord/{current}/{size}")
     public Result getAllRecord(@PathVariable Integer current, @PathVariable Integer size){
        return new Result(true,recordService.getAllRecord(current,size));
     }

    /**
     * 按条件分页查询全部用户捐赠记录
     * @param current 当前页
     * @param size 每页条数
     * @param allRecordForm 暂存查询条件
     * @return
     */
    @PostMapping("/getAllRecordBy/{current}/{size}")
     public Result getAllRecordBy(@PathVariable Integer current, @PathVariable Integer size,@RequestBody AllRecordForm allRecordForm){
        return new Result(true,recordService.getAllRecordBy(current,size,allRecordForm));
     }

    /**
     * 分页查找全部用户
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @GetMapping("/user/{current}/{size}")
    public Result getAllUser(@PathVariable("current") Integer current, @PathVariable("size") Integer size){
        return new Result(true,userInfoService.getUserPage(current, size));
    }

    /**
     * 按条件分页查找全部用户
     * @param current 当前页
     * @param size 每页条数
     * @param userInfoForm 暂存查询条件
     * @return
     */
    @PostMapping("/userBy/{current}/{size}")
    public Result getUserBy(@PathVariable("current") Integer current, @PathVariable("size") Integer size, @RequestBody UserInfoForm userInfoForm){
        return new Result(true,userInfoService.getUserBy(current,size,userInfoForm));
    }

    /**
     * 修改用户禁用属性(冻结/解冻)
     * @param userInfo 修改的用户实体
     * @return
     */
    @PutMapping("/user/updateInfo/")
    public Result updateDisable(@RequestBody UserInfo userInfo){
        userInfoService.updateById(userInfo);
        return new Result(true);
    }

    /**
     * 分页查询全部待审核订单
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @GetMapping("/admin/order/{current}/{size}")
    public Result getUncheckedRecord(@PathVariable("current") Integer current, @PathVariable("size") Integer size){
        return new Result(true,recordService.getUncheckRecord(current,size));
    }

    /**
     * 按条件分页查询全部待审核订单
     * @param current 当前页
     * @param size 每页条数
     * @param allRecordForm 暂存查询信息
     * @return
     */
    @PostMapping("/getAllowRecordBy/{current}/{size}")
    public Result getAllowRecordBy(@PathVariable Integer current, @PathVariable Integer size,@RequestBody AllRecordForm allRecordForm){
        return new Result(true,recordService.getAllowRecordBy(current,size,allRecordForm));
    }

    /**
     * 修改订单审核状态（未审核-->已通过/未通过）
     * @param record 待审核的订单
     * @return
     */
    @PutMapping("/admin/allowOrder")
    public Result updateAllowOrder(@RequestBody Record record){
        recordService.updateAllowOrder(record);
        return new Result(true);
    }

    /**
     * 批量审核捐赠记录
     * @param recordList 单页的捐赠记录
     * @return
     */
    @PutMapping("/admin/allowAll")
    public Result allowAllOrder(@RequestBody List<Record> recordList){
        recordService.allAllOrder(recordList);
        return new Result(true,"添加成功");
    }

    /**
     * 分页查询全部发放记录
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @GetMapping("/allOutRecord/{current}/{size}")
    public Result getAllOutRecord(@PathVariable Integer current, @PathVariable Integer size){
        return new Result(true, recordService.getAllOutRecord(current,size));
    }

    /**
     * 按条件分页查询全部发放记录
     * @param current 当前页
     * @param size 每页条数
     * @param record 暂存查询信息
     * @return
     */
    @PostMapping("/outRecordBy/{current}/{size}")
    public Result getAllOutRecordBy(@PathVariable Integer current, @PathVariable Integer size,@RequestBody Record record){
        return new Result(true,recordService.OutRecordBy(current,size,record));
    }

    /**
     * 分页查询该管理员的全部发放记录
     * @param current 当前页
     * @param size 每页条数
     * @param request request
     * @return
     */
    @GetMapping("/getOutRecord/{current}/{size}")
    public Result outRecord(@PathVariable Integer current, @PathVariable Integer size,HttpServletRequest request){
        UserInfo admin = (UserInfo) request.getSession().getAttribute(Commons.SESSION_USER_KEY);
        return new Result(true, recordService.getOutRecord(current,size,admin));
    }

    /**
     * 添加该管理员的发放记录并修改仓库物资
     * @param record 管理员输入的物资信息
     * @param request request
     * @return
     */
    @PostMapping("/addOutRecord/")
    public Result addOutRecord(@RequestBody Record record, HttpServletRequest request){
        UserInfo admin = (UserInfo) request.getSession().getAttribute(Commons.SESSION_USER_KEY);
        return recordService.addOutRecord(admin,record);
    }

    /**
     * 查询全部物资的名称和数量
     * @return
     */
    @GetMapping("/nameAndTotal")
    public Result getMaterialNameAndTotal(){
        return new Result(true,materialService.getName());
    }

    /**
     * 获取pie的数据
     * @return
     */
    @GetMapping("/getPie")
    public Result getPie(){
        return new Result(true,materialService.getPie());
    }

    /**
     * 获取全部新闻公告
     * @return
     */
    @GetMapping("/getAnno")
    public Result getNewsAndAnno(){
        return new Result(true,annoService.getAnno());
    }

    /**
     * 添加新闻公告
     * @param anno 新闻公告实体
     * @return
     */
    @PostMapping("/addAnno")
    public Result addAnno(@RequestBody Anno anno){
        annoService.save(anno);
        return new Result(true);
    }

    /**
     * 根据标题查找公告
     * @return
     */
    @GetMapping("/AnnoByTitle/{annoTitle}")
    public Result searchAnnoByTitle(@PathVariable String annoTitle){
        return new Result(true,annoService.searchAnnoByTitle(annoTitle));
    }

    /**
     * 根据标题查找新闻
     * @return
     */
    @GetMapping("/newsByTitle/{newsTitle}")
    public Result searchNewsByTitle(@PathVariable String newsTitle){
        return new Result(true,annoService.searchNewsByTitle(newsTitle));
    }

    /**
     * 根据id删除新闻公告
     * @param id 公告id
     * @return
     */
    @DeleteMapping("/deleteAnno/{id}")
    public Result deleteAnno(@PathVariable Integer id){
        annoService.removeById(id);
        return new Result(true);
    }

}
