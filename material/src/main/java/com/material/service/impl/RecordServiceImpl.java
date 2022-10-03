package com.material.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.bean.*;
import com.material.bean.infomation.AllRecordForm;
import com.material.bean.infomation.RecordForm;
import com.material.config.Result;
import com.material.mapper.RecordMapper;
import com.material.service.MaterialService;
import com.material.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private MaterialService materialService;


    /**
     * 把用户的捐赠信息添加成待审核记录 --> 交给管理员
     * @param materialList 用户传入的捐赠物资信息的集合
     * @param userInfo session中的用户信息
     */
    @Transactional
    @Override
    public void toCheck(List<Material> materialList, UserInfo userInfo) {
        for (int i = 0; i < materialList.size(); i++) {
            Record record = new Record();
            record.setRecordName(userInfo.getUserName());
            record.setRecordGoods(materialList.get(i).getGoods());
            record.setRecordTotal(materialList.get(i).getTotal());
            record.setRecordUnit(materialList.get(i).getUnit());
            record.setRecordType(materialList.get(i).getType());
            record.setRecordCheck("待审核");
            Date date = new Date();
            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Timestamp timestamp = Timestamp.valueOf(nowTime);
            record.setRecordDate(timestamp);
            record.setRecordRemark(materialList.get(i).getRemark());

            recordMapper.insert(record);
        }
    }

    /**
     * 分页查询该用户捐赠记录
     * @param current 当前页
     * @param size 每页总条数
     * @param userName 用户名
     * @return
     */
    @Override
    public Page<Record> getUserRecords(Integer current,Integer size,String userName) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_name","record_goods","record_total","record_unit","record_type","record_date","record_check","record_remark")
                        .eq("record_name",userName);
        Page<Record> page = new Page<>(current,size);

        return recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 按条件分页查询用户记录
     * @param current 当前页
     * @param size 每页总条数
     * @param recordForm 暂存查询条件
     * @param userName session中的用户名
     * @return
     */
    @Override
    public Page<Record> getUSerRecordsBy(Integer current, Integer size, RecordForm recordForm, String userName) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_name","record_goods","record_total","record_unit","record_type","record_date","record_check","record_remark")
                .eq("record_name",userName)
                .like(StringUtils.isNotBlank(recordForm.getGoods()),"record_goods",recordForm.getGoods())
                .eq(StringUtils.isNotBlank(recordForm.getType()),"record_type",recordForm.getType())
                .eq(StringUtils.isNotBlank(recordForm.getCheck()),"record_check",recordForm.getCheck());
        Page<Record> page = new Page<>(current, size);
        return recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 分页查询全部捐赠记录
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @Override
    public Page<Record> getAllRecord(Integer current, Integer size) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_name","record_goods","record_total","record_unit","record_type","record_date","record_check","record_remark")
                .eq("record_check","已通过");
        Page<Record> page = new Page<>(current,size);
        return recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 按条件分页查询全部捐赠记录
     * @param current 当前页
     * @param size 每页条数
     * @param allRecordForm 暂存查询条件
     * @return
     */
    @Override
    public Page<Record> getAllRecordBy(Integer current, Integer size, AllRecordForm allRecordForm) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_name","record_goods","record_total","record_unit","record_type","record_date","record_check","record_remark")
                .eq("record_check","已通过")
                .like(StringUtils.isNotBlank(allRecordForm.getUserName()),"record_name",allRecordForm.getUserName())
                .like(StringUtils.isNotBlank(allRecordForm.getGoods()),"record_goods",allRecordForm.getGoods())
                .eq(StringUtils.isNotBlank(allRecordForm.getType()),"record_type",allRecordForm.getType())
                .eq(StringUtils.isNotBlank(allRecordForm.getCheck()),"record_check",allRecordForm.getCheck());
        Page<Record> page = new Page<>(current,size);
        return  recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 分页查找全部待审核记录
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @Override
    public Page<Record> getUncheckRecord(Integer current, Integer size) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_id","record_name","record_goods","record_total","record_unit","record_type","record_date","record_check","record_remark")
                        .eq("record_check","待审核");
        Page<Record> page = new Page<>(current, size);
        return recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 按条件分页查找全部待审核记录
     * @param current 当前页
     * @param size 每页条数
     * @param allRecordForm 暂存查询条件
     * @return
     */
    @Override
    public Page<Record> getAllowRecordBy(Integer current, Integer size, AllRecordForm allRecordForm) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_id","record_name","record_goods","record_total","record_unit","record_type","record_date","record_check","record_remark")
                .eq("record_check","待审核")
                .like(StringUtils.isNotBlank(allRecordForm.getUserName()),"record_name",allRecordForm.getUserName())
                .like(StringUtils.isNotBlank(allRecordForm.getGoods()),"record_goods",allRecordForm.getGoods())
                .eq(StringUtils.isNotBlank(allRecordForm.getType()),"record_type",allRecordForm.getType());
        Page<Record> page = new Page<>(current, size);
        return  recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 修改订单的状态（已通过/未通过）
     * @param record 单条捐赠记录
     */
    @Transactional
    @Override
    public void updateAllowOrder(Record record) {

        List<Material> list = new ArrayList<>();
        if (record.getRecordCheck().equals("已通过")){
            //修改记录信息
            recordMapper.updateById(record);
            //添加物资
            Material material = new Material();
            material.setGoods(record.getRecordGoods());
            material.setTotal(record.getRecordTotal());
            material.setUnit(record.getRecordUnit());
            material.setType(record.getRecordType());
            list.add(material);
            materialService.addMaterial(list);
        }else if(record.getRecordCheck().equals("未通过")){
            recordMapper.updateById(record);
        }
    }

    /**
     * 修改单页订单审核状态（已通过/未通过）
     * @param recordList 单页捐赠记录
     */
    @Transactional
    @Override
    public void allAllOrder(List<Record> recordList) {
        List<Material> list = new ArrayList<>();
        if (recordList.get(0).getRecordCheck().equals("已通过")){
            for (int i = 0; i < recordList.size(); i++) {
                //修改记录信息
                recordMapper.updateById(recordList.get(i));
                //添加物资
                Material material = new Material();
                material.setGoods(recordList.get(i).getRecordGoods());
                material.setTotal(recordList.get(i).getRecordTotal());
                material.setUnit(recordList.get(i).getRecordUnit());
                material.setType(recordList.get(i).getRecordType());
                list.add(material);
                materialService.addMaterial(list);
            }
        }else if(recordList.get(0).getRecordCheck().equals("未通过")){
            for (int i = 0; i < recordList.size(); i++) {
                recordMapper.updateById(recordList.get(i));
            }
        }
    }

    /**
     * 查询该管理员的发放记录
     * @param current 当前页
     * @param size 每页条数
     * @param admin session中admin信息
     * @return
     */
    @Override
    public Page<Record> getOutRecord(Integer current, Integer size,UserInfo admin) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_id","record_name","record_goods","record_total","record_unit","record_type","record_date","record_address","record_remark")
                .eq("record_name",admin.getUserName());
        Page<Record> page = new Page<>(current,size);
        return recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 添加该管理员的发放记录
     * @param admin session中admin信息
     * @param record 管理员输入的物资信息
     */
    @Transactional
    @Override
    public Result addOutRecord(UserInfo admin, Record record) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods",record.getRecordGoods())
                .eq("unit",record.getRecordUnit())
                .eq("type",record.getRecordType());
        //根据物资名称、单位、种类查询仓库是否有这种物资
        Material material = materialService.getOne(queryWrapper);
        if (material != null){
            //判断仓库物资总量与发放的数量
            if (material.getTotal() >= record.getRecordTotal()){
                //仓库物资总量 material.getTotal()
                //发放的数量 record.getRecordTotal()
                //修改仓库物资
                material.setTotal(material.getTotal() - record.getRecordTotal());
                materialService.updateById(material);
                //添加发放记录
                record.setRecordName(admin.getUserName());
                Date date = new Date();
                String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                Timestamp timestamp = Timestamp.valueOf(nowTime);
                record.setRecordDate(timestamp);
                recordMapper.insert(record);
                return new Result(true,"发放成功");
            }
            return new Result(false,"仓库物资不足,请查看物资数量");
        }
        return new Result(false,"仓库没有这种物资");
    }


    /**
     * 分页查询全部发放记录
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @Override
    public Page<Record> getAllOutRecord(Integer current, Integer size) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_id","record_name","record_goods","record_total","record_unit","record_type","record_date","record_address","record_remark")
                .isNotNull("record_address");
        Page<Record> page = new Page<>(current, size);
        return recordMapper.selectPage(page,queryWrapper);
    }

    /**
     * 按条件分页查询全部发放记录
     * @param current 当前页
     * @param size 每页条数
     * @param record 暂存查询条件
     * @return
     */
    @Override
    public Page<Record> OutRecordBy(Integer current, Integer size, Record record) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("record_id","record_name","record_goods","record_total","record_unit","record_type","record_date","record_address","record_remark")
                .like(StringUtils.isNotBlank(record.getRecordGoods()),"record_goods",record.getRecordGoods())
                .eq(StringUtils.isNotBlank(record.getRecordType()),"record_type",record.getRecordType())
                .like(StringUtils.isNotBlank(record.getRecordAddress()),"record_address",record.getRecordAddress())
                .isNotNull("record_address");
        Page<Record> page = new Page<>(current, size);
        return recordMapper.selectPage(page,queryWrapper);
    }
}
