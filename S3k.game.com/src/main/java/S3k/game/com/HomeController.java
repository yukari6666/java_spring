package S3k.game.com;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	//홈 로그인 화면 -2019.06.14 -JI.K
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
//		DBCommon<Person> dbCommon = new DBCommon<Person>("E:\\Tomcat\\student190527.sqlite", "User");
		return "login";
	}
	
	//로그아웃 로직 라우터 -2019.06.14 -JI.K
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(Locale locale, Model model) {
//		return "login";
//	}
	
	//로그인 로직 라우터- 2019.0614 -JI.K
	@RequestMapping(value = "/do_sign_in", method = RequestMethod.GET)
	public String doSignIn(Locale locale, Model model, HttpServletRequest request, @RequestParam("id") String id, @RequestParam("password") String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		DBCommon<Person> dbCommon = new DBCommon<Person>("E:\\Tomcat\\student190527.sqlite", "User");
		if (dbCommon.signIn(id, password)) {
			HttpSession session = request.getSession();
			//위에서 설정한 sign_in이라는 세션 변수에 true값을 넣었다.
			session.setAttribute("sign_in", true);
			return "redirect:/Character";
		} else {
			return "redirect:/";
		}
	}
	
	//캐릭터 정보 화면 -2019.06.14 -SL.K
	@RequestMapping(value = "/character", method = RequestMethod.GET)
	public String character(Locale locale, Model model) {
		//user list
		//user choice logic
		DBCommon<Person> dbCommon = new DBCommon<Person>("E:\\Tomcat\\Game", "userInfo");
		return "Character";
	}
	
	//대전 화면 -2019.06.14 -SH.K
	@RequestMapping(value = "/fight_screen", method = RequestMethod.GET)
	public String fight_screen(Locale locale, Model model) {
		DBCommon<Person> dbCommon = new DBCommon<Person>("E:\\Tomcat\\student190527.sqlite", "User");
		model.addAttribute("select_result", dbCommon.selectDataTableTag(new Person()));
		return "fight";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String usertest(Locale locale, Model model) {
		return "usertest";
	}
	
	@RequestMapping(value = "/usertest", method = RequestMethod.GET)
	public String usertest(Locale locale, Model model,@RequestParam("idx") int idx) {
//		DBCommon<Person> dbCommon = new DBCommon<Person>("E:\\Tomcat\\Game.sqlite", "userInfo");
//		String searhHP = dbCommon.selectHP(idx);
		selectHp sh = new selectHp();
		model.addAttribute("test_HP", sh.UserHp(idx));
		return "usertest";
	}
	
	//대전 로직 -2019.06.14 -all
	//랜덤 주사위를 통해 공격과 방어에 대한 개념을 분리하고 업데이트를 위한 예시 작성 -2019.06.14 02:25pm -JW.S
	@RequestMapping(value = "/fight_logic", method = RequestMethod.GET)
	public String fight(Locale locale, Model model
								 ,@RequestParam("idx") int idx
								 ,@RequestParam("attackPoint_M") String attackPoint_M, @RequestParam("attackPoint_E") String attackPoint_E
								 ,@RequestParam("HP_M") String HP_M,@RequestParam("HP_E") String HP_E ) {
		DBCommon<Battle> dbCommon = new DBCommon<Battle>("E:\\Tomcat\\student190527.sqlite", "Battle");
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
	
}
