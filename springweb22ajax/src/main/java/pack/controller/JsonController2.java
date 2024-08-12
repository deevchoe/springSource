package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController2 {
	@GetMapping("list2")
	@ResponseBody
	public Map<String, Object> getJsons(){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();// Map 객체를 담고있는 List!
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "홍길동");
		data.put("age", "23");
		dataList.add(data);
		
		data = new HashMap<String, String>();
		data.put("name", "신길동");
		data.put("age", "25");
		dataList.add(data);
		
		data = new HashMap<String, String>();
		data.put("name", "고길동");
		data.put("age", "27");
		dataList.add(data);
		
		//return data;	// 마지막 data인 고길동만 넘어가!!! 3명을 다 넘겨야하는데!!!		list로 넘겨야지
		System.out.println("data : " + data);
		
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("datas", dataList);	// datas라는 키에다가 dataList를 주면 돼!
		System.out.println("dataList : " + dataList);
		// [{name=홍길동, age=23}, {name=신길동, age=25}, {name=고길동, age=27}]
		// @RestponseBody에 의해 {"datas":[{"name":"홍길동","age":"23"},{"name":"신길동","age":"25"},{"name":"고길동","age":"27"}]}
		return data2;
	}
	// 복수일때는 key:value로 넘겨야해.
	//list,map, key:value로 데이터를 만드는게 map.   복수일 땐 map!!!
}
