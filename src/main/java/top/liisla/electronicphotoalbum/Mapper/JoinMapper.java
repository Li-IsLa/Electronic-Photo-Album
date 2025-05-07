package top.liisla.electronicphotoalbum.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.liisla.electronicphotoalbum.Entity.Contorller.Join.ForgetPasswordEntityController;
import top.liisla.electronicphotoalbum.Entity.Dao.Join.ForgetPasswordEntityDaoOutput;
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
    @Insert("insert into userinfo (userName, userPassword, userPhoneNumber, userEmail, userGender, userPersonalSignature, userPower) values (#{userName}, #{userPassword}, #{userPhoneNumber}, #{userEmail}, #{userGender}, #{userPersonalSignature}, #{userPower})")
    int insertOfRegister(RegisterEntityDao registerEntityDao);

    //    登录查询
    @Select("SELECT userPassword,userName,userID FROM userinfo WHERE userEmail = #{userAccount} OR userPhoneNumber = #{userAccount}")
    LoginEntityDaoOutpt account(LoginEntityDaoInput loginEntityDaoInput);

    //    添加userKey
    @Update("update userinfo set userKey = #{userKey} where userEmail = #{loginEntityDaoInput.userAccount} OR userPhoneNumber = #{loginEntityDaoInput.userAccount}")
    int addUserKey(LoginEntityDaoInput loginEntityDaoInput, String userKey);

    //    select ForgetPasswordKey and ForgetPasswordKeyTime
    @Select("select userinfo.userForgetPasswordKey, userinfo.userForgetPasswordKeyTime from userinfo where userEmail = #{userAccount} OR userPhoneNumber = #{userAccount}")
    ForgetPasswordEntityDaoOutput selectUserForgetPasswordKey(ForgetPasswordEntityController forgetPasswordEntityController);

    //    upDate userInfo in userPassword
    @Update("update userinfo set userPassword = #{newUserPassword} where userEmail = #{userAccount} OR userPhoneNumber = #{userAccount}")
    int updateUserPasswordForForgetPassword(ForgetPasswordEntityController forgetPasswordEntityController);

    //    添加ForgetPasswordKey
    @Update("update userinfo set userForgetPasswordKey = #{forgetPasswordKey}, userForgetPasswordKeyTime = #{timeStamp} where userEmail = #{userAccount} OR userPhoneNumber = #{userAccount}")
    int addForgetPasswordKey(Long timeStamp, String forgetPasswordKey, String userAccount);
}
