package cn.com.fero.tlc.proxy.http.test;

import cn.com.fero.tlc.proxy.TCLProxyServer;
import cn.com.fero.tlc.proxy.service.TLCProxyMailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;


/**
 * Created by gizmo on 15/6/19.
 */
public class TLCProxyMailTest extends TLCProxyTest {

    @Autowired
    private TLCProxyMailService tlcProxyMailService;

    @Test
    public void testSendMial() {
        tlcProxyMailService.sendTextMail("wanghongmeng@fero.com.cn", "test", "aaaaaaaaaaaaa");
    }

    @Test
    public void testSendErrorMial() {
        tlcProxyMailService.sendErrorMail("发生重大异常");
    }
}
