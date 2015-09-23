package test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONObject;

public class Main {
	public static void main(String[] args) {
		Integer d = new Integer(4);
		Integer c = 4;
		Integer b = 4;
		System.out.println(d == b);
		System.out.println(d.equals(b));
		JSONObject src = new JSONObject();
		/**
		 * 
		 * 如果放进去一个Integer值域范围的Long类型，JSONObject会自动将其转换为Integer类型。
		 */
		src.put("startTime", new Long(Integer.MAX_VALUE));
		System.out.println(src.get("startTime").getClass().getName());
		
		A a = new A();
		System.out.println(a.getStartTime());
		try {
		    /**
		     * BeanUtils在将JSONObject的属性复制到Bean时
		     * 在目标属性是java.sql.Timestamp的情况下，仅支持下面几种source属性类型：
		     * java.sql.Timestamp
		     * java.util.Date
		     * java.util.Calendar
		     * Long
		     * 其他类型，先进行toString拿到字符串，然后调用java.sql.Timestamp.valueOf(String value)方法尝试转换为Timestamp类型。
		     * 
		     * 因此，在这个例子中，会报错。
		     */
			BeanUtils.copyProperties(a, src);
			System.out.println(a.getStartTime());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static class A {
		private Timestamp startTime;

		public Timestamp getStartTime() {
			return startTime;
		}

		public void setStartTime(Timestamp startTime) {
			this.startTime = startTime;
		}
	}
}
