package com.hilkr.auth.utils;
import com.hilkr.auth.JialegouAuthApplication;
import com.hilkr.auth.service.IAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * 描述:
 * 获取5000用户的Token，写入文件中，主要用于秒杀服务测试
 *
 * @author hilkr
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JialegouAuthApplication.class)
public class TokenTest {

   @Autowired
   private IAuthService authService;

   @Test
   public void getToken() throws IOException {
       File userInfo = new File("/Users/sam/Documents/Developer/swe15086/jialegou/jialegou-auth/src/test/java/com/hilkr/auth/file/userInfo.txt");
       if (userInfo.exists()){
           userInfo.delete();
       }
       FileWriter fw = new FileWriter(userInfo, true);
       BufferedWriter bw = new BufferedWriter(fw);
       for (int i =0;i < 50 ; i++) {
           String token = this.authService.authentication("username"+i,"abcdefg"+i);
           bw.append("username"+i+","+token+"\r\n");
       }
       bw.close();
       fw.close();

   }

   @Test
   public void getTokenCSV(){
       try {
           File csv = new File("/Users/sam/Documents/Developer/swe15086/jialegou/jialegou-auth/src/test/java/com/hilkr/auth/file/Token.csv");//CSV文件
           BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
           for (int i =0; i < 50; i++) {
               //新增一行数据
               String token = this.authService.authentication("username"+i,"abcdefg"+i);
               bw.write("username"+i+","+token);
               bw.newLine();
           }
           bw.close();
       } catch (FileNotFoundException e) {
           //捕获File对象生成时的异常
           e.printStackTrace();
       } catch (IOException e) {
           //捕获BufferedWriter对象关闭时的异常
           e.printStackTrace();
       }
   }
}
