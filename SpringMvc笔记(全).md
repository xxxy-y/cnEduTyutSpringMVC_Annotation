1. 在配置文件添加@EnableWebMvc注解是为了快速配置SpringMvc注解，如果不添加此注解会导致后续无法通过实现WebMvcConfigurer接口进行自定义配置。

2. 如果日志文件有报错无法显示Mvc相关的日志，请添加以下两个日志依赖：
   ```xml
      <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-api</artifactId>
          <version>2.0.5</version>
      </dependency>
      <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-jdk14</artifactId>
          <version>2.0.5</version>
      </dependency>
   ```

3. 视图解析器(ThymeleafViewResolver类型，因为使用的Thymeleaf视图解析器)需要模板引擎(SpringTemplateEngine类型)，模板引擎(SpringTemplateEngine类型)需要模板解析器(SpringResourceTemplateResolver类型)。

4. thymeleaf模板解析器只能用在html页面上。

5. 正常来说，一个带有@RequestMapping注解的方法的返回值应该为ModelAndView，但我们经常直接返回View的名称，这是因为SpringMVC中的视图解析器会将其自动包装为ModelAndView对象。

6. 当我们需要使用ModelAndView对象向视图中插入数据时，一般需要在方法中创建一个对应该视图的ModelAndView对象，但是也可以简化一下，将ModelAndView对象当做该方法的形式参数，那样的话，SpringMVC就会通过Spring的依赖注入会自动帮助我们传递实例对象。(这不一定是必须的：但是要给需要自动注入的参数添加@ModelAttribute注解（或者使用@NotNull注解）)

7. 当处理器方法的参数中包含实体类型的参数时，是将请求参数包装为该实体类，通过Spring的依赖注入。
   当处理器方法的参数中包含Model类型的参数时，这个Model参数其实是用来向视图传递数据的，而不是从请求参数中获取数据。 在Spring MVC中，Model是一个接口，用于存储和传递数据给视图。它允许在处理器方法中设置属性，并将这些属性传递给视图进行渲染。
   当处理器方法的参数中包含Model类型的参数时，Spring会自动创建一个Model对象，并将其传递给处理器方法。我们可以在处理器方法中通过操作Model对象来设置属性，这些属性的值会被传递给视图。
   处理器方法可以通过调用Model对象的方法来设置属性，例如addAttribute、setAttribute等。这些属性可以在视图中使用，以完成数据的展示和渲染。
   需要注意的是，Model对象的作用域是当前请求的生命周期内，即在当前请求中设置的属性只对当前请求有效，不会影响其他请求。
   所以，当处理器方法的参数中包含Model类型的参数时，并不是从请求参数中获取数据，而是用来向视图传递数据的。

   当使用SpringMVC默认支持的数据类型作为处理器的形参类型时，SpringMVC的参数处理适配器会默认识别这些类型并进行赋值

   常见的默认类型：

   * httpServletRequest:获取请求信息
   * httpServletResponse:处理响应信息
   * HttpSession:获取session中存放的对象
   * Model/ModelMap:Model是一个接口，ModelMap是一个类，Model的实现类对象和ModelMap对象都可以设置model数据，model数据会填充到request域

8. 当我使用`@RequestMapping`注释指定访问路径时，需要与视图解析器中的前缀和后缀连接起来是一个完整的网络地址。但是当我设置前缀为`/WEB-INF/pages/`，路径为`/index`时，会自动去除重复的一个`/`，但是如果少了一个`/`的话，它不会给你自动添加。

