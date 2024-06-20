package top.liisla.electronicphotoalbum.Dao.Join;

import top.liisla.electronicphotoalbum.Entity.Dao.Join.JoinEntityDao;

public interface JoinDao {
//    注册检查
    boolean registrationCheck(JoinEntityDao joinEntityDao);
//    注册添加
    boolean registrationInsert(JoinEntityDao joinEntityDao);
}
