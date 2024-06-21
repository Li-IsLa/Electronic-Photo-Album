package top.liisla.electronicphotoalbum.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoInput;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.LoginEntityDaoOutpt;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.RegisterEntityDao;


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
    int insertOfRegister(RegisterEntityDao registerEntityDao);

    //    登录查询
    @Select("SELECT userPassword,userName FROM userinfo WHERE userEmail = #{userAccount} OR userPhoneNumber = #{userAccount}")
    LoginEntityDaoOutpt account(LoginEntityDaoInput loginEntityDaoInput);

//    添加userKey
    @Update("update userinfo set userKey = #{userKey} where userEmail = #{loginEntityDaoInput.userAccount} OR userPhoneNumber = #{loginEntityDaoInput.userAccount}")
    int addUserKey(LoginEntityDaoInput loginEntityDaoInput, String userKey);
}
