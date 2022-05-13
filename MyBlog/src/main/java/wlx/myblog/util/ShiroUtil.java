package wlx.myblog.util;

import org.apache.shiro.SecurityUtils;
import wlx.myblog.shiro.AccountProfile;

public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
