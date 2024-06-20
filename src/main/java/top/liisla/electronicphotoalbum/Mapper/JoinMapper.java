package top.liisla.electronicphotoalbum.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.JoinEntityDao;


@Mapper
public interface JoinMapper {
//    查询电话号码是否重复
    @Select("select count(*) from userinfo where userPhoneNumber = #{userPhoneNumber}")
    int countPhoneNumberOfJoin(String userPhoneNumber);
//    查询邮箱是否重复
    @Select("select count(*) from userinfo where userEmail = #{userEmail}")
    int countEmailNumberOfJoin(String userEmail);
//    向数据库添加注册信息
    @Insert("insert into userinfo (userName, userPassword, userPhoneNumber, userEmail, userGender, userPersonalSignature) values (#{userName}, #{userPassword}, #{userPhoneNumber}, #{userEmail}, #{userGender}, #{userPersonalSignature})")
    int insertOfRegister(JoinEntityDao joinEntityDao);
}
