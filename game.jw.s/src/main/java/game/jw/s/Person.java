package game.jw.s;

public class Person {
	public int idx;
	public String id;
	public String pass;
	public String name;
	public String attackPoint;
	public String guardPoint;
	public String HP;
	
	public Person() {
	}
	
	public Person(String id, String pass, String name, String attackPoint, String guardPoint, String HP) {
		this.id= id;
		this.pass= pass;
		this.name = name;
		this.attackPoint=attackPoint;
		this.guardPoint=guardPoint;
		this.HP = HP;
	}
	public String toTableTagString() {
		String tagString = "";
		tagString = tagString + "<tr>";
		tagString = tagString + "<td>";
		tagString = tagString + this.idx;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.id;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.pass;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.name;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.attackPoint;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.guardPoint;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.HP;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + "<a href='modify?idx=" + this.idx + "'>�닔�젙�븯湲�</a>";
		tagString = tagString + "</td>";
		tagString = tagString + "</tr>";
		return tagString;
	}	
}
