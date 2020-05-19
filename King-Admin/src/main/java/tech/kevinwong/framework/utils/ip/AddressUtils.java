package tech.kevinwong.framework.utils.ip;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.kevinwong.common.utils.StringUtils;
import tech.kevinwong.common.utils.http.HttpUtils;
import tech.kevinwong.framework.config.KingConfig;

/**
 * 获取地址类
 * 
 * @author Kevin<EastascendWang@gmail.com>
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        if (KingConfig.isIpAddress())
        {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            JSONObject data = obj.getObject("data", JSONObject.class);
            String region = data.getString("region");
            String city = data.getString("city");
            address = region + " " + city;
        }
        return address;
    }
}
