package cos.chat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cos.chat.model.Chatter;

@Repository
public class ChatterDAO {
	
	@Autowired
	private JdbcTemplate template;
	public JdbcTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	private RelationDAO relationDAO;
	
	
	public RelationDAO getRelationDAO() {
		return relationDAO;
	}
    @Autowired
	public void setRelationDAO(RelationDAO relationDAO) {
		this.relationDAO = relationDAO;
	}

	public int getIDByName(String name){
		return (int) this.getTemplate().queryForLong("select id from chatter where name= ?",
				new Object[]{name});
	}
	
	public Chatter getChatterByID(int chatterID){
		final Chatter chatter=new Chatter();
		this.getTemplate().query("SELECT * FROM chatter WHERE id = ?", new Object[] {chatterID},
				new RowCallbackHandler(){
			public void processRow(ResultSet rs)throws SQLException{
				chatter.setId(new Integer(rs.getInt("id")));
				chatter.setName(rs.getString("name"));
				chatter.setGroupID(new Integer(rs.getInt("groupID")));
				chatter.setGroupName(rs.getString("groupName"));
				chatter.setUsedNumber(0);
				chatter.setContent(rs.getString("content"));
				chatter.setUrl(rs.getString("url"));
				chatter.setType(rs.getString("type"));
			}
		});
		return chatter;
	}

	public Chatter getChatter(String name){
		
		final Chatter chatter=new Chatter();
		try{
			this.getTemplate().query("SELECT * FROM chatter WHERE name = ?", new Object[] {name},
					new RowCallbackHandler(){
				public void processRow(ResultSet rs)throws SQLException{
					if(rs.getRow()==0)
						throw new NullPointerException("没找到这相应的对象，请再次确认username是否存在");
					chatter.setId(new Integer(rs.getInt("id")));
					chatter.setName(rs.getString("name"));
					chatter.setGroupID(new Integer(rs.getInt("groupID")));
					chatter.setUsedNumber(0);
					chatter.setContent(rs.getString("content"));
					chatter.setGroupName(rs.getString("groupName"));
					chatter.setUrl(rs.getString("url"));
					chatter.setType(rs.getString("type"));
				}
			});
		}catch(NullPointerException e){
			throw e;
		}
		return chatter;
	}
	
	public Map<String,Object> getMatchedChatter(String name){
		int chatterID=getIDByName(name);
		int matchedID=relationDAO.getMatchedID(chatterID);
		List matchedIdentifiers=this.getTemplate().queryForList("SELECT identifier FROM user WHERE chatterID = ? AND state = 1",
				new Object[]{matchedID});
		if(matchedIdentifiers.size()==0)
			throw new NullPointerException("没找到匹配的对象");
		//这里应该考虑等待时间问题的，但是时间有限。。顾及不了这么多了请原谅
		int index=RandomUtils.nextInt(matchedIdentifiers.size());
		String matchedIdentifier=matchedIdentifiers.get(index).toString();
		Map map=new HashMap();
		map.put("matchedIdentifier", matchedIdentifier);
		Chatter matchedChatter=getChatterByID(matchedID);
		map.put("matchedChatter",matchedChatter);
		return map;
        
	}
	
	public List<Map<String,Object>> getChattersByGroupName(String groupName){
		return this.getTemplate().
				queryForList("SELECT name,content,url FROM chatter WHERE groupName = ?", new Object[]{groupName});
	}
	
	public List<Map<String,Object>> getChattersByType(String type){
		return this.getTemplate().
				queryForList("SELECT name,content,url FROM chatter WHERE type = ?", new Object[]{type});
	}
	
	public List<String> getChatterGroupNames(){
		List<Map<String,Object>> maps=this.getTemplate().queryForList("SELECT DISTINCT groupName FROM chatter");
        List<String> list=new ArrayList<String>();
		for(int i=0;i<maps.size();i++){
        	list.add(maps.get(i).get("groupName").toString());
        }
		return list;
	}
	
	public List<String> getChatterTypes(){
		List<Map<String,Object>> maps=this.getTemplate().queryForList("SELECT DISTINCT type FROM chatter");
        List<String> list=new ArrayList<String>();
		for(int i=0;i<maps.size();i++){
        	list.add(maps.get(i).get("type").toString());
        }
		return list;
	}

}
