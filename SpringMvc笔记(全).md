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