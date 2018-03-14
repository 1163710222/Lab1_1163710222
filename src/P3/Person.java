package P3;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	 public String name;
	 public List<Person> relation;
	 public boolean tag ;
	 public int distance;//用于记录目标长度
	Person(String name)
	{
		relation = new ArrayList<>();
		this.name = name;
		tag = true;
		this.distance = 0; 
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((relation == null) ? 0 : relation.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (relation == null) {
			if (other.relation != null)
				return false;
		} else if (!relation.equals(other.relation))
			return false;
		return true;
	}
	
}//建立了人的信息,以及初始化关系图
