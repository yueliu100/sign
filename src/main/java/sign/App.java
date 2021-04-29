package sign;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;

public class App {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            // 引用 java.security.MessageDigest公共类
            // getInstance("MD5")方法 设置加密格式
            md5 = MessageDigest.getInstance("MD5"); // 此句是核心
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法[可逆] 执行一次加密，执行两次解密
     */
    public static String object2str(Map<String, String> map) {
        Collection<String> keyset = map.keySet();
        List<String> list = new ArrayList<String>(keyset);

        // 对key键值按字典升序排序
        Collections.sort(list);
        Map<String, String> new_map = new TreeMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("put前前前前前前前前前前前前前" + map.get(list.get(i)));
            new_map.put(list.get(i).toString(), map.get(list.get(i)).toString());
            System.out.println("put后后后后后后后后后" + new_map);
        }
        String str1 = JSON.toJSONString(new_map);
        return str1;
    }

    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public static String str2map(String str) {
        String[] new_str = str.split("\\?");
        String map_str = new_str[1];
        String[] param_list = map_str.split("\\&");
        Map<String, String> param_map = new TreeMap<String, String>();
        for (int i = 0; i < param_list.length; i++) {
            param_map.put(param_list[i].split("\\=")[0].toString(), param_list[i].split("=")[1]);
        }
        String param_str = JSON.toJSONString(param_map);
        return param_str;
    }

    // // 测试主函数
    public static void main(String args[]) {
    String s = new String("tangfuqiang");
    Map<String, String> param = new HashMap<>();
    param.put("domain_id", "0");
    param.put("Timestamp", System.currentTimeMillis() / 1000 + "000");
    System.out.println("排序后的：" + object2str(param));
    System.out.println("MD5后：" + string2MD5(object2str(param)).toUpperCase());
    System.out.println("加密的：" + convertMD5(s));
    System.out.println("解密的：" + convertMD5(convertMD5(s)));
    System.out.println(str2map("vdsbvf?ccc=1&aaa=2"));
    }
}
