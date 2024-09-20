package top.liisla.electronicphotoalbum.Dao.Aspect;

import top.liisla.electronicphotoalbum.Entity.Aspect.CheckUserLoginEntityAspect;

public interface AspectDao {
    boolean checkUserLoginKey(CheckUserLoginEntityAspect checkUserLoginEntityAspect);
}
