package cn.com.fero.tlc.proxy.fetcher.impl;

import cn.com.fero.tlc.proxy.common.TLCProxyConstants;
import cn.com.fero.tlc.proxy.exception.TLCProxyProxyException;
import cn.com.fero.tlc.proxy.http.TLCProxyHTMLParser;
import cn.com.fero.tlc.proxy.http.TLCProxyRequest;
import cn.com.fero.tlc.proxy.fetcher.TLCProxyIpFetcher;
import cn.com.fero.tlc.proxy.util.TLCProxyLoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghongmeng on 2015/7/15.
 */
public class TLCProxyDL5566IpFetcher extends TLCProxyIpFetcher {
    @Value("${tlc.proxy.url.fetch.dl5566}")
    private String fetchUrl;

    @Override
    public List<String> doFetch() {
        try {
            TLCProxyLoggerUtil.getLogger().info("开始抓取代理5566国内高匿代理");
            List<String> ipList = new ArrayList();

            String content = TLCProxyRequest.get(fetchUrl, false)
                    .get(TLCProxyConstants.SPIDER_CONST_RESPONSE_CONTENT).toString();
            List<TagNode> ipNodeList = TLCProxyHTMLParser.parseNode(content, "//div[@id='list']//table[@class='table table-bordered table-striped']/tbody/tr");

            for (TagNode ipNode : ipNodeList) {
                String type = TLCProxyHTMLParser.parseText(ipNode, "td[4]");
                if(StringUtils.containsIgnoreCase(type, TLCProxyConstants.SPIDER_CONST_HTTPS)) {
                    String ip = TLCProxyHTMLParser.parseText(ipNode, "td[1]");
                    String port = TLCProxyHTMLParser.parseText(ipNode, "td[2]");
                    if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(port)) {
                        continue;
                    }

                    String ipStr = ip + TLCProxyConstants.SPIDER_CONST_COLON + port;
                    ipList.add(ipStr);
                }
            }

            return ipList;
        } catch (Exception e) {
            throw new TLCProxyProxyException(e);
        } finally {
            TLCProxyLoggerUtil.getLogger().info("抓取代理5566国内高匿代理结束");
        }
    }
}
