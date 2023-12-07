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
9. 