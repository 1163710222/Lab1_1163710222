package P3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FriendshipGraph {
	
	public List<Person> FG;
	public FriendshipGraph() {
		FG = new ArrayList<>();
	}
	public void addVertex(Person p) 
	{
		for(Person tep : FG)
		{
			if(tep.name.equals(p.name))
			{
				System.out.println("Each person has an unique name");
				System.exit(0);
			}	
		}
		FG.add(p);
	}
	public  void addEdge(Person p1,Person p2)
	{
		for(Person xx:p1.relation)
		{
			if(xx.equals(p2)&&!xx.equals(xx)){
				System.out.println("The wrong relations");
				System.exit(0);
			}
		}
		p1.relation.add(p2);
		p2.relation.add(p1);
	}
	public int getDistance(Person p1,Person p2)
	{
		Queue<Person> personBox1 = new LinkedList<>();
		personBox1.add(p1);
		int box = -1; ;
		if(p1.equals(p2))
			return 0;
		else{
			Person first = personBox1.peek();
			Circle:while(personBox1!=null)
					{
						if(first.relation.size()==0)
							break Circle;
						else{
							first = personBox1.peek();
							if(first==null)
								break Circle;
							else
							{
								for(Person temp:first.relation)
								{
									temp.distance=first.distance+1;
									if(temp.equals(p2)){
										box = temp.distance;
										break Circle;
									}
									if(temp.tag==true){
										personBox1.add(temp);
										temp.tag=false;
									}	
								}
								personBox1.remove(first);
							}
						}
					}
					for(Person abc : personBox1)
					{
						abc.distance=0;
						abc.tag = true;
					}
				return box;
		}
	}
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel"); 
		Person ross = new Person("Ross");
		Person ben = new Person("Ben"); 
		Person kramer = new Person("Kramer"); 
		graph.addVertex(rachel); 
		graph.addVertex(ross); 
		graph.addVertex(ben); 
		graph.addVertex(kramer); 
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel); 
		graph.addEdge(ross, ben); 
		graph.addEdge(ben, ross); 
		System.out.println(graph.getDistance(rachel, ross)); 
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel)); 
		System.out.println(graph.getDistance(rachel, kramer));  

	}
}
