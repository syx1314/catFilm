package cn.catmovie.catmovie2.bean;

/**
 * Created by 呆呆 on 2019/8/28.
 */

public class UserInfo {
    private String user_name;
    private String user_portrait;
    private String user_points;
    private String user_last_login_ip;
    private String user_login_num;
    private String user_end_time;
    private UserGroup group;

    public String getUser_name() {
        return user_name;
    }

    public String getUser_portrait() {
        return user_portrait;
    }

    public String getUser_points() {
        return user_points;
    }

    public String getUser_last_login_ip() {
        return user_last_login_ip;
    }

    public String getUser_login_num() {
        return user_login_num;
    }

    public String getUser_end_time() {
        return user_end_time;
    }

    public UserGroup getGroup() {
        return group;
    }
}
