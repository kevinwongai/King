package tech.kevinwong.framework.security.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import tech.kevinwong.common.constant.Constants;
import tech.kevinwong.common.constant.HttpStatus;
import tech.kevinwong.common.utils.ServletUtils;
import tech.kevinwong.common.utils.StringUtils;
import tech.kevinwong.framework.manager.AsyncManager;
import tech.kevinwong.framework.manager.factory.AsyncFactory;
import tech.kevinwong.framework.security.LoginUser;
import tech.kevinwong.framework.security.service.TokenService;
import tech.kevinwong.framework.web.domain.AjaxResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author Kevin<EastascendWang@gmail.com>
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