9. SpringMVC工作原理:

   处理器就是Controller类, 将生成的响应结果返回给处理器适配器

   处理器映射器可以将URL与处理器绑定起来,当该URL访问时,便会返回相对应的处理器

   处理器适配器根据处理器对象的类型和方法签名, 将请求参数进行适配并传递给处理器进行处理

   视图解析器将逻辑视图名解析为物理视图名, 即具体的页面地址

   Spring MVC框架的执行流程可以概括为以下几个步骤：
      1. 客户端发送请求：客户端通过浏览器发送一个HTTP请求到服务器。
      2. 前端控制器(DispatcherServlet)接收请求：前端控制器（DispatcherServlet）是Spring MVC的核心组件，它作为一个中央处理器接收所有的请求，并将请求分发给适当的处理器进行处理。
      3. 前端控制器(DispatcherServlet)将请求发送到处理器映射器进行映射：前端控制器根据URL路径找到合适的处理器映射器（Handler Mapping），处理器映射器根据配置的URL映射规则找到对应的处理器（通常是Controller类）将找到的对应的处理器的名称返回给前端控制器(DispatchServlet)。
      4. 前端控制器(DispatcherServlet)将得到的处理器名称发送到处理器适配器, 来通过处理器适配器调用处理器：前端控制器将请求传递给处理器适配器（Handler Adapter），处理器适配器负责根据对象的类型和方法签名, 将请求参数进行适配并传递给处理器对象进行实际处理，并将处理结果(响应的数据和视图的逻辑名称)封装为ModelAndView对象, 最后将ModelAndView对象返回给前端控制器(DispatchServlet)。
      5. 处理器适配器将请求参数进行适配并将参数传递给处理器处理请求：处理器（Controller类）根据业务逻辑处理请求，并返回一个ModelAndView对象，其中包含处理结果和需要展示的视图信息, 将ModelAndView对象返回给处理器适配器, 处理器适配器再将ModelAndView对象返回给前端控制器(DispatchServlet)。
      6. 前端控制器将ModelAndView对象发送给视图解析器来解析视图：前端控制器将ModelAndView对象传递给视图解析器（View Resolver），视图解析器根据配置的视图解析规则找到对应的视图（通常是JSP页面或模板文件）, 最后返回视图(View)对象。
      7. 视图渲染和响应生成：视图解析器将模型数据传递给视图进行渲染，生成最终的响应结果。渲染后的视图内容会被发送到客户端作为HTTP响应。
      8. 响应返回给客户端：前端控制器将最终生成的响应返回给客户端，完成一次请求响应周期。

10. DispatcherServlet 是 SpringMVC 的核心类，其全限定名为 org.springframework.web.servlet.DispatcherServlet。DispatcherServlet是SpringMVC的流程控制中心，也称为SpringMVC的前端控制器，它可以拦截客户端的请求。拦截客户端请求后，DispatcherServlet会根据具体规则将请求交给其他组件处理，所有请求都要经过DispatcherServlet进行转发处理，这样就降低了SpringMVC组件之间的耦合性。

11. SpringMVC有三大组件：处理器映射器，处理器适配器，视图解析器。

12. 基于请求方式的URL路径映射：@GetMapping、@PostMapping、@PutMapping、@DeleteMapping、@PatchMapping

13. 基于Ant风格的URL路径映射：
	1. ?：匹配如何单字符，可以看作为[anyone]。
	2. *：匹配0或者任意数量的字符，可以看作为[any]、[oneMore]，作为目录不能为空。
	3. **：匹配0或者多级目录，可以看作为[anyPath]。

14. 基于RESTFul风格的URL路径映射：把请求参数变为请求路径的一种风格，将类似于`http://localhost:8080/secondMapping/a?id=1` 变为 `http://localhost:8080/secondMapping/a/id/1` 类似风格的。

15. Spring MVC使用了Java编译器的参数名字信息（如果可用）来进行参数绑定。在编译Java文件时，参数名字信息可以选择是否包含在编译后的字节码中。如果编译时未包含参数名字信息，Spring MVC就无法自动进行参数绑定，需要通过@RequestParam来指定请求参数名。

	可以在编译Java文件时，添加参数名字信息。可以通过在编译时使用特定的编译参数来实现，如使用javac的"-parameters"选项或使用其他构建工具的相应配置。

16. 重定向（Redirect）和请求转发（Forward）是在Web开发中常用的两种页面跳转方式，它们有以下特点和区别：

	特点：

	1. 重定向是通过发送新的HTTP请求来实现页面跳转，而请求转发是服务器内部的跳转，不会发送新的HTTP请求。
	2. 重定向可以跳转到不同的域名或者不同的应用程序，而请求转发只能在同一个应用程序内进行跳转。
	3. 重定向可以实现客户端的跳转，而请求转发只能在服务器端进行跳转。

	区别：

	1. 执行机制：重定向是客户端行为，客户端发送新的HTTP请求到服务器，服务器响应重定向响应码和目标URL，客户端再次发起新的请求；请求转发是服务器行为，服务器将请求转发到指定的资源，客户端并不感知。
	2. 地址栏：重定向时，地址栏会显示跳转后的新URL；请求转发时，地址栏URL不会发生变化。
	3. 数据传递：重定向不能直接共享请求中的数据，URL参数可以传递数据；请求转发可以共享请求中的数据，通过request对象进行数据传递。
	4. 跨域和路径：重定向可以跳转到不同的域名或者不同的应用程序，URL可以是完整的URL；请求转发只能在同一个应用程序内进行跳转，URL是相对于当前应用程序的路径。

	选择使用重定向还是请求转发取决于具体的需求和场景。如果需要在不同的域名或者不同的应用程序之间进行跳转，或者希望地址栏显示新的URL，可以选择重定向；如果只是在同一个应用程序内进行跳转，并且需要共享请求中的数据，可以选择请求转发。

