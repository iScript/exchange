package com.ykq.counter;
//
//import com.baidu.aip.speech.AipSpeech;
//import com.tencentcloudapi.asr.v20190614.AsrClient;
//import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskRequest;
//import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskResponse;
//import com.tencentcloudapi.asr.v20190614.models.DescribeTaskStatusRequest;
//import com.tencentcloudapi.asr.v20190614.models.DescribeTaskStatusResponse;
//import com.tencentcloudapi.common.Credential;
//import com.tencentcloudapi.common.exception.TencentCloudSDKException;
//import com.tencentcloudapi.common.profile.ClientProfile;
//import com.tencentcloudapi.common.profile.HttpProfile;
//import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

//@SpringBootTest
class CounterApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	void baidu(){
//		AipSpeech client = new AipSpeech("23192455", "CRw3xATD2QAUU3NNIoVns9cr", "s9UjP4FFCwukTKcx11ZBWmr1GySE86bq");
//
//		String path = "/Users/sig/Downloads/bugscaner-tts-auido.mp3";
//		JSONObject asrRes = client.asr(path,"pcm",16000,null);
//
////		String url = "http://tools.bugscaner.com/api/tts/?text=2&yusu=1&fayin=0&key=6d9f19c27248c126550d6f736e316fbb&audio=bugscaner-tts-auido.mp3";
////		JSONObject asrRes = client.asr(url,"test","mp3",16000,null);
//
//		System.out.println(asrRes);
//
//
//	}

//	@Test
//	void tx1(){
//		int i ;
//		for (i=0;i<10;i++){
//			try{
//				//重要，此处<Your SecretId><Your SecretKey>需要替换成客户自己的账号信息，获取方法：
//				//请参考接口说明（https://cloud.tencent.com/document/product/1093/37139）中的使用步骤 1 进行获取。
//				Credential cred = new Credential("AKIDlbDahfiwFJGwMW4NwB2pnZVN4xckjEk1", "KQkqHrAVfhU0GIBqHS2NSHpLwJQRItHP");
//
//				HttpProfile httpProfile = new HttpProfile();
//				httpProfile.setEndpoint("asr.tencentcloudapi.com");
//
//				ClientProfile clientProfile = new ClientProfile();
//				clientProfile.setHttpProfile(httpProfile);
//
//				AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);
//
//				String url = "https://zxs-voice.oss-cn-hangzhou.aliyuncs.com/speed8/none/num1/"+i+".mp3";
//				String params = "{\"EngineModelType\":\"16k_zh\",\"ChannelNum\":1,\"ResTextFormat\":0,\"SourceType\":0,\"Url\":\""+url+"\"}";
//				CreateRecTaskRequest req = CreateRecTaskRequest.fromJsonString(params, CreateRecTaskRequest.class);
//
//				CreateRecTaskResponse resp = client.CreateRecTask(req);
//
//				System.out.println(CreateRecTaskRequest.toJsonString(resp));
//
//				String taskId = i+" "+resp.getData().getTaskId().toString();
//
//
//				FileWriter writer = new FileWriter("/Users/sig/Desktop/1.txt",true);
//
//				BufferedWriter bw = new BufferedWriter(writer);
//
//				bw.write(taskId);
//				bw.newLine();
//				bw.flush();
//				bw.close();
//				writer.close();
//
//			} catch (TencentCloudSDKException | IOException e) {
//				System.out.println(e.toString());
//			}
//		}
//
//
//
//	}
//
//	@Test
//	void tx2(){
//		try{
//			File file = new File("/Users/sig/Desktop/1.txt");
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//			String strLine = null;
//			int lineCount = 1;
//
//			Credential cred = new Credential("AKIDlbDahfiwFJGwMW4NwB2pnZVN4xckjEk1", "KQkqHrAVfhU0GIBqHS2NSHpLwJQRItHP");
//
//			HttpProfile httpProfile = new HttpProfile();
//			httpProfile.setEndpoint("asr.tencentcloudapi.com");
//
//			ClientProfile clientProfile = new ClientProfile();
//			clientProfile.setHttpProfile(httpProfile);
//
//			AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);
//
//
//
//			while(null != (strLine = bufferedReader.readLine())){
//
//
//				String[] strlines = strLine.split(" ");
//				String taskId = strlines[1];
//				String params2 = "{\"TaskId\":"+taskId+"}";
//				System.out.println("第[" + lineCount + "]行数据:[" + strlines[0] + "]");
//				DescribeTaskStatusRequest req2 = DescribeTaskStatusRequest.fromJsonString(params2, DescribeTaskStatusRequest.class);
//
//				DescribeTaskStatusResponse resp2 = client.DescribeTaskStatus(req2);
//				System.out.println(DescribeTaskStatusRequest.toJsonString(resp2));
//
//
//				if(resp2.getData().getStatusStr().equals("success")){
//					String s = resp2.getData().getResult();
//					int start = s.indexOf("  ");
//					int end = s.indexOf("。");
//					String s2 = s.substring(start,end);
//
//					System.out.println(s2);
//					//System.out.println(DescribeTaskStatusRequest.toJsonString(resp2));
//
//
//				}
//				lineCount++;
//			}
//
//		//  1 2 3 5 8 11 15
//			// add sub mul div none
//			// 99999
//		//	https://zxs-voice.oss-cn-hangzhou.aliyuncs.com/speed8/add/num1/286.mp3
//
//
//		} catch (TencentCloudSDKException | FileNotFoundException e) {
//			System.out.println(e.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
