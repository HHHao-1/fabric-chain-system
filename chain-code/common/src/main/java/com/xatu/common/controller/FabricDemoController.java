package com.xatu.common.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xatu.common.pojo.Value;
import com.xatu.common.utils.HttpClient;
import io.github.ecsoya.fabric.FabricPagination;
import io.github.ecsoya.fabric.FabricPaginationQuery;
import io.github.ecsoya.fabric.FabricResponse;
import io.github.ecsoya.fabric.bean.FabricObject;
import io.github.ecsoya.fabric.service.IFabricObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class FabricDemoController {
  
  @Autowired
  private IFabricObjectService fabricService;
  
  @GetMapping("/")
  public ModelAndView index(HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("baseURL", baseUrl(request));
    return modelAndView;
  }
  
  private String baseUrl(HttpServletRequest request) {
    String scheme = request.getScheme();
    String serverName = request.getServerName();
    int port = request.getServerPort();
    String path = request.getContextPath();
    return scheme + "://" + serverName + ":" + port + path;
  }
  
  @GetMapping("/list")
//  public FabricPagination<FabricObject> list(FabricPaginationQuery<FabricObject> query, String value) {
  public String[] list(FabricPaginationQuery<FabricObject> query, String value) {
    if (value != null && !value.equals("")) {
      JsonObject json = new JsonObject();
      json.addProperty("value", value);
      query.equals("values", json);
    }
    FabricPagination<FabricObject> fabricObjectFabricPagination = fabricService.pagination(query);
    String[] res = new String[fabricObjectFabricPagination.getData().size()];
    Gson gson = new Gson();
    AtomicInteger count = new AtomicInteger();
    fabricObjectFabricPagination.getData().stream().forEach(s->{
      try {
        String str = HttpClient.get("http://192.168.3.238:8080/ipfs/"+s.getValues().get("value"));
        String strValue = gson.fromJson(str, Value.class).getValues().getValue();
        res[count.get()] = strValue;
        count.getAndIncrement();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return res;
//    if (!"".equals(txDatas[s].getValue()) && txDatas[s].getValue().contains("author")) {
//    return fabricService.pagination(query);
  }
  
  @GetMapping("/add")
  public FabricResponse add(FabricObject object) {
    return fabricService.create(object);
  }
  
  @GetMapping("/update")
  public FabricResponse update(FabricObject object) {
    return fabricService.update(object);
  }
  
  @GetMapping("/remove")
  public FabricResponse remove(FabricObject object) {
    return fabricService.delete(object.getId(), object.getType());
  }
}

