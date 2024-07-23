package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		try {
			// 1-1) xml 설정문서 읽어오기
			Charset charset = Charset.forName("UTF-8");	// 설정파일의 한글처리를 위함
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2) 위에서 생성한 Reader객체를 사용하여 SqlSessionFactory 객체를 생성한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();  // 사용한 스트림객체 닫아주기
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * SqlSession객체를 제공하는 메서드
	 * @return	SqlSession객체
	 */
	public static SqlSession getSqlSession() {
		
		return sqlSessionFactory.openSession();
	}
	
	
	/**
	 * SqlSession객체를 제공하는 메서드
	 * @param autoCommit 오토커밋 여부 
	 * @return	SqlSession객체
	 */
	public static SqlSession getSqlSession(boolean autoCommit) {
		
		return sqlSessionFactory.openSession(autoCommit);
	}
}
