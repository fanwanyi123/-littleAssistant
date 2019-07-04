package com.fanyi.assistant.controller;

import com.fanyi.assistant.model.User;
import com.fanyi.assistant.service.UserService;
import com.fanyi.assistant.util.CodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(CodeUtil.class);
    private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };
    @Autowired
    private UserService userService;

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerifyCode")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            CodeUtil randomValidateCode = new CodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }

    /**
     * 校验验证码
     */
    @ResponseBody
    @RequestMapping(value = "/checkUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public Object checkVerify(@RequestParam String userName, @RequestParam String password, @RequestParam String verifyInput, HttpSession session) {
        Map<String,String>  checkResultMap = new HashMap<String,String>();
        try {
            //从session中获取随机数
            String inputStr = verifyInput;
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                checkResultMap.put("result","codeError");
            }else if (random.equals(inputStr)) {
                boolean hasUser = userService.findUserByInputVal(userName,encryptBasedDes(password));
                if (hasUser){
                    session.setAttribute("userName",userName);
                    checkResultMap.put("result","success");
                }else {
                    checkResultMap.put("result","userError");
                }
            } else {
                checkResultMap.put("result","codeError");
            }

        } catch (Exception e) {
            logger.error("验证码校验失败", e);
        }
        return checkResultMap;
    }

    /**
     * 注册
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="registerUser",method  = RequestMethod.POST)
    public Object registerUser(HttpServletRequest request, HttpSession session) {
        Map<String,String>  checkResultMap = new HashMap<String,String>();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String trueName = request.getParameter("name");
        String email = request.getParameter("email");
        String rcode = request.getParameter("rcode");
        User user  =new User();
        user.setUserName(userName);
        user.setPassword(encryptBasedDes(password));
        user.setTrueName(trueName);
        user.setEmail(email);
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if (random == null) {
            checkResultMap.put("result","codeError");
        }else if (random.equals(rcode)) {
            boolean hasUserName = userService.findUserByUserName(userName);
            if (hasUserName){
                checkResultMap.put("result","existUser");
            }else {
                userService.insertUserInfo(user);
                checkResultMap.put("result","success");
            }
        }else {
            checkResultMap.put("result","codeError");
        }
        return checkResultMap;
    }

    /**
     *  EDS的加密解密代码
     */
    @SuppressWarnings("restriction")
    public static String encryptBasedDes(String data) {
        String encryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            // log.error("加密错误，错误信息：", e);
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }
    @SuppressWarnings("restriction")
    public static String decryptBasedDes(String cryptData) {
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            // 把字符串进行解码，解码为为字节数组，并解密
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }
}
