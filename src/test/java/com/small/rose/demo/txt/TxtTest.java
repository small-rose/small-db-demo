package com.small.rose.demo.txt;

import com.small.rose.demo.DbDemoApplicationTests;
import com.small.rose.demo.utils.FileNameUtil;
import com.small.rose.demo.vo.TorderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ tx ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/4 13:11
 * @Version ： 1.0
 **/

@Slf4j
public class TxtTest extends DbDemoApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate ;

    @Test
    public void aVoid(){
        String name = "1_abc_${5}.txt";
        List<String> hanler = FileNameUtil.hanler(name, 5);
        hanler.forEach(System.out::println);

        String name2 = "1_abc_${Random(6)}.txt";
        List<String> hanler2 = FileNameUtil.hanler(name2, 5);
        hanler2.forEach(System.out::println);

        String name3 = "1_abc_${oRder(7)}.txt";
        List<String> hanler3 = FileNameUtil.hanler(name3, 4);
        hanler3.forEach(System.out::println);


        String name4 = "1_abc_${random(7)}.txt";
        List<String> hanler4 = FileNameUtil.hanler(name3, 1);
        hanler4.forEach(System.out::println);
    }

    @Test
    public void  txtTest() {

        File file = new File("d:\\1_test_bean_write.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =4;

        for (int i = 1; i < maxPageNo ; i++) {
            EasyTxt.write(file, TorderVO.class, "\\|",  i, 10, (pageNo, pageSize)->{
                //do query ，带分页
                return new ArrayList<TorderVO>();
            }).doWrite();
        }

    }

    @Test
    public void  txtTest02() {

        File file = new File("d:\\1_test_bean_write.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =4;

        for (int i = 1; i < maxPageNo ; i++) {

            // 不处理分页
            EasyTxt.write(file, TorderVO.class, "||", ()->{

                return new ArrayList();
            }).doWrite();
        }

    }

    @Test
    public void  txtTest03() {

        File file = new File("d:\\1_test_bean_write.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =4;

        for (int i = 1; i < maxPageNo ; i++) {

            // 不处理分页
            EasyTxt.write(file, TorderVO.class, "||", ()->{

                return new ArrayList();
            }).doWrite();
        }

    }

    @Test
    public void  txtTest2() {

        File file = new File("d:\\1_test_bean_write.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =4;

        // 准备SQL
        String sql = "";
        // 准备Bean
        // 分隔符
        EasyTxt.write(file, "\\|", jdbcTemplate).doWrite(sql, TorderVO.class);



    }

    @Test
    public void  txtTestCustom() {

        File file = new File("d:\\2407_test_bean_write_${5}.txt");
        if (file.exists()){ file.delete();}

        // 准备SQL
        String sql = "SELECT ID as line1, ORDER_NO as line2,  ORDER_NAME as line3, ORDER_DATE as line4 FROM  T_ORDER ";
        // 准备Bean
        // 分隔符
        // 指定1个文件多少条记录
        /*
        1_test_bean_write_10000.txt
        1_test_bean_write_10001.txt
         */
        EasyTxt.write(file, "\\|", jdbcTemplate, 100, -1).doWrite(sql, TorderVO.class);


    }
}
