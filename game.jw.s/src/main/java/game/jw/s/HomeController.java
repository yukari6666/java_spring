package game.jw.s;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	// �솃(濡쒓렇�씤) �솕硫� 2019.06.14 -JI.K
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object isSignIn = session.getAttribute("login");
		if (isSignIn != null && (Boolean) isSignIn) {
			DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\Game.sqlite", "userInfo");
			model.addAttribute("select_result", dbCommon.selectDataTableTag(new Person()));
			return "Character";
		}
		return "redirect:/login";
	}
	
	// user �뀒�씠釉� �깮�꽦
	@RequestMapping(value = "/create_table", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\Game.sqlite", "userInfo");
		dbCommon.createTable(new Person());
		return "done";
	}
	
	//유저를 초이스해서 뿌려주는 로직 자체는 완성한 듯 하나, view와 컨트롤러 사이의 연결이 되지 않는다. 원인은 알아내는 중. 2019.06.15 06:52pm -JW.S
	@RequestMapping(value = "/choice", method = RequestMethod.GET)
	public String selectCharacter(Locale locale, Model model, @RequestParam("idx") int idx) {
		selectHp<Person> hp = new selectHp<Person>();
		model.addAttribute("select_character", hp.UserHp(idx));
		return "selectCharacter";
	}

	// user �젙蹂� �엯�젰 url �깮�꽦 2019.06.14 -JI.K
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Locale locale, Model model) {
		return "input";
	}

	// input action �씪�슦�꽣 2019.06.14 -JI.K
	@RequestMapping(value = "/input_data", method = RequestMethod.GET)
	public String inputData(@RequestParam("id") String id, @RequestParam("pass") String pass,
			@RequestParam("name") String name, @RequestParam("attackPoint") String attackPoint,
			@RequestParam("guardPoint") String guardPoint, @RequestParam("HP") String HP) {
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\Game.sqlite", "userInfo");
		dbCommon.insertData(new Person(id,pass,name,attackPoint,guardPoint,HP));

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(pass.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			pass = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		dbCommon.insertData(new Person(id, pass, name, attackPoint, guardPoint, HP));
		return "done";
	}

	// 濡쒓렇�븘�썐 濡쒖쭅 �씪�슦�꽣 2019.06.14 -JI.K
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(Locale locale, Model model) {
//		return "login";
//	}

	//홈 로그인 화면 -2019.06.14 -JI.K
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "login";
	}

	//로그아웃 로직 라우터 -2019.06.14 -JI.K
	@RequestMapping(value = "/sign_out", method = RequestMethod.GET)
	public String signOut(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	//로그인 로직 라우터- 2019.0614 -JI.K
	@RequestMapping(value = "/do_sign_in", method = RequestMethod.GET)
	public String do_sign_in(Locale locale, Model model, HttpServletRequest request, @RequestParam("id") String id,
			@RequestParam("pass") String pass) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(pass.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			pass = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\Game.sqlite", "userInfo");
		if (dbCommon.signIn(id, pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("login", true);
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}

	//캐릭터 정보 화면 -2019.06.14 -SL.K
	@RequestMapping(value = "/Character", method = RequestMethod.GET)
	public String character(Locale locale, Model model) {
		// user list
		// user choice logic
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\Game.sqlite", "userInfo");
		model.addAttribute("select_result", dbCommon.selectDataTableTag(new Person()));
		return "Character";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String usertest(Locale locale, Model model) {
		return "usertest";
	}
	
	@RequestMapping(value = "/usertest", method = RequestMethod.GET)
	public String usertest(Locale locale, Model model,@RequestParam("idx") int idx) {
//		DBCommon<Person> dbCommon = new DBCommon<Person>("E:\\Tomcat\\Game.sqlite", "userInfo");
//		String searhHP = dbCommon.selectHP(idx);
		selectHp<Person> sh = new selectHp<Person>();
		model.addAttribute("test_HP", sh.UserHp(idx));
		return "usertest";
	}

	//대전 화면 -2019.06.14 -SH.K
	@RequestMapping(value = "/fight_screen", method = RequestMethod.GET)
	public String fight_screen(Locale locale, Model model) {
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\Game.sqlite", "userInfo");
		model.addAttribute("select_result", dbCommon.selectDataTableTag(new Person()));
		return "fight";
	}
	
	//대전 로직 -2019.06.14 -all
	//랜덤 주사위를 통해 공격과 방어에 대한 개념을 분리하고 업데이트를 위한 예시 작성 -2019.06.14 02:25pm -JW.S
	@RequestMapping(value = "/fight_logic", method = RequestMethod.GET)
	public String fight(Locale locale, Model model
								 ,@RequestParam("idx") int idx
								 ,@RequestParam("attackPoint_M") String attackPoint_M, @RequestParam("attackPoint_E") String attackPoint_E
								 ,@RequestParam("HP_M") String HP_M,@RequestParam("HP_E") String HP_E ) {
		DBCommon<Battle> dbCommon = new DBCommon<Battle>("c:\\tomcat\\Game.sqlite", "Battle");
		model.addAttribute("select_battle", dbCommon.selectDataTableTag(new Battle()));
		java.util.Random rand = new java.util.Random();
		int attackDice = rand.nextInt(6+1);
		int guardDice = rand.nextInt(6+1);
		String text;
		if(attackDice >= 4) {
			text = "공격에 성공했습니다! 적의 HP가 "+(Integer.parseInt(HP_E)-Integer.parseInt(attackPoint_M))+"남았습니다.";
			dbCommon.insertData(new Battle(text));
		}
		else {
			text = "공격에 실패했습니다! 적의 HP가 "+HP_E+"남았습니다.";
			dbCommon.insertData(new Battle(text));
		}
		if(guardDice >=4) {
			text="방어에 성공하셨습니다! HP가 "+(Integer.parseInt(HP_M)-(Integer.parseInt(attackPoint_E)/2))+"남았습니다.";
			dbCommon.insertData(new Battle(text));
		}
		else{
			text = "방어에 실패했습니다! HP가 "+(Integer.parseInt(HP_M)-Integer.parseInt(attackPoint_E))+"남았습니다.";
			dbCommon.insertData(new Battle(text));
		}
		return "fight";
	}

	// �뜲�씠�꽣 �닔�젙 �솕硫�
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Locale locale, Model model, @RequestParam("idx") int idx) {
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\Game.sqlite", "userInfo");
		model.addAllAttributes(dbCommon.detailsData(new Person(), idx));
		return "modify";
	}

}
