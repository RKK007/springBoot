package com.sfac.java_spring_boot.models.test.controller;

import com.sfac.java_spring_boot.models.test.entity.City;
import com.sfac.java_spring_boot.models.test.entity.Country;
import com.sfac.java_spring_boot.models.test.service.CityService;
import com.sfac.java_spring_boot.models.test.service.CountryServcie;
import com.sfac.java_spring_boot.models.test.vo.ApplicationTest;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/*
@author:Lenovo
@program:java_spring_boot
@date:2020-08-10 10:39:03
@description:
*/
@Controller
@RequestMapping("/test")
public class TestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @Value("${server.port}")
    private int port;

    @Value("${com.name}")
    private String name;

    @Value("${com.age}")
    private int age;

    @Value("${com.desc}")
    private String desc;

    @Value("${com.random}")
    private String random;

    @Autowired
    private ApplicationTest at;


    //日志测试
    /**
     * 127.0.0.1:8086/test/logTest ---- get
     */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test";
    }



    //系统配置文件测试
/*    127.0.0.1:8086/test/config      */
    @GetMapping("/config")
    @ResponseBody
    public String configTest(){
        return new StringBuffer()
                .append(port).append("----")
                .append(name).append("----")
                .append(age).append("----")
                .append(desc).append("----")
                .append(random).append("----").toString();
    }



    //自定义配置文件测试
    /*    127.0.0.1:8086/test/config2      */
    @GetMapping("/config2")
    @ResponseBody
    public String configTest2(){
        return new StringBuffer()
                .append(at.getPort()).append("----")
                .append(at.getName()).append("----")
                .append(at.getAge()).append("----")
                .append(at.getRandom()).append("----")
                .append(at.getDesc()).append("----").toString();
    }



    //项目启动测试
    /*
    * 127.0.0.1/test/testDesc?paramKey=fuck ---get
    * */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(@RequestParam("paramKey") String paramValue, HttpServletRequest request){
        String param = request.getParameter("paramKey");
        return "<center style='color:green;'>This is my first springBoot<h2>"+paramValue+"====="+param+"</h2></center>";
    }





    @Autowired
    private CityService cityService;

    @Autowired
    private CountryServcie countryServcie;

    //测试thymeleaf路径是否正常
    /*
     * 127.0.0.1/test/index ---get
     * */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap){
        int countryId = 522;
        List<City> cities = cityService.getCitiesByCountryId(countryId);//根据ID查询city和对应的国家
        cities = cities.stream().limit(10).collect(Collectors.toList());//取前十条记录
        Country country = countryServcie.getCountryByCountryId(countryId);//根据ID查询国家以及该国家下的所有城市

        modelMap.addAttribute("thymeleafTitle", "This thymeleaf replaced");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo", "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo", "/upload/111.jpg");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/updateCity");
//        modelMap.addAttribute("template", "test/index");

        //返回外层碎片组装器index.html
        return "index";
    }



    //单文件上传
    @PostMapping(value = "/file",consumes = "multipart/form-data")
    public String fileUpload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select file!");
            return "redirect:/test/index";
        }
        try {
            String destFilePath = "D:\\UpLoad\\" + file.getOriginalFilename();
            file.transferTo(new File(destFilePath));
            redirectAttributes.addFlashAttribute("message","Upload file success!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message","Upload file failed!");
            e.printStackTrace();
        }
        return "redirect:/test/index";
    }



    //多文件上传
    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String fileUploads(@RequestParam MultipartFile[] files, RedirectAttributes redirectAttributes){
        boolean empty = true;//假定文件没有选择
        try {
            for(MultipartFile file:files){
                if(file.isEmpty()){
                    continue;
                }
                String destFilePath = "D:\\UpLoad\\" + file.getOriginalFilename();
                file.transferTo(new File(destFilePath));
                empty = false;
            }
            if(empty){
                redirectAttributes.addFlashAttribute("message","Please select file!");
            }else{
                redirectAttributes.addFlashAttribute("message","Upload file success!");
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message","Upload file failed!");
            e.printStackTrace();
        }
        return "redirect:/test/index";
    }





    //文件下载
    @GetMapping(value = "/downloadFile")
    public ResponseEntity<Resource> downFile(@RequestParam("fileName") String fileName){
        try {
            Resource resource = new UrlResource(Paths.get("D:\\Upload\\" + fileName).toUri());
            String downName = new String(resource.getFilename().getBytes("utf-8"),"ISO8859-1");//处理文件名的编码格式
            return ResponseEntity.ok().header("Content-Type","application/octet-stream")
                    .header(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment; filename=\"%s\"",downName))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    //文件名
    private String fn;

    //文件查看
    @PostMapping(value = "/seeFile")
    @ResponseBody
    public String seeFile(@RequestParam("fileName") String fileName) throws Exception {
        fn = fileName;
        InputStream is = new FileInputStream("D:\\Upload\\"+fileName);
        byte[] bytes = new byte[1024000];
        int temp = is.read(bytes);
        is.close();
        return new String(bytes,0,temp);
    }




    //保存文件
    @PostMapping(value = "saveFile")
    @ResponseBody
    public String saveFile(String innerText) throws IOException {
        OutputStream outputStream = new FileOutputStream("D:\\UpLoad\\"+fn);
        byte[] b = innerText.getBytes();//将字符串转为byte数组
        outputStream.write(b);//全部写入
        outputStream.flush();//刷新流
        outputStream.close();
        return "";
    }



}