package com.itheima.lottery.controller;

import com.itheima.lottery.bean.User;
import com.itheima.lottery.service.UserService;
import com.itheima.lottery.utils.UploadUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;

@Controller
public class UploadController {



    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    @ResponseBody
    /**
     * 注意：
     *    1. form 表单中 需要指定 method post方式, enctype = multipart/formdata
     *    2. 后台Controller中接收的时候需要使用MultipartFile
     *              getName
     *              getOriginalFilename 原始的文件名
     *              getInputStream()
     */
    public String upload(String username, MultipartFile wenjian) throws IOException {
        System.out.println("访问到了uploadcontroller");
        System.out.println("username:"+username);
        // 输出的是 input 标签 name属性对应的值 name="wenjian"
        System.out.println("wenjian.getNam:"+ wenjian.getName());
        // 输出的是 用户上传的文件名
        System.out.println("wenjian.getOriginalFilename :"+wenjian.getOriginalFilename());

        //获取文件的内容
        InputStream is = wenjian.getInputStream();

        File file = new File("D:/uploadfiles",wenjian.getOriginalFilename());
        //将文件保存到磁盘中
        FileOutputStream fos = new FileOutputStream(file);

        IOUtils.copy(is,fos);
       /*
            int len=0;
            byte[] bytes = new byte[1024];
            while((len = is.read(bytes)!=-1){
                fos.write(bytes,0,len);
            }
        */
        // 关流
        is.close();
        fos.close();
        return "shangchuan chenggong";
    }

    @PostMapping("/upload2")
   /* @ResponseBody*/
    /*
    * 1.同一个文件夹下面有可能出现重名的文件
    * 2.一个文件夹下面不能有太多的文件 2000
    *     目录分离
    *        基于时间: 2018/06/30/24/60/60/FADFAWERQEWR.jpg
    *        基于用户: zhangsan  lisi 只适合用户比较少
    *        基于算法:
    *           A-Z :  26个  0-9 10
    *           A-Z0-9  36
    * 3. 注意IE会将文件的全路径上传上来.
     *       frame 程序员 ---> IE 出来
     *
     *4. 注意: 默认上传文件的大小为1mb
     * */
    public String upload2(String username, HttpSession session, MultipartFile wenjian) throws IOException {

        //获取文件的内容
        InputStream is = wenjian.getInputStream();

        // 222.jpg  C:\Users\Kaijun\Pictures\Saved Pictures\70x70.png
        String originalFilename = wenjian.getOriginalFilename();
        System.out.println(originalFilename);
        // FALKDJFOIQWYREIUQEWT.jpg 生成一个uuid名称出来
        String uuidFilename = UploadUtils.getUUIDName(originalFilename);
        System.out.println(uuidFilename);

        //产生一个随机目录
        String randomDir = UploadUtils.getDir();

        File fileDir = new File("D:/uploadfiles"+randomDir);
        //若文件夹不存在,则创建出文件夹
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        //          D:/uploadfiles/A/C/C:\Users\Kaijun\Pictures\Saved Pictures\70x70.png
        File file = new File("D:/uploadfiles"+randomDir,uuidFilename);
        //将文件输出到目标的文件中
        wenjian.transferTo(file);


///////////////////////////////////////////////////////////////////////////////////
        //将保存的文件路径更新到用户信息headimg中  /A/C/fafadsf.jpg
        String savePath = randomDir+"/"+uuidFilename;

        //获取当前的user
        User user = (User) session.getAttribute("user");
        user.setHeadimg(savePath);

        //调用业务更新user
        userService.update(user);
/////////////////////////////////////////////////////////////////////////////////////
        //生成响应 : 跳转去用户详情页面
        return "redirect:/user/userUI";
    }

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/get/{filename:.+}")  // 123.jpg , 123.png
    // 注意我们需要文件的后缀名
    public ResponseEntity get(@PathVariable String filename){
        //1.根据用户名去获取相应的图片
        System.out.println(filename);

        Path path = Paths.get("D:/uploadfiles","123.jpg");
        System.out.println(path);
        //2.将文件加载进来
        Resource resource = resourceLoader.getResource("file:" + path.toString());

        return ResponseEntity.ok(resource);
    }

    //   /get/2/9/9EC41B00E59D48EB99A6F2657F75B6CA.gif
    @GetMapping("/get/{dir1}/{dir2}/{filename:.+}")  // 123.jpg , 123.png
    // 注意我们需要文件的后缀名
    public ResponseEntity get(@PathVariable String dir1,
                              @PathVariable String dir2,
                              @PathVariable String filename){
        //1.根据用户名去获取相应的图片
        System.out.println(filename);

        Path path = Paths.get("D:/uploadfiles"+"/"+dir1+"/"+dir2,filename);
        System.out.println(path);
        //2.将文件加载进来
        Resource resource = resourceLoader.getResource("file:" + path.toString());

        return ResponseEntity.ok(resource);
    }

}
