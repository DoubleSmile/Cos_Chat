package cos.chat.test;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cos.chat.controller.IndexController;
import cos.chat.dao.ChatterDAO;
import cos.chat.dao.LinkDAO;
import cos.chat.dao.RelationDAO;
import cos.chat.model.Chatter;
import cos.chat.service.ChatterService;
import cos.chat.service.LinkService;

public class ChatterDaoTest {
	
	@Test
	public void fun() throws Exception{
		ComboPooledDataSource cpds=new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver"); 
		cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/cos_chat" ); 
		cpds.setUser("root"); 
		cpds.setPassword("");
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(cpds);
		ChatterDAO chatterDAO=new ChatterDAO();
		chatterDAO.setTemplate(template);
		RelationDAO relationDAO=new RelationDAO();
		relationDAO.setTemplate(template);
		chatterDAO.setRelationDAO(relationDAO);
		ChatterService chatterService=new ChatterService();
		LinkDAO linkDAO=new LinkDAO();
		linkDAO.setTemplate(template);
		chatterService.setChatterDAO(chatterDAO);
		chatterService.setLinkDAO(linkDAO);
		LinkService linkService=new LinkService();
		linkService.setLinkDAO(linkDAO);
		IndexController controller=new IndexController();
		controller.setchatterService(chatterService);
		controller.setLinkService(linkService);
		for(int i=0;i<10;i++){
			
			System.out.println(chatterDAO.getMatchedChatter("蒙多").get("matchedIndentifier"));
		}
//		System.out.println(relationDAO.getMatchedID(19));
		
		
		
		
		/*List<String> groupNames=chatterDAO.getChatterGroupNames();
		List<String> types=chatterDAO.getChatterTypes();
		JSONObject all=JSONUtil.combineForAndroid(groupNames, types, chatterService);
		System.out.println(all.toString());*/
		
//		JSONObject all1=JSONUtil.combine(groupNames, types, chatterService);
//		System.out.println(all1.toString());
		
		
//		linkDAO.addLink(1, 2);
//		System.out.println(linkDAO.getLinkedID(4));
		
//		System.out.println(chatterService.getChatter("蛮王"));
//		System.out.println(chatterService.getMatchedChatter("蛮王"));
		
//		List<Map<String,Object>> list=chatterDAO.getChattersByGroupName("西游记");
//		Map<String,Object> map=new HashMap();
//		map.put("indentifier","西游记");
//		list.add(map);
//		JSONArray array=new JSONArray();
//		System.out.println(array.fromObject(list));
		
		
		/*List<Map<String,Object>> list=chatterDAO.getChattersByType("最新");
		Map<String,Object> map=new HashMap();
		map.put("indentifier","最新");
		list.add(map);
		JSONArray array=JSONArray.fromObject(list);
		
		List<Map<String,Object>> list1=chatterDAO.getChattersByType("名著");
		Map<String,Object> map1=new HashMap();
		map1.put("indentifier","名著");
		list1.add(map1);
		JSONArray array1=JSONArray.fromObject(list1);
		
		JSONArray all=new JSONArray();
		all.add(array);
		all.add(array1);
		System.out.println(all.toString());*/
//		List<String> list=chatterDAO.getChatterTypes();
//		System.out.println(list.toString());
		
//		List<Map<String,Object>> tempList=chatterDAO.getChattersByGroupName("西游记");
//		JSONArray array=JSONArray.fromObject(tempList);
//        JSONObject object=new JSONObject();
//        object.put("西游记", array);
//        all.add(object);
	}

}