17. 在Spring MVC中，使用HttpServletRequest参数来向视图传输数据是不推荐的做法。这是因为HttpServletRequest是Servlet API中的类，它属于底层的Web技术，不是Spring MVC框架中的组件。

	在Spring MVC中，推荐使用ModelAndView对象或Model对象来向视图传递数据。ModelAndView是一个包含模型数据和视图名称的类，它可以将数据和视图进行封装并一起返回给前端。而Model对象是一个接口，它可以用来存储模型数据，并将其传递给视图。

	使用HttpServletRequest参数来向视图传递数据可能导致视图无法正确获取到数据。在视图中，可以通过EL表达式或JSTL标签来访问模型数据。如果使用HttpServletRequest参数来传递数据，视图无法识别和解析这些数据，因此无法找到对应的变量。

	正确的做法是在Spring MVC的处理方法中使用ModelAndView对象或Model对象来封装数据，并将其返回给视图。在视图中可以使用EL表达式或JSTL标签来访问这些数据，例如使用`${username}`来访问用户名数据。

	总之，不推荐在Spring MVC中使用HttpServletRequest参数来向视图传递数据，而是应该使用ModelAndView对象或Model对象来封装数据并返回给视图。

18. @RestController是一个注解，它是Spring Framework中的一个特殊注解，主要用于声明一个控制器类，并将其标记为处理RESTful请求的控制器。

	@RestController注解是@Controller和@ResponseBody注解的结合体，它的作用有以下几个方面：

	1. 声明控制器类：@RestController注解可以用于标记一个类，将其声明为一个控制器类。这样，Spring容器就会将这个类实例化，并将其作为一个控制器来处理请求。
	2. 处理RESTful请求：@RestController注解通常用于处理RESTful风格的请求，包括GET、POST、PUT、DELETE等请求。它结合了@Controller和@ResponseBody注解的功能，可以将方法的返回值直接作为响应体返回给客户端，而不需要通过视图解析器进行视图渲染。
	3. 自动序列化为JSON或XML：由于@RestController注解的特殊性，它会将方法返回的对象自动序列化为JSON或XML格式的数据，并将其作为响应体返回给客户端。这样，在编写控制器方法时，可以直接返回一个对象，而不需要手动进行序列化和反序列化的操作。
	4. 简化开发流程：使用@RestController注解可以简化开发流程，减少代码的编写和配置的工作量。通过简单的注解配置，就可以将一个普通的Java类转变为一个处理RESTful请求的控制器，大大提高了开发效率。

19. SpringMVC的拦截器：处理前和处理后，包含了真正的请求映射的处理，在整个流程结束后还执行了一次`afterCompletion`方法，如果`preHandle`方法返回的是true，则执行下面的方法，如果是false方法，则之后的流程全部取消。如果在处理中发生异常，不会执行处理后`postHandle`方法，但是会执行`afterCompletion`方法，我们可以在`afterCompletion`方法中获取到抛出的异常。多级拦截器：在处理之前，是按照顺序从前向后进行拦截的，但是处理完成之后，就按照倒序执行处理后方法，而完成后是在所有的`postHandle`执行之后再同样的以倒序方式执行。与单个拦截器的情况一样，一旦拦截器返回false，那么之后无论有无拦截器，都不再继续。

20. 我们在一开始说了SpringMVC有两个容器，一个是Web容器一个是根容器。Web容器只负责Controller等表现层内容根容器就是Spring容器，它负责Service、Dao等，并且它是Web容器的父容器。根容器就是Spring容器，它负责Service、Dao等，并且它是Web容器的父容器。

	![](6ZAyRrDw2QMU8Xv.png)

21. HandlerMapping保存了所有的请求映射信息（Controller中定义的），它可以根据请求找到处理器Handler，但并不是简单的返回处理器，而是将处理器和拦截器封装，形成一个处理器执行链（类似于之前的Filter）

22. 在容器中查找所有的HandlerAdapter，它用于处理请求并返回ModelAndView对象，默认有三种实现HttpRequestHandlerAdapter，SimpleControllerHandlerAdapter和AnnotationMethodHandlerAdapter。当HandlerMapping找到处理请求的Controller之后，会选择一个合适的HandlerAdapter处理请求，比如我们之前使用的是注解方式配置Controller，现在有一个请求携带了一个参数，那么HandlerAdapter会对请求的数据进行解析，并传入方法作为实参，最后根据方法的返回值将其封装为ModelAndView对象
