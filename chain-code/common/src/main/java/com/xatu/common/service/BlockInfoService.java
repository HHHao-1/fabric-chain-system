package com.xatu.common.service;

import com.google.gson.Gson;
import com.xatu.common.pojo.Value;
import com.xatu.common.utils.HttpClient;
import io.github.ecsoya.fabric.FabricQueryResponse;
import io.github.ecsoya.fabric.bean.*;
import io.github.ecsoya.fabric.service.IFabricInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * @Author hao
 * @create 2021/6/23 5:05 下午
 */
@Service
public class BlockInfoService {
  @Autowired
  private IFabricInfoService iFabricInfoService;
//  @Autowired
//  private IFabricObjectService fabricService;
//  @Autowired
//  private IFabricService iFabricService;
  
  public Map<String, Object> blocksInfo() {
    FabricQueryResponse<FabricLedger> a = iFabricInfoService.queryFabricLedger();
    FabricQueryResponse<FabricBlock> b = iFabricInfoService.queryBlockByNumber(0);
    long height = a.data.getHeight();
    List<String> blocksHash = new ArrayList<>();
    List<String> datasHash = new ArrayList<>();
    List<Integer> txCounts = new ArrayList<>();
    List<Long> blockNumbers = new ArrayList<>();
    blocksHash.add(a.data.getCurrentHash());
//    FabricQueryResponse<List<FabricTransaction>> t = iFabricInfoService.queryTransactions(94);
//    FabricQueryResponse<FabricTransactionRWSet> tt = iFabricInfoService.queryTransactionRWSet(t.data.get(0).getTxId());
    for (long i = height - 1; i > -1; i--) {
      if (i != height - 1) {
        blocksHash.add(iFabricInfoService.queryBlockByNumber(i + 1).data.getPreviousHash());
      }
      datasHash.add(iFabricInfoService.queryBlockByNumber(i).data.getDataHash());
      txCounts.add(iFabricInfoService.queryBlockByNumber(i).data.getTransactionCount());
      blockNumbers.add(iFabricInfoService.queryBlockByNumber(i).data.getBlockNumber());
    }
    Map<String, Object> map = new HashMap<>();
    map.put("blocksHash", blocksHash);
    map.put("datasHash", datasHash);
    map.put("txCounts", txCounts);
    map.put("height", height);
    map.put("blockNumbers", blockNumbers);
    return map;
  }
  public Map<String, Object> txInfo(long blockNum){
    Map<String, Object> map = new HashMap<>();
    Gson gson = new Gson();
    FabricQueryResponse<List<FabricTransaction>> fabricQueryResponse = iFabricInfoService.queryTransactions(blockNum);
    String[] txIds = new String[fabricQueryResponse.data.size()];
    FabricTransactionRW[] txDatas = new FabricTransactionRW[fabricQueryResponse.data.size()];
    IntStream.range(0, fabricQueryResponse.data.size()).forEach(s->{
      txDatas[s] =
          iFabricInfoService.queryTransactionRWSet(fabricQueryResponse.data.get(s).getTxId()).data.getWrites().get(0);
      txIds[s]=fabricQueryResponse.data.get(s).getTxId();
      if (!"".equals(txDatas[s].getValue()) && txDatas[s].getValue().contains("author")) {
        Value.Values values = gson.fromJson(txDatas[s].getValue(), Value.Values.class);
        try {
          String str = HttpClient.get("http://192.168.3.238:8080/ipfs/"+values.getValue());
          String strValue = gson.fromJson(str,Value.class).getValues().getValue();
          values.setValue(strValue);
          txDatas[s].setValue(gson.toJson(values));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    map.put("txIds",txIds);
    map.put("txDatas",txDatas);
    return map;
  }
}
