package com.foreverything.bbs.util;

import com.foreverything.bbs.mapper.TopicMapper;
import com.foreverything.bbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName IDUtil
 * @Author CeaserBorgia
 * @Date 19:11 2019/12/16
 * @Description
 */

@Component
public class IDUtil {

    private static IDUtil idUtil;
    private static IDUtil idUtil1;

    private static List<Long> topicIDList=new ArrayList<>();
    private static List<Integer> userIDList=new ArrayList<>();

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    UserMapper userMapper;

    @PostConstruct
    public void init(){
        /**
         * @Author:CeaserBorgia
         * @Date:19:19 2019/12/16
         * @param:
         *  * @param
         *
         * @Desccription: 工具类在静态方法中引用mapper接口需调用该方法，否则报空指针
         *
         */
        idUtil=this;
        idUtil.topicMapper=this.topicMapper; //装配Mapper后给储存topicID的List赋值

        topicIDList=topicMapper.getTopicIdCollection();
    }

    @PostConstruct
    public void init1(){
        idUtil1=this;
        idUtil1.userMapper=this.userMapper; //装配Mapper后给储存topicID的List赋值

        userIDList=userMapper.getUserIdCollection();
    }

    public static Long initTopicID(){
        /**
         * @Author:CeaserBorgia
         * @Date:23:08 2019/12/16
         * @param:
         *  * @param
         *
         * @Desccription: 生成不重复的12位ID
         */
        Long id;
        while(topicIDList.contains((id=randomALongID()))){
            id=randomALongID();
        }
        return id;
    }
    public static int initUserID(){
        int id;
        while (userIDList.contains((id=randomID()))){
            id=randomID();
        }
        return id;
    }

    private static Long randomALongID(){
        /**
         * @Author: CeaserBorgia
         * @Date: 19:25 2019/12/16
         * @param:
         *  * @param
         *
         * @Desccription: 生成12位的随机数，并不校验重复
         */
        Random random=new Random();
        long id=Math.abs(random.nextLong())%(1000000000000L);
        if(id<1e11){
            id+=1e11;
        }
        return id;
    }

    private static int randomID(){
        int radom = new Random().nextInt(999999);
        if (radom < 100000) {
            radom += 100000;
        }
        return radom;
    }

}

