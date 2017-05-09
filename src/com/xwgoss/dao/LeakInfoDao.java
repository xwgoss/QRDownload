package com.xwgoss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.xwgoss.bean.LeakInfo;

public class LeakInfoDao {
	private JdbcTemplate jdbaTemplate;
	public void setDataSource(DataSource dataSource)
	{
		this.jdbaTemplate=new JdbcTemplate(dataSource);
	}
	public boolean insertLeakInfo(LeakInfo leakInfo)
	{
		String sql="insert into leakinfo (id,apk_version,apk_packagename,android_version,device_name,leakinfo,hump_path) values (?,?,?,?,?,?,?)";
		int i=jdbaTemplate.update(sql,leakInfo.getId(),leakInfo.getApk_version(),leakInfo.getApk_packagename(),leakInfo.getAndroid_version()
				,leakInfo.getDevice_name(),leakInfo.getLeakinfo(),leakInfo.getHump_path());
		if(i==1)
			return true;
		return false;
	}
	
	public List<LeakInfo> getLeakInfos(String str){
		StringBuffer sb=new StringBuffer("select * from leakinfo");
		if(str!=null)
			sb.append(str);
		return jdbaTemplate.query(sb.toString(), new ResultSetExtractor<List<LeakInfo>>(){

			@Override
			public List<LeakInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<LeakInfo> leakInfos=new ArrayList<LeakInfo>();
				while(rs.next()){
					LeakInfo leakInfo=new LeakInfo();
					leakInfo.setId(rs.getString("id"));
					leakInfo.setApk_version(rs.getString("apk_version"));
					leakInfo.setApk_packagename(rs.getString("apk_packagename"));
					leakInfo.setAndroid_version(rs.getString("android_version"));
					leakInfo.setDevice_name(rs.getString("device_name"));
					leakInfo.setHump_path(rs.getString("hump_path"));
					leakInfo.setLeakinfo(rs.getString("leakinfo"));
					leakInfo.setCreate_time(rs.getTimestamp("create_time").toString());
					leakInfos.add(leakInfo);
				}
				return leakInfos;
			}
		});
	}
}
