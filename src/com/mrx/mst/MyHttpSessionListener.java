package com.mrx.mst;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener{
	
    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        System.out.println( se.getSession() + "创建了！！id=" + se.getSession().getId());
    }

    /* HttpSession的销毁时机需要在web.xml中进行配置，如下：
     * <session-config>
              <session-timeout>1</session-timeout>
          </session-config>
          这样配置就表示session在1分钟之后就被销毁
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        System.out.println(se.getSession() + "session销毁了！！id=" + se.getSession().getId());
    }

}
