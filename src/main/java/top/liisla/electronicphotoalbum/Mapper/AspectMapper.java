package top.liisla.electronicphotoalbum.Mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.liisla.electronicphotoalbum.Entity.Aspect.CheckUserLoginEntityAspect;

@Mapper
public interface AspectMapper {
    @Select("select if(userinfo.userKey = #{userKey}, true, false) from userinfo where userID = #{userID}")
    boolean checkUserLoginKey(CheckUserLoginEntityAspect checkUserLoginEntityAspect);
}
