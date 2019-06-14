package S3k.game.com;

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
//	public String toTableTagString() {
//		String tagString = "";
//		tagString = tagString + "<tr>";
//		tagString = tagString + "<td>";
//		tagString = tagString + this.idx;
//		tagString = tagString + "</td>";
//		tagString = tagString + "<td>";
//		tagString = tagString + this.id;
//		tagString = tagString + "</td>";
//		tagString = tagString + "<td>";
//		tagString = tagString + this.pass;
//		tagString = tagString + "</td>";
//		tagString = tagString + "<td>";
//		tagString = tagString + this.name;
//		tagString = tagString + "</td>";
//		tagString = tagString + "<td>";
//		tagString = tagString + this.address;
//		tagString = tagString + "</td>";
//		tagString = tagString + "<td>";
//		tagString = tagString + this.birthday;
//		tagString = tagString + "</td>";
//		tagString = tagString + "<td>";
//		tagString = tagString + this.favoriteColor;
//		tagString = tagString + "</td>";
//		tagString = tagString + "<td>";
//		tagString = tagString + "<a href='modify?idx=" + this.idx + "'>수정하기</a>";
//		tagString = tagString + "</td>";
//		tagString = tagString + "</tr>";
//		return tagString;
//	}	
}
