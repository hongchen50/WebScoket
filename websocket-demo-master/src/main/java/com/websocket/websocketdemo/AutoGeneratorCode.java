package com.websocket.websocketdemo;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class AutoGeneratorCode {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String s = System.getProperty("user.dir");
        gc.setOutputDir(s+"/src/main/java");
        gc.setAuthor("洪辰");
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ASSIGN_UUID);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);
        //2配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/socket?useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123123");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        //3.包的配置
        PackageConfig pc=new PackageConfig();
        pc.setParent("com.websocket.websocketdemo");
        pc.setEntity("bean");
        pc.setMapper("mapper");
        pc.setController("controller");
        pc.setService("service");
        mpg.setPackageInfo(pc);
        //策略配置
        StrategyConfig sc=new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setInclude("friend","user");
        sc.setEntityLombokModel(true);
        sc.setLogicDeleteFieldName("deleted");
        //自动填充
//        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
//        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
//        ArrayList<TableFill> tableFills = new ArrayList<>();
//        tableFills.add(create_time);
//        tableFills.add(update_time);
//        sc.setTableFillList(tableFills);
//        sc.setRestControllerStyle(true);
        sc.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(sc);
        mpg.execute();

    }
}
