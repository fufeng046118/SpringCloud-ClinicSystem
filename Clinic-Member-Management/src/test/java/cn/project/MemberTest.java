package cn.project;

import cn.project.mapper.MemberMapper;
import cn.project.pojo.Member;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberTest {
    @Resource
    MemberMapper memberMapper;
    @Test
    public void test(){
        int pageSize = 2;
        int pageNo = 1;
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("pageSize",pageSize*pageNo);
            put("pageNo",(pageNo-1)*pageSize);
            put("memberLevelId",1);
        }};
        List<Member> members = memberMapper.selectMemberByMap(map);
        members.forEach(System.out::println);
    }
}
