# 常用 annotation
## @Controller
>宣告此類別為一個 controller

## @RestController
>結合了 @ResponseBody 和 @Controller

## @RequestMapping(value = "/url")
>定義此類別的網址

## @GetMapping("/url")
>處理該 url 的 get method

## @PostMapping("/url")
>處理該 url 的 post method

## @Value("${com.hung.adminEmpId}")
>讀取 app.prop 設定中的值

## @ConfigurationProperties(prefix="com.hung")
>讀取 app.prop 設定中 com.hung 的值

## @PropertySource(value="classpath:myApp.properties",encoding="utf-8")
>讀取自定義的 prop 設定檔

# Thymeleaf
## Thymeleaf 是一個可以讓後端程式指定到前端 html 的一個套件，而且有著非常強大的前端屬性顯示功能，不太會影響到 HTML 結構
'''
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
'''

'''java
@Controller
public class homeController {
	@GetMapping("/employee")
	public String home(Model model, HttpServletRequest request, HttpSession session) {
    	...

    	// render 到 templates 中的 home.html
		return "home";
	}
}
'''

### 顯示文字
><p th:text="${message}"></p>

### 顯示日期並且格式化
><p th:text="*{#dates.format(regDate, 'yyyy-MM-dd HH:mm:ss')}"></p>

### 下載連結
><a th:href="@{/download(fileName=${file})}" th:text="${file}"></a>

### 判斷式(unless可以想成是else)
><div th:if="${empList} != null">
><div th:unless="${empList} != null">empList is null!</div>

### 加上  ?: _ 表示，如果 userName 不存在則顯示後面的預設文字
><p th:text="${userName} ?: _">There is no userName attribute...</p>

### 存取整個 List value
'''
<tr th:each="empInfo: ${empList}">
	<td th:text="${empInfo.no}"</td>
	<td th:text="${empInfo.name}"</td>
	<td th:text="${empInfo.age}"></td>
	<td th:text="${empInfo.hireDate}"></td>
</tr>
'''

### HTML註解
><!--/* 我是註解 */--> 可以讓瀏覽器中看不到註解