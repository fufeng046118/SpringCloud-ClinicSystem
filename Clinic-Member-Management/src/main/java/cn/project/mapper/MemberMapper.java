package cn.project.mapper;

import cn.project.pojo.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberMapper extends BaseMapper<Member> {
    List<Member> selectMemberByMap(Map<String,Object> map);
}
